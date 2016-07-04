package com.server.controller;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import com.communication.CommunicationObject;
import com.communication.LobbyStatus;
import com.communication.RMIClientControllerRemote;
import com.communication.RMILobbyRemote;
import com.communication.RoomStatus;
import com.communication.values.RoomState;
// TODO: Auto-generated Javadoc
//comandi: newRoom(roomName,maxplayers, minplayers),   joinRoom(roomName)   startgame(roomName)--->requires admin
//         getRoomList()--->obj ad hoc     leaveRoom(roomName)    1-setMap_mapname-->2-send serialized xml---->requires admin 

/**
 * The Class Lobby.
 */
public class Lobby extends Observable implements Runnable, Observer, RMILobbyRemote  {
	
	/** The clients. */
	private ArrayList<ClientHandler> clients;
	
	/** The inactive clients. */
	private ArrayList<ClientHandler> inactiveClients;
	
	/** The rooms. */
	private ArrayList<Room> rooms;
	
	/** The responses to commands from the client. */
	private String[] commandResponse;
	
	/** The lobby status. */
	private LobbyStatus lobbyStatus;
	
	/** The boolean rmi. */
	private boolean RMI;
	
	/** The remote controllers. */
	private ArrayList<RMISubscribed> remoteControllers;
	
	/** The Constant CLIENTRMI_HOST. */
	private final static String CLIENTRMI_HOST="127.0.0.1";
	
	/** The Constant CLIENTRMI_PORT. */
	private final static int CLIENTRMI_PORT=1098;
	
	/**
	 * Instantiates a new lobby.
	 *
	 * @param RMI the rmi
	 */
	public Lobby(boolean RMI){ 
		this.remoteControllers = new ArrayList<RMISubscribed>();
		this.RMI = RMI;
		inactiveClients = new ArrayList<ClientHandler>();
		clients = new ArrayList<ClientHandler>();
		rooms = new ArrayList<Room>();
		commandResponse = new String[] {"Success", "Command not recognized","The game already started","There are not enought players",
				"You are not the admin, you can't start the game","You are not in this room","Room not found", "You are already in the room",
				"You must left your current room first", "You must be the admin of the room to change the map","The room is full"};
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() { 
		if(RMI){
			try {
				this.setUpRMI();
			} catch (RemoteException | AlreadyBoundException e) {
				e.printStackTrace();
			}
			System.out.println("RMI successfully set up");
		}
		else{
		clients = new ArrayList<ClientHandler>();
		SocketAcceptor acceptor = new SocketAcceptor(this);
		Thread thread = new Thread(acceptor);
		thread.start();
		System.out.println("Socket server successfully set up");
		}
	}
	 
 	/**
 	 * Command parser.
 	 *
 	 * @param command the command from the client
 	 * @param sender the sender
 	 * @param obj the object sent with the comand
 	 * @return the index of commandresponse
 	 */
 	/*
	'\NEWROOM_roomname_maxPl_minPl'
	'\JOINROOM_roomname'
	'\STARTGAME_roomname' requires admin of the room
	'\LEAVEROOM_roomname'
	'\SETMAP   Document->obj   requires admin
	todo: passaggio lobby->game con ACK di STARTGAME 
	      test gioco 
	*/
	private int commandParser(String command, ClientHandler sender, Object obj){
		String[] ret = command.split("_");
		Room r = null;
		switch(ret[0]){
		case "\\SETTIMEOUT":
			r = findRoomByClient(sender);
			if(r==null){
				sendToClient(sender, "lobby_msg-" + "You are in lobby");
				return 0;
			}
			if(!sender.isEquals(r.getAdmin())){
				sendToClient(sender, "lobby_msg-" + "You are not the room admin");
				return 0;
			}
			int sec;
			try{
				sec = Integer.parseInt(ret[1]);
			}
			catch(Exception e){
				sendToClient(sender, "lobby_msg-" + "Wrong input format");
				return 0;
			}
			r.setTimerDelay(sec * 1000);
			break;
		case "\\SETMAP":
			if(this.clients.contains(sender)){
				sendToClient(sender, "lobby_msg-" + "You are in lobby");
				return 0;
			}
			r = findRoomByClient(sender);
			if(!sender.isEquals(r.getAdmin()))
				return 9;
			r.setMap((String) obj);
			sendToClient(sender, "lobby_msg-" + "Map successfully changed");
			break;
		case "\\NEWROOM":
			r = findRoomByClient(sender);
			if(r!=null){
				sendToClient(sender, "lobby_msg-" + "Leave the room first");
				return 0;
			}
			if(ret.length!=4){
				sendToClient(sender, "lobby_msg-" + "Wrong input format");
				break;
			}
			int max=0, min=0;
			try{
				max = Integer.parseInt(ret[2]);
				min = Integer.parseInt(ret[3]);
			} catch(Exception e){
				sendToClient(sender, "lobby_msg-" + "Wrong input format");
				return 0;
			}
			if(max<min){
				sendToClient(sender, "lobby_msg-" + "MaxPlayers can't be lower than MinPlayers");
				break;
			}
			createRoom(ret[1], sender, Integer.parseInt(ret[2]),Integer.parseInt(ret[3]));
			this.clients.remove(sender);
			sendToClient(sender, "lobby_msg-" + "Room " + ret[1] + " successfully created. You are the Admin");
			break;
		case "\\JOINROOM":
			Room rTmp = findRoomByClient(sender);
			r = findRoom(ret[1]);
			if(rTmp != null && rTmp != r)
				return 8;
			if(r==null)
				return 6;
			if(r.hasJoined(sender))
				return 7;
			if(r.isFull())
				return 10;
			if (!joinRoom(ret[1],sender))
				return 2;//game already started
			sendToClient(sender, "lobby_msg-" + "You joined room " + ret[1]);
			break;
		case "\\STARTGAME":
			r = findRoomByClient(sender);
			if(r==null){
				sendToClient(sender, "lobby_msg-" + "You are in the lobby, you can't start the game");
				return 0;
			}
			int retg = startRoom(r,sender);
			return retg;
		case "\\LEAVEROOM":
			r = findRoomByClient(sender);
			if(r==null){
				sendToClient(sender, "lobby_msg-" + "You are in the lobby");
				return 0;
			}
			this.clients.add(sender);
			if(r.getPlayers().size()==1){
				this.rooms.remove(r);
				sendToClient(sender, "lobby_msg-" + "Room " + r.getName() + " deleted");
				break;
			}
			ClientHandler newAdmin = r.leaveRoom(sender);
			if(newAdmin!=null)
				newAdmin.sendToClient("lobby_msg-You are the new admin of the room", null);
			sendToClient(sender, "lobby_msg-" + "You left room " + r.getName());
			break;
		default:
			return 1;//command not recognized
		}
		return 0;//success
	}
	
	/**
	 * Find room by client.
	 *
	 * @param client the client
	 * @return the room of the client. null if he's in the lobby
	 */
	private Room findRoomByClient(ClientHandler client){
		for(Room r : this.rooms)
			for(ClientHandler c : r.getPlayers())
				if (c.isEquals(client))
				return r;
		return null;
	}
	
	/**
	 * Restore player when he reconnects.
	 *
	 * @param newInstance the new instance
	 * @param oldInstance the old instance
	 */
	private void restorePlayer(ClientHandler newInstance, ClientHandler oldInstance){
		Room room = findPlayerRoom(oldInstance);
		GameHandler roomGH = room.getGameHandler();
		ArrayList<ClientHandler> oldInst = roomGH.getPlayers();
		ArrayList<ClientHandler> newInst = new ArrayList<ClientHandler>();
		for(ClientHandler ch : oldInst){//sostituisco la vecchia istanza con la nuova (quindi cambio socket e ClientListener)
			if(ch.isEquals(newInstance))//uso il for per mantenere l'ordine se no va tutto a puttane
				newInst.add(newInstance);//sostituendo l'istanza non devo settare active=true perchè lo è di default
			else
				newInst.add(ch);
		}
		roomGH.setPlayers(newInst);
		this.deleteObserver(newInstance);
		newInstance.deleteObserver(this);
		newInstance.addObserver(roomGH);
		newInstance.inGame=true;
		inactiveClients.remove(newInstance);
		if(RMI){
			roomGH.replaceController(newInstance, RMISubscribed.getRemoteController(remoteControllers, newInstance));
		}
		room.getGameHandler().broadcastAnnounce("GAMEMESSAGE", "Player " + newInstance.getUserName() + " reconnected to the game");
		try {
			roomGH.updateClientGame();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof IdentifyPlayer) {// ricevo un nuovo client
			ClientHandler toAdd = (ClientHandler) arg1;
			for(ClientHandler ch : this.inactiveClients)
				if(ch.isEquals(toAdd)){//l'equals confronta solo i nomi 
					restorePlayer(toAdd,ch);
						return;
					}
			clients.add(toAdd);
			lobbyStatus = generateLobbyStatus();
			setChanged();
			notifyObservers(lobbyStatus);
		} else if (arg0 instanceof ClientHandler) {// ricevo un comando da un
													// client
			String command = ((CommunicationObject) arg1).getMsg();
			Object object = ((CommunicationObject) arg1).getObj();
			ClientHandler sender = (ClientHandler) arg0;
			if (command.equals("DisconnectedFromLobby")){ //se il giocatore chiude il client
				ClientHandler c = (ClientHandler) arg0;
				this.deleteObserver(c);
				c.closeSocket();
				removePlayer(c);
				sendLobbyStatus();
			}
			else {
				int resp = commandParser(command, sender, object);
				if (resp != 0)// se c'è un errore, altrimenti l'esito corretto viene già comunicato
					sendToClient(sender, "lobby_msg-" + commandResponse[resp]);
				else 
					sendLobbyStatus();
			}
		}
	}
	
	/**
	 * Handles player's disconnection.
	 *
	 * @param sender the sender
	 */
	public void disconnectFromGame(ClientHandler sender, GameHandler gameHandler){
		if(!RMI)
			sender.closeSocket();
		sender.setActive(false);
		Room room = findPlayerRoom(sender);
		room.getGameHandler().deleteObserver(sender);
		removePlayer(sender);
	}
	
	/**
	 * Send lobby status to clients.
	 */
	private void sendLobbyStatus(){
		lobbyStatus = generateLobbyStatus();
		setChanged();
		notifyObservers(lobbyStatus);
	}
	
	/**
	 * Find player room.
	 *
	 * @param clientHandler the client handler
	 * @return the room of the client
	 */
	private Room findPlayerRoom(ClientHandler clientHandler){
		for (Room r : this.rooms)
			for (int i = 0; i < r.getPlayers().size(); i++)
				if (r.getPlayers().get(i).isEquals(clientHandler))
					return r;
		return null;
	}

	/**
	 * Removes the player from lobby -forever- or game -forever if game not started, until he reconnects otherwise-. 
	 *
	 * @param clientHandler the client handler
	 */
	private void removePlayer(ClientHandler clientHandler) {
		Room room = findPlayerRoom(clientHandler); // trovo la room di appartenenza, se c'è
		if (room == null) {
			for (int i = 0; i < this.clients.size(); i++)
				if (this.clients.get(i).isEquals(clientHandler))
					this.clients.remove(i);// rimuovo il client dalla lista dei clients nella lobby
		} else {
			if (!clientHandler.inGame) {// se il giocatore non è il gioco viene cancellato forever
				room.getPlayers().remove(clientHandler);
			} else {// se il giocatore sta giocando
				clientHandler.setActive(false);
				inactiveClients.add(clientHandler);
				room.getGameHandler().broadcastAnnounce("GAMEMESSAGE", "Player " + clientHandler.getUserName() + " disconnected from the game");
			}
		}
	}
	
	/**
	 * Generate lobby status to be sent to the clients.
	 *
	 * @return the lobby status
	 */
	private LobbyStatus generateLobbyStatus(){
		ArrayList<String> freeClients = new ArrayList<String>(); 
		ArrayList<RoomStatus> rooms = new ArrayList<RoomStatus>();
		
		for(ClientHandler c : this.getClients())
			freeClients.add(c.getUserName());
		for(Room r : this.getRooms()){
			ArrayList<String> roomPlayers = new ArrayList<String>();
			for(ClientHandler ch : r.getPlayers())
				roomPlayers.add(ch.getUserName());
			rooms.add(new RoomStatus(r.getName(),r.getAdmin().getUserName(),
										r.getMinPlayers(),r.getMaxPlayers(), roomPlayers, r.getMapName(), r.getTimerDelay(), r.getState()));}
		return new LobbyStatus(freeClients,rooms);
	}
	
	/**
	 * Send to client.
	 *
	 * @param client the target client
	 * @param msg the message
	 */
	private void sendToClient(ClientHandler client, String msg){
		setChanged();
	    notifyObservers(client.getUserName() + "_" + msg);
	}
	
	/**
	 * Join room.
	 *
	 * @param roomName the room name
	 * @param player the player
	 * @return true, if successful. false if the room is in game. other errors are handled in command parser
	 */
	private boolean joinRoom(String roomName, ClientHandler player){
		Room r = findRoom(roomName);
		if(r.getState().equals(RoomState.IN_GAME))
			return false;
		r.addPlayer(player);
		clients.remove(player);
		return true;
		
	}
	
	/**
	 * Retrieves room instance from it's name.
	 *
	 * @param name the name of the room
	 * @return the room instance
	 */
	private Room findRoom(String name){
		for(Room r: rooms)
			if(r.getName().equals(name))
				return r;
		return null;
	}
	
	/**
	 * Creates the room.
	 *
	 * @param name the name of the room
	 * @param admin the admin of the room
	 * @param maxPl the players's no upper bount
	 * @param minPl the players's no lower bound
	 */
	private void createRoom(String name, ClientHandler admin, int maxPl, int minPl){
		rooms.add(new Room(name,admin,maxPl,minPl,this,0));
	}
	
	/**
	 * End game.
	 *
	 * @param r the room of the game
	 * @param gameHandler the game handler
	 * @param winner the winner
	 */
	public void endGame(Room r, GameHandler gameHandler, ClientHandler winner){
		gameHandler.broadcastAnnounce("ENDGAME", "Player " + winner.getUserName() + " won the game. You will return to lobby");
		gameHandler.deleteObservers();
		for(ClientHandler ch : gameHandler.getPlayers()){
			ch.deleteObservers();
			if(ch.isActive())//se il client non è disconnesso lo rimando alla lobby
				clients.add(ch);
			else//se invece è ancora disconnesso alla fine della partita, lo tolgo dai clients inattivi
				for(int i=0;i<this.inactiveClients.size();i++)
					if(ch.getUserName().equals(this.inactiveClients.get(i).getUserName()))
						this.inactiveClients.remove(i);
			ch.addObserver(this);
			this.addObserver(ch);
			ch.inGame=false;
		}
			for(int i=0;i<rooms.size();i++)
				if(r.getName().equals(rooms.get(i).getName()))
						rooms.remove(i);
			lobbyStatus = generateLobbyStatus();
			setChanged();
			notifyObservers(lobbyStatus);
			
	}
	
	/**
	 * Start room.
	 *
	 * @param r the room
	 * @param player the player who is tryng to start the game
	 * @return the index of the command response
	 */
	private int startRoom(Room r, ClientHandler player){
		switch (r.canStart(player)){
		case 1:
			return 3;
		case 2:
			return 4;
		case 0:
			r.startRoom(RMI);
			for(ClientHandler ch : r.getPlayers() ){
				ch.deleteObserver(this);
				this.deleteObserver(ch);
			}
			return 0;
		}
		return 0;
	}

	/**
	 * Checks if is nickname used.
	 *
	 * @param nick the nick
	 * @return true, if is nickname used
	 */
	public boolean isNicknameUsed(String nick){
		for(ClientHandler ch : this.clients)
			if(ch.getUserName().equals(nick))
					return true;
		for(Room r : this.rooms)
			for(ClientHandler ch : r.getPlayers())
				if(ch.getUserName().equals(nick) && ch.isActive())
					return true;
		return false;
	}
	
	/**
	 * Gets the clients.
	 *
	 * @return the clients
	 */
	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	/**
	 * Gets the rooms.
	 *
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	//------------------RMI methods----------------------
	
	/**
	 * Sets the up RMI.
	 *
	 * @throws RemoteException the remote exception
	 * @throws AlreadyBoundException the already bound exception
	 */
	//metodo locale non remoto
	public void setUpRMI() throws RemoteException, AlreadyBoundException{
		Registry registry = LocateRegistry.createRegistry(1099);
		RMILobbyRemote lobbyRemote = (RMILobbyRemote) UnicastRemoteObject.exportObject(this, 0);
		registry.bind("lobby", lobbyRemote);
		}
	
	/**
	 * Gets the remote controllers.
	 *
	 * @return the remote controllers
	 */
	//metodo locale non remoto
	public ArrayList<RMISubscribed> getRemoteControllers() {
		return remoteControllers;
	}
	
	/**
	 * Gets the clienthandler instance from it's name.
	 *
	 * @param userName the user name
	 * @return the client handler
	 */
	//metodo locale non remoto
	private ClientHandler nameToHandler(String userName){
		for(ClientHandler ch : this.clients)
			if(ch.getUserName().equals(userName))
				return ch;
		for(Room r : this.rooms)
			for(ClientHandler ch : r.getPlayers())
				if(ch.getUserName().equals(userName))
					return ch;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMILobbyRemote#RMIlogIn(java.lang.String)
	 * the login for RMI protocol
	 */
	public int RMIlogIn(String userName){
		if(userName.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX1234567890]"))//regex equivalente a tutti i caratteri a parte le lettere
			return 1;
		if(userName.length()<5 || userName.length()>13)
			return 2;
		if(this.isNicknameUsed(userName))
			return 3;
		ClientHandler client = new ClientHandler(null,null,null,userName,this);
		ClientHandler toAdd = client;
		for(ClientHandler ch : this.inactiveClients)
			if(ch.isEquals(toAdd)){//l'equals confronta solo i nomi 
				restorePlayer(toAdd,ch);
					return 0;
				}
		clients.add(toAdd);
		return 0;
	}
	
	/**
	 * Broadcast lobby status.
	 */
	private void broadcastLobbyStatus(){
		lobbyStatus = generateLobbyStatus();
		for(RMISubscribed RMIs : this.remoteControllers)
			if(!RMIs.getCh().inGame)
			try {
				RMIs.getRemContr().RMIupdateLobby(lobbyStatus);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMILobbyRemote#RMIsubscribe(java.lang.String, int)
	 * binds a client to the lobby
	 */
	public void RMIsubscribe(String userName, int port) throws AccessException, RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry(CLIENTRMI_HOST,port);
		RMIClientControllerRemote remContr = (RMIClientControllerRemote) registry.lookup(userName + "CONTROLLER");
		RMISubscribed sub = new RMISubscribed(this.nameToHandler(userName), remContr);
		this.remoteControllers.add(sub);
		broadcastLobbyStatus();
		
	}
	
	/* (non-Javadoc)
	 * @see com.communication.RMILobbyRemote#RMIlobbyCommand(java.lang.String, java.lang.String, java.lang.String)
	 * command parser for RMI protocol
	 */
	public String RMIlobbyCommand(String userName, String command, String map){
		ClientHandler sender = this.nameToHandler(userName);
		String[] ret = command.split("_");
		Room r = null;
		switch(ret[0]){
		case "\\SETTIMEOUT":
			r = findRoomByClient(sender);
			if(r==null)
				return "You are in lobby";
			if(!sender.isEquals(r.getAdmin())){
				return "You are not the room admin";
				
			}
			int sec;
			try{
				sec = Integer.parseInt(ret[1]);
			}
			catch(Exception e){
				return "Wrong input format";
				
			}
			r.setTimerDelay(sec * 1000);
			return "Timer updated";
		case "\\SETMAP":
			r = findRoomByClient(sender);
			if(!sender.isEquals(r.getAdmin()))
				return commandResponse[9];
			
			r.setMap(map);
			broadcastLobbyStatus();
			return "Map successfully changed";
		case "\\NEWROOM":
			r = findRoomByClient(sender);
			if(r!=null){
				return "Leave the room first";
			}
			if(ret.length!=4){
				return "Wrong input format";
			}
			int max=0, min=0;
			try{
				max = Integer.parseInt(ret[2]);
				min = Integer.parseInt(ret[3]);
			} catch(Exception e){
				return "Wrong input format";
			}
			if(max<min){
				return  "MaxPlayers can't be lower than MinPlayers";
			}
			createRoom(ret[1], sender, Integer.parseInt(ret[2]),Integer.parseInt(ret[3]));
			this.clients.remove(sender);
			broadcastLobbyStatus();
			return  "Room " + ret[1] + " successfully created. You are the Admin";
		case "\\JOINROOM":
			Room rTmp = findRoomByClient(sender);
			if(ret.length>1)
				r = findRoom(ret[1]);
			else
				return "Wrong input format";
			if(rTmp != null && rTmp != r)
				return commandResponse[8];
			if(r==null)
				return commandResponse[6];
			if(r.hasJoined(sender))
				return commandResponse[7];
			if(r.isFull())
				return commandResponse[10];
			if (!joinRoom(ret[1],sender))
				return commandResponse[2];//game already started
			broadcastLobbyStatus();
			return  "You joined room " + ret[1];
		case "\\STARTGAME":
			r = findRoomByClient(sender);
			if(r==null){
				return "You are in the lobby, you can't start the game";
			}
			int retg = startRoom(r,sender);
			broadcastLobbyStatus();
			return commandResponse[retg];
		case "\\LEAVEROOM":
			r = findRoomByClient(sender);
			this.clients.add(sender);
			if(r.getPlayers().size()==1){
				this.rooms.remove(r);
				broadcastLobbyStatus();
				return "Room " + r.getName() + " deleted";
			}
			ClientHandler newAdmin = r.leaveRoom(sender);
			if(newAdmin!=null)
				RMISendToClient(newAdmin,"You are the new admin of the room");
			broadcastLobbyStatus();
			return "You left room " + r.getName();
		default:
			return commandResponse[1];//command not recognized
		}
	}
	
	
	/**
	 * RMI send to client.
	 *
	 * @param ch the target clienthandler
	 * @param msg the message
	 */
	//oggeto locale non remoto
	public void RMISendToClient(ClientHandler ch, String msg){//viola il presupposto dell'RMI ma nella lobby per forza devo mandare dei messaggi
		for(RMISubscribed RMIs : this.remoteControllers)
			if(RMIs.getCh().isEquals(ch))
				try {
					RMIs.getRemContr().RMIprintMsg(msg);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
}
