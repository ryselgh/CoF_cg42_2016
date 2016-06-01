package com.communication.board ;

import java.io.Serializable;

public class BonusTokenDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4144704840197344725L;
	private BonusDTO[] bonus;
	
	
	/**
	 * @return the bonus on token
	 */
	
	public BonusDTO[] getBonus() {
		return this.bonus;	
	}


	/**
	 * @param b the b to set
	 */
	public void setBonus(BonusDTO[] bonus) {
		this.bonus = bonus;
	}
	
	
	
}

