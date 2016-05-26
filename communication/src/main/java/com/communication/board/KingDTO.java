package com.communication.board ;

import java.io.Serializable;

public class KingDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8509422918979755207L;
	private CityDTO location;
	
	
	/**
	 * @return the king location
	 */
	
	public CityDTO getLocation() {
		return this.location;	
	}
	
	/**
	 * set the king location
	 */
	
	public void setLocation(CityDTO l) {
		this.location = l;	
	}
	
}

