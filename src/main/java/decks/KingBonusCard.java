package decks ;

import board.Bonus;


public class KingBonusCard
{
	
	
	private Bonus bonus;
	private int n;
	

	
	
	public KingBonusCard(int n, Bonus b) {
		this.n = n;
		bonus = b;
	}
	
	
	
	public Bonus getBonus() {
		return bonus;	
	}
	
}

