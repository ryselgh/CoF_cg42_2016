package com.server.broadcast ;




public class BonusTokenInfo
{
	
	
	private BonusInfo[] b;
	

	/**
	 * constructor  of the token
	 * @param b b is the bonus on the token
	 */
	
	public BonusTokenInfo(BonusInfo[] b) {
		this.b = b;	
	}
	
	/**
	 * @return the bonus on token
	 */
	
	public BonusInfo[] getBonus() {
		return this.b;	
	}
	
}

