package com.server.broadcast ;




public class BonusCardInfo
{
	
	
	private BonusInfo b;
	

	/**
	 * constructor of the bonus card 
	 * @param b b is the bonus on the bonus card
	 */
	
	public BonusCardInfo(BonusInfo b) {
		this.b = b;	
	}
	
	/**
	 * @return the nìbonus on the bonus card
	 */
	
	public BonusInfo getBonus() {
		return this.b;	
	}
	
}

