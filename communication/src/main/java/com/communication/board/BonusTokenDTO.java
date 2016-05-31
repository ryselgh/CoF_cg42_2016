package com.communication.board ;

import java.io.Serializable;

public class BonusTokenDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4144704840197344725L;
	private BonusDTO[] b;
	
	
	/**
	 * @return the bonus on token
	 */
	
	public BonusDTO[] getBonus() {
		return this.b;	
	}


	public void setBonus(BonusDTO[] b) {
		this.b = b;
	}
	
	
	
}

