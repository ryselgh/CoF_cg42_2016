package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;

public class ObtainPermitDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2281940547406660429L;
	private GameDTO game;
	private PoliticsCardDTO[] politics;
	private int regionIndex;
    private int slot;
	int counter = 0;
    int jollycnt = 0;
    private ArrayList<String> errors;
	private boolean disable = false;
	
	
	/**
	 * @return the game
	 */
	public GameDTO getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(GameDTO game) {
		this.game = game;
	}
	/**
	 * @return the politics
	 */
	public PoliticsCardDTO[] getPolitics() {
		return politics;
	}
	/**
	 * @param politics the politics to set
	 */
	public void setPolitics(PoliticsCardDTO[] politics) {
		this.politics = politics;
	}
	/**
	 * @return the regionIndex
	 */
	public int getRegionIndex() {
		return regionIndex;
	}
	/**
	 * @param regionIndex the regionIndex to set
	 */
	public void setRegionIndex(int regionIndex) {
		this.regionIndex = regionIndex;
	}
	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}
	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
	/**
	 * @return the jollycnt
	 */
	public int getJollycnt() {
		return jollycnt;
	}
	/**
	 * @param jollycnt the jollycnt to set
	 */
	public void setJollycnt(int jollycnt) {
		this.jollycnt = jollycnt;
	}
	/**
	 * @return the errors
	 */
	public ArrayList<String> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}
	/**
	 * @return the disable
	 */
	public boolean isDisable() {
		return disable;
	}
	/**
	 * @param disable the disable to set
	 */
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
    
}
