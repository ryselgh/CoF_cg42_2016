package com.communication.decks ;

import java.io.Serializable;
import java.util.ArrayList;

public class PoliticsDeckDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7820391438178142842L;
	private ArrayList<PoliticsCardDTO> politicsDeck;
	private ArrayList<PoliticsCardDTO> garbage;
	
	
	/**
	 * @return the politicsDeck
	 */
	public ArrayList<PoliticsCardDTO> getPoliticsDeck() {
		return politicsDeck;
	}
	/**
	 * @param politicsDeck the politicsDeck to set
	 */
	public void setPoliticsDeck(ArrayList<PoliticsCardDTO> politicsDeck) {
		this.politicsDeck = politicsDeck;
	}
	/**
	 * @return the garbage
	 */
	public ArrayList<PoliticsCardDTO> getGarbage() {
		return garbage;
	}
	/**
	 * @param garbage the garbage to set
	 */
	public void setGarbage(ArrayList<PoliticsCardDTO> garbage) {
		this.garbage = garbage;
	}

	

}

