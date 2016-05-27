package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.gamelogic.GameDTO;

public class BuyMainActionDTO extends ActionDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4937580050032786292L;
	private GameDTO game;
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
