package com.server.controller;

import java.util.ArrayList;

public class Room {
	private ArrayList<ClientHandler> players;
	private String MapLocation;
	private ClientHandler admin;
	private int maxPlayers, minPlayers;
	
	public Room(String name, ClientHandler adm, int maxPlayers, int minPlayers){
		this.admin = adm;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;
		players = new ArrayList<ClientHandler>();
		players.add(admin);
	}
	
	public boolean addPlayer(ClientHandler pl){
		if(players.size()==maxPlayers)
			return false;
		players.add(pl);
		return true;
	}
	
	public void setMap(String mapLoc){//non lo metto nel costruttore perchè va passata serializzata, credo
		this.MapLocation = mapLoc;
	}
	
	public void startGame(){
		if(players.size()>=minPlayers){}
	}
}
