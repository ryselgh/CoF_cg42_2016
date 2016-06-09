package com.communication.gamelogic;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.*;
import com.communication.decks.*;


public class PlayerDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2311120497268898556L;
	private ArrayList<EmporiumDTO> availableEmporiums;
	private ArrayList<AssistantDTO> availableAssistants;
	private ArrayList<PermitsCardDTO> permits;
	private ArrayList<PoliticsCardDTO> hand;
	private ArrayList<BonusCardDTO> bonusCards;
	private PawnDTO pawn;
	private int coins;
	private int score;
	private String playerID;
	
	/**
	 * @return the availableEmporiums
	 */
	public ArrayList<EmporiumDTO> getAvailableEmporiums() {
		return availableEmporiums;
	}
	/**
	 * @param availableEmporiums the availableEmporiums to set
	 */
	public void setAvailableEmporiums(ArrayList<EmporiumDTO> availableEmporiums) {
		this.availableEmporiums = availableEmporiums;
	}
	/**
	 * @return the availableAssistants
	 */
	public ArrayList<AssistantDTO> getAvailableAssistants() {
		return availableAssistants;
	}
	/**
	 * @param availableAssistants the availableAssistants to set
	 */
	public void setAvailableAssistants(ArrayList<AssistantDTO> availableAssistants) {
		this.availableAssistants = availableAssistants;
	}
	/**
	 * @return the permits
	 */
	public ArrayList<PermitsCardDTO> getPermits() {
		return permits;
	}
	/**
	 * @param permits the permits to set
	 */
	public void setPermits(ArrayList<PermitsCardDTO> permits) {
		this.permits = permits;
	}
	/**
	 * @return the hand
	 */
	public ArrayList<PoliticsCardDTO> getHand() {
		return hand;
	}
	/**
	 * @param hand the hand to set
	 */
	public void setHand(ArrayList<PoliticsCardDTO> hand) {
		this.hand = hand;
	}
	/**
	 * @return the bonusCards
	 */
	public ArrayList<BonusCardDTO> getBonusCards() {
		return bonusCards;
	}
	/**
	 * @param bonusCards the bonusCards to set
	 */
	public void setBonusCards(ArrayList<BonusCardDTO> bonusCards) {
		this.bonusCards = bonusCards;
	}
	/**
	 * @return the pawn
	 */
	public PawnDTO getPawn() {
		return pawn;
	}
	/**
	 * @param pawn the pawn to set
	 */
	public void setPawn(PawnDTO pawn) {
		this.pawn = pawn;
	}
	/**
	 * @return the coins
	 */
	public int getCoins() {
		return coins;
	}
	/**
	 * @param coins the coins to set
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the playerID
	 */
	public String getPlayerID() {
		return playerID;
	}
	/**
	 * @param playerID the playerID to set
	 */
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	
}
