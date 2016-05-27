package com.communication.market ;

import java.io.Serializable;

import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;

public class PoliticsOnSaleDTO extends OnSaleDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7006683771485241850L;
	private PoliticsCardDTO politicsCard;
	private int price;
	private PlayerDTO seller;
	
	
	/**
	 * @return the politicsCard
	 */
	public PoliticsCardDTO getPoliticsCard() {
		return politicsCard;
	}
	/**
	 * @param politicsCard the politicsCard to set
	 */
	public void setPoliticsCard(PoliticsCardDTO politicsCard) {
		this.politicsCard = politicsCard;
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
	
	
}

