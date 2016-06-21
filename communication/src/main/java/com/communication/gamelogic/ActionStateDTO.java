package com.communication.gamelogic;

import java.io.Serializable;


/**
 * The Class ActionStateDTO.
 */
public class ActionStateDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5299453812776035121L;
	
	/** The game(DTO). */
	private GameDTO game;
	
	/** The main counter. */
	private int mainCounter;
	
	/** The speed counter. */
	private int speedCounter;
	
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
	
	/**
	 * Gets the main counter.
	 *
	 * @return the mainCounter
	 */
	public int getMainCounter() {
		return mainCounter;
	}
	
	/**
	 * Sets the main counter.
	 *
	 * @param mainCounter the mainCounter to set
	 */
	public void setMainCounter(int mainCounter) {
		this.mainCounter = mainCounter;
	}
	
	/**
	 * Gets the speed counter.
	 *
	 * @return the speedCounter
	 */
	public int getSpeedCounter() {
		return speedCounter;
	}
	
	/**
	 * Sets the speed counter.
	 *
	 * @param speedCounter the speedCounter to set
	 */
	public void setSpeedCounter(int speedCounter) {
		this.speedCounter = speedCounter;
	}

}
