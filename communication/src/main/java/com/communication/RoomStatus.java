package com.communication;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The Class RoomStatus.
 */
public class RoomStatus implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5422552318901770319L;
	
	/** The max players. */
	private int minPlayers,maxPlayers;
	
	/** The admin name. */
	private String roomName, adminName;
	
	/** The list of players. */
	private ArrayList<String> players;
	
	/** The default map. */
	private boolean defaultMap;
	
	/**
	 * Instantiates a new room status.
	 *
	 * @param rn the room name
	 * @param an the admin name
	 * @param minpl the min numbe rof players
	 * @param maxpl the max number of olayers
	 * @param players the list of players
	 * @param defMap the default map
	 */
	public RoomStatus(String rn,String an, int minpl, int maxpl, ArrayList<String> players, boolean defMap){
		this.players = players;
		this.roomName = rn;
		this.adminName = an;
		this.minPlayers = minpl;
		this.maxPlayers = maxpl;
		this.defaultMap = defMap;
	}

	/**
	 * Gets the min number of players.
	 *
	 * @return the min number of players
	 */
	public int getMinPlayers() {
		return minPlayers;
	}

	/**
	 * Gets the max number of players.
	 *
	 * @return the max numbee of players
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * Gets the room name.
	 *
	 * @return the room name
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * Gets the admin name.
	 *
	 * @return the admin name
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * Gets the list  of players.
	 *
	 * @return the list of players
	 */
	public ArrayList<String> getPlayers() {
		return players;
	}

	/**
	 * Checks if is default map.
	 *
	 * @return true, if is default map
	 */
	public boolean isDefaultMap() {
		return defaultMap;
	}
	
	
	
}
