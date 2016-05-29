package com.server.model.decks ;

import com.server.model.board.Bonus;
import com.server.values.BonusType;


public class KingBonusCard
{
	
	
	private Bonus bonus;
	private int n;
	

	/**
	 * constructor of the card
	 * @param n n is the number of the card from 1 to 5
	 * @param b b is the bonus you receive when you get the card
	 */
	
	public KingBonusCard(int n, Bonus b) {
		//		Bonus[] bonuses = new Bonus[BonusType.values().length];
		//for(BonusType bt: BonusType.values()){
		if(b.getType()!=BonusType.POINT)
			throw new IllegalArgumentException("The BonusType is wrong or the card does not exist");
		else{
			this.n = n;
			bonus = b;
		}

	}
	
	/**
	 *  is the getter of the bonus
	 * @return the bonus
	 */
	
	public Bonus getBonus() {
		return bonus;	
	}
	
}

