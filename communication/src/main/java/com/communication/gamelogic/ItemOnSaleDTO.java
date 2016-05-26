package com.communication.gamelogic;

import java.io.Serializable;

public class ItemOnSaleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7966883847105139462L;
	private int price;
	private Object obj;
	
	
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
