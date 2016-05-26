package com.communication.market;

import java.io.Serializable;

import com.communication.board.AssistantDTO;
import com.communication.gamelogic.PlayerDTO;

public class AssistantOnSaleDTO implements Serializable{

	
	private AssistantDTO assistant;
	private PlayerDTO seller;
	private int price;
	
	
	/**
	 * @return the assistant
	 */
	public AssistantDTO getAssistant() {
		return assistant;
	}
	/**
	 * @param assistant the assistant to set
	 */
	public void setAssistant(AssistantDTO assistant) {
		this.assistant = assistant;
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

