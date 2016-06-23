package com.server.model.board ;

import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;


public class Councilor
{
	
	
	private CouncilorColor color;
	

	/**
	 * the constructor of the councilor
	 * @param cc cc is the color of the councilor
	 */
	
	public Councilor(CouncilorColor cc) {
		if (cc==null)
			throw new NullPointerException();
		else
			this.color = cc;	
	}
	
	/**
	 * @return the color of the councilor
	 */
	
	public CouncilorColor getCouncilorColor() {
		return this.color;	
	}
	
	public boolean isEquals(Councilor c){
		return this.color.equals(c.color);
	}
	
	public boolean equalsDTO(CouncilorDTO cDTO){
		if (!(cDTO == null)) {
			if (this.color.equals(cDTO.getColor()))
				return true;
			return false;
		} else
			throw new NullPointerException("cDTO cannot be null");
	}
	
	public CouncilorDTO toDTO(){
		CouncilorDTO cDTO = new CouncilorDTO();
		cDTO.setColor(this.getCouncilorColor());
		return cDTO;
	}
}

