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
	private GameHandler gameHandler;
	private Lobby lobby;
	private Thread threadGameHandler;
	
	public Room(String name, ClientHandler adm, int maxPlayers, int minPlayers, Lobby lobby){
		this.lobby=lobby;
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
		for(ClientHandler ch : players){
			ch.inGame = true;
		}
		this.status = RoomState.IN_GAME;
		gameHandler = new GameHandler(players,defaultMap,rawMap,lobby, this);
		threadGameHandler = new Thread(gameHandler);
		threadGameHandler.start();
	}
	
	public void stopThreadGameHandler(){
		try {
			threadGameHandler.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	
	public boolean isFull(){
		if(this.maxPlayers==this.players.size())
			return true;
		return false;
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

	public GameHandler getGameHandler() {
		return gameHandler;
	}

	
	
}
