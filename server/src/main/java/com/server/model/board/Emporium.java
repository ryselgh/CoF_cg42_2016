package com.server.model.board ;

import com.communication.board.EmporiumDTO;
import com.communication.gamelogic.PlayerDTO;
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
	
	public EmporiumDTO toDTO(PlayerDTO plDTO){
		EmporiumDTO eDTO = new EmporiumDTO();
		eDTO.setPlayer(plDTO);
		return eDTO;
	}
	
}

