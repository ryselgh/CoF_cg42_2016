package market ;

import board.Bonus;
import decks.PermitsCard;
import gamelogic.Player;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PermitOnSale implements OnSaleInterface
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private PermitsCard permit;
	private Player seller;
	private int price;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public PermitOnSale(Player pl, PermitsCard pc, int pr) {
		this.permit=pc;
		this.price = pr;
		this.seller = pl;	
	}
	
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addPermits(this.permit);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removePermit(this.permit);
	}
	
	public int getPrice(){
		return this.price;
	};
	
	public String printDetails()
	{
		String letters = "", bonus = "";
		for(Bonus b: permit.getBonus())
			bonus += b.getType().toString() + "(" + Integer.toString(b.getQnt()) + ") ";
		for(String l : permit.getCityLetter())
			letters += l + " ";
		return "Permit card: {[Letters= "+ letters + "], [Bonus= " + bonus + "]}\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
}

