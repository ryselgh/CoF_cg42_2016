package com.communication;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5422552318901770319L;
	private int minPlayers,maxPlayers;
	private String roomName, adminName;
	private ArrayList<String> players;
	private String mapName;
	private int timerDelay;
	
	public RoomStatus(String rn,String an, int minpl, int maxpl, ArrayList<String> players, String mapName,int timerDelay){
		this.players = players;
		this.roomName = rn;
		this.adminName = an;
		this.minPlayers = minpl;
		this.maxPlayers = maxpl;
		this.mapName = mapName;
		this.timerDelay = timerDelay;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getAdminName() {
		return adminName;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public String getMapName() {
		return mapName;
	}

	public int getTimerDelay() {
		return timerDelay;
	}
	
	
	
}
