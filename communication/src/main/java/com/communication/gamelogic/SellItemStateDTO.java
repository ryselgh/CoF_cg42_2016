package com.communication.gamelogic;

import java.io.Serializable;

public class SellItemStateDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2600302059014877119L;
	private GameDTO game;

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
	
}
