package com.server.model.market ;

import com.communication.market.PermitOnSaleDTO;
import com.server.model.board.Bonus;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Player;

public class PermitOnSale extends OnSale
{
	
	private PermitsCard permit;
	private Player seller;
	private int price;
	
	
	public PermitOnSale(Player pl, PermitsCard pc, int pr) {
		this.permit=pc;
		this.price = pr;
		this.seller = pl;	
	}
	
	public void obtain(Player buyer)
	{
		buyer.setCoins(buyer.getCoins() - this.price);
		buyer.addPermits(this.permit);
		seller.setCoins(seller.getCoins() + this.price);
		seller.removePermit(this.permit);
	}
	
	public int getPrice(){
		return this.price;
	};
	
	public String printDetails()
	{
		String letters = "", bonus = "";
		for(Bonus b: permit.getBonus())
			bonus += b.getType().toString() + "(" + Integer.toString(b.getQnt()) + ") ";
		for(String l : permit.getCityLetter())
			letters += l + " ";
		return "Permit card: {[Letters= "+ letters + "], [Bonus= " + bonus + "]}\nPrice= " + Integer.toString(price) + "\n\n";
	}
	
	public PermitOnSaleDTO toDTO(){
		PermitOnSaleDTO posDTO = new PermitOnSaleDTO();
		posDTO.setPermit(permit.toDTO());
		posDTO.setPrice(price);
		posDTO.setSeller(seller.toDTO());
		return posDTO;
	}
	
	
}

