package com.server.controller;

import java.util.ArrayList;

public class RoomStatus {
	private int minPlayers,maxPlayers;
	private String roomName, adminName;
	private ArrayList<String> players;
	
	public RoomStatus(String rn,String an, int minpl, int maxpl, ArrayList<String> players){
		this.players = players;
		this.roomName = rn;
		this.adminName = an;
		this.minPlayers = minpl;
		this.maxPlayers = maxpl;
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
	
	
	
}
