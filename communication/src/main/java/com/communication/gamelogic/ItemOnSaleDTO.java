package com.communication.gamelogic;

import java.io.Serializable;



/**
 * The Class ItemOnSaleDTO.
 */
public class ItemOnSaleDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5035687814804543490L;
	
	/** The price. */
	private int price;
	
	/** The object. */
	private Object obj;
	
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Gets the object.
	 *
	 * @return the object
	 */
	public Object getObj() {
		return obj;
	}
	
	/**
	 * Sets the object.
	 *
	 * @param obj the object to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
