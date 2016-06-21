package com.communication.board ;

import java.io.Serializable;

import com.communication.values.BonusType;


/**
 * The Class BonusDTO.
 */
public class BonusDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1208217492066931434L;
	
	/** The type. */
	private BonusType type;
	
	/** The quantity. */
	private int quantity;
	
	
	/**
	 * Gets the type.
	 *
	 * @return the type of the bonus
	 */
	
	public BonusType getType() {
		return this.type;	
	}
	
	/**
	 * Gets the qnt.
	 *
	 * @return the quantity of the bonus
	 */
	
	public int getQnt() {
		return this.quantity;	
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(BonusType type) {
		this.type = type;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

