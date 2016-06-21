package com.communication.gamelogic;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.*;
import com.communication.decks.*;


// TODO: Auto-generated Javadoc
/**
 * The Class PlayerDTO.
 */
public class PlayerDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2311120497268898556L;
	
	/** The list of available emporiums(DTO). */
	private ArrayList<EmporiumDTO> availableEmporiums;
	
	/** The list of available assistants(DTO). */
	private ArrayList<AssistantDTO> availableAssistants;
	
	/** The list of permits(DTO). */
	private ArrayList<PermitsCardDTO> permits;
	
	/** The hand(DTO). */
	private ArrayList<PoliticsCardDTO> hand;
	
	/** The list of bonus cards(DTO). */
	private ArrayList<BonusCardDTO> bonusCards;
	
	/** The pawn(DTO). */
	private PawnDTO pawn;
	
	/** The coins. */
	private int coins;
	
	/** The score. */
	private int score;
	
	/** The player id. */
	private String playerID;
	
	/**
	 * Gets the list of available emporiums(DTO).
	 *
	 * @return the list of availableEmporiums(DTO)
	 */
	public ArrayList<EmporiumDTO> getAvailableEmporiums() {
		return availableEmporiums;
	}
	
	/**
	 * Sets the list of available emporiums(DTO).
	 *
	 * @param availableEmporiums the list of availableEmporiums(DTO) to set
	 */
	public void setAvailableEmporiums(ArrayList<EmporiumDTO> availableEmporiums) {
		this.availableEmporiums = availableEmporiums;
	}
	
	/**
	 * Gets the list of available assistants(DTO).
	 *
	 * @return the list of availableAssistants(DTO)
	 */
	public ArrayList<AssistantDTO> getAvailableAssistants() {
		return availableAssistants;
	}
	
	/**
	 * Sets the list of available assistants(DTO).
	 *
	 * @param availableAssistants the list of availableAssistants (DTO)to set
	 */
	public void setAvailableAssistants(ArrayList<AssistantDTO> availableAssistants) {
		this.availableAssistants = availableAssistants;
	}
	
	/**
	 * Gets the list of permitsCards(DTO).
	 *
	 * @return the list of permitsCard(DTO)
	 */
	public ArrayList<PermitsCardDTO> getPermits() {
		return permits;
	}
	
	/**
	 * Sets the list of permitsCards(DTO).
	 *
	 * @param permits the list of permitsCards(DTO) to set
	 */
	public void setPermits(ArrayList<PermitsCardDTO> permits) {
		this.permits = permits;
	}
	
	/**
	 * Gets the hand(DTO).
	 *
	 * @return the hand(DTO)
	 */
	public ArrayList<PoliticsCardDTO> getHand() {
		return hand;
	}
	
	/**
	 * Sets the hand(DTO).
	 *
	 * @param hand the hand(DTO) to set
	 */
	public void setHand(ArrayList<PoliticsCardDTO> hand) {
		this.hand = hand;
	}
	
	/**
	 * Gets the bonus cards(DTO).
	 *
	 * @return the bonusCards(DTO)
	 */
	public ArrayList<BonusCardDTO> getBonusCards() {
		return bonusCards;
	}
	
	/**
	 * Sets the bonus cards(DTO).
	 *
	 * @param bonusCards the bonusCards(DTO) to set
	 */
	public void setBonusCards(ArrayList<BonusCardDTO> bonusCards) {
		this.bonusCards = bonusCards;
	}
	
	/**
	 * Gets the pawn(DTO).
	 *
	 * @return the pawn(DTO)
	 */
	public PawnDTO getPawn() {
		return pawn;
	}
	
	/**
	 * Sets the pawn(DTO).
	 *
	 * @param pawn the pawn(DTO) to set
	 */
	public void setPawn(PawnDTO pawn) {
		this.pawn = pawn;
	}
	
	/**
	 * Gets the coins.
	 *
	 * @return the coins
	 */
	public int getCoins() {
		return coins;
	}
	
	/**
	 * Sets the coins.
	 *
	 * @param coins the coins to set
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Gets the player id.
	 *
	 * @return the playerID
	 */
	public String getPlayerID() {
		return playerID;
	}
	
	/**
	 * Sets the player id.
	 *
	 * @param playerID the playerID to set
	 */
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	
}
