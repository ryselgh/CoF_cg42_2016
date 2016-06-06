package com.communication;

import java.io.Serializable;
import java.util.ArrayList;


public class LobbyStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7914174063076904758L;
	private ArrayList<String> freeClients;//clients outside rooms
	private ArrayList<RoomStatus> rooms;
	
	
	public LobbyStatus(ArrayList<String> freeClients, ArrayList<RoomStatus> rooms){
		this.freeClients = freeClients;
		this.rooms = rooms;
	}


	public ArrayList<String> getFreeClients() {
		return freeClients;
	}


	public ArrayList<RoomStatus> getRooms() {
		return rooms;
	}
	
	
}
