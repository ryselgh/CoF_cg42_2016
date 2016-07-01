package com.communication;



// TODO: Auto-generated Javadoc
/**
 * The Class ItemOnSale.
 */
public class ItemOnSale implements SellableSer{/**
	 * 
	 */
	private static final long serialVersionUID = 507204036033312702L;

/** The price. */
//used for client-server communication
	private int price;
	
	/** The obj. */
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
	 * Gets the obj.
	 *
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	
	
}
