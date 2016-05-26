package com.communication.board ;

import java.io.Serializable;

import com.communication.values.CouncilorColor;


public class CouncilorDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -281370232277918380L;
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

