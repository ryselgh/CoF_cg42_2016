package com.server.model.decks ;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.gamelogic.Player;



public class PoliticsCard
{
	
	
	private CouncilorColor color;
	
	
/**
 * constructor of the politicscard
 * @param c is the color of card
 */
	
	
	public PoliticsCard(CouncilorColor c) {
		color=c;

	}
	
	/**
	 * 
	 * @return the color of the card
	 */
	
	public CouncilorColor getColor() {
		
		return color;	
	}
	
	public static PoliticsCard fromDTO(PoliticsCardDTO pcDTO, Player player){
		for(PoliticsCard pc : player.getHand())
			if(pc.equals(pcDTO))
				return pc;
		return null;
	}
	
	public boolean equals(PoliticsCardDTO pcDTO){
		if(this.color.equals(pcDTO.getColor()))
			return true;
		return false;
	}
}

