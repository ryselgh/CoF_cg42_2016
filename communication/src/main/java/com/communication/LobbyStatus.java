package com.communication;

import java.io.Serializable;
import java.util.ArrayList;



/**
 * The Class LobbyStatus.
 */
public class LobbyStatus implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7914174063076904758L;
	
	/** The list of free clients. */
	private ArrayList<String> freeClients;//clients outside rooms
	
	/** The list of rooms. */
	private ArrayList<RoomStatus> rooms;
	
	
	/**
	 * Instantiates a new lobby status.
	 *
	 * @param freeClients the list free clients
	 * @param rooms the list of rooms
	 */
	public LobbyStatus(ArrayList<String> freeClients, ArrayList<RoomStatus> rooms){
		this.freeClients = freeClients;
		this.rooms = rooms;
	}


	/**
	 * Gets the list of free clients.
	 *
	 * @return the list of free clients
	 */
	public ArrayList<String> getFreeClients() {
		return freeClients;
	}


	/**
	 * Gets the list of rooms.
	 *
	 * @return the list of rooms
	 */
	public ArrayList<RoomStatus> getRooms() {
		return rooms;
	}
	
	
}
