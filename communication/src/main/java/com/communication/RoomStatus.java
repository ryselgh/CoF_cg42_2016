package com.communication;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
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
	
	/** The players. */
	private ArrayList<String> players;
	
	/** The map name. */
	private String mapName;
	
	/** The timer delay. */
	private int timerDelay;
	
	/**
	 * Instantiates a new room status.
	 *
	 * @param rn the roomname
	 * @param an the adminname
	 * @param minpl the minpl
	 * @param maxpl the maxpl
	 * @param players the players
	 * @param mapName the map name
	 * @param timerDelay the timer delay
	 */
	public RoomStatus(String rn,String an, int minpl, int maxpl, ArrayList<String> players, String mapName,int timerDelay){
		this.players = players;
		this.roomName = rn;
		this.adminName = an;
		this.minPlayers = minpl;
		this.maxPlayers = maxpl;
		this.mapName = mapName;
		this.timerDelay = timerDelay;
	}

	/**
	 * Gets the min players.
	 *
	 * @return the min players
	 */
	public int getMinPlayers() {
		return minPlayers;
	}

	/**
	 * Gets the max players.
	 *
	 * @return the max players
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
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<String> getPlayers() {
		return players;
	}

	/**
	 * Gets the map name.
	 *
	 * @return the map name
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * Gets the timer delay.
	 *
	 * @return the timer delay
	 */
	public int getTimerDelay() {
		return timerDelay;
	}
	
	
	
}
