package com.communication.gamelogic;

import java.io.Serializable;

import com.communication.ObjectDTO;

public class ItemOnSaleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5035687814804543490L;
	private int price;
	private ObjectDTO obj;
	
	
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
	public ObjectDTO getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(ObjectDTO obj) {
		this.obj = obj;
	}
	
}
