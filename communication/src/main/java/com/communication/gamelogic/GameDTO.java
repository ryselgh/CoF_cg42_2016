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
	
	/** The list of players(DTO). */
	private ArrayList<PlayerDTO> players;
	
	/** The players qty. */
	private int playersQty;
	
	/** The map(DTO). */
	private MapDTO map;
	
	/** The market(DTO). */
	private MarketDTO market;
	
	/** The actual player(DTO). */
	private PlayerDTO actualPlayer;
	
	/** The final turn. */
	private boolean finalTurn;
	
	/** The default map. */
	private boolean defaultMap;
	
	/** The main action(DTO). */
	private MainActionDTO mainAction;
	
	/** The speed action(DTO). */
	private SpeedActionDTO speedAction;
	
	/**
	 * Gets the list of players(DTO).
	 *
	 * @return the list of players(DTO)
	 */
	public ArrayList<PlayerDTO> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the list of players(DTO).
	 *
	 * @param players the list of players(DTO) to set
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
	 * Sets the players quantity.
	 *
	 * @param playersQty the playersQuantity to set
	 */
	public void setPlayersQty(int playersQty) {
		this.playersQty = playersQty;
	}
	
	/**
	 * Gets the map(DTO).
	 *
	 * @return the map(DTO)
	 */
	public MapDTO getMap() {
		return map;
	}
	
	/**
	 * Sets the map(DTO).
	 *
	 * @param map the map(DTO) to set
	 */
	public void setMap(MapDTO map) {
		this.map = map;
	}
	
	/**
	 * Gets the market(DTO).
	 *
	 * @return the market(DTO)
	 */
	public MarketDTO getMarket() {
		return market;
	}
	
	/**
	 * Sets the market(DTO).
	 *
	 * @param market the market(DTO) to set
	 */
	public void setMarket(MarketDTO market) {
		this.market = market;
	}
	
	/**
	 * Gets the actual player(DTO).
	 *
	 * @return the actualPlayer(DTO)
	 */
	public PlayerDTO getActualPlayer() {
		return actualPlayer;
	}
	
	/**
	 * Sets the actual player(DTO).
	 *
	 * @param actualPlayer the actualPlayer(DTO) to set
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
	 * Checks if is default map.
	 *
	 * @return the defaultMap
	 */
	public boolean isDefaultMap() {
		return defaultMap;
	}
	
	/**
	 * Sets the default map.
	 *
	 * @param defaultMap the defaultMap to set
	 */
	public void setDefaultMap(boolean defaultMap) {
		this.defaultMap = defaultMap;
	}
	
	/**
	 * Gets the main action(DTO).
	 *
	 * @return the mainAction(DTO)
	 */
	public MainActionDTO getMainAction() {
		return mainAction;
	}
	
	/**
	 * Sets the main action(DTO).
	 *
	 * @param mainAction the mainAction(DTO) to set
	 */
	public void setMainAction(MainActionDTO mainAction) {
		this.mainAction = mainAction;
	}
	
	/**
	 * Gets the speed action(DTO).
	 *
	 * @return the speedAction(DTO)
	 */
	public SpeedActionDTO getSpeedAction() {
		return speedAction;
	}
	
	/**
	 * Sets the speed action(DTO).
	 *
	 * @param speedAction the speedAction(DTO) to set
	 */
	public void setSpeedAction(SpeedActionDTO speedAction) {
		this.speedAction = speedAction;
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
	
}