package board ;

import model.BonusType;


public class Bonus
{
	
	
	private BonusType type;
	private int quantity;
	

	/**
	 * constructor  of the bonus
	 * @param t t is the bonus type
	 * @param q q is the quantity of the bonus
	 */
	
	public Bonus(BonusType t, int q) {
		this.type = t;
		this.quantity = q;
	}
	
	/**
	 * @return the type of the bonus
	 */
	
	public BonusType getType() {
		return this.type;	
	}
	
	/**
	 * @return the quantity of the bonus
	 */
	
	public int getQnt() {
		return this.quantity;	
	}
	
}

