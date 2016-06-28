package com.communication.actions;

import java.io.Serializable;


import com.communication.decks.PoliticsCardDTO;



/**
 * The Class ObtainPermitDTO.
 */
public class ObtainPermitDTO extends ActionDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2281940547406660429L;
	
	/** The politics(DTO). */
	private PoliticsCardDTO[] politics;
	
	/** The region index. */
	private int regionIndex;
    
    /** The slot. */
    private int slot;
	
	/**
	 * Instantiates a new obtain permit dto.
	 */
	public ObtainPermitDTO(){}
	
	/**
	 * Gets the array of politicsCard(DTO).
	 *
	 * @return the array of politicsCard (DTO)
	 */
	public PoliticsCardDTO[] getPolitics() {
		return politics;
	}
	
	/**
	 * Sets the array of politicsCard(DTO).
	 *
	 * @param politics the politics to set
	 */
	public void setPolitics(PoliticsCardDTO[] politics) {
		this.politics = politics;
	}
	
	/**
	 * Gets the region index.
	 *
	 * @return the regionIndex
	 */
	public int getRegionIndex() {
		return regionIndex;
	}
	
	/**
	 * Sets the region index.
	 *
	 * @param regionIndex the regionIndex to set
	 */
	public void setRegionIndex(int regionIndex) {
		this.regionIndex = regionIndex;
	}
	
	/**
	 * Gets the slot.
	 *
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}
	
	/**
	 * Sets the slot.
	 *
	 * @param slot the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}
    
}
