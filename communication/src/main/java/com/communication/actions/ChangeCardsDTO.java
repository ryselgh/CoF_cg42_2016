package com.communication.actions;

import java.io.Serializable;


/**
 * The Class ChangeCardsDTO.
 */
public class ChangeCardsDTO extends ActionDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1141199441417494594L;
	
	/** The balcony index. */
	private int balconyIndex;
	
	/**
	 * Instantiates a new change cards dto.
	 */
	public ChangeCardsDTO(){}
	
	/**
	 * Gets the balcony index.
	 *
	 * @return the balconyIndex
	 */
	public int getBalconyIndex() {
		return balconyIndex;
	}
	
	/**
	 * Sets the balcony index.
	 *
	 * @param balconyIndex the balconyIndex to set
	 */
	public void setBalconyIndex(int balconyIndex) {
		this.balconyIndex = balconyIndex;
	}
	
}
