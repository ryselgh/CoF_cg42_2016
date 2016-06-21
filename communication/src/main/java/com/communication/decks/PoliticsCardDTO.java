package com.communication.decks ;

import java.io.Serializable;

import com.communication.values.CouncilorColor;




/**
 * The Class PoliticsCardDTO.
 */
public class PoliticsCardDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5778545366782153279L;
	
	/** The coulor. */
	private CouncilorColor color;

	/**
	 * Gets the coulor.
	 *
	 * @return the coulor
	 */
	public CouncilorColor getColor() {
		return color;
	}

	/**
	 * Sets the coulor.
	 *
	 * @param color the coulor to set
	 */
	public void setColor(CouncilorColor color) {
		this.color = color;
	}
	
}

