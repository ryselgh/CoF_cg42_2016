package com.server.model.decks ;

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
		if (!(pcDTO == null)) {
			for (PoliticsCard pc : player.getHand())
				if (pc.equalsDTO(pcDTO))
					return pc;
			return null;
		} else
			throw new NullPointerException("pcDTO cannot be null");
	}
	
	public boolean equalsDTO(PoliticsCardDTO pcDTO){
		if (!(pcDTO == null)) {
			if (this.color.equals(pcDTO.getColor()))
				return true;
			return false;
		} else
			throw new NullPointerException("pcDTO cannot be null");
	}
	
	public PoliticsCardDTO toDTO(){
		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(color);
		return pcDTO;
	}
}

