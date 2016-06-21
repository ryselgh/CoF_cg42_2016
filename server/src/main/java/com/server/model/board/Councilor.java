package com.server.model.board ;

import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;



/**
 * The Class Councilor.
 */
public class Councilor
{
	
	
	/** The color. */
	private CouncilorColor color;
	

	/**
	 * the constructor of the councilor.
	 *
	 * @param cc cc is the color of the councilor
	 */
	
	public Councilor(CouncilorColor cc) {
		if (cc==null)
			throw new NullPointerException();
		else
			this.color = cc;	
	}
	
	/**
	 * Gets the councilor color.
	 *
	 * @return the color of the councilor
	 */
	
	public CouncilorColor getCouncilorColor() {
		return this.color;	
	}
	
	/**
	 * Equals dto.
	 *
	 * @param cDTO the CouncilorDTO
	 * @return true, if successful
	 */
	public boolean equalsDTO(CouncilorDTO cDTO){
		if (!(cDTO == null)) {
			if (this.color.equals(cDTO.getColor()))
				return true;
			return false;
		} else
			throw new NullPointerException("cDTO cannot be null");
	}
	
	/**
	 * To dto.
	 *
	 * @return the councilor dto
	 */
	public CouncilorDTO toDTO(){
		CouncilorDTO cDTO = new CouncilorDTO();
		cDTO.setColor(this.getCouncilorColor());
		return cDTO;
	}
}

