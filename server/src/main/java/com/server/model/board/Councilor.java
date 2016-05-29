package com.server.model.board ;

import com.communication.board.CouncilorDTO;
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
	
	public boolean equals(CouncilorDTO cDTO){
		if(this.color.equals(cDTO.getColor()))
			return true;
		return false;
	}
	
}

