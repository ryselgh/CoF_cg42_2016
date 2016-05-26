package com.server.model.board ;

import com.server.model.gamelogic.Player;



public class Emporium
{
	
	
	private Player player;
	

	/**
	 * the constructor of the emporium
	 * @param p p is the player
	 */
	
	public Emporium(Player p) {
		this.player = p;	
	}
	
	/**
	 * @return the player of the emporium 
	 */
	
	public Player getPlayer() {
		return this.player;	
	}
	
}

