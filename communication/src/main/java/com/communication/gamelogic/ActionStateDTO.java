package com.communication.gamelogic;

import java.io.Serializable;

public class ActionStateDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5299453812776035121L;
	private GameDTO game;
	private int mainCounter;
	private int speedCounter;
	
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
	 * @return the mainCounter
	 */
	public int getMainCounter() {
		return mainCounter;
	}
	/**
	 * @param mainCounter the mainCounter to set
	 */
	public void setMainCounter(int mainCounter) {
		this.mainCounter = mainCounter;
	}
	/**
	 * @return the speedCounter
	 */
	public int getSpeedCounter() {
		return speedCounter;
	}
	/**
	 * @param speedCounter the speedCounter to set
	 */
	public void setSpeedCounter(int speedCounter) {
		this.speedCounter = speedCounter;
	}

}
