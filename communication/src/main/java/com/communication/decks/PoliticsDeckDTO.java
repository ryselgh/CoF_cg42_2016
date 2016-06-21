package com.communication.decks ;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The Class PoliticsDeckDTO.
 */
public class PoliticsDeckDTO implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7820391438178142842L;
	
	/** The politics deck(DTO). */
	private ArrayList<PoliticsCardDTO> politicsDeck;
	
	/** The garbage(DTO). */
	private ArrayList<PoliticsCardDTO> garbage;
	
	
	/**
	 * Gets the politics deck(DTO).
	 *
	 * @return the politicsDeck(DTO)
	 */
	public ArrayList<PoliticsCardDTO> getPoliticsDeck() {
		return politicsDeck;
	}
	
	/**
	 * Sets the politics deck(DTO).
	 *
	 * @param politicsDeck the politicsDeck(DTO) to set
	 */
	public void setPoliticsDeck(ArrayList<PoliticsCardDTO> politicsDeck) {
		this.politicsDeck = politicsDeck;
	}
	
	/**
	 * Gets the garbage(DTO).
	 *
	 * @return the garbage(DTO)
	 */
	public ArrayList<PoliticsCardDTO> getGarbage() {
		return garbage;
	}
	
	/**
	 * Sets the garbage(DTO).
	 *
	 * @param garbage the garbage(DTO) to set
	 */
	public void setGarbage(ArrayList<PoliticsCardDTO> garbage) {
		this.garbage = garbage;
	}

	

}

