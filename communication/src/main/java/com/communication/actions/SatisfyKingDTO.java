package com.communication.actions;

import java.io.Serializable;

import com.communication.decks.PoliticsCardDTO;
import com.communication.board.*;

public class SatisfyKingDTO extends ActionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -128520425439341078L;
	private PoliticsCardDTO[] politics; 
	private CityDTO destination;
	
	public SatisfyKingDTO(){}
	/**
	 * @return the politics
	 */
	public PoliticsCardDTO[] getPolitics() {
		return politics;
	}
	/**
	 * @param politics the politics to set
	 */
	public void setPolitics(PoliticsCardDTO[] politics) {
		this.politics = politics;
	}
	/**
	 * @return the destination
	 */
	public CityDTO getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(CityDTO destination) {
		this.destination = destination;
	}
    
}
