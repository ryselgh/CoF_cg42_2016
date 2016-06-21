package com.communication.board ;

import java.io.Serializable;


/**
 * The Class KingDTO.
 */
public class KingDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8509422918979755207L;
	
	/** The location(DTO). */
	private CityDTO location;
	
	
	/**
	 * Gets the location(DTO).
	 *
	 * @return the king location(DTO)
	 */
	
	public CityDTO getLocation() {
		return this.location;	
	}
	
	/**
	 * set the king location(DTO).
	 *
	 * @param l the new location(DTO)
	 */
	
	public void setLocation(CityDTO l) {
		this.location = l;	
	}
	
}

