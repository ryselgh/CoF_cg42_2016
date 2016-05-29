package com.server.model.board ;

import com.communication.board.BonusDTO;
import com.communication.values.BonusType;


public class Bonus
{
	
	
	private BonusType type;
	private int quantity;
	

	/**
	 * constructor  of the bonus
	 * @param t t is the bonus type
	 * @param q q is the quantity of the bonus
	 */
	
	public Bonus(BonusType t, int q) {
		this.type = t;
		this.quantity = q;
	}
	
	/**
	 * @return the type of the bonus
	 */
	
	public BonusType getType() {
		return this.type;	
	}
	
	/**
	 * @return the quantity of the bonus
	 */
	
	public int getQnt() {
		return this.quantity;	
	}
	
	public boolean equals(BonusDTO bDTO){
		if(!type.equals(bDTO.getType()) || quantity != bDTO.getQnt())
			return false;
		return true;
	}
	public static boolean hasNobilityBonus(Bonus[] b)
	{
		for(Bonus bo: b)
			if(bo.getType().equals(BonusType.NOBILITY))
					return true;
		return false;
			
	}
}

