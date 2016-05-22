package com.client.model ;


public class BonusInfo{
	
	
	private String type;
	private int quantity;
	
	/**
	 * is the constructor of the class BonusInfo
	 * @param type is the type of the bonus 
	 * @param quantity is the quantity of the bonus
	 */

	private BonusInfo(String type, int quantity){
		this.type = type;
		this.quantity = quantity;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
}

