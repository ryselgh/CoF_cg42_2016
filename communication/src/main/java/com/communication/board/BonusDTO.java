package com.communication.board ;

import java.io.Serializable;

import com.communication.values.BonusType;

public class BonusDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1208217492066931434L;
	private BonusType type;
	private int quantity;
	
	
	/**
	 * @return the type of the bonus
	 */
	
	public BonusType getType() {
		return this.type;	
	}
	
	/**
	 * @return the quantity of the bonus
	 */
	
	public int getQnt() {
		return this.quantity;	
	}

	/**
	 * @param type the type to set
	 */
	public void setType(BonusType type) {
		this.type = type;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

