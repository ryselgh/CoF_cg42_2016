package com.server.model.market;

import com.server.model.board.Assistant;
import com.server.model.gamelogic.Player;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class AssistantOnSale implements OnSaleInterface
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Assistant assistant;
	private Player seller;
	private int price;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public AssistantOnSale(Player pl, Assistant a, int pr) {
		this.assistant=a;
		this.price = pr;
		this.seller = pl;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addAssistant(this.assistant);
		seller.setCoins(seller.getCoins() + this.price);
		buyer.removeAssistant(this.assistant);
	}
	
	public int getPrice(){
		return this.price;
	};
	

	public String printDetails()
	{
		return "Assistant\nPrice= " + Integer.toString(price) + "\n\n";
	}
}

