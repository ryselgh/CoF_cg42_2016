package com.communication.board ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;



public class EmporiumDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7415930713250635058L;
	private PlayerDTO player;

	/**
	 * @return the player
	 */
	public PlayerDTO getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}
	
}