package com.server.model.board ;

import com.communication.board.BonusCardDTO;
import com.communication.board.BonusDTO;


/**
 * The Class BonusCard.
 */
public class BonusCard
{
	
	
	/** The b. */
	private Bonus b;
	

	/**
	 * constructor of the bonus card .
	 *
	 * @param b b is the bonus on the bonus card
	 */
	
	public BonusCard(Bonus b) {
		if (b==null)
			throw new NullPointerException();
		else
			this.b = b;	
	}
	
	/**
	 * Gets the bonus.
	 *
	 * @return the bonus on the bonus card
	 */
	
	public Bonus getBonus() {
		return this.b;	
	}
	
	/**
	 * To dto.
	 *
	 * @return the bonus card dto
	 */
	public BonusCardDTO toDTO(){
		BonusCardDTO ret = new BonusCardDTO();
		BonusDTO bDTO = new BonusDTO();
		bDTO.setQuantity(this.getBonus().getQnt());
		bDTO.setType(this.getBonus().getType());
		ret.setBonus(bDTO);
		return ret;
	}
	
}

