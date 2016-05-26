package com.communication.gamelogic;

import java.io.Serializable;

public class SpeedActionDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -932440682439649409L;
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

