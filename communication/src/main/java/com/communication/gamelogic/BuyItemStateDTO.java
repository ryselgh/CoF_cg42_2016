package com.communication.gamelogic;

import java.io.Serializable;

public class BuyItemStateDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3296873352821554650L;
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
