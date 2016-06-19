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
//comandi: newRoom(roomName,maxplayers, minplayers),   joinRoom(roomName)   startgame(roomName)--->requires admin
//         getRoomList()--->obj ad hoc     leaveRoom(roomName)    1-setMap_mapname-->2-send serialized xml---->requires admin 

public class Lobby extends Observable implements Runnable, Observer, RMILobbyRemote  {
	private ArrayList<ClientHandler> clients;
	private ArrayList<ClientHandler> inactiveClients;
	private ArrayList<Room> rooms;
	private String[] commandResponse;
	private LobbyStatus lobbyStatus;
	private boolean RMI;
	private ArrayList<RMISubscribed> remoteControllers;
	private final static String CLIENTRMI_HOST="127.0.0.1";
	private final static int CLIENTRMI_PORT=1098;
	
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

	@Override
	public void run() { 
		if(RMI)
			try {
				this.setUpRMI();
			} catch (RemoteException | AlreadyBoundException e) {
				e.printStackTrace();
			}
		else{
		clients = new ArrayList<ClientHandler>();
		SocketAcceptor acceptor = new SocketAcceptor(this);
		Thread thread = new Thread(acceptor);
		thread.start();
		}
	}
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
		case "\\SETMAP":
			r = findRoomByClient(sender);
			if(!sender.isEquals(r.getAdmin()))
				return 9;
			r.setMap((String) obj);
			sendToClient(sender, "lobby_msg-" + "Map successfully changed");
			break;
		case "\\NEWROOM":
			if(ret.length!=4){
				sendToClient(sender, "lobby_msg-" + "Wrong input format");
				break;
			}
			else if(Integer.parseInt(ret[2])<Integer.parseInt(ret[3])){
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
			if(r.getPlayers().size()==1){
				this.rooms.remove(r);
				sendToClient(sender, "lobby_msg-" + "Room " + ret[1] + " deleted");
				break;
			}
			ClientHandler newAdmin = r.leaveRoom(sender);
			if(newAdmin!=null)
				newAdmin.sendToClient("lobby_msg-You are the new admin of the room", null);
			sendToClient(sender, "lobby_msg-" + "You left room " + ret[1]);
			break;
		default:
			return 1;//command not recognized
		}
		return 0;//success
	}
	
	private Room findRoomByClient(ClientHandler client){
		for(Room r : this.rooms)
			for(ClientHandler c : r.getPlayers())
				if (c.isEquals(client))
				return r;
		return null;
	}
	
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
		inactiveClients.remove(newInstance);
		room.getGameHandler().broadcastAnnounce("CLIENTCONNECTED", newInstance.getUserName());
		roomGH.updateClientGame();
	}
	
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
	
	public void disconnectFromGame(ClientHandler sender){
		sender.closeSocket();
		Room room = findPlayerRoom(sender);
		room.getGameHandler().deleteObserver(sender);
		removePlayer(sender);
	}
	
	private void sendLobbyStatus(){
		lobbyStatus = generateLobbyStatus();
		setChanged();
		notifyObservers(lobbyStatus);
	}
	
	private Room findPlayerRoom(ClientHandler clientHandler){
		for (Room r : this.rooms)
			for (int i = 0; i < r.getPlayers().size(); i++)
				if (r.getPlayers().get(i).isEquals(clientHandler))
					return r;
		return null;
	}

	private void removePlayer(ClientHandler clientHandler) {
		Room room = findPlayerRoom(clientHandler); // trovo la room di appartenenza, se c'è
		if (room == null) {
			for (int i = 0; i < this.clients.size(); i++)
				if (this.clients.get(i).isEquals(clientHandler))
					this.clients.remove(i);// rimuovo il client dalla lista dei clients nella lobby
		} else {
			if (!clientHandler.inGame) {// se il giocatore non è il gioco viene cancellato forever
				if (room != null)
					room.getPlayers().remove(clientHandler);
			} else {// se il giocatore sta giocando
				clientHandler.setActive(false);
				inactiveClients.add(clientHandler);
				room.getGameHandler().broadcastAnnounce("CLIENTDISCONNECTED", clientHandler.getUserName());
			}
		}
	}
	
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
										r.getMinPlayers(),r.getMaxPlayers(), roomPlayers, r.isDefaultMap()));}
		return new LobbyStatus(freeClients,rooms);
	}
	
	private void sendToClient(ClientHandler client, String msg){
		setChanged();
	    notifyObservers(client.getUserName() + "_" + msg);
	}
	private boolean joinRoom(String roomName, ClientHandler player){
		Room r = findRoom(roomName);
		if(r.getState().equals(RoomState.IN_GAME))
			return false;
		r.addPlayer(player);
		clients.remove(player);
		return true;
		
	}
	
	private Room findRoom(String name){
		for(Room r: rooms)
			if(r.getName().equals(name))
				return r;
		return null;
	}
	private void createRoom(String name, ClientHandler admin, int maxPl, int minPl){
		rooms.add(new Room(name,admin,maxPl,minPl,this));
	}
	
	public void endGame(Room r, GameHandler gameHandler, ClientHandler winner){
		gameHandler.broadcastAnnounce("ENDGAME", winner.getUserName());
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
	
	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	//------------------RMI methods----------------------
	
	//metodo locale non remoto
	public void setUpRMI() throws RemoteException, AlreadyBoundException{
		Registry registry = LocateRegistry.createRegistry(1099);
		RMILobbyRemote lobbyRemote = (RMILobbyRemote) UnicastRemoteObject.exportObject(this, 0);
		registry.bind("lobby", lobbyRemote);
		}
	
	//metodo locale non remoto
	public ArrayList<RMISubscribed> getRemoteControllers() {
		return remoteControllers;
	}
	
	//metodo locale non remoto
	private ClientHandler nameToHandler(String userName){
		for(ClientHandler ch : this.clients)
			if(ch.getUserName().equals(userName))
				return ch;
		return null;
	}
	
	public int RMIlogIn(String userName){
		if(userName.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX]"))//regex equivalente a tutti i caratteri a parte le lettere
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
	
	private void broadcastLobbyStatus(){
		lobbyStatus = generateLobbyStatus();
		for(RMISubscribed RMIs : this.remoteControllers)
			try {
				RMIs.getRemContr().RMIupdateLobby(lobbyStatus);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public void RMIsubscribe(String userName, int port) throws AccessException, RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry(CLIENTRMI_HOST,port);
		RMIClientControllerRemote remContr = (RMIClientControllerRemote) registry.lookup(userName + "CONTROLLER");
		RMISubscribed sub = new RMISubscribed(this.nameToHandler(userName), remContr);
		this.remoteControllers.add(sub);
		broadcastLobbyStatus();
		
	}
	
	public String RMIlobbyCommand(String userName, String command, String map){
		ClientHandler sender = this.nameToHandler(userName);
		String[] ret = command.split("_");
		Room r = null;
		switch(ret[0]){
		case "\\SETMAP":
			r = findRoomByClient(sender);
			if(!sender.isEquals(r.getAdmin()))
				return commandResponse[9];
			r.setMap(map);
			broadcastLobbyStatus();
			return "Map successfully changed";
		case "\\NEWROOM":
			if(ret.length!=4){
				return "lobby_msg-" + "Wrong input format";
			}
			else if(Integer.parseInt(ret[2])<Integer.parseInt(ret[3])){
				return "lobby_msg-" + "MaxPlayers can't be lower than MinPlayers";
			}
			createRoom(ret[1], sender, Integer.parseInt(ret[2]),Integer.parseInt(ret[3]));
			this.clients.remove(sender);
			broadcastLobbyStatus();
			return "lobby_msg-" + "Room " + ret[1] + " successfully created. You are the Admin";
		case "\\JOINROOM":
			Room rTmp = findRoomByClient(sender);
			r = findRoom(ret[1]);
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
			return "lobby_msg-" + "You joined room " + ret[1];
		case "\\STARTGAME":
			r = findRoomByClient(sender);
			if(r==null){
				return "lobby_msg-" + "You are in the lobby, you can't start the game";
			}
			int retg = startRoom(r,sender);
			broadcastLobbyStatus();
			return commandResponse[retg];
		case "\\LEAVEROOM":
			r = findRoomByClient(sender);
			if(r.getPlayers().size()==1){
				this.rooms.remove(r);
				return "lobby_msg-" + "Room " + ret[1] + " deleted";
			}
			ClientHandler newAdmin = r.leaveRoom(sender);
			if(newAdmin!=null)
				RMISendToClient(newAdmin,"You are the new admin of the room");
			broadcastLobbyStatus();
			return "lobby_msg-" + "You left room " + ret[1];
		default:
			return commandResponse[1];//command not recognized
		}
	}
	
	
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
