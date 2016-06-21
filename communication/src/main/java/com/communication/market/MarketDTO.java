package com.communication.market ;

import java.io.Serializable;
import java.util.ArrayList;



/**
 * The Class MarketDTO.
 */
public class MarketDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -345087950962402211L;
	
	/** The objects(DTO) on sale. */
	private ArrayList<OnSaleDTO> objectsOnSale;

	/**
	 * Gets the objects(DTO) on sale.
	 *
	 * @return the list of objectsOnSale(DTO)
	 */
	public ArrayList<OnSaleDTO> getObjectsOnSale() {
		return objectsOnSale;
	}

	/**
	 * Sets the objects(DTO) on sale.
	 *
	 * @param objectsOnSale the list of objectsOnSale(DTO) to set
	 */
	public void setObjectsOnSale(ArrayList<OnSaleDTO> objectsOnSale) {
		this.objectsOnSale = objectsOnSale;
	}
	
}

