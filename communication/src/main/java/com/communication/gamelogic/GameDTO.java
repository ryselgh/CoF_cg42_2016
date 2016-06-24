package com.communication.gamelogic ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.MapDTO;
import com.communication.market.MarketDTO;

public class GameDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4159569654633234785L;
	private static final int MAXPLAYERS = 8;
	private static final int COINOFFSET = 10;
	private static final int ASSISTOFFSET = 1;
	private static final int INITIALCARDS = 6;
	private ArrayList<PlayerDTO> players;
	private int playersQty;
	private MapDTO map;
	private MarketDTO market;
	private PlayerDTO actualPlayer;
	private boolean finalTurn;
	private String mapName;
	private MainActionDTO mainAction;
	private SpeedActionDTO speedAction;
	/**The name of the player currently buying at the market. We need this because of the shuffled order*/
	private String marketCurrentPlayer="";
	/**
	 * @return the players
	 */
	public ArrayList<PlayerDTO> getPlayers() {
		return players;
	}
	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<PlayerDTO> players) {
		this.players = players;
	}
	/**
	 * @return the playersQty
	 */
	public int getPlayersQty() {
		return playersQty;
	}
	/**
	 * @param playersQty the playersQty to set
	 */
	public void setPlayersQty(int playersQty) {
		this.playersQty = playersQty;
	}
	/**
	 * @return the map
	 */
	public MapDTO getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(MapDTO map) {
		this.map = map;
	}
	/**
	 * @return the market
	 */
	public MarketDTO getMarket() {
		return market;
	}
	/**
	 * @param market the market to set
	 */
	public void setMarket(MarketDTO market) {
		this.market = market;
	}
	/**
	 * @return the actualPlayer
	 */
	public PlayerDTO getActualPlayer() {
		return actualPlayer;
	}
	/**
	 * @param actualPlayer the actualPlayer to set
	 */
	public void setActualPlayer(PlayerDTO actualPlayer) {
		this.actualPlayer = actualPlayer;
	}
	/**
	 * @return the finalTurn
	 */
	public boolean isFinalTurn() {
		return finalTurn;
	}
	/**
	 * @param finalTurn the finalTurn to set
	 */
	public void setFinalTurn(boolean finalTurn) {
		this.finalTurn = finalTurn;
	}
	/**
	 * @return the defaultMap
	 */
	public String getMapName() {
		return mapName;
	}
	/**
	 * @param defaultMap the defaultMap to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	/**
	 * @return the mainAction
	 */
	public MainActionDTO getMainAction() {
		return mainAction;
	}
	/**
	 * @param mainAction the mainAction to set
	 */
	public void setMainAction(MainActionDTO mainAction) {
		this.mainAction = mainAction;
	}
	/**
	 * @return the speedAction
	 */
	public SpeedActionDTO getSpeedAction() {
		return speedAction;
	}
	/**
	 * @param speedAction the speedAction to set
	 */
	public void setSpeedAction(SpeedActionDTO speedAction) {
		this.speedAction = speedAction;
	}
	/**
	 * @return the maxplayers
	 */
	public static int getMaxplayers() {
		return MAXPLAYERS;
	}
	/**
	 * @return the coinoffset
	 */
	public static int getCoinoffset() {
		return COINOFFSET;
	}
	/**
	 * @return the assistoffset
	 */
	public static int getAssistoffset() {
		return ASSISTOFFSET;
	}
	/**
	 * @return the initialcards
	 */
	public static int getInitialcards() {
		return INITIALCARDS;
	}
	public String getMarketCurrentPlayer() {
		return marketCurrentPlayer;
	}
	public void setMarketCurrentPlayer(String marketCurrentPlayer) {
		this.marketCurrentPlayer = marketCurrentPlayer;
	}
	
	
}