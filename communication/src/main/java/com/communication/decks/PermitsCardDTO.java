package com.communication.decks ;

import java.io.Serializable;

import com.communication.board.BonusDTO;



public class PermitsCardDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6765039719538532134L;
	private BonusDTO[] bonuses;
	private String[] cityLetter;
	private boolean faceDown;
	
	
	/**
	 * @return the bonuses
	 */
	public BonusDTO[] getBonuses() {
		return bonuses;
	}
	/**
	 * @param bonuses the bonuses to set
	 */
	public void setBonuses(BonusDTO[] bonuses) {
		this.bonuses = bonuses;
	}
	/**
	 * @return the cityLetter
	 */
	public String[] getCityLetter() {
		return cityLetter;
	}
	/**
	 * @param cityLetter the cityLetter to set
	 */
	public void setCityLetter(String[] cityLetter) {
		this.cityLetter = cityLetter;
	}
	/**
	 * @return the faceDown
	 */
	public boolean isFaceDown() {
		return faceDown;
	}
	/**
	 * @param faceDown the faceDown to set
	 */
	public void setFaceDown(boolean faceDown) {
		this.faceDown = faceDown;
	}
	
}

