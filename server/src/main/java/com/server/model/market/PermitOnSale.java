package com.server.model.market ;

import com.communication.market.PermitOnSaleDTO;
import com.server.model.board.Bonus;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Player;


/**
 * The Class PermitOnSale.
 */
public class PermitOnSale extends OnSale
{
	
	/** The permit. */
	private PermitsCard permit;
	
	/** The seller. */
	private Player seller;
	
	/** The price. */
	private int price;
	
	/** The uid. */
	private String UID;
	
	
	/**
	 * Instantiates a new permit on sale.
	 *
	 * @param pl the player
	 * @param pc the permitsCard
	 * @param pr the price
	 * @param UID the uid
	 */
	public PermitOnSale(Player pl, PermitsCard pc, int pr, String UID) {
		this.permit=pc;
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
		buyer.addPermits(this.permit);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removePermit(this.permit);
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
		String letters = "", bonus = "";
		for(Bonus b: permit.getBonus())
			bonus += b.getType().toString() + "(" + Integer.toString(b.getQnt()) + ") ";
		for(String l : permit.getCityLetter())
			letters += l + " ";
		return "Permit card: {[Letters= "+ letters + "], [Bonus= " + bonus + "]}\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	/**
	 * To dto.
	 *
	 * @return the permit on sale dto
	 */
	public PermitOnSaleDTO toDTO(){
		PermitOnSaleDTO posDTO = new PermitOnSaleDTO();
		posDTO.setPermit(permit.toDTO());
		posDTO.setPrice(price);
		posDTO.setSeller(seller.toDTO());
		posDTO.setUID(this.UID);
		return posDTO;
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

