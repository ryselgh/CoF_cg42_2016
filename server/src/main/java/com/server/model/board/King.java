package com.server.model.board ;

import com.communication.board.KingDTO;

public class King
{
	
	
	private City location;
	

	/**
	 * constructor of the king
	 * @param c c is the king city
	 */
	
	public King(City c) {
		this.location = c;	
	}
	
	/**
	 * @return the king location
	 */
	
	public City getLocation() {
		return this.location;	
	}
	
	/**
	 * set the king location
	 */
	
	public void setLocation(City l) {
		this.location = l;	
	}
	
	public KingDTO toDTO(){
		KingDTO kDTO = new KingDTO();
		kDTO.setLocation(this.getLocation().toDTO());
		return kDTO;
	}
}

