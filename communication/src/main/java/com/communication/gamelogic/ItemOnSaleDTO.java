package com.communication.gamelogic;

import java.io.Serializable;

import com.communication.SerObject;

public class ItemOnSaleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5035687814804543490L;
	private int price;
	private SerObject obj;
	
	
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
	public SerObject getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(SerObject obj) {
		this.obj = obj;
	}
	
}
