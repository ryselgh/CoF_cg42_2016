package com.server.model.board ;




public class BonusCard
{
	
	
	private Bonus b;
	

	/**
	 * constructor of the bonus card 
	 * @param b b is the bonus on the bonus card
	 */
	
	public BonusCard(Bonus b) {
		this.b = b;	
	}
	
	/**
	 * @return the nìbonus on the bonus card
	 */
	
	public Bonus getBonus() {
		return this.b;	
	}
	
}

