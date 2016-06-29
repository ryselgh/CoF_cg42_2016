package com.communication.gamelogic ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.MapDTO;
import com.communication.market.MarketDTO;


/**
 * The Class GameDTO.
 */
public class GameDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4159569654633234785L;
	
	/** The Constant MAXPLAYERS. */
	private static final int MAXPLAYERS = 8;
	
	/** The Constant COINOFFSET. */
	private static final int COINOFFSET = 10;
	
	/** The Constant ASSISTOFFSET. */
	private static final int ASSISTOFFSET = 1;
	
	/** The Constant INITIALCARDS. */
	private static final int INITIALCARDS = 6;
	
	/** The players. */
	private ArrayList<PlayerDTO> players;
	
	/** The players qty. */
	private int playersQty;
	
	/** The map. */
	private MapDTO map;
	
	/** The market. */
	private MarketDTO market;
	
	/** The actual player. */
	private PlayerDTO actualPlayer;
	
	/** The final turn. */
	private boolean finalTurn;
	
	/** The map name. */
	private String mapName;
	
	/**The name of the player currently buying at the market. We need this because of the shuffled order*/
	private String marketCurrentPlayer="";
	
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<PlayerDTO> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the players.
	 *
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<PlayerDTO> players) {
		this.players = players;
	}
	
	/**
	 * Gets the players qty.
	 *
	 * @return the playersQty
	 */
	public int getPlayersQty() {
		return playersQty;
	}
	
	/**
	 * Sets the players qty.
	 *
	 * @param playersQty the playersQty to set
	 */
	public void setPlayersQty(int playersQty) {
		this.playersQty = playersQty;
	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public MapDTO getMap() {
		return map;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map to set
	 */
	public void setMap(MapDTO map) {
		this.map = map;
	}
	
	/**
	 * Gets the market.
	 *
	 * @return the market
	 */
	public MarketDTO getMarket() {
		return market;
	}
	
	/**
	 * Sets the market.
	 *
	 * @param market the market to set
	 */
	public void setMarket(MarketDTO market) {
		this.market = market;
	}
	
	/**
	 * Gets the actual player.
	 *
	 * @return the actualPlayer
	 */
	public PlayerDTO getActualPlayer() {
		return actualPlayer;
	}
	
	/**
	 * Sets the actual player.
	 *
	 * @param actualPlayer the actualPlayer to set
	 */
	public void setActualPlayer(PlayerDTO actualPlayer) {
		this.actualPlayer = actualPlayer;
	}
	
	/**
	 * Checks if is final turn.
	 *
	 * @return the finalTurn
	 */
	public boolean isFinalTurn() {
		return finalTurn;
	}
	
	/**
	 * Sets the final turn.
	 *
	 * @param finalTurn the finalTurn to set
	 */
	public void setFinalTurn(boolean finalTurn) {
		this.finalTurn = finalTurn;
	}
	
	/**
	 * Gets the map name.
	 *
	 * @return the defaultMap
	 */
	public String getMapName() {
		return mapName;
	}
	
	/**
	 * Sets the map name.
	 *
	 * @param mapName the new map name
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	/**
	 * Gets the maxplayers.
	 *
	 * @return the maxplayers
	 */
	public static int getMaxplayers() {
		return MAXPLAYERS;
	}
	
	/**
	 * Gets the coinoffset.
	 *
	 * @return the coinoffset
	 */
	public static int getCoinoffset() {
		return COINOFFSET;
	}
	
	/**
	 * Gets the assistoffset.
	 *
	 * @return the assistoffset
	 */
	public static int getAssistoffset() {
		return ASSISTOFFSET;
	}
	
	/**
	 * Gets the initialcards.
	 *
	 * @return the initialcards
	 */
	public static int getInitialcards() {
		return INITIALCARDS;
	}
	
	/**
	 * Gets the market current player.
	 *
	 * @return the market current player
	 */
	public String getMarketCurrentPlayer() {
		return marketCurrentPlayer;
	}
	
	/**
	 * Sets the market current player.
	 *
	 * @param marketCurrentPlayer the new market current player
	 */
	public void setMarketCurrentPlayer(String marketCurrentPlayer) {
		this.marketCurrentPlayer = marketCurrentPlayer;
	}
	
	
}