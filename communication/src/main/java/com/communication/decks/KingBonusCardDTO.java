package com.communication.decks ;

import java.io.Serializable;

import com.communication.board.BonusDTO;



/**
 * The Class KingBonusCardDTO.
 */
public class KingBonusCardDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7351862483377228961L;
	
	/** The bonus(DTO). */
	private BonusDTO bonus;
	
	/** The number of the card. */
	private int n;
	
	
	/**
	 * Gets the bonus(DTO).
	 *
	 * @return the bonus(DTO)
	 */
	public BonusDTO getBonus() {
		return bonus;
	}
	
	/**
	 * Sets the bonus(DTO).
	 *
	 * @param bonus the bonus(DTO) to set
	 */
	public void setBonus(BonusDTO bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number of the card from 1 to 5
	 */
	public int getNumber() {
		return n;
	}
	
	/**
	 * Sets the number.
	 *
	 * @param n the number to set
	 */
	public void setNumber(int n) {
		this.n = n;
	}
	
	
	
}

