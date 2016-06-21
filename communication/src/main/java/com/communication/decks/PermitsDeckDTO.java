package com.communication.decks ;

import java.io.Serializable;
import java.util.ArrayList;




/**
 * The Class PermitsDeckDTO.
 */
public class PermitsDeckDTO implements Serializable{



	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4626740472611528797L;
	
	/** The permits deck. */
	private ArrayList<PermitsCardDTO> permitsDeck;
	
	/** The slots. */
	private PermitsCardDTO[] slot;
	
	/** The region code. */
	private int regionCode;
	
	/**
	 * Gets the permits deck(DTO).
	 *
	 * @return the permitsDeck(DTO)
	 */
	public ArrayList<PermitsCardDTO> getPermitsDeck() {
		return permitsDeck;
	}
	
	/**
	 * Sets the permits deck(DTO).
	 *
	 * @param permitsDeck the permitsDeck(DTO) to set
	 */
	public void setPermitsDeck(ArrayList<PermitsCardDTO> permitsDeck) {
		this.permitsDeck = permitsDeck;
	}
	
	/**
	 * Gets the slot.
	 *
	 * @param i the number of the slot
	 * @return the slot
	 */
	public PermitsCardDTO getSlot(int i) {
		return slot[i];
	}
	
	/**
	 * Sets the slot(DTO).
	 *
	 * @param slot the slot(DTO) to set
	 */
	public void setSlot(PermitsCardDTO[] slot) {
		this.slot = slot;
	}
	
	/**
	 * Gets the region code.
	 *
	 * @return the regionCode
	 */
	public int getRegionCode() {
		return regionCode;
	}
	
	/**
	 * Sets the region code.
	 *
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}

	
}

