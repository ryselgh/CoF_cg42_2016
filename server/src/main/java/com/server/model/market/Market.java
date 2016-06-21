package com.server.model.market ;

import java.util.ArrayList;

import com.communication.market.MarketDTO;
import com.communication.market.OnSaleDTO;


public class Market{

	
	private ArrayList<OnSale> objectsOnSale;
	
	
	public Market() {
		objectsOnSale = new ArrayList<OnSale>();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public OnSale getObjOnSale(int arrayPointer) {
			return objectsOnSale.get(arrayPointer);	
	}
	
	public ArrayList<OnSale> getObjectsOnSale() {
		return objectsOnSale;	
}
	
	public void addObj(OnSale o)
	{
		objectsOnSale.add(o);
	}
	
	public void removeObj(int o)
	{
		objectsOnSale.remove(o);
	}
	
	public void removeObj(String UID)
	{
		for(int i=0;i<objectsOnSale.size();i++)
			if(objectsOnSale.get(i).getUID().equals(UID))
				objectsOnSale.remove(i);
	}
	
	public int getObjNumber()
	{
		return objectsOnSale.size();
	}

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

