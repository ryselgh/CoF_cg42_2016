package com.server.model.board ;

import com.server.values.CouncilorColor;


public class Councilor
{
	
	
	private CouncilorColor color;
	

	/**
	 * the constructor of the councilor
	 * @param cc cc is the color of the councilor
	 */
	
	public Councilor(CouncilorColor cc) {
		this.color = cc;	
	}
	
	/**
	 * @return the color of the councilor
	 */
	
	public CouncilorColor getCouncilorColor() {
		return this.color;	
	}
	
}

