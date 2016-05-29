package com.communication.board;

import java.io.Serializable;

public class BonusCardDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1243754028522972629L;
	private BonusDTO b;

	/**
	 * @return the bonus
	 */
	public BonusDTO getBonus() {
		return b;
	}

	/**
	 * @param b the bonus to set
	 */
	public void setBonus(BonusDTO b) {
		this.b = b;
	}
	
}

