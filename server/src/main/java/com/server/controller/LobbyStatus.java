package com.server.controller;

import java.util.ArrayList;

public class LobbyStatus {
	private ArrayList<String> freeClients;//clients outside rooms
	private ArrayList<RoomStatus> rooms;
	
	
	public LobbyStatus(Lobby lobby){
		for(ClientHandler c : lobby.getClients())
			freeClients.add(c.getUserName());
		for(Room r : lobby.getRooms()){
			ArrayList<String> roomPlayers = new ArrayList<String>();
			for(ClientHandler ch : r.getPlayers())
				roomPlayers.add(ch.getUserName());
			rooms.add(new RoomStatus(r.getName(),r.getAdmin().getUserName(),
										r.getMinPlayers(),r.getMaxPlayers(), roomPlayers));}
	}


	public ArrayList<String> getFreeClients() {
		return freeClients;
	}


	public ArrayList<RoomStatus> getRooms() {
		return rooms;
	}
	
	
}
