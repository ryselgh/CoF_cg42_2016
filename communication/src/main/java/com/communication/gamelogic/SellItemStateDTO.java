package com.communication.gamelogic;

import java.io.Serializable;


/**
 * The Class SellItemStateDTO.
 */
public class SellItemStateDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2600302059014877119L;
	
	/** The game(DTO). */
	private GameDTO game;

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
