package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.board.*;

public class SatisfyKingDTO extends ActionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -128520425439341078L;
	private PoliticsCardDTO[] politics; 
	private CityDTO destination;
	
	
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
	 * @return the destination
	 */
	public CityDTO getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(CityDTO destination) {
		this.destination = destination;
	}
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
	private GameDTO game;
	private int counter = 0;
    private int jollycnt = 0;
    private ArrayList<String> errors;
    private boolean disable;
    
}
