package com.server.model.market ;

import com.communication.market.PoliticsOnSaleDTO;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Player;


// TODO: Auto-generated Javadoc
/**
 * The Class PoliticsOnSale.
 */
public class PoliticsOnSale extends OnSale{
	
	/** The politics card. */
	private PoliticsCard politicsCard;
	
	/** The price. */
	private int price;
	
	/** The seller. */
	private Player seller;
	
	/** The uid. */
	private String UID;
	
	
	/**
	 * Instantiates a new politics on sale.
	 *
	 * @param pl the pl
	 * @param pc the pc
	 * @param pr the pr
	 * @param UID the uid
	 */
	public PoliticsOnSale(Player pl, PoliticsCard pc, int pr,String UID) {
		this.politicsCard=pc;
		this.price = pr;
		this.seller = pl;
		this.UID = UID;
	}
	
	/* 
	 * the obtain method
	 */
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addPolitics(this.politicsCard);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removePolitics(this.politicsCard);
	}
	
	/**
	 * gets the price
	 * 
	 * @return rhe price
	 */
	public int getPrice(){
		return this.price;
	};
	
	/**
	 * print details about the sellable objcet
	 * 
	 * @return a string with the details
	 *
	 */
	public String printDetails()
	{
		return "Politic card: [Color= "+ this.politicsCard.getColor().toString() + "]\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	/**
	 * To dto.
	 *
	 * @return the politics on sale dto
	 */
	public PoliticsOnSaleDTO toDTO(){
		PoliticsOnSaleDTO polosDTO = new PoliticsOnSaleDTO();
		polosDTO.setPoliticsCard(politicsCard.toDTO());
		polosDTO.setPrice(price);
		polosDTO.setSeller(seller.toDTO());
		polosDTO.setUID(this.UID);
		return polosDTO;
	}
	

	/*
	 * gets the UID
	 * 
	 * @return the UID
	 */
	public String getUID() {
		return UID;
	}
}

