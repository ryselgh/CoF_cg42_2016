package com.communication.decks ;

import java.io.Serializable;
import java.util.ArrayList;



public class PermitsDeckDTO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -4626740472611528797L;
	private ArrayList<PermitsCardDTO> permitsDeck;
	private PermitsCardDTO[] slot;
	private int regionCode;
	
	/**
	 * @return the permitsDeck
	 */
	public ArrayList<PermitsCardDTO> getPermitsDeck() {
		return permitsDeck;
	}
	/**
	 * @param permitsDeck the permitsDeck to set
	 */
	public void setPermitsDeck(ArrayList<PermitsCardDTO> permitsDeck) {
		this.permitsDeck = permitsDeck;
	}
	/**
	 * @return the slot
	 */
	public PermitsCardDTO[] getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(PermitsCardDTO[] slot) {
		this.slot = slot;
	}
	/**
	 * @return the regionCode
	 */
	public int getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}

	
}

