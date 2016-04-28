package gamelogic ;

import java.util.ArrayList;

import board.Map;
import market.Market;

/**
 * This is the main controller of the game 
 */

public class Game {

	private static final int MAXPLAYERS = 8;
	private static final int COINOFFSET = 10;
	private static final int ASSISTOFFSET = 1;
	private ArrayList<Player> players;
	private int playersQty;
	private Map map;
	private Market market;
	private Player actualPlayer;
	private boolean finalTurn;
	
	/**
	 * Constructs a new object of type Game
	 * @param playersQty how many players are playing
	 * @param map the map of the game
	 * @throws Exception if the number of players is less than 2 or greater than {@value #MAXPLAYERS}
	 */
	
	public Game(int playersQty) {
		this.playersQty = playersQty;
		this.initializeObjects();
		
		//exception
		if(playersQty < 2 || playersQty > MAXPLAYERS)
			throw new IllegalArgumentException("The quantity of players indicated is not in the permitted range");
	}
	
	/**
	 * Initializes all the objects needed for the map
	 */
	
	private void initializeObjects() {
		market = new Market (); // Will be implemented soon...
		players = new ArrayList<Player>(playersQty);
				
		//Players initialize
		for(int i=0; i<playersQty; i++){
			players.add(new Player());
		}
		actualPlayer = players.get(0);
		
		//Map
		map = new Map(players);
		
		//Player construction
		for(int i=0; i<playersQty; i++){
			if(playersQty>4)
				players.get(i).setCoins(i+(COINOFFSET-(playersQty-4)/2));
			else
				players.get(i).setCoins(i+COINOFFSET);
			players.get(i).setAvailableEmporiums(map.getPlayerEmporiums());
			players.get(i).setScore(0);
			players.get(i).addAssistant(i+ASSISTOFFSET);
			players.get(i).addPolitics(map.getPoliticsDeck().draw(6));
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
	
}

