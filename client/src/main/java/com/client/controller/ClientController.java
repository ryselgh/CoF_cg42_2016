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

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.client.view.InterfaceMiddleware;
import com.client.view.gui.GUIController;
import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.LobbyStatus;
import com.communication.RMIClientControllerRemote;
import com.communication.RMILobbyRemote;

import com.communication.actions.ActionDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;


// TODO: Auto-generated Javadoc
/**
 * The Class ClientController.
 */
public class ClientController extends Observable implements Observer, RMIClientControllerRemote, Runnable{

	private InterfaceMiddleware view;
	
	/** The game. */
	private GameDTO game; 
	
	/** The connection. */
	private SocketConnection connection;
	
	/** The lobby status. */
	private LobbyStatus lobbyStatus;
	
	/** The console listener. */
	private ConsoleListener consoleListener;
	
	/** The console thread. */
	private Thread consoleThread;
	
	/** The in game. */
	private boolean inGame = false;
	
	/** The tmp user name. */
	private String userName, tmpUserName;
	
	/** The current act state. */
	private SelectActionState currentActState;
	
	/** The available actions. */
	private boolean[] availableActions;

	/** The input queue. */
	private ArrayBlockingQueue<String> cliQueue;
	
	/** The rmi. */
	private boolean RMI;
	
	/** The Constant SERVERRMI_HOST. */
	private final static String SERVERRMI_HOST="127.0.0.1";
	
	/** The Constant SERVERRMI_PORT. */
	private final static int SERVERRMI_PORT=1099;
	
	/** The RMI registry. */
	private Registry registry;
	
	/** The lobby remote object. */
	private RMILobbyRemote lobbyRemote;
	
	/** The client port. */
	private int CLIENT_PORT;
	
	private boolean abortFlag=false;
	
	private boolean isGUI;
	
	private GUIController guiController;
	/**
	 * Instantiates a new client controller. Creates the cli object and starts a new console listener thread
	 *
	 * @param RMI the rmi
	 * @param guiController 
	 */
	public ClientController(boolean RMI, boolean isGUI, GUIController guiController){
		this.cliQueue = new ArrayBlockingQueue<String>(50);
		this.view = new InterfaceMiddleware(this, isGUI,this.cliQueue, guiController);
		this.RMI = RMI;
		this.isGUI = isGUI;
		this.guiController = guiController;
	}

	public void setConnection(boolean isRMI){//metodo usato dalla gui, se si usa la cli la connessione viene passata nel costruttore e viene chiamato subito run()
		this.RMI = isRMI;
		try {
			this.start();
		} catch (IOException | NotBoundException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setGuiController(GUIController guiController){
		this.guiController= guiController;
		this.guiController.addObserver(view);
		this.view.addObserver(guiController);
	}
	public void setConsoleListener(ConsoleListener consoleListener) {
		this.consoleListener = consoleListener;
	}


	/**
	 * Run. If the protocol used is Socket, then it starts the connection listening. If otherwise RMI is used, the identification starts immediately
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws NotBoundException
	 *             the not bound exception
	 * @throws AlreadyBoundException
	 *             the already bound exception
	 */
	public void start() throws IOException, NotBoundException, AlreadyBoundException {
		if (!RMI) {
			connection = new SocketConnection();
			connection.addObserver(this);
			this.addObserver(connection);
			
			
			Thread sockConnThread = new Thread(connection);
			sockConnThread.start();
			
			//ExecutorService executor = Executors.newFixedThreadPool(1);
	        //executor.execute(connection);
	            
			view.printMsg("Connected to the server");
			this.setChanged();
			this.notifyObservers("SOCKETCONNECTION_STARTLISTEN");
		} else {// se è RMI parte subito l'identificazione
			int resp = 0;
			String nick = "";
			do {
				nick = view.getUsername();
				resp = RMISetupAndLogin(nick);
				switch (resp) {
				case 1:
					view.printMsg("Illegal characters");
					break;
				case 2:
					view.printMsg("Nickname size must be >5 and <13");
					break;
				case 3:
					view.printMsg("Nickname already used");
					break;
				}
			} while (resp != 0);
		}
	}

	private int RMISetupAndLogin(String nick) throws RemoteException, NotBoundException, AlreadyBoundException{
		registry = LocateRegistry.getRegistry(SERVERRMI_HOST, SERVERRMI_PORT);
		lobbyRemote = (RMILobbyRemote) registry.lookup("lobby");
		int resp = lobbyRemote.RMIlogIn(nick);
		if(resp !=0)
			return resp;
		view.printMsg("Logged in successfully");
		this.userName = nick;
		int startPort = 1098;
		registry = createRegistry(startPort);
		RMIClientControllerRemote contrRemote = (RMIClientControllerRemote) UnicastRemoteObject.exportObject(this, 0);
		registry.bind(nick + "CONTROLLER", contrRemote);
		lobbyRemote.RMIsubscribe(nick, CLIENT_PORT);
		if(isGUI)
			view.GUIGoToLobby();
		else
			consoleListener.addObserver(this);
		return 0;
	}
	/**
	 * Creates the registry. This method is used to find an available port, if the game is used locally. The global instance CLIENT_PORT is updated with the used one
	 *
	 * @param startPort the start port
	 * @return the registry
	 */
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
	

	/**
	 * Read file. Used for the custom map
	 *
	 * @param path the path
	 * @param encoding the encoding
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	static String readFile(String path, Charset encoding) 
			throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	/**
	 * Checks if nickname is correct.
	 *
	 * @param name the name
	 * @return the string
	 */
	
	
	
	
	private void RMIGUILogin(String nick){
		int resp=-1;
		try {
			resp = RMISetupAndLogin(nick);
		} catch (RemoteException | NotBoundException | AlreadyBoundException e) {
			e.printStackTrace();
		}
		switch (resp) {
		case 1:
			view.printMsg("Illegal characters");
			break;
		case 2:
			view.printMsg("Nickname size must be >5 and <13");
			break;
		case 3:
			view.printMsg("Nickname already used");
			break;
		case 0:
			this.setChanged();
			this.notifyObservers("GUI_LOGINSUCCESS");
		}
	}
	
	public void GUISendCommand(String cmd, Object obj){
		connection.sendToServer(cmd,obj);
	}
	
	public void changeMap(String name_url){
		String newMap = "";
		if(name_url.substring(0, "default".length()).equals("default")){
			String mapNo = name_url.substring("default".length(), name_url.length());
			if(mapNo.contains("[^0123456789]") || Integer.parseInt(mapNo)>8 || Integer.parseInt(mapNo) == 0){
					view.printMsg("Wrong input format");
					return;
			}
			newMap = "default" + mapNo;
		}
		else{
			try {
				newMap = readFile(name_url, StandardCharsets.UTF_8);
			} catch (Exception e) {
				view.printMsg("Error during map import");
			}
		}
		if(RMI)
			try {
				lobbyRemote.RMIlobbyCommand(this.userName, "\\SETMAP", newMap);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			connection.sendToServer("\\SETMAP", newMap);
		return;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * handles every input from other threads. Some of the RMI logic is in separate methods to make it callable from remote
	 */
	@Override
	public void update(Observable o, Object change){
		if(o instanceof GUIController){
			String[] info = ((String) change).split("_");
			if(info[0].equals("USERNAME")){
				if(this.RMI)
					this.RMIGUILogin(info[1]);
				else
					connection.sendToServer("INSERTNICKNAME",info[1]);//sono sicuro che il server sia già in attesa
				}
			else if(info[0].equals("CONNECTION")){
				if(info[1].equals("RMI"))
					this.RMI = true;
				else{
					this.RMI=false;
					ClientController a = this;
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								a.start();
							} catch (IOException | NotBoundException | AlreadyBoundException e) {
								e.printStackTrace();
							}
						}
					}).start();
					view.startTurn(null);
				}
			}
			return;
		}
		if(o instanceof ConsoleListener){
			String inStr = this.cliQueue.poll();
			if(inStr==null)
				return;
			String[] split = inStr.split("_");
			if(split[0].equals("\\SETMAP")){
				this.changeMap(split[1]);
			}
		else{
			if(RMI)
				try {
					view.printMsg(lobbyRemote.RMIlobbyCommand(this.userName, inStr, null));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				connection.sendToServer(inStr ,null);
			return;
		}
		}
		CommunicationObject in = (CommunicationObject) change;
		String cmd = in.getMsg();
		Object obj = in.getObj();

		if (cmd.contains("lobby_msg-")) {// messaggio della lobby
			cmd = cmd.substring("lobby_msg-".length());
			view.printMsg(cmd + "\n");
		} 
		else {// messaggio di gioco / input
			switch (cmd) {
			case "TOBUYEMPTY":
				view.printMsg("Every item has been bought. Turn skipped");
				break;
			case "TIMEOUT":
				if(this.userName.equals((String) obj)){
					this.abortFlag = true;
					this.setChanged();
					this.notifyObservers("ABORT");//lo passo alla view
					currentActState.setAbortFlag(true);//per passarlo allo stato
					this.cliQueue.add("ABORT");//per passarlo alla cli
					view.printMsg("You ran out of time. Turn skipped");
				}
				else
					view.printMsg("Player " + (String) obj + " ran out of time. Turn skipped");
				break;
			case "LOBBYSTATUS":
				this.lobbyStatus = (LobbyStatus) obj;
				view.updateLobby(lobbyStatus);
				break;
			case "INSERTNICKNAME":
				if(!isGUI){
					this.tmpUserName= view.getUsername();
					connection.sendToServer("INSERTNICKNAME",this.tmpUserName);
				}
				break;
			case "INSERTNICKNAMEACK":
				this.userName = this.tmpUserName;
				if(isGUI){
					this.setChanged();
					this.notifyObservers("GUI_LOGINSUCCESS");
				}
				else
					consoleListener.addObserver(this);
				break;
			case "INSERTNICKNAMENACK":
				view.printMsg(((String) obj)+ "\n");
				break;
			case "ONETOKEN":
				BonusTokenDTO[] availBts = (BonusTokenDTO[]) obj;
				BonusTokenDTO btDTO = view.oneToken(availBts);
				connection.sendToServer("INPUT_ONETOKEN",btDTO);
				break;

			case "ONETOKEN_ACK":
				break;

			case "ONETOKEN_NACK":
				break;
			case "TWOTOKENS":
				BonusTokenDTO[] availBts2 = (BonusTokenDTO[]) obj;
				BonusTokenDTO[] btDTO2 = view.twoTokens(availBts2);
				connection.sendToServer("INPUT_TWOTOKENS",btDTO2);
				break;

			case "TWOTOKENS_ACK":
				break;

			case "TWOTOKENS_NACK":
				break;
			case "FREECARD":
				PermitsCardDTO pcDTO = view.freeCard(game);
				connection.sendToServer("INPUT_FREECARD",pcDTO);
				break;

			case "FREECARDACK":
				break;

			case "FREECARDNACK":
				view.printMsg(((String) obj)+ "\n");
				break;

			case "BONUSCARD":
				PermitsCardDTO pcOwnedDTO= view.bonusCard(game);
				connection.sendToServer("INPUT_BONUSCARD",pcOwnedDTO);
				break;

			case "BONUSCARDACK":
				break;

			case "BONUSCARDNACK":
				view.printMsg(((String) obj)+ "\n");
				break;

			case "TOSELL":
				ToSellState sellState = new ToSellState(this.view, this.connection);
				Thread sellThread = new Thread(sellState);
				sellThread.start();
				break;

			case "TOSELLACK":
				view.printMsg(((String) obj)+ "\n");
				break;

			case "TOSELLNACK":
				view.printMsg(((String) obj)+ "\n");
				break;

			case "TOBUY":					
				ToBuyState buyState = new ToBuyState(this.game, this.view, connection);
				Thread buyThread = new Thread(buyState);
				buyThread.start();
				break;

			case "TOBUYACK":
				view.printMsg(((String) obj)+ "\n");
				break;

			case "TOBUYNACK":
				view.printMsg(((String) obj)+ "\n");
				break;
			case "StartTurn":  
				PoliticsCardDTO polcDTO = (PoliticsCardDTO) obj;
				view.startTurn(polcDTO);
				break;

			case "AvailableActions":
				this.abortFlag = false;
				this.setChanged();
				this.notifyObservers("RESETABORT");
				availableActions = (boolean[]) obj;
				this.availableActions= availableActions;//non date ascolto al warning, serve per il case "ActionNotValid"
				SelectActionState actState = new SelectActionState(this.game, availableActions, this.view, connection);
				currentActState = actState;
				Thread actThread = new Thread(actState);
				actThread.start();
				break;

			case "ActionAccepted": 
				break;

			case "ActionNotValid":
				view.printMsg(((String) obj)+ "\n");
				SelectActionState actStateRetry = new SelectActionState(this.game, this.availableActions, this.view, connection);
				currentActState = actStateRetry;
				Thread actThreadRetry = new Thread(actStateRetry);
				actThreadRetry.start();
				break;
			case "GAMEDTO":
				if(!isGUI)
					consoleListener.deleteObserver(this);//per stare sicuri, da togliere se verificato che STARTGAME arriva a tutti
				this.inGame = true;//se il player si riconnette dopo una disconnessione 
				view.updateGame((GameDTO) obj);
				this.game = (GameDTO) obj;
				break;
			case "STARTGAME":
				if(!isGUI)
					consoleListener.deleteObserver(this);
				this.inGame = true;
				//consoleListener.deleteObserver(this);//in gioco gli input sono ad invocazione  
				view.updateGame((GameDTO) obj);
				this.game = (GameDTO) obj;
				break;
			case "ENDGAME":
				view.printMsg((String) obj);
				this.inGame=false;
				break;
			case "GAMEMESSAGE":
				view.printMsg((String) obj);
				break;
			default:
				throw new IllegalArgumentException("Command not recognized: " + cmd);
			}
		}

	}

	/**
	 * City DTO equals. Compares two CityDTO
	 *
	 * @param c1 the c 1
	 * @param c2 the c 2
	 * @return true, if successful
	 */
	public boolean cityDTOEquals(CityDTO c1, CityDTO c2){
		if(c1.getName().equals(c2.getName()))
			return true;
		return false;
	}
	
	/**
	 * Checks if the client is in game.
	 *
	 * @return true, if is in game
	 */
	public boolean isInGame(){
		return this.inGame;
	}
	
	//------RMI methods---------
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIupdateLobby(com.communication.LobbyStatus)
	 * updates the lobby status through the received object
	 */
	public void RMIupdateLobby(LobbyStatus status){
		this.lobbyStatus = status;
		view.updateLobby(status);
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIupdateGame(com.communication.gamelogic.GameDTO)
	 * updates the game status through the received object
	 */
	public void RMIupdateGame(GameDTO game){
		this.consoleListener.deleteObserver(this);
		this.inGame = true;//se il player si riconnette dopo una disconnessione 
		view.updateGame(game);
		this.game = game;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIprintMsg(java.lang.String)
	 * prints a received message
	 */
	public void RMIprintMsg(String msg){
		view.printMsg(msg);
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIgetAction(boolean[])
	 * gets the action. just for RMI protocol
	 */
	public ActionDTO RMIgetAction(boolean[] availableActions){
		int selectedAction = view.getActionIndex(availableActions);
		SelectActionState actState = new SelectActionState(this.game, availableActions, this.view, null);
		if(abortFlag)
			return null;
		ActionDTO compiledAction = actState.getActionInstance(selectedAction);
		return compiledAction;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIStartTurn(com.communication.decks.PoliticsCardDTO)
	 * receive the politics card drawn at the beginning of the turn
	 */
	
	public void RMIAbort(){
		this.abortFlag = true;
		this.setChanged();
		this.notifyObservers("ABORT");//lo passo alla view
		if(currentActState != null)
			currentActState.setAbortFlag(true);//per passarlo all'eventuale stato di select action
		this.cliQueue.add("ABORT");//per passarlo alla cli
		view.printMsg("You ran out of time. Turn skipped");
	}
	
	public void RMIStartTurn(PoliticsCardDTO polcDTO){
		view.startTurn(polcDTO);
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIOneToken(com.communication.board.BonusTokenDTO[])
	 * gets one bonus token to give to the server
	 */
	public BonusTokenDTO RMIOneToken(BonusTokenDTO[] availBts){
		BonusTokenDTO btDTO = view.oneToken(availBts);
		return btDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMITwoTokens(com.communication.board.BonusTokenDTO[])
	 * gets two bonus token to give to the server
	 */
	public BonusTokenDTO[] RMITwoTokens(BonusTokenDTO[] availBts){
		BonusTokenDTO[] btDTO = view.twoTokens(availBts);
		return btDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIFreeCard()
	 * gets a permit card to give to the server
	 */
	public PermitsCardDTO RMIFreeCard(){
		PermitsCardDTO pcDTO=view.freeCard(game);
		return pcDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIBonusCard()
	 * gets a bonus card to give to the server
	 */
	public PermitsCardDTO RMIBonusCard(){
		PermitsCardDTO pcOwnedDTO=view.bonusCard(game);
		return pcOwnedDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIToSell()
	 * gets an ItemOnSale(item and price) to give to the server
	 */
	public ItemOnSale RMIToSell(){
		ItemOnSale item = view.toSell();
		return item;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMIClientControllerRemote#RMIToBuy()
	 * gets the UID to give to the server of the item to buy 
	 */
	public String RMIToBuy(){
		String onSaleUID = view.toBuy(game);
		return onSaleUID;
	}

	@Override
	public void run() {
		
	}
	
	
	
}
