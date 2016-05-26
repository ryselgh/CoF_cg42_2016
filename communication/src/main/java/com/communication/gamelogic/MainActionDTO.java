package com.communication.gamelogic ;

import java.io.Serializable;

public class MainActionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9134405533631894152L;
	private int actionCounter;
	private GameDTO game;
	
	/**
	 * @return the actionCounter
	 */
	public int getActionCounter() {
		return actionCounter;
	}
	/**
	 * @param actionCounter the actionCounter to set
	 */
	public void setActionCounter(int actionCounter) {
		this.actionCounter = actionCounter;
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
	
}

