package com.communication.board ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;




/**
 * The Class EmporiumDTO.
 */
public class EmporiumDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7415930713250635058L;
	
	/** The player(DTO). */
	private PlayerDTO player;

	/**
	 * Gets the player(DTO).
	 *
	 * @return the player(DTO)
	 */
	public PlayerDTO getPlayer() {
		return player;
	}

	/**
	 * Sets the player(DTO).
	 *
	 * @param player the player(DTO) to set
	 */
	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}
	
}