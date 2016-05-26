package com.communication.decks ;

import java.io.Serializable;

import com.communication.values.CouncilorColor;



public class PoliticsCardDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5778545366782153279L;
	private CouncilorColor color;

	/**
	 * @return the color
	 */
	public CouncilorColor getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(CouncilorColor color) {
		this.color = color;
	}
	
}

