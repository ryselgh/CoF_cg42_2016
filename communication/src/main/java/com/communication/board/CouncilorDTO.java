package com.communication.board ;

import java.io.Serializable;

import com.communication.values.CouncilorColor;



/**
 * The Class CouncilorDTO.
 */
public class CouncilorDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -281370232277918380L;
	
	/** The color. */
	private CouncilorColor color;

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public CouncilorColor getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the color to set
	 */
	public void setColor(CouncilorColor color) {
		this.color = color;
	}
	
	
}

