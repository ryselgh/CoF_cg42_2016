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
	private boolean defaultMap;
	
	public RoomStatus(String rn,String an, int minpl, int maxpl, ArrayList<String> players, boolean defMap){
		this.players = players;
		this.roomName = rn;
		this.adminName = an;
		this.minPlayers = minpl;
		this.maxPlayers = maxpl;
		this.defaultMap = defMap;
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

	public boolean isDefaultMap() {
		return defaultMap;
	}
	
	
	
}
