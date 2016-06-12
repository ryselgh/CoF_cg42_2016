package com.server.model.board ;

import java.util.ArrayList;

import com.communication.board.KingDTO;
import com.communication.gamelogic.PlayerDTO;

public class King
{
	
	
	private City location;
	

	/**
	 * constructor of the king
	 * @param c c is the king city
	 */
	
	public King(City c) {
		if (c==null)
			throw new NullPointerException();
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
	
	public KingDTO toDTO(ArrayList<PlayerDTO> plsDTO){
		KingDTO kDTO = new KingDTO();
		kDTO.setLocation(this.getLocation().toDTO(plsDTO));
		return kDTO;
	}
}

