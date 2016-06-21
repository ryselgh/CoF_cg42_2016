package com.communication.decks ;

import java.io.Serializable;

import com.communication.board.BonusDTO;




/**
 * The Class PermitsCardDTO.
 */
public class PermitsCardDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6765039719538532134L;
	
	/** The  bonuses. */
	private BonusDTO[] bonuses;
	
	/** The city letters. */
	private String[] cityLetter;
	
	/** The face down. */
	private boolean faceDown;
	
	
	/**
	 * Gets the bonuses(DTO).
	 *
	 * @return the bonuses(DTO)
	 */
	public BonusDTO[] getBonuses() {
		return bonuses;
	}
	
	/**
	 * Sets the bonuses.(DTO)
	 *
	 * @param bonuses the bonuses(DTO) to set
	 */
	public void setBonuses(BonusDTO[] bonuses) {
		this.bonuses = bonuses;
	}
	
	/**
	 * Gets the city letters.
	 *
	 * @return the cityLetters
	 */
	public String[] getCityLetter() {
		return cityLetter;
	}
	
	/**
	 * Sets the city letters.
	 *
	 * @param cityLetter the cityLetters to set
	 */
	public void setCityLetter(String[] cityLetter) {
		this.cityLetter = cityLetter;
	}
	
	/**
	 * Checks if is face down.
	 *
	 * @return true if it's facedown
	 */
	public boolean isFaceDown() {
		return faceDown;
	}
	
	/**
	 * Sets  face down.
	 *
	 * @param faceDown the boolean to set
	 */
	public void setFaceDown(boolean faceDown) {
		this.faceDown = faceDown;
	}
	
}

