package com.client.model;

import java.util.ArrayList;

import com.client.ClientObservable;

public class GameStatus extends ClientObservable{

	
	/*Game Status*/
	private int playersQty;
	private int actualPlayer;
	private int turn;
	private boolean finalTurn;
	
	/*Players Status*/
	private int[] playerEmporiums;
	private int[] playerAssistants;
	private int[] playerCoins;
	private int[] playerPoints;
	private int[] playerCards;
	private int[] usedPermits;
	private int[] pawnPosition;
	private ArrayList<ArrayList<String>> activePermits;
	private ArrayList<ArrayList<BonusInfo>> PlayerKingBonus;
	private ArrayList<ArrayList<String>> hand;
	private String drawnCard;
	
	/*Map Status*/
	private String[][] map = new String[15][5];
	private ArrayList<String> cityNames;
	private ArrayList<ArrayList<String>> closeCities;
	private ArrayList<ArrayList<BonusInfo>> cityTokens;
	private ArrayList<ArrayList<Integer>> cityEmporiums; // Cities: A = 1, B = 2, ..., O = 15; Emporium's owner: Player1 = 1, etc...
	private ArrayList<ArrayList<String>> balcony; //Sea:0, Hill:1, Mountain:2, King:3; 2nd dimentsion: from 0 to 3 strings that identify the color	
	private int kingLocation; //A-O -> 1-15
	private ArrayList<String> councilorPool;
	private int assistantsPool;
	private ArrayList<BonusInfo> kingBonus;
	private ArrayList<BonusInfo> colorBonus;
	private PermitsDeckInfo[] permitsDeck;
	private ArrayList<BonusInfo> nobilityTrack;
	
	/*Market*/
	private ArrayList<String> onSaleItems;
	private ArrayList<Integer> ItemPrice;
	
	
	public GameStatus(){
		//importer method
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
	 * @return the actualPlayer
	 */
	public int getActualPlayer() {
		return actualPlayer;
	}
	/**
	 * @param actualPlayer the actualPlayer to set
	 */
	public void setActualPlayer(int actualPlayer) {
		this.actualPlayer = actualPlayer;
	}
	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}
	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
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
	 * @return the playerEmporiums
	 */
	public int[] getPlayerEmporiums() {
		return playerEmporiums;
	}
	/**
	 * @param playerEmporiums the playerEmporiums to set
	 */
	public void setPlayerEmporiums(int[] playerEmporiums) {
		this.playerEmporiums = playerEmporiums;
	}
	/**
	 * @return the playerAssistants
	 */
	public int[] getPlayerAssistants() {
		return playerAssistants;
	}
	/**
	 * @param playerAssistants the playerAssistants to set
	 */
	public void setPlayerAssistants(int[] playerAssistants) {
		this.playerAssistants = playerAssistants;
	}
	/**
	 * @return the playerCoins
	 */
	public int[] getPlayerCoins() {
		return playerCoins;
	}
	/**
	 * @param playerCoins the playerCoins to set
	 */
	public void setPlayerCoins(int[] playerCoins) {
		this.playerCoins = playerCoins;
	}
	/**
	 * @return the playerPoints
	 */
	public int[] getPlayerPoints() {
		return playerPoints;
	}
	/**
	 * @param playerPoints the playerPoints to set
	 */
	public void setPlayerPoints(int[] playerPoints) {
		this.playerPoints = playerPoints;
	}
	/**
	 * @return the playerCards
	 */
	public int[] getPlayerCards() {
		return playerCards;
	}
	/**
	 * @param playerCards the playerCards to set
	 */
	public void setPlayerCards(int[] playerCards) {
		this.playerCards = playerCards;
	}
	/**
	 * @return the usedPermits
	 */
	public int[] getUsedPermits() {
		return usedPermits;
	}
	/**
	 * @param usedPermits the usedPermits to set
	 */
	public void setUsedPermits(int[] usedPermits) {
		this.usedPermits = usedPermits;
	}
	/**
	 * @return the pawnPosition
	 */
	public int[] getPawnPosition() {
		return pawnPosition;
	}
	/**
	 * @param pawnPosition the pawnPosition to set
	 */
	public void setPawnPosition(int[] pawnPosition) {
		this.pawnPosition = pawnPosition;
	}
	/**
	 * @return the activePermits
	 */
	public ArrayList<ArrayList<String>> getActivePermits() {
		return activePermits;
	}
	/**
	 * @param activePermits the activePermits to set
	 */
	public void setActivePermits(ArrayList<ArrayList<String>> activePermits) {
		this.activePermits = activePermits;
	}
	/**
	 * @return the playerKingBonus
	 */
	public ArrayList<ArrayList<BonusInfo>> getPlayerKingBonus() {
		return PlayerKingBonus;
	}
	/**
	 * @param playerKingBonus the playerKingBonus to set
	 */
	public void setPlayerKingBonus(ArrayList<ArrayList<BonusInfo>> playerKingBonus) {
		PlayerKingBonus = playerKingBonus;
	}
	/**
	 * @return the hand
	 */
	public ArrayList<ArrayList<String>> getHand() {
		return hand;
	}
	/**
	 * @param hand the hand to set
	 */
	public void setHand(ArrayList<ArrayList<String>> hand) {
		this.hand = hand;
	}
	/**
	 * @return the drawnCard
	 */
	public String getDrawnCard() {
		return drawnCard;
	}
	/**
	 * @param drawnCard the drawnCard to set
	 */
	public void setDrawnCard(String drawnCard) {
		this.drawnCard = drawnCard;
	}
	/**
	 * @return the cityEmporiums
	 */
	public ArrayList<ArrayList<Integer>> getCityEmporiums() {
		return cityEmporiums;
	}
	/**
	 * @param cityEmporiums the cityEmporiums to set
	 */
	public void setCityEmporiums(ArrayList<ArrayList<Integer>> cityEmporiums) {
		this.cityEmporiums = cityEmporiums;
	}
	/**
	 * @return the balcony
	 */
	public ArrayList<ArrayList<String>> getBalcony() {
		return balcony;
	}
	/**
	 * @param balcony the balcony to set
	 */
	public void setBalcony(ArrayList<ArrayList<String>> balcony) {
		this.balcony = balcony;
	}
	/**
	 * @return the kingLocation
	 */
	public int getKingLocation() {
		return kingLocation;
	}
	/**
	 * @param kingLocation the kingLocation to set
	 */
	public void setKingLocation(int kingLocation) {
		this.kingLocation = kingLocation;
	}
	/**
	 * @return the councilorPool
	 */
	public ArrayList<String> getCouncilorPool() {
		return councilorPool;
	}
	/**
	 * @param councilorPool the councilorPool to set
	 */
	public void setCouncilorPool(ArrayList<String> councilorPool) {
		this.councilorPool = councilorPool;
	}
	/**
	 * @return the assistantsPool
	 */
	public int getAssistantsPool() {
		return assistantsPool;
	}
	/**
	 * @param assistantsPool the assistantsPool to set
	 */
	public void setAssistantsPool(int assistantsPool) {
		this.assistantsPool = assistantsPool;
	}
	/**
	 * @return the kingBonus
	 */
	public ArrayList<BonusInfo> getKingBonus() {
		return kingBonus;
	}
	/**
	 * @param kingBonus the kingBonus to set
	 */
	public void setKingBonus(ArrayList<BonusInfo> kingBonus) {
		this.kingBonus = kingBonus;
	}
	/**
	 * @return the colorBonus
	 */
	public ArrayList<BonusInfo> getColorBonus() {
		return colorBonus;
	}
	/**
	 * @param colorBonus the colorBonus to set
	 */
	public void setColorBonus(ArrayList<BonusInfo> colorBonus) {
		this.colorBonus = colorBonus;
	}
	/**
	 * @return the permitsDeck
	 */
	public PermitsDeckInfo[] getPermitsDeck() {
		return permitsDeck;
	}
	/**
	 * @param permitsDeck the permitsDeck to set
	 */
	public void setPermitsDeck(PermitsDeckInfo[] permitsDeck) {
		this.permitsDeck = permitsDeck;
	}
	/**
	 * @return the nobilityTrack
	 */
	public ArrayList<BonusInfo> getNobilityTrack() {
		return nobilityTrack;
	}
	/**
	 * @param nobilityTrack the nobilityTrack to set
	 */
	public void setNobilityTrack(ArrayList<BonusInfo> nobilityTrack) {
		this.nobilityTrack = nobilityTrack;
	}
	/**
	 * @return the onSaleItems
	 */
	public ArrayList<String> getOnSaleItems() {
		return onSaleItems;
	}
	/**
	 * @param onSaleItems the onSaleItems to set
	 */
	public void setOnSaleItems(ArrayList<String> onSaleItems) {
		this.onSaleItems = onSaleItems;
	}
	/**
	 * @return the itemPrice
	 */
	public ArrayList<Integer> getItemPrice() {
		return ItemPrice;
	}
	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(ArrayList<Integer> itemPrice) {
		ItemPrice = itemPrice;
	}


	/**
	 * @return the closeCities
	 */
	public ArrayList<ArrayList<String>> getCloseCities() {
		return closeCities;
	}


	/**
	 * @param closeCities the closeCities to set
	 */
	public void setCloseCities(ArrayList<ArrayList<String>> closeCities) {
		this.closeCities = closeCities;
	}


	/**
	 * @return the cityNames
	 */
	public ArrayList<String> getCityNames() {
		return cityNames;
	}


	/**
	 * @param cityNames the cityNames to set
	 */
	public void setCityNames(ArrayList<String> cityNames) {
		this.cityNames = cityNames;
	}


	/**
	 * @return the cityTokens
	 */
	public ArrayList<ArrayList<BonusInfo>> getCityTokens() {
		return cityTokens;
	}


	/**
	 * @param cityTokens the cityTokens to set
	 */
	public void setCityTokens(ArrayList<ArrayList<BonusInfo>> cityTokens) {
		this.cityTokens = cityTokens;
	}
	
	
	
}
