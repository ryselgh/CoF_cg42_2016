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
import com.communication.actions.ActionDTO;
import com.communication.actions.BuildDTO;
import com.communication.actions.BuyAssistantDTO;
import com.communication.actions.BuyMainActionDTO;
import com.communication.actions.ChangeCardsDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.CouncilorDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;
import com.communication.values.CouncilorColor;

public class ClientController extends Observable implements Observer{

	private ClientCLI cli;
	private GameDTO game; 
	private SocketConnection connection;
	private Logger logger;
	private LobbyStatus lobbyStatus;
	private int playerID;
	private ConsoleListener consoleListener;
	private Thread consoleThread;
	private boolean inGame = false;

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
		cli.printMsg("Lobby commands: \n'\\NEWROOM_roomname_maxPl_minPl' \n'\\JOINROOM_roomname' \n'\\STARTGAME' requires admin of the room \n'\\LEAVEROOM' \n'\\SETMAP_filepath'");
	}

	private void printLobbyStatus(){
		cli.printMsg("\n\n");
		String clientsInLobby = "";
		if(lobbyStatus.getFreeClients().size()==0)
			clientsInLobby = "none";
		else{
		for(int i=0;i<lobbyStatus.getFreeClients().size();i++){
			clientsInLobby += lobbyStatus.getFreeClients().get(i);
			if(i!=lobbyStatus.getFreeClients().size()-1)
				clientsInLobby += ", ";
		}
		}
		cli.printMsg("Clients in lobby: " + clientsInLobby);
		if(lobbyStatus.getRooms().size()==0)
			cli.printMsg("Rooms: none");
		else{
		cli.printMsg("\nRooms:");
		for(RoomStatus rs : lobbyStatus.getRooms()){
			cli.printMsg("\n[" + rs.getRoomName() + "]");
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
			boolean[] availableActions;
			int selectedAction;
			ActionDTO compiledAction;
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
				consoleListener = new ConsoleListener(this);
				consoleListener.addObserver(this);
				consoleThread = new Thread(consoleListener);
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
				String onSaleUID;
				ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
				onSaleUID = cli.getObjectToBuyUID(availableOnSale.size(), playerID, availableOnSale);
				connection.sendToServer("INPUT_TOBUY", onSaleUID);
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

			case "AvailableActions":

				//la ricezione di questo comando implica che il client deve mandare un'azione al server
				//main: obtainpermit[0], satisfyking[1], shiftcouncilmain[2], build[3]
				//speed: buyassistant[4], changecards[5], shiftcouncilspeed[6], buymainaction[7]
				//pass[8]
				// SONO DA RIORDINARE SECONDO LA SCHEDA DEL GIOCO <-------------------------------------------IMPORTANTE!!
				availableActions = (boolean[]) obj;
				selectedAction = cli.getAction(availableActions);
				compiledAction = getActionInstance(selectedAction);
				connection.sendToServer("INPUT_ACTION", compiledAction);
				break;

			case "ActionAccepted": 
				// ack dell'invio azione.
				break;

			case "ActionNotValid":
				cli.printMsg(((String) obj)+ "\n");
				
				availableActions = (boolean[]) obj;
				selectedAction = cli.getAction(availableActions);
				compiledAction = getActionInstance(selectedAction);
				connection.sendToServer("INPUT_ACTION", compiledAction);
				break;
			case "GAMEDTO":
				this.cli.setGameAndBuildMap((GameDTO) obj);
				this.game = (GameDTO) obj;
				break;
			case "STARTGAME":
				this.inGame = true;
				//consoleListener.deleteObserver(this);//in gioco gli input sono ad invocazione  
				this.cli.setGameAndBuildMap((GameDTO) obj);
				this.game = (GameDTO) obj;
				break;
			default:
				throw new IllegalArgumentException("Command not recognized: " + cmd);
			}
		}

	}

	private ActionDTO getActionInstance(int selectedAction) {
		ArrayList<PoliticsCardDTO> polCards = new ArrayList<PoliticsCardDTO>();
		PoliticsCardDTO[] cardsRet;
		switch(selectedAction){
		case 3:
			BuildDTO build = new BuildDTO();
			cli.printMsg("Where do you want to build?");
			int buildHere = cli.getInputCities(game.getMap().getCity());
			build.setCity(game.getMap().getCity()[buildHere]);
			PermitsCardDTO usedPermit = game.getPlayers().get(playerID).getPermits().get(cli.getBuildPermit(playerID));
			build.setPermit(usedPermit);
			return build;
		case 0:
			ObtainPermitDTO obtPerm = new ObtainPermitDTO();
			int reg = cli.getTargetRegion(2);
			int slot = cli.waitCorrectIntInput("Insert slot number: 0= left  1=right", 0, 1);
			polCards = cli.waitInputCards(this.game.getActualPlayer().getHand());
			cardsRet = new PoliticsCardDTO[polCards.size()];
			cardsRet = polCards.toArray(cardsRet);
			obtPerm.setPolitics(cardsRet);
			obtPerm.setRegionIndex(reg);
			obtPerm.setSlot(slot);
			return obtPerm;
		case 1:
			SatisfyKingDTO satKing = new SatisfyKingDTO();
			polCards = cli.waitInputCards(this.game.getActualPlayer().getHand());
			cardsRet = new PoliticsCardDTO[polCards.size()];
			cardsRet = polCards.toArray(cardsRet);
			CityDTO[] cities = this.game.getMap().getCity();
			CityDTO[] validCities = new CityDTO[cities.length - 1];
			int i=0;
			for(CityDTO c : cities)
				if(!c.equals(this.game.getMap().getKing().getLocation())){
					validCities[i]=c;
					i++;
				}
			CityDTO dest = validCities[cli.getInputCities(validCities)];
			satKing.setDestination(dest);
			satKing.setPolitics(cardsRet);
			break;
		case 2:
			ShiftCouncilMainDTO shiftMain = new ShiftCouncilMainDTO();
			ArrayList<CouncilorColor> avColors = new ArrayList<CouncilorColor>();
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(!avColors.contains(c.getColor()))
					avColors.add(c.getColor());
			int balIndex = cli.getTargetBalcony();
			CouncilorColor targetColor = avColors.get(cli.getColorIndex(avColors));
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(c.getColor().equals(targetColor))
					shiftMain.setCouncilor(c);
			shiftMain.setBalconyIndex(balIndex);
			return shiftMain;
		case 4:
			return new BuyAssistantDTO();
		case 7:
			return new BuyMainActionDTO();
		case 5:
			ChangeCardsDTO changeDTO = new ChangeCardsDTO();
			changeDTO.setBalconyIndex(cli.getTargetBalcony());
			break;
		case 6:
			ShiftCouncilSpeedDTO shiftSpeed = new ShiftCouncilSpeedDTO();
			ArrayList<CouncilorColor> availColors = new ArrayList<CouncilorColor>();
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(!availColors.contains(c.getColor()))
					availColors.add(c.getColor());
			int balcIndex = cli.getTargetBalcony();
			CouncilorColor targColor = availColors.get(cli.getColorIndex(availColors));
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(c.getColor().equals(targColor))
					shiftSpeed.setCouncilor(c);
			shiftSpeed.setBalconyIndex(balcIndex);
			return shiftSpeed;
		}
		return null;
	}

	public boolean isInGame(){
		return this.inGame;
	}
}
