package com.communication.board ;

import java.io.Serializable;


/**
 * The Class BonusTokenDTO.
 */
public class BonusTokenDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4144704840197344725L;
	
	/** The bonus array (DTO). */
	private BonusDTO[] bonus;
	
	
	/**
	 * Gets the bonus array (DTO).
	 *
	 * @return the bonus on token
	 */
	
	public BonusDTO[] getBonus() {
		return this.bonus;	
	}


	/**
	 * Sets the bonus.
	 *
	 * @param bonus the bonus array (DTO)
	 */
	public void setBonus(BonusDTO[] bonus) {
		this.bonus = bonus;
	}
	
	
	
}

