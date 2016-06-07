package com.server.model.board;

import com.communication.board.BalconyDTO;
import com.communication.board.CouncilorDTO;

public class Balcony
{

	private Councilor[] councilor;
	//private Region region;

	/**
	 * constructor of the balcony
	 * @param c c is the array of the councilors
	 */

	public Balcony(Councilor[] c) {
		if(c == null)
			throw new IllegalArgumentException("Argument not valid.");
		this.councilor = c; 
		
		
	}

	/**
	 * @return the councilors in that balcony
	 */

	public Councilor[] getCouncilors() {
		return this.councilor; 
	}

	/**
	 * @param councilor the councilor to set
	 */
	public void setCouncilor(Councilor[] councilor) {
		this.councilor = councilor;
	}


	public BalconyDTO toDTO(){
		BalconyDTO bDTO = new BalconyDTO();
		CouncilorDTO[] councDTO = new CouncilorDTO[this.getCouncilors().length];
		for(int i=0;i<this.getCouncilors().length;i++)
			councDTO[i]=this.getCouncilors()[i].toDTO();
		return bDTO;
			
		
	}

}

