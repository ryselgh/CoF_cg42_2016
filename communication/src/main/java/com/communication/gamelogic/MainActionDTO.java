package com.communication.gamelogic ;

import java.io.Serializable;


/**
 * The Class MainActionDTO.
 */
public class MainActionDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9134405533631894152L;
	
	/** The action counter. */
	private int actionCounter;
	
	/** The game(DTO). */
	private GameDTO game;
	
	/**
	 * Gets the action counter.
	 *
	 * @return the actionCounter
	 */
	public int getActionCounter() {
		return actionCounter;
	}
	
	/**
	 * Sets the action counter.
	 *
	 * @param actionCounter the actionCounter to set
	 */
	public void setActionCounter(int actionCounter) {
		this.actionCounter = actionCounter;
	}
	
	/**
	 * Gets the game(DTO).
	 *
	 * @return the game(DTO)
	 */
	public GameDTO getGame() {
		return game;
	}
	
	/**
	 * Sets the game(DTO).
	 *
	 * @param game the game(DTO) to set
	 */
	public void setGame(GameDTO game) {
		this.game = game;
	}
	
}

