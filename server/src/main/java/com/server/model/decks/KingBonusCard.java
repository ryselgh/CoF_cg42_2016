package com.server.model.decks ;

import com.communication.decks.KingBonusCardDTO;
import com.communication.values.BonusType;
import com.server.model.board.Bonus;



/**
 * The Class KingBonusCard.
 */
public class KingBonusCard
{
	
	
	/** The bonus. */
	private Bonus bonus;
	
	/** The n. */
	private int n;
	

	/**
	 * constructor of the card.
	 *
	 * @param n n is the number of the card from 1 to 5
	 * @param b b is the bonus you receive when you get the card
	 */
	
	public KingBonusCard(int n, Bonus b) {
		if(b==null)
			throw new NullPointerException();
		else{
			if(!b.getType().equals(BonusType.POINT))
				throw new IllegalArgumentException();
			else{
				this.n = n;
				bonus = b;
			}
		}
	}
	
	/**
	 *Gets the bonus.
	 *
	 * @return the bonus
	 */
	
	public Bonus getBonus() {
		return bonus;	
	}
	
	/**
	 * To dto.
	 *
	 * @return the king bonus card dto
	 */
	public KingBonusCardDTO toDTO(){
		KingBonusCardDTO kbDTO = new KingBonusCardDTO();
		kbDTO.setBonus(bonus.toDTO());
		kbDTO.setNumber(n);
		return kbDTO;
	}
}

