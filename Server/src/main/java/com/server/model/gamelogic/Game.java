package com.server.model.gamelogic ;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.server.model.board.City;
import com.server.model.board.Map;
import com.server.model.market.Market;

/**
 * This is the main controller of the game 
 */

public class Game {

	private static final int MAXPLAYERS = 8;
	private static final int COINOFFSET = 10;
	private static final int ASSISTOFFSET = 1;
	private static final int INITIALCARDS = 6;
	private ArrayList<Player> players;
	private int playersQty;
	private Map map;
	private Market market;
	private Player actualPlayer;
	private boolean finalTurn;
	private boolean defaultMap;
	private Document rawMap;
	private MainAction mainAction;
	private SpeedAction speedAction;
	private GraphMap graphMap;
	
	/**
	 * Constructs a new object of type Game
	 * @param playersQty how many players are playing
	 * @param map the map of the game
	 * @param defaultMap set to true if you want to load the default map
	 * @throws Exception if the number of players is less than 2 or greater than {@value #MAXPLAYERS}
	 */
	
	public Game(int playersQty, boolean defaultMap, Document rawMap) {
		this.playersQty = playersQty;
		this.defaultMap = defaultMap;
		this.rawMap = rawMap;
		mainAction = new MainAction(this);
		speedAction = new SpeedAction(this);
		this.initializeObjects();
		
		//exception
		if(playersQty < 2 || playersQty > MAXPLAYERS)
			throw new IllegalArgumentException("The quantity of players indicated is not in the permitted range");
	}
	
	/**
	 * Initializes all the objects needed for the game
	 */
	
	private void initializeObjects() {
		market = new Market (); // Will be implemented soon...
		players = new ArrayList<Player>(playersQty);
				
		//Players initialize
		for(int i=0; i<playersQty; i++){
			players.add(new Player(i+1));
		}
		actualPlayer = players.get(0);
		
		//Map
		map = new Map(players.toArray(new Player[players.size()]),defaultMap,rawMap);
		graphMap = new GraphMap(map);
		
		
		//Player construction
		for(int i=0; i<playersQty; i++){
			if(playersQty>4)
				players.get(i).setCoins(i+(COINOFFSET-(playersQty-4)/2));
			else
				players.get(i).setCoins(i+COINOFFSET);
			players.get(i).setAvailableEmporiums(map.getPlayerEmporiums(i));
			players.get(i).setScore(0);
			players.get(i).addAssistant(this.getMap().getAssistant(i+ASSISTOFFSET));
			for(int j=0;j<INITIALCARDS;j++)
				players.get(i).addPolitics(map.getPoliticsDeck().draw());
		}
		
	}
	
	/**
	 * @return an array of Player(s)
	 */
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Select a specific player by its number
	 * @param id the integer that identifies the wanted player
	 * @return a specific Player
	 */
	
	public Player getThatPlayer(int id){
		return this.players.get(id);
	}
	
	/**
	 * The market
	 */
	
	public Market getMarket() {
		// TODO implement me
		return null;	
	}
	
	/**
	 * @return the player that is playing right now
	 */
	
	public Player getActualPlayer() {
		return actualPlayer;	
	}
	
	/**
	 * @param finalTurn set to true when a player places its last emporium
	 */
	
	public void setFinalTurn(boolean finalTurn) {
		this.finalTurn = finalTurn;
	}
	
	/**
	 * @param finalTurn true if the actualPlayer have placed his last emporium
	 */
	
	public boolean isFinalTurn() {
		return this.finalTurn;
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
	public void setActualPlayer(int index)
	{
		this.actualPlayer = this.players.get(index);
	}
	
	public int getActualPlayerIndex(){
		for(int i=0;i<players.size();i++)
			if(players.get(i).equals(actualPlayer))
				return i;
		return -1;
				
	}
	
	/**
	 * @return the mainAction
	 */
	public MainAction getMainAction() {
		return mainAction;
	}

	/**
	 * @return the speedAction
	 */
	public SpeedAction getSpeedAction() {
		return speedAction;
	}

	/**
	 * @return the graphMap
	 */
	public GraphMap getGraphMap() {
		return graphMap;
	}
	
	public City getCityFromName(String name){
		for(City c : this.getMap().getCity())
			if(c.getName().equals(name))
				return c;
		return null;
	}
	
}