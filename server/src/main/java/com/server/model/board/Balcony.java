package com.server.model.board;

import com.communication.board.BalconyDTO;
import com.communication.board.CouncilorDTO;


/**
 * The Class Balcony.
 */
public class Balcony
{

	/** The councilor. */
	private Councilor[] councilor;
	//private Region region;

	/**
	 * constructor of the balcony.
	 *
	 * @param c c is the array of the councilors
	 */

	public Balcony(Councilor[] c) {
		if(c == null)
			throw new IllegalArgumentException("Argument not valid.");
		this.councilor = c; 
		
		
	}

	/**
	 * Gets the councilors(array).
	 *
	 * @return the councilors in that balcony
	 */

	public Councilor[] getCouncilors() {
		return this.councilor; 
	}

	/**
	 * Sets the councilor.
	 *
	 * @param councilor the councilors(array) to set
	 */
	public void setCouncilor(Councilor[] councilor) {
		this.councilor = councilor;
	}


	/**
	 * To dto.
	 *
	 * @return the balcony dto
	 */
	public BalconyDTO toDTO(){
		BalconyDTO bDTO = new BalconyDTO();
		CouncilorDTO[] councDTO = new CouncilorDTO[this.getCouncilors().length];
		for(int i=0;i<this.getCouncilors().length;i++)
			councDTO[i]=this.getCouncilors()[i].toDTO();
		bDTO.setCouncilor(councDTO);
		return bDTO;
			
		
	}

}

