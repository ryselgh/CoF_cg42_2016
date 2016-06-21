package com.communication.board;

import java.io.Serializable;


/**
 * The Class BonusCardDTO.
 */
public class BonusCardDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1243754028522972629L;
	
	/** The bonus(DTO). */
	private BonusDTO bonus;

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
	
}

