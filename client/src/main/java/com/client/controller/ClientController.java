package com.client.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.client.ClientObservable;
import com.client.ClientObserver;
import com.client.view.ClientCLI;
import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.LobbyStatus;
import com.communication.RoomStatus;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;

public class ClientController extends Observable implements Observer{

	private ClientCLI cli;
	private GameDTO game;
	private SocketConnection connection;
	private Logger logger;
	private LobbyStatus lobbyStatus;
	private int playerID; // <------------------------------------ PROVVISORIO, serve comunque un identificativo
	private ConsoleListener consoleListener;

	public ClientController(){
		cli = new ClientCLI(this);
		cli.addObserver(this);
		Thread thread = new Thread(cli);
		thread.start();
	}

	public void run() throws IOException{
		connection = new SocketConnection();
		try {
			connection.run();
			connection.addObserver(this);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Connection lost.", e);
		}
		cli.printMsg("Connected to the server");
		connection.startListen();
	}

	private void printLobbyCommand(){
		cli.printMsg("Lobby commands: \n'\\NEWROOM_roomname_maxPl_minPl' \n'\\JOINROOM_roomname' \n'\\STARTGAME_roomname' requires admin of the room \n'\\LEAVEROOM_roomname' \n'\\SETMAP_filepath'");
	}

	private void printLobbyStatus(){
		String clientsInLobby = "";
		for(int i=0;i<lobbyStatus.getFreeClients().size();i++){
			clientsInLobby += lobbyStatus.getFreeClients().get(i);
			if(i!=lobbyStatus.getFreeClients().size()-1)
				clientsInLobby += ", ";
		}
		cli.printMsg("Clients in lobby: " + clientsInLobby);
		cli.printMsg("Rooms:");
		for(RoomStatus rs : lobbyStatus.getRooms()){
			cli.printMsg("[" + rs.getRoomName() + "]");
			String clientsInRoom = "";
			for(int i=0;i<rs.getPlayers().size();i++){
				clientsInRoom += rs.getPlayers().get(i);
				if(i!=rs.getPlayers().size()-1)
					clientsInRoom += ", ";
			}
			cli.printMsg("Clients: " + clientsInRoom);
			cli.printMsg("Admin: " + rs.getAdminName());
			cli.printMsg("Minimum players: " + rs.getMinPlayers());
			cli.printMsg("Maximum players: " + rs.getMaxPlayers());
			if(rs.isDefaultMap())
				cli.printMsg("Map: default");
			else
				cli.printMsg("Map: custom");
		}
	}



	static String readFile(String path, Charset encoding) 
			throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}


	@Override
	public void update(Observable o, Object change){
		if(o instanceof ConsoleListener){
			String[] split = ((String) change).split("_");
			if(split[0].equals("\\SETMAP")){
				String newMap;
				try {
					newMap = readFile(split[1], StandardCharsets.UTF_8);
					connection.sendToServer("\\SETMAP", newMap);
				} catch (Exception e) {
					cli.printMsg("Error during map import");
				}
				return;
			}
			connection.sendToServer((String) change ,null);
			return;
		}
		CommunicationObject in = (CommunicationObject) change;
		String cmd = in.getMsg();
		Object obj = in.getObj();

		if (cmd.contains("lobby_msg-")) {// messaggio della lobby
			cmd = cmd.substring("lobby_msg-".length());
			cli.printMsg(cmd + "\n");
		} 
		else {// messaggio di gioco / input
			switch (cmd) {
			case "LOBBYSTATUS":
				this.lobbyStatus = (LobbyStatus) obj;
				printLobbyStatus();
				break;
			case "INSERTNICKNAME":
				cli.printMsg("Insert your nickname:");
				String nick = cli.getMsg();
				while(!connection.isNicknameCorrect(nick)){
					cli.printMsg("Only characters and numbers are allowed, try again:");
					nick = cli.getMsg();
				}
				connection.sendToServer("INSERTNICKNAME",nick);
				break;
			case "INSERTNICKNAMEACK":
				consoleListener = new ConsoleListener();
				consoleListener.addObserver(this);
				Thread consoleThread = new Thread(consoleListener);
				consoleThread.start();
				printLobbyCommand();
				break;
			case "INSERTNICKNAMENACK":
				cli.printMsg(((String) obj)+ "\n");
				break;
			case "ONETOKEN":
				BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
				//get bonus token. obj = BonusToken[] tokenPool
				ArrayList<BonusTokenDTO> bt = new ArrayList<BonusTokenDTO>();
				for(CityDTO city: game.getMap().getCity())
					bt.add(city.getToken());
				BonusTokenDTO[] btArray = new BonusTokenDTO[bt.size()];
				bt.toArray(btArray);
				btDTO = cli.getTokenBonus(btArray, 1);
				connection.sendToServer("INPUT_ONETOKEN",btDTO);
				break;

			case "ONETOKEN_ACK":
				break;

			case "ONETOKEN_NACK":
				break;
			case "TWOTOKENS":
				BonusTokenDTO[] btDTO2 = new BonusTokenDTO[1];
				//get bonus token. obj = BonusToken[] tokenPool
				ArrayList<BonusTokenDTO> bt2 = new ArrayList<BonusTokenDTO>();
				for(CityDTO city: game.getMap().getCity())
					bt2.add(city.getToken());
				BonusTokenDTO[] btArray2 = new BonusTokenDTO[bt2.size()];
				bt2.toArray(btArray2);
				btDTO2 = cli.getTokenBonus(btArray2, 2);
				connection.sendToServer("INPUT_TWOTOKENS",btDTO2);
				break;

			case "TWOTOKENS_ACK":
				break;

			case "TWOTOKENS_NACK":
				break;
			case "FREECARD":
				PermitsCardDTO pcDTO=null;
				//get ret: una carta permesso di quelle a faccia in su nelle regioni
				int regIndex = cli.getTargetRegion(2);
				ArrayList<PermitsCardDTO> availablePermits = new ArrayList<PermitsCardDTO>();
				availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(0));
				availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(1));
				int pcIndex = cli.getPermitIndex(availablePermits);
				pcDTO = availablePermits.get(pcIndex);
				connection.sendToServer("INPUT_FREECARD",pcDTO);
				break;

			case "FREECARDACK":
				break;

			case "FREECARDNACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "BONUSCARD":
				PermitsCardDTO pcOwnedDTO=null;
				ArrayList<PermitsCardDTO> ownedPermits = new ArrayList<PermitsCardDTO>();
				ownedPermits.addAll(game.getPlayers().get(playerID).getPermits());
				for(PermitsCardDTO pc: ownedPermits)
					if(!pc.isFaceDown())
						ownedPermits.remove(pc);
				int pcOwnedIndex = cli.getPermitIndex(ownedPermits);
				pcOwnedDTO = ownedPermits.get(pcOwnedIndex);
				connection.sendToServer("INPUT_BONUSCARD",pcOwnedDTO);
				break;

			case "BONUSCARDACK":
				break;

			case "BONUSCARDNACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "TOSELL":						//Da aggiustare      aggiustala così: se item è null equivale a pass
				ItemOnSale its = null;
				//get its: l'oggetto da vendere al mercato
				Object item = cli.getItemToSell(playerID);
				if(item instanceof String){
					String pass = (String) item;
					connection.sendToServer("INPUT_TOSELL", null);
				}else{
					int price = cli.getSellPrice();
					its = new ItemOnSale(price, (Object) item);
					connection.sendToServer("INPUT_TOSELL",its);
				}
				break;

			case "TOSELLACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "TOSELLNACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "TOBUY":					//Anche questa
				OnSaleDTO onSaleDTO = null;
				ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
				onSaleDTO = availableOnSale.get(cli.getObjectToBuyIndex(availableOnSale.size(), playerID));
				connection.sendToServer("INPUT_TOBUY", onSaleDTO);
				break;

			case "TOBUYACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "TOBUYNACK":
				cli.printMsg(((String) obj)+ "\n");
				break;
			case "StartTurn":  // AO' la coerenza, o tutto upper o tutto lower <----------------------README---------------------
				// aggiorna interfaccia con la carta pescata(obj =
				// PoliticsCardDTO)
				PoliticsCardDTO polcDTO = (PoliticsCardDTO) obj;
				cli.printMsg("You drew this card: "+polcDTO.getColor().toString());
				break;

			case "AvailableActions": //Serve sapere anche se possibile fare main/speed o è market time <------------------------README------------------------ 
				// aggiorna il vettore di azioni disponibili (obj = boolean[]).
				// la descrizione del vettore la trovi in
				// ActionState.getAvailableActions()

				//la ricezione di questo comando implica che il client deve mandare un'azione al server
				//main: build[0], obtainpermit[1], satisfyking[2], shiftcouncilmain[3]
				//speed: buyassistant[4], buymainaction[5], changecards[6], shiftcouncilspeed[7]
				//pass[8]
				boolean[] availableActions = (boolean[]) obj;
				boolean error = true;
				int selection = -1;
				int actionChoice = cli.getAction(playerID, 0, 0, false);
				while(error){
					switch(actionChoice){
					case 1:
						selection = cli.mainActionChoice() - 1;
						break;
					case 2:
						selection = (cli.speedActionChoice() - 1) + 4; // 4: offset speed action
						break;
					case 3:
						//com'è gestito qui il mercato?
						break;
					case 4:
						selection = 8;
						break;
					}
					if(availableActions[selection])
						error = false;
					else
						cli.printMsg("Selected action not available. Try again.");
				}
				// connection.sendToServer("Boh", deviSpiegarmiStaParte);
				break;

			case "ActionAccepted": 
				// ack dell'invio azione.
				break;

			case "ActionNotValid":
				// ack negativo dell'invio azione. il server ne aspetta un'altra
				break;
			case "GAMEDTO":
				consoleListener.deleteObserver(this);//in gioco gli input sono ad invocazione
				this.cli.setGameAndBuildMap((GameDTO) obj);
				break;
			default:
				throw new IllegalArgumentException("Command not recognized: " + cmd);
			}
		}

	}


}
