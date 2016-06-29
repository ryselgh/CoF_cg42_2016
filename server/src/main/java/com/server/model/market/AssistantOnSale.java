package com.server.model.market;

import com.communication.market.AssistantOnSaleDTO;
import com.server.model.board.Assistant;
import com.server.model.gamelogic.Player;


/**
 * The Class AssistantOnSale.
 */
public class AssistantOnSale extends OnSale{
	
	/** The assistant. */
	private Assistant assistant;
	
	/** The seller. */
	private Player seller;
	
	/** The price. */
	private int price;
	
	/** The uid. */
	private String UID;

	
	/**
	 * Instantiates a new assistant on sale.
	 *
	 * @param pl the player
	 * @param a the assistant
	 * @param pr the price
	 * @param UID the uid
	 */
	public AssistantOnSale(Player pl, Assistant a, int pr, String UID) {
		if(a==null)
			throw new NullPointerException();
		this.assistant=a;
		this.price = pr;
		this.seller = pl;
		this.UID = UID;
	}
	
	/* 
	 * obtain method
	 */
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addAssistant(this.assistant);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removeAssistant(this.assistant);
	}
	
	/**
	 * gets the price
	 * 
	 * @return the price
	 */
	public int getPrice(){
		return this.price;
	}
	

	/**
	 * print the details of the object
	 * 
	 * @return a string with the information of the object
	 */
	public String printDetails()
	{
		return "Assistant\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	/**
	 * To dto.
	 *
	 * @return the assistant on sale dto
	 */
	public AssistantOnSaleDTO toDTO(){
		AssistantOnSaleDTO assDTO = new AssistantOnSaleDTO();
		assDTO.setAssistant(assistant.toDTO());
		assDTO.setPrice(price);
		assDTO.setSeller(seller.toDTO());
		assDTO.setUID(this.UID);
		return assDTO;
	}

	/**
	 * gets the UID
	 * 
	 * @return the UID
	 */
	public String getUID() {
		return UID;
	}
	
	
}

