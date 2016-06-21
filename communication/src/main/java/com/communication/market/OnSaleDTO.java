package com.communication.market ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;


/**
 * The Class OnSaleDTO.
 */
public class OnSaleDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2466917896104839445L;
	
	/** The seller(DTO). */
	private PlayerDTO seller;
	
	/** The obj. */
	private Object obj;	
	
	/** The price. */
	private int price;
	
	/** The uid. */
	private String UID;
	
	
	/**
	 * Gets the seller(playerDTO).
	 *
	 * @return the seller(playerDTO)
	 */
	public PlayerDTO getSeller() {
		return seller;
	}
	
	/**
	 * Sets the seller(playerDTO).
	 *
	 * @param seller the seller(playerDTO) to set
	 */
	public void setSeller(PlayerDTO seller) {
		this.seller = seller;
	}
	
	/**
	 * Gets the obj.
	 *
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	
	/**
	 * Sets the obj.
	 *
	 * @param obj2 the obj to set
	 */
	public void setObj(Object obj2) {
		this.obj = obj2;
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
	 * Sets the price.
	 *
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUID() {
		return UID;
	}
	
	/**
	 * Sets the uid.
	 *
	 * @param uID the new uid
	 */
	public void setUID(String uID) {
		UID = uID;
	}
	
}

