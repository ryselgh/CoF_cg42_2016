package com.server.model.market;

import com.communication.SerObject;
import com.communication.market.AssistantOnSaleDTO;
import com.server.model.board.Assistant;
import com.server.model.gamelogic.Player;

public class AssistantOnSale extends OnSale{
	
	private Assistant assistant;
	private Player seller;
	private int price;

	
	public AssistantOnSale(Player pl, Assistant a, int pr) {
		if(a==null)
			throw new NullPointerException();
		this.assistant=a;
		this.price = pr;
		this.seller = pl;
	}
	
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addAssistant(this.assistant);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removeAssistant(this.assistant);
	}
	
	public int getPrice(){
		return this.price;
	}
	

	public String printDetails()
	{
		return "Assistant\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	public AssistantOnSaleDTO toDTO(){
		AssistantOnSaleDTO assDTO = new AssistantOnSaleDTO();
		assDTO.setAssistant(assistant.toDTO());
		assDTO.setObj(assistant.toDTO());
		assDTO.setPrice(price);
		assDTO.setSeller(seller.toDTO());
		return assDTO;
	}
}

