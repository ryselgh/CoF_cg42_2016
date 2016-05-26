package com.communication.market ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;

public class OnSaleDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2787902738966299455L;
	private PlayerDTO seller;
	private Object obj;	
	private int price;
	
	
	/**
	 * @return the seller
	 */
	public PlayerDTO getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public void setSeller(PlayerDTO seller) {
		this.seller = seller;
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
	
}

