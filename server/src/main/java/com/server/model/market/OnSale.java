package com.server.model.market ;

import com.communication.market.AssistantOnSaleDTO;
import com.communication.market.OnSaleDTO;
import com.communication.market.PermitOnSaleDTO;
import com.communication.market.PoliticsOnSaleDTO;
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
	public boolean equals(OnSaleDTO osDTO){
		if(osDTO == null)
			throw new NullPointerException("OnSale item can't be NULL");
		else{
			/*if(!seller.equals(osDTO.getSeller()))
				return false; //TODO: UNRELATED TYPES, FIX HERE!
			else */if(!objectEquals(obj,osDTO))
				return false;
			else if(price != osDTO.getPrice())
				return false;
			return true;
		}
	}
	
	private boolean objectEquals(Object obj, Object objDTO){
		if((obj instanceof AssistantOnSale) && (objDTO instanceof AssistantOnSaleDTO))
			return true;
		else if((obj instanceof PermitOnSale) && (objDTO instanceof PermitOnSaleDTO))
			if(((PermitOnSale) obj).equals((PermitOnSaleDTO) objDTO))
				return true;
			else
				return false;
		else if((obj instanceof PoliticsOnSale) && (objDTO instanceof PoliticsOnSaleDTO))
			if(((PoliticsOnSale) obj).equals((PoliticsOnSaleDTO) objDTO))
				return true;
			else
				return false;
		else
			return false;
	}
	
}

