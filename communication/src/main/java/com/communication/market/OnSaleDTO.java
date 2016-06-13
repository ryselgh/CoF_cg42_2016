package com.communication.market ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;

public class OnSaleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2466917896104839445L;
	private PlayerDTO seller;
	private Object obj;	
	private int price;
	private String UID;
	
	
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
	 * @param obj2 the obj to set
	 */
	public void setObj(Object obj2) {
		this.obj = obj2;
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
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	
}

