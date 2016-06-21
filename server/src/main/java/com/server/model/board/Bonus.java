package com.server.model.board ;

import com.communication.board.BonusDTO;
import com.communication.values.BonusType;



/**
 * The Class Bonus.
 */
public class Bonus
{
	
	
	/** The type. */
	private BonusType type;
	
	/** The quantity. */
	private int quantity;
	

	/**
	 * constructor  of the bonus.
	 *
	 * @param t t is the bonus type
	 * @param q q is the quantity of the bonus
	 */

	public Bonus(BonusType t, int q) {
		if (q<=0)
			throw new IllegalArgumentException();
		else{
			this.type = t;
			this.quantity = q;

		}
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type of the bonus
	 */
	
	public BonusType getType() {
		return this.type;	
	}
	
	/**
	 * Gets the qnt.
	 *
	 * @return the quantity of the bonus
	 */
	
	public int getQnt() {
		return this.quantity;	
	}
	
	/**
	 * Equals dto.
	 *
	 * @param bDTO the bonusDTO
	 * @return true, if successful
	 */
	public boolean equalsDTO(BonusDTO bDTO){
		if(!(bDTO==null)){
			if(type.equals(bDTO.getType()) && quantity == bDTO.getQnt())
				return true;
			return false;
		}else
			throw new NullPointerException("Data Transfer Object 'BonusDTO' cannot be NULL");
	}
	
	/**
	 * Checks for nobility bonus in an array of bonus.
	 *
	 * @param b the b
	 * @return true, if successful
	 */
	public static boolean hasNobilityBonus(Bonus[] b)
	{
		for(Bonus bo: b){
			if(bo != null)
				if(bo.getType().equals(BonusType.NOBILITY))
					return true;
		}
		return false;
			
	}

	/**
	 * To dto.
	 *
	 * @return the bonus dto
	 */
	public BonusDTO toDTO(){
		BonusDTO bDTO = new BonusDTO();
		bDTO.setQuantity(this.quantity);
		bDTO.setType(this.type);
		return bDTO;
	}
}

