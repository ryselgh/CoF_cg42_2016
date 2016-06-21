package com.communication.actions;

import java.io.Serializable;

import com.communication.decks.PoliticsCardDTO;
import com.communication.board.*;


/**
 * The Class SatisfyKingDTO.
 */
public class SatisfyKingDTO extends ActionDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -128520425439341078L;
	
	/** The array of politicsCard (DTO). */
	private PoliticsCardDTO[] politics; 
	
	/** The destination(DTO). */
	private CityDTO destination;
	
	/**
	 * Instantiates a new satisfy king dto.
	 */
	public SatisfyKingDTO(){}
	
	/**
	 * Gets the array of politicsCard(DTO).
	 *
	 * @return the array of PoliticsCard(DTO)
	 */
	public PoliticsCardDTO[] getPolitics() {
		return politics;
	}
	
	/**
	 * Sets  the array of politicsCard(DTO)
	 *
	 * @param politics the  the array of politicsCard(DTO) to set
	 */
	public void setPolitics(PoliticsCardDTO[] politics) {
		this.politics = politics;
	}
	
	/**
	 * Gets the destination(DTO).
	 *
	 * @return the destination
	 */
	public CityDTO getDestination() {
		return destination;
	}
	
	/**
	 * Sets the destination(DTO).
	 *
	 * @param destination the destination to set
	 */
	public void setDestination(CityDTO destination) {
		this.destination = destination;
	}
    
}
