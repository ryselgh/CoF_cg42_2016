package com.server.model.decks ;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.server.model.gamelogic.Player;
import com.server.values.CouncilorColor;



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
	
}

