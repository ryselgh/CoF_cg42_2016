package com.server.model.market ;

import com.server.model.gamelogic.Player;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class OnSale
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Player seller;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	protected static Object obj;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int price;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public OnSale(Player p, Object o, int pr) {
		this.seller = p;
		this.obj = o;
		this.price = pr;
	}
	public OnSale(){};
	
	public void obtain(Player buyer){};
	public int getPrice(){return 0;};
	public String printDetails(){return "";};
	
}

