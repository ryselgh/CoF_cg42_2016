package com.communication.market;

import java.io.Serializable;

import com.communication.board.AssistantDTO;
import com.communication.gamelogic.PlayerDTO;


/**
 * The Class AssistantOnSaleDTO.
 */
public class AssistantOnSaleDTO extends OnSaleDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4458860367625926332L;

	/** The assistant(DTO). */
	private AssistantDTO assistant;
	
	/** The seller(DTO). */
	private PlayerDTO seller;
	
	/** The price. */
	private int price;
	
	/** The uid. */
	private String UID;
	
	
	/**
	 * Gets the assistant(DTO).
	 *
	 * @return the assistant(DTO)
	 */
	public AssistantDTO getAssistant() {
		return assistant;
	}
	
	/**
	 * Sets the assistant(DTO).
	 *
	 * @param assistant the assistant(DTO) to set
	 */
	public void setAssistant(AssistantDTO assistant) {
		this.assistant = assistant;
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
	
	/* 
	 * this override methods returns an object which is instanceof assistant
	 * @return assistant 
	 */
	@Override
	public AssistantDTO getObj(){
		return assistant;
	}
	
	
}

