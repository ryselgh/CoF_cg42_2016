package com.communication.market ;

import java.io.Serializable;
import java.util.ArrayList;


public class MarketDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -345087950962402211L;
	private ArrayList<OnSaleDTO> objectsOnSale;

	/**
	 * @return the objectsOnSale
	 */
	public ArrayList<OnSaleDTO> getObjectsOnSale() {
		return objectsOnSale;
	}

	/**
	 * @param objectsOnSale the objectsOnSale to set
	 */
	public void setObjectsOnSale(ArrayList<OnSaleDTO> objectsOnSale) {
		this.objectsOnSale = objectsOnSale;
	}
	
}

