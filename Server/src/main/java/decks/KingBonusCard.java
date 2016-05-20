package decks ;

import board.Bonus;


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
		this.n = n;
		bonus = b;
	}
	
	/**
	 *  is the getter of the bonus
	 * @return the bonus
	 */
	
	public Bonus getBonus() {
		return bonus;	
	}
	
}

