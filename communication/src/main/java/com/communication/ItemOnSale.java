package com.communication;

import java.io.Serializable;


/**
 * The Class ItemOnSale.
 */
public class ItemOnSale implements Serializable{/** The price. */
//used for client-server communication
	private int price;
	
	/** The object. */
	private Object obj;
	
	/**
	 * Instantiates a new item on sale.
	 *
	 * @param pr the price
	 * @param obj the object
	 */
	public ItemOnSale(int pr, Object obj){
		this.price = pr;
		this.obj = obj;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Gets the object.
	 *
	 * @return the object
	 */
	public Object getObj() {
		return obj;
	}
	
	
}
