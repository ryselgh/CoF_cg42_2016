package com.communication.market ;

import java.io.Serializable;

import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.PlayerDTO;


/**
 * The Class PermitOnSaleDTO.
 */
public class PermitOnSaleDTO extends OnSaleDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8712200773097467600L;
	
	/** The permit(DTO). */
	private PermitsCardDTO permit;
	
	/** The seller(DTO). */
	private PlayerDTO seller;
	
	/** The price. */
	private int price;
	
	/** The uid. */
	private String UID;
	
	
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
	
	/**
	 * Gets the permit(DTO).
	 *
	 * @return the permit(DTO)
	 */
	public PermitsCardDTO getPermit() {
		return permit;
	}
	
	/**
	 * Sets the permit(DTO).
	 *
	 * @param permit the permit(DTO) to set
	 */
	public void setPermit(PermitsCardDTO permit) {
		this.permit = permit;
	}
	
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
	
	
}

