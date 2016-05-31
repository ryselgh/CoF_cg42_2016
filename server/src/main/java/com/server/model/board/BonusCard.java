package com.server.model.board ;

import com.communication.board.BonusCardDTO;
import com.communication.board.BonusDTO;

public class BonusCard
{
	
	
	private Bonus b;
	

	/**
	 * constructor of the bonus card 
	 * @param b b is the bonus on the bonus card
	 */
	
	public BonusCard(Bonus b) {
		this.b = b;	
	}
	
	/**
	 * @return the nìbonus on the bonus card
	 */
	
	public Bonus getBonus() {
		return this.b;	
	}
	
	public BonusCardDTO toDTO(){
		BonusCardDTO ret = new BonusCardDTO();
		BonusDTO bDTO = new BonusDTO();
		bDTO.setQuantity(this.getBonus().getQnt());
		bDTO.setType(this.getBonus().getType());
		ret.setBonus(bDTO);
		return ret;
	}
	
}

