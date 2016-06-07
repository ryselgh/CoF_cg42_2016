package com.server.controller;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.communication.values.RoomState;

public class Room {
	private ArrayList<ClientHandler> players;
	private String rawMap;
	private ClientHandler admin;
	private int maxPlayers, minPlayers;
	private String name;
	private boolean defaultMap = true;
	private RoomState status;
	
	public Room(String name, ClientHandler adm, int maxPlayers, int minPlayers){
		this.name = name;
		this.admin = adm;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;
		this.status = RoomState.WAITING_PLAYERS;
		players = new ArrayList<ClientHandler>();
		players.add(admin);
	}
	
	public String getName(){
		return name;
	}
	
	public int canStart(ClientHandler player){
		if(players.size()<minPlayers)
			return 1;
		else if(!player.equals(admin))
			return 2;
		return 0;
	}
	
	public void startRoom(){
		for(ClientHandler ch : players)
			ch.inGame = true;//blocca il loop mvc della lobby
		GameHandler gh = new GameHandler(players,defaultMap,rawMap);
		Thread thread = new Thread(gh);
		thread.start();
	}
	
	public ClientHandler leaveRoom(ClientHandler player) {//ritorna il nuovo admin, se l'admin lefta
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).equals(player)) {
				if (players.get(i).equals(admin)) {
					players.remove(i);
					this.admin = players.get(0);
					return this.admin;
				} else
					players.remove(i);
			}
		return null;
	}

	public boolean addPlayer(ClientHandler pl){
		if(players.size()==maxPlayers)
			return false;
		players.add(pl);
		return true;
	}
	
	public boolean hasJoined(ClientHandler player){
		if(players.contains(player))
			return true;
		return false;
	}
	public void setMap(String map){//non lo metto nel costruttore perchè va passata serializzata, credo
		this.rawMap = map;
		this.defaultMap = false;
	}
	

	public ArrayList<ClientHandler> getPlayers() {
		return players;
	}

	public ClientHandler getAdmin() {
		return admin;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getMinPlayers() {
		return minPlayers;
	}
	
	public RoomState getState(){
		return this.status;
	}
	
	public boolean isDefaultMap() {
		return defaultMap;
	}

	
	
}
