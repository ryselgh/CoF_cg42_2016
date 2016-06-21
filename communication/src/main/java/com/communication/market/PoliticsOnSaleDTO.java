package com.communication.market ;

import java.io.Serializable;

import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;


/**
 * The Class PoliticsOnSaleDTO.
 */
public class PoliticsOnSaleDTO extends OnSaleDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7006683771485241850L;
	
	/** The politics card(DTO). */
	private PoliticsCardDTO politicsCard;
	
	/** The price. */
	private int price;
	
	/** The seller(DTO). */
	private PlayerDTO seller;
	
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
	 * Gets the politics card(DTO).
	 *
	 * @return the politicsCard(DTO)
	 */
	public PoliticsCardDTO getPoliticsCard() {
		return politicsCard;
	}
	
	/**
	 * Sets the politics card(DTO).
	 *
	 * @param politicsCard the politicsCard(DTO) to set
	 */
	public void setPoliticsCard(PoliticsCardDTO politicsCard) {
		this.politicsCard = politicsCard;
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
	
	
}

