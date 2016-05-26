package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.gamelogic.GameDTO;

public class ChangeCardsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1141199441417494594L;
	private GameDTO game;
	private int balconyIndex;
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
	 * @return the balconyIndex
	 */
	public int getBalconyIndex() {
		return balconyIndex;
	}
	/**
	 * @param balconyIndex the balconyIndex to set
	 */
	public void setBalconyIndex(int balconyIndex) {
		this.balconyIndex = balconyIndex;
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
