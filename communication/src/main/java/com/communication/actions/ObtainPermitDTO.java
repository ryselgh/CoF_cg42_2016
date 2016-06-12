package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;

public class ObtainPermitDTO extends ActionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2281940547406660429L;
	private PoliticsCardDTO[] politics;
	private int regionIndex;
    private int slot;
	
	public ObtainPermitDTO(){}
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
	 * @return the regionIndex
	 */
	public int getRegionIndex() {
		return regionIndex;
	}
	/**
	 * @param regionIndex the regionIndex to set
	 */
	public void setRegionIndex(int regionIndex) {
		this.regionIndex = regionIndex;
	}
	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}
    
}
