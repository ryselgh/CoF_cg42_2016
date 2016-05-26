package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;

public class BuildDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6895733758879038L;
	private CityDTO city;
	private PermitsCardDTO permit;
	private GameDTO game;
	private ArrayList<String> errors;
	private boolean disable = false;
	
	
	/**
	 * @return the city
	 */
	public CityDTO getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(CityDTO city) {
		this.city = city;
	}
	/**
	 * @return the permit
	 */
	public PermitsCardDTO getPermit() {
		return permit;
	}
	/**
	 * @param permit the permit to set
	 */
	public void setPermit(PermitsCardDTO permit) {
		this.permit = permit;
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
