package com.server.model.decks ;

import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.gamelogic.Player;




/**
 * The Class PoliticsCard.
 */
public class PoliticsCard
{
	
	
	/** The color. */
	private CouncilorColor color;
	
	
/**
 * constructor of the politicscard.
 *
 * @param c is the color of card
 */
	
	
	public PoliticsCard(CouncilorColor c) {
		if (c==null)
			throw new NullPointerException();
		else {
		color=c;
		}

	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color of the card
	 */
	
	public CouncilorColor getColor() {
		
		return color;	
	}
	
	/**
	 * From dto.
	 *
	 * @param pcDTO the politicsCardDTO
	 * @param player the player
	 * @return the politics card
	 */
	public static PoliticsCard fromDTO(PoliticsCardDTO pcDTO, Player player){
		if (pcDTO == null) 
			throw new NullPointerException("pcDTO cannot be null");
		else{
			for (PoliticsCard pc : player.getHand())
				if (pc.equalsDTO(pcDTO))
					return pc;
			return null;
		}
	}
	
	/**
	 * Equals dto.
	 *
	 * @param pcDTO the pc dto
	 * @return true, if successful
	 */
	public boolean equalsDTO(PoliticsCardDTO pcDTO){
		if (!(pcDTO == null)) {
			if (this.color.equals(pcDTO.getColor()))
				return true;
			return false;
		} else
			throw new NullPointerException("pcDTO cannot be null");
	}
	
	/**
	 * To dto.
	 *
	 * @return the politics card dto
	 */
	public PoliticsCardDTO toDTO(){
		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(color);
		return pcDTO;
	}
}

