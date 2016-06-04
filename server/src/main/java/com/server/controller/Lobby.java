package com.server.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.w3c.dom.Document;

import com.communication.CommunicationObject;
import com.communication.values.RoomState;
//comandi: newRoom(roomName,maxplayers, minplayers),   joinRoom(roomName)   startgame(roomName)--->requires admin
//         getRoomList()--->obj ad hoc     leaveRoom(roomName)    1-setMap_mapname-->2-send serialized xml---->requires admin 

public class Lobby extends Observable implements Runnable, Observer  {
	private ArrayList<ClientHandler> clients;
	private ArrayList<Room> rooms;
	private String[] commandResponse;
	public Lobby(){ 
		clients = new ArrayList<ClientHandler>();
		rooms = new ArrayList<Room>();
		commandResponse = new String[] {"Success", "Command not recognized","The game already started","There are not enought players",
				"You are not the admin, you can't start the game","You are not in this room"};
	}

	@Override
	public void run() { 
		clients = new ArrayList<ClientHandler>();
		SocketAcceptor acceptor = new SocketAcceptor(this);
		Thread thread = new Thread(acceptor);
		thread.start();
	}
	 
	private int commandParser(String command, ClientHandler sender){
		String[] ret = command.split("_");
		switch(ret[0]){
		case "newRoom":
			createRoom(ret[1], sender, Integer.parseInt(ret[2]),Integer.parseInt(ret[3]));
			break;
		case "joinRoom":
			if (!joinRoom(ret[1],sender))
				return 2;//game already started
			break;
		case "startGame":
			return startRoom(ret[1],sender);
		case "leaveRoom":
			if (!leaveRoom(ret[1],sender))
				return 5;
		default:
			return 1;//command not recognized
		}
		return 0;//success
	}

	private boolean leaveRoom(String roomName, ClientHandler player){
		Room r = findRoom(roomName);
		return r.leaveRoom(player);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof IdentifyPlayer)//ricevo un nuovo client
			clients.add((ClientHandler) arg1);
		else if(arg0 instanceof ClientHandler){//ricevo un comando da un client
			String command = ((CommunicationObject) arg1).getMsg();
			ClientHandler sender = (ClientHandler) arg0;
			int resp = commandParser(command, sender);
			sendToClient(sender, "lobby_msg-" + commandResponse[resp]);
			}
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
	
	private int startRoom(String name, ClientHandler player){
		Room r = findRoom(name);
		switch (r.canStart(player)){
		case 1:
			return 3;
		case 2:
			return 4;
		case 0:
			r.startRoom();
			for(ClientHandler ch : r.getPlayers() )
				ch.deleteObserver(this);
			return 0;
		}
		return 0;
	}

	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	
	
}
