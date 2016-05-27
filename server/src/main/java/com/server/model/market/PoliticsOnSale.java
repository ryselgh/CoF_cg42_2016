package com.server.model.market ;

import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Player;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PoliticsOnSale extends OnSale
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private PoliticsCard politicsCard;
	private int price;
	private Player seller;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public PoliticsOnSale(Player pl, PoliticsCard pc, int pr) {
		this.politicsCard=pc;
		this.price = pr;
		this.seller = pl;
	}
	
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addPolitics(this.politicsCard);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removePolitics(this.politicsCard);
	}
	
	public int getPrice(){
		return this.price;
	};
	
	public String printDetails()
	{
		return "Politic card: [Color= "+ this.politicsCard.getColor().toString() + "]\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
}

