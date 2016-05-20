package com.server.model.board ;




public class BonusToken
{
	
	
	private Bonus[] b;
	

	/**
	 * constructor  of the token
	 * @param b b is the bonus on the token
	 */
	
	public BonusToken(Bonus[] b) {
		this.b = b;	
	}
	
	/**
	 * @return the bonus on token
	 */
	
	public Bonus[] getBonus() {
		return this.b;	
	}
	
}

