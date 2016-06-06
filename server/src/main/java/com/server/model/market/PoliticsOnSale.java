package com.server.model.market ;

import com.communication.market.PoliticsOnSaleDTO;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Player;


public class PoliticsOnSale extends OnSale{
	
	private PoliticsCard politicsCard;
	private int price;
	private Player seller;
	
	
	public PoliticsOnSale(Player pl, PoliticsCard pc, int pr) {
		this.politicsCard=pc;
		this.price = pr;
		this.seller = pl;
	}
	
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addPolitics(this.politicsCard);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removePolitics(this.politicsCard);
	}
	
	public int getPrice(){
		return this.price;
	};
	
	public String printDetails()
	{
		return "Politic card: [Color= "+ this.politicsCard.getColor().toString() + "]\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	public PoliticsOnSaleDTO toDTO(){
		PoliticsOnSaleDTO polosDTO = new PoliticsOnSaleDTO();
		polosDTO.setPoliticsCard(politicsCard.toDTO());
		polosDTO.setPrice(price);
		polosDTO.setSeller(seller.toDTO());
		return polosDTO;
	}
	
}

