package com.server.model.market ;


import com.server.model.gamelogic.Player;

public class OnSale{

	private Player seller;	
	protected static Object obj;
	private int price;
	private String UID;


	public OnSale(Player p, Object o, int pr, String UID) {
		this.seller = p;
		this.obj = o;
		this.price = pr;
		this.UID = UID;
	}

	public OnSale(){};

	
	public void obtain(Player buyer){};
	
	public int getPrice(){return 0;};
	
	public String printDetails(){return "";};
	
	/*public boolean equalsDTO(OnSaleDTO osDTO){
		if (!(osDTO == null)) {
			if (!seller.toDTO().equals(osDTO.getSeller()))
				return false;
			else if (!objectEquals(obj, osDTO))
				return false;
			else if (price != osDTO.getPrice())
				return false;
			return true;
		} else
			throw new NullPointerException("osDTO cannot be null");
	}

	private boolean objectEquals(Object obj, Object objDTO){
		if((obj instanceof AssistantOnSale) && (objDTO instanceof AssistantOnSaleDTO))
			return true;
		else if((obj instanceof PermitOnSale) && (objDTO instanceof PermitOnSaleDTO))
			if(((PermitOnSale) obj).equalsDTO((PermitOnSaleDTO) objDTO))
				return true;
			else
				return false;
		else if((obj instanceof PoliticsOnSale) && (objDTO instanceof PoliticsOnSaleDTO))
			if(((PoliticsOnSale) obj).equalsDTO((PoliticsOnSaleDTO) objDTO))
				return true;
			else
				return false;
		else
			return false;
	}*/
	/*public OnSaleDTO toDTO() {
		OnSaleDTO osDTO = new OnSaleDTO();
		osDTO.setObj(obj);
		osDTO.setPrice(price);
		osDTO.setSeller(seller.toDTO());
		return osDTO;
	}*/
	
	public String getUID(){ return this.UID;}

}