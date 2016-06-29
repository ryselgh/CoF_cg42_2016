package com.server.model.market ;

import java.util.ArrayList;

import com.communication.market.MarketDTO;
import com.communication.market.OnSaleDTO;



/**
 * The Class Market.
 */
public class Market{

	
	/** The objects on sale. */
	private ArrayList<OnSale> objectsOnSale;
	
	
	/**
	 * Instantiates a new market.
	 */
	public Market() {
		objectsOnSale = new ArrayList<OnSale>();	
	}
	
	/**
	 * @param arrayPointer the array pointer
	 * @return the obj on sale
	 */
	
	public OnSale getObjOnSale(int arrayPointer) {
			return objectsOnSale.get(arrayPointer);	
	}
	
	/**
	 * Gets the objects on sale.
	 *
	 * @return the objects on sale
	 */
	public ArrayList<OnSale> getObjectsOnSale() {
		return objectsOnSale;	
}
	
	/**
	 * Adds the obj.
	 *
	 * @param o the object
	 */
	public void addObj(OnSale o)
	{
		objectsOnSale.add(o);
	}
	
	/**
	 * Removes the obj.
	 *
	 * @param o the object
	 */
	public void removeObj(int o)
	{
		objectsOnSale.remove(o);
	}
	
	/**
	 * Removes the object.
	 *
	 * @param UID the uid
	 */
	public void removeObj(String UID)
	{
		for(int i=0;i<objectsOnSale.size();i++)
			if(objectsOnSale.get(i).getUID().equals(UID))
				objectsOnSale.remove(i);
	}
	
	/**
	 * Gets the obj number.
	 *
	 * @return the obj number
	 */
	public int getObjNumber()
	{
		return objectsOnSale.size();
	}

	/**
	 * To dto.
	 *
	 * @return the market dto
	 */
	public MarketDTO toDTO(){
		MarketDTO marketDTO = new MarketDTO();
		ArrayList<OnSaleDTO> osDTO = new ArrayList<OnSaleDTO>();
		for(OnSale os : objectsOnSale)
			if(os instanceof AssistantOnSale)
				osDTO.add(((AssistantOnSale)os).toDTO());
			else if(os instanceof PermitOnSale)
				osDTO.add(((PermitOnSale)os).toDTO());
			else if(os instanceof PoliticsOnSale)
				osDTO.add(((PoliticsOnSale)os).toDTO());
		marketDTO.setObjectsOnSale(osDTO);
		return marketDTO;
	}
	
}

