package com.communication.market ;

import java.io.Serializable;

import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.PlayerDTO;

public class PermitOnSaleDTO extends OnSaleDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8712200773097467600L;
	private PermitsCardDTO permit;
	private PlayerDTO seller;
	private int price;
	private String UID;
	
	
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	/**
	 * @return the permit
	 */
	public PermitsCardDTO getPermit() {
		return permit;
	}
	/**
	 * @param permit the permit to set
	 */
	public void setPermit(PermitsCardDTO permit) {
		this.permit = permit;
	}
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

