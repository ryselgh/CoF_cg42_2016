package com.server.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.w3c.dom.Document;

import com.communication.CommunicationObject;
import com.communication.LobbyStatus;
import com.communication.RoomStatus;
import com.communication.values.RoomState;
//comandi: newRoom(roomName,maxplayers, minplayers),   joinRoom(roomName)   startgame(roomName)--->requires admin
//         getRoomList()--->obj ad hoc     leaveRoom(roomName)    1-setMap_mapname-->2-send serialized xml---->requires admin 

public class Lobby extends Observable implements Runnable, Observer  {
	private ArrayList<ClientHandler> clients;
	private ArrayList<Room> rooms;
	private String[] commandResponse;
	private LobbyStatus lobbyStatus;
	public Lobby(){ 
		clients = new ArrayList<ClientHandler>();
		rooms = new ArrayList<Room>();
		commandResponse = new String[] {"Success", "Command not recognized","The game already started","There are not enought players",
				"You are not the admin, you can't start the game","You are not in this room","Room not found", "You are already in the room",
				"You must left your current room first", "You must be the admin of the room to change the map","The room is full"};
	}

	@Override
	public void run() { 
		clients = new ArrayList<ClientHandler>();
		SocketAcceptor acceptor = new SocketAcceptor(this);
		Thread thread = new Thread(acceptor);
		thread.start();
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
			if(!sender.equals(r.getAdmin()))
				return 9;
			r.setMap((String) obj);
			sendToClient(sender, "lobby_msg-" + "Map successfully changed");
			break;
		case "\\NEWROOM":
			if(Integer.parseInt(ret[2])<Integer.parseInt(ret[3])){
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
				if (c.equals(client))
				return r;
		return null;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof IdentifyPlayer){//ricevo un nuovo client
			clients.add((ClientHandler) arg1);
			lobbyStatus = generateLobbyStatus();
			setChanged();
		    notifyObservers(lobbyStatus);
		}
		else if(arg0 instanceof ClientHandler){//ricevo un comando da un client
			String command = ((CommunicationObject) arg1).getMsg();
			Object object = ((CommunicationObject) arg1).getObj();
			ClientHandler sender = (ClientHandler) arg0;
			int resp = commandParser(command, sender, object);
			if(resp!=0)//se c'è un errore, altrimenti l'esito corretto viene già comunicato
				sendToClient(sender, "lobby_msg-" + commandResponse[resp]);
			else{
				lobbyStatus = generateLobbyStatus();
				setChanged();
			    notifyObservers(lobbyStatus);
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
		rooms.add(new Room(name,admin,maxPl,minPl));
	}
	
	private int startRoom(Room r, ClientHandler player){
		switch (r.canStart(player)){
		case 1:
			return 3;
		case 2:
			return 4;
		case 0:
			r.startRoom();
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
				if(ch.getUserName().equals(nick))
					return true;
		return false;
	}
	
	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	
	
}
