package com.client.model ;


public class BonusInfo{
	
	
	private String type;
	private int quantity;

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

