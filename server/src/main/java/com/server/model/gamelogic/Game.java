package com.server.model.gamelogic ;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.communication.gamelogic.GameDTO;
import com.communication.gamelogic.PlayerDTO;
import com.server.model.board.City;
import com.server.model.board.Map;
import com.server.model.market.Market;

// TODO: Auto-generated Javadoc
/**
 * This is the main controller of the game.
 */

public class Game {
	
	
	/** The Constant COINOFFSET. */
	private static final int COINOFFSET = 10;
	
	/** The Constant ASSISTOFFSET. */
	private static final int ASSISTOFFSET = 1;
	
	/** The Constant INITIALCARDS. */
	private static final int INITIALCARDS = 6;
	
	/** The players. */
	private ArrayList<Player> players;
	
	/** The players qty. */
	private int playersQty;
	
	/** The map. */
	private Map map;
	
	/** The market. */
	private Market market;
	
	/** The actual player. */
	private Player actualPlayer;
	
	/** The final turn. */
	private boolean finalTurn;
	
	/** The default map. */
	private boolean defaultMap;
	
	/** The raw map. */
	private String rawMap;
	
	/** The graph map. */
	private GraphMap graphMap;
	
	/** The client names. */
	private String[] clientNames;
	
	/**
	 * Constructs a new object of type Game.
	 *
	 * @param playersQty how many players are playing
	 * @param defaultMap set to true if you want to load the default map
	 * @param rawMap the raw map
	 * @param clientNames the client names
	 */
	
	public Game(int playersQty, boolean defaultMap, String rawMap, String[] clientNames) {
		this.clientNames = clientNames;
		this.playersQty = playersQty;
		this.defaultMap = defaultMap;
		this.rawMap = rawMap;
		this.initializeObjects();
		
	}
	
	/**
	 * Initializes all the objects needed for the game.
	 */
	
	private void initializeObjects() {
		market = new Market (); 
		players = new ArrayList<Player>(playersQty);
				
		//Players initialize
		for(int i=0; i<playersQty; i++){
			players.add(new Player(clientNames[i]));
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
	 * Gets the players.
	 *
	 * @return an array of Player(s)
	 */
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Select a specific player by its number.
	 *
	 * @param id the integer that identifies the wanted player
	 * @return a specific Player
	 */
	
	public Player getThatPlayer(int id){
		return this.players.get(id);
	}
	
	/**
	 * The market.
	 *
	 * @return the market
	 */
	
	public Market getMarket() {
		return this.market;	
	}
	
	/**
	 * Gets the actual player.
	 *
	 * @return the player that is playing right now
	 */
	
	public Player getActualPlayer() {
		return actualPlayer;	
	}
	
	/**
	 * Sets the final turn.
	 *
	 * @param finalTurn set to true when a player places its last emporium
	 */
	
	public void setFinalTurn(boolean finalTurn) {
		this.finalTurn = finalTurn;
	}
	
	/**
	 * Checks if is final turn.
	 *
	 * @return true, if is final turn
	 */
	
	public boolean isFinalTurn() {
		return this.finalTurn;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Sets the actual player.
	 *
	 * @param index the new actual player
	 */
	public void setActualPlayer(int index)
	{
		this.actualPlayer = this.players.get(index);
	}
	
	/**
	 * Gets the actual player index.
	 *
	 * @return the actual player index
	 */
	public int getActualPlayerIndex(){
		for(int i=0;i<players.size();i++)
			if(players.get(i).equals(actualPlayer))
				return i;
		return -1;
				
	}
	
	/**
	 * Gets the graph map.
	 *
	 * @return the graphMap
	 */
	public GraphMap getGraphMap() {
		return graphMap;
	}
	
	/**
	 * Gets the city from name.
	 *
	 * @param name the name
	 * @return the city from name
	 */
	public City getCityFromName(String name){
		for(City c : this.getMap().getCity())
			if(c.getName().equals(name))
				return c;
		return null;
	}
	
	/**
	 * To dto.
	 *
	 * @return the game DTO
	 */
	public GameDTO toDto(){
		GameDTO gameDTO = new GameDTO();
		ArrayList<PlayerDTO> plsDTO = new ArrayList<PlayerDTO>();
		for(Player p: players)
			plsDTO.add(p.toDTO());
		gameDTO.setPlayers(plsDTO);
		gameDTO.setActualPlayer(actualPlayer.toDTO());
		gameDTO.setDefaultMap(defaultMap);
		gameDTO.setFinalTurn(finalTurn);
		gameDTO.setMap(map.toDTO(plsDTO));
		gameDTO.setMarket(market.toDTO());
		gameDTO.setPlayersQty(playersQty);
		return gameDTO;
	}
	
}