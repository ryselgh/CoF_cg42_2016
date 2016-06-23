package com.communication.gamelogic;


import com.communication.SellableSer;


public class ItemOnSaleDTO implements SellableSer{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5035687814804543490L;
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
