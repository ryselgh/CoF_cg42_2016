package com.client.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;


import com.client.view.ClientCLI;
import com.communication.CommunicationObject;
import com.communication.LobbyStatus;
import com.communication.RMIClientControllerRemote;
import com.communication.RMILobbyRemote;
import com.communication.RoomStatus;
import com.communication.actions.ActionDTO;
import com.communication.actions.BuildDTO;
import com.communication.actions.BuyAssistantDTO;
import com.communication.actions.BuyMainActionDTO;
import com.communication.actions.ChangeCardsDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.PassDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.CouncilorDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.values.CouncilorColor;

public class ClientController extends Observable implements Observer, RMIClientControllerRemote{

	private ClientCLI cli;
	private GameDTO game; 
	private SocketConnection connection;
	private LobbyStatus lobbyStatus;
	private ConsoleListener consoleListener;
	private Thread consoleThread;
	private boolean inGame = false;
	private Thread cliListThread;
	private String userName, tmpUserName;
	private SelectActionState currentActState;
	private boolean[] availableActions;
	private ArrayBlockingQueue<String> cliQueue;
	private boolean RMI;
	private final static String SERVERRMI_HOST="127.0.0.1";
	private final static int SERVERRMI_PORT=1099;
	private Registry registry;
	private RMILobbyRemote lobbyRemote;
	private int CLIENT_PORT;
	
	public ClientController(boolean RMI){
		this.RMI = RMI;
		this.cliQueue = new ArrayBlockingQueue<String>(50);
		cli = new ClientCLI(this,this.cliQueue);
		this.newConsoleListenerThread();
		
	}

	public void run() throws IOException, NotBoundException, AlreadyBoundException{
		if(!RMI){
		connection = new SocketConnection();
		try {
			connection.run();
			connection.addObserver(this);
		} catch (IOException e) {
			System.out.println("Failed to connect to server");
			return;
		}
		cli.printMsg("Connected to the server");
		connection.startListen();
		}
		else{//se è RMI parte subito l'identificazione
			registry = LocateRegistry.getRegistry(SERVERRMI_HOST,SERVERRMI_PORT);
			lobbyRemote = (RMILobbyRemote) registry.lookup("lobby");
			int resp =0;
			String nick = "";
			do{
				nick = this.getUserName();
				resp = lobbyRemote.RMIlogIn(nick);
				switch(resp){
				case 1:
					cli.printMsg("Illegal characters");
					break;
				case 2:
					cli.printMsg("Nickname size must be >5 and <13");
					break;
				case 3:
					cli.printMsg("Nickname already used");
					break;
				}
			}while(resp!=0);
			cli.printMsg("Logged in successfully");
			this.userName = nick;
			int startPort = 1098;
			registry = createRegistry(startPort);
			RMIClientControllerRemote contrRemote = (RMIClientControllerRemote) UnicastRemoteObject.exportObject(this, 0);
			registry.bind(nick + "CONTROLLER", contrRemote);;
			lobbyRemote.RMIsubscribe(nick,CLIENT_PORT);
			consoleListener.addObserver(this);
		}
	}

	private Registry createRegistry(int startPort){
		Registry registry = null;
		int port = startPort;
		do{
			try {
				registry = LocateRegistry.createRegistry(port);
			} catch (RemoteException e) {
				registry = null;
				port--;
			}
		}while(registry == null);
		this.CLIENT_PORT = port;
		return registry;
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

	private String isCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX]"))//regex equivalente a tutti i caratteri a parte le lettere
			return "Illegal characters";
		if(name.length()<5 || name.length()>13)
			return "Nickname size must be >5 and <13";
		return "";
	}
	
	private void newConsoleListenerThread(){//quando il player entra in gioco il thread si fotte, quindi ogni volta che torna nella lobby ne creo uno
		consoleListener = new ConsoleListener(this, this.cli, this.cliQueue);
		consoleThread = new Thread(consoleListener);
		consoleThread.start();
	}
	
	private String getUserName(){
		cli.printMsg("Insert your nickname:");
		String nick = cli.getMsg();
		while(nick == null || !isCorrect(nick).equals("")){
			if(nick!= null)
				cli.printMsg(isCorrect(nick));
			nick = cli.getMsg();
		}
		return nick;
	}
	@Override
	public void update(Observable o, Object change){
		if(o instanceof ConsoleListener){
			String inStr = this.cliQueue.poll();
			String[] split = inStr.split("_");
			if(split[0].equals("\\SETMAP")){
				String newMap;
				try {
					newMap = readFile(split[1], StandardCharsets.UTF_8);
					if(RMI)
						lobbyRemote.RMIlobbyCommand(this.userName, "\\SETMAP", newMap);
					else
						connection.sendToServer("\\SETMAP", newMap);
				} catch (Exception e) {
					cli.printMsg("Error during map import");
				}
				return;
			}
			if(RMI)
				try {
					lobbyRemote.RMIlobbyCommand(this.userName, inStr, null);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				connection.sendToServer(inStr ,null);
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
			boolean[] availableActions = null;
			int selectedAction;
			ActionDTO compiledAction;
			switch (cmd) {
			case "TOBUYEMPTY":
				cli.printMsg("Every item has been bought. Turn skipped");
				break;
			case "TIMEOUT":
				if(this.userName.equals((String) obj)){
					currentActState.setAbortFlag(true);
					this.cliQueue.add("ABORT");
					cli.printMsg("You ran out of time. Turn skipped");
				}
				else
					cli.printMsg("Player " + (String) obj + " ran out of time. Turn skipped");
				break;
			case "CLIENTCONNECTED":
				if(this.userName.equals((String) obj))
					cli.printMsg("Game session restored");
				else
					cli.printMsg("Player " + (String) obj + " reconnected to the game");
				break;
			case "CLIENTDISCONNECTED":
				cli.printMsg("Player " + (String) obj + " disconnected from the game");
				break;
			case "LOBBYSTATUS":
				this.lobbyStatus = (LobbyStatus) obj;
				printLobbyStatus();
				break;
			case "INSERTNICKNAME":
				this.tmpUserName= this.getUserName();
				connection.sendToServer("INSERTNICKNAME",this.tmpUserName);
				break;
			case "INSERTNICKNAMEACK":
				this.userName = this.tmpUserName;
				printLobbyCommand();
				consoleListener.addObserver(this);
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
				ownedPermits.addAll(game.getActualPlayer().getPermits());
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

			case "TOSELL":
				ToSellState sellState = new ToSellState(this.cli, this.connection);
				Thread sellThread = new Thread(sellState);
				sellThread.start();
				break;

			case "TOSELLACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "TOSELLNACK":
				cli.printMsg(((String) obj)+ "\n");
				break;

			case "TOBUY":					
				ToBuyState buyState = new ToBuyState(this.game, this.cli, connection);
				Thread buyThread = new Thread(buyState);
				buyThread.start();
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
				this.availableActions= availableActions;
				SelectActionState actState = new SelectActionState(this.game, availableActions, this.cli, connection);
				currentActState = actState;
				Thread actThread = new Thread(actState);
				actThread.start();
				break;

			case "ActionAccepted": 
				// ack dell'invio azione.
				break;

			case "ActionNotValid":
				cli.printMsg(((String) obj)+ "\n");
				SelectActionState actStateRetry = new SelectActionState(this.game, availableActions, this.cli, connection);
				currentActState = actStateRetry;
				Thread actThreadRetry = new Thread(actStateRetry);
				actThreadRetry.start();
				break;
			case "GAMEDTO":
				consoleListener.deleteObserver(this);//per stare sicuri, da togliere se verificato che STARTGAME arriva a tutti
				this.inGame = true;//se il player si riconnette dopo una disconnessione 
				this.cli.setGameAndBuildMap((GameDTO) obj);
				this.game = (GameDTO) obj;
				break;
			case "STARTGAME":
				consoleListener.deleteObserver(this);
				this.inGame = true;
				//consoleListener.deleteObserver(this);//in gioco gli input sono ad invocazione  
				this.cli.setGameAndBuildMap((GameDTO) obj);
				this.game = (GameDTO) obj;
				break;
			case "ENDGAME":
				cli.printMsg("Player " + ((String) obj) + " won the game. You will return to lobby");
				this.inGame=false;
				break;
			default:
				throw new IllegalArgumentException("Command not recognized: " + cmd);
			}
		}

	}

	public boolean cityDTOEquals(CityDTO c1, CityDTO c2){
		if(c1.getName().equals(c2.getName()))
			return true;
		return false;
	}
	
	public boolean isInGame(){
		return this.inGame;
	}
	
	//------RMI methods---------
	
	public void RMIupdateLobby(LobbyStatus status){
		this.lobbyStatus = status;
		printLobbyStatus();
	}
	
	public void RMIupdateGame(GameDTO game){
		this.inGame = true;//se il player si riconnette dopo una disconnessione 
		this.cli.setGameAndBuildMap(game);
		this.game = game;
	}
	
	public void RMIprintMsg(String msg){
		this.cli.printMsg(msg);
	}
	
	public ActionDTO getAction(boolean[] availableActions){
		int selectedAction = cli.getAction(availableActions);
		SelectActionState actState = new SelectActionState(this.game, availableActions, this.cli, null);
		ActionDTO compiledAction = actState.getActionInstance(selectedAction);
		return compiledAction;
	}
	
	
	
	
	
	
	
	
	
	
}
