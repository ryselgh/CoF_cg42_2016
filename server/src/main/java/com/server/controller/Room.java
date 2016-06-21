package com.server.controller;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.communication.values.RoomState;

// TODO: Auto-generated Javadoc
/**
 * The Class Room.
 */
public class Room {
	
	/** The players. */
	private ArrayList<ClientHandler> players;
	
	/** The raw map. */
	private String rawMap;
	
	/** The admin. */
	private ClientHandler admin;
	
	/** The min,max players. */
	private int maxPlayers, minPlayers;
	
	/** The name. */
	private String name;
	
	/** The default map. */
	private boolean defaultMap = true;
	
	/** The status. */
	private RoomState status;
	
	/** The game handler. */
	private GameHandler gameHandler;
	
	/** The lobby. */
	private Lobby lobby;
	
	/** The thread instance of the game handler. */
	private Thread threadGameHandler;
	
	/**
	 * Instantiates a new room.
	 *
	 * @param name the name of the room
	 * @param adm the admin
	 * @param maxPlayers the max players
	 * @param minPlayers the min players
	 * @param lobby the lobby instance
	 */
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
	
	/**
	 * Gets the room name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Can start.
	 *
	 * @param player the player who is trying to start the room
	 * @return the int of the command response
	 */
	public int canStart(ClientHandler player){
		if(players.size()<minPlayers)
			return 1;
		else if(!player.isEquals(admin))
			return 2;
		return 0;
	}
	
	/**
	 * Start room.
	 *
	 * @param RMI the rmi
	 */
	public void startRoom(boolean RMI){
		for(ClientHandler ch : players){
			ch.inGame = true;
		}
		this.status = RoomState.IN_GAME;
		gameHandler = new GameHandler(players,defaultMap,rawMap,lobby, this, RMI, lobby.getRemoteControllers());
		threadGameHandler = new Thread(gameHandler);
		threadGameHandler.start();
	}
	
	/**
	 * Stops the thread of the game handler.
	 */
	public void stopThreadGameHandler(){
		try {
			threadGameHandler.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Leave room.
	 *
	 * @param player the player who is leaving
	 * @return the client handler of the new admin, if the previous one is the player who left
	 */
	public ClientHandler leaveRoom(ClientHandler player) {//ritorna il nuovo admin, se l'admin lefta
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).isEquals(player)) {
				if (players.get(i).isEquals(admin)) {
					players.remove(i);
					this.admin = players.get(0);
					return this.admin;
				} else
					players.remove(i);
			}
		return null;
	}

	/**
	 * Adds a player to the room.
	 *
	 * @param pl the player to be added
	 * @return true, if successful. false if the room is full
	 */
	public boolean addPlayer(ClientHandler pl){
		if(isFull())
			return false;
		players.add(pl);
		return true;
	}
	
	/**
	 * Checks if the room is full.
	 *
	 * @return true, if it is full
	 */
	public boolean isFull(){
		if(this.maxPlayers==this.players.size())
			return true;
		return false;
	}
	
	/**
	 * Checks if a specific player is in the room.
	 *
	 * @param player the player
	 * @return true, if found
	 */
	public boolean hasJoined(ClientHandler player){
		if(players.contains(player))
			return true;
		return false;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the new map XML
	 */
	public void setMap(String map){//non lo metto nel costruttore perchè va passata serializzata, credo
		this.rawMap = map;
		this.defaultMap = false;
	}
	

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<ClientHandler> getPlayers() {
		return players;
	}

	/**
	 * Gets the admin.
	 *
	 * @return the admin
	 */
	public ClientHandler getAdmin() {
		return admin;
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
	 * Gets the min players.
	 *
	 * @return the min players
	 */
	public int getMinPlayers() {
		return minPlayers;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public RoomState getState(){
		return this.status;
	}
	
	/**
	 * Checks if is default map.
	 *
	 * @return true, if is default map
	 */
	public boolean isDefaultMap() {
		return defaultMap;
	}

	/**
	 * Gets the game handler.
	 *
	 * @return the game handler
	 */
	public GameHandler getGameHandler() {
		return gameHandler;
	}

	
	
}
