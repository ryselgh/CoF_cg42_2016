package com.server.broadcast ;

import java.util.ArrayList;


public class PermitsDeckInfo{



	private ArrayList<PermitsCardInfo> permitsDeck;
	private PermitsCardInfo[] slot = new PermitsCardInfo[2];

	/**
	 * permitsDeck is the constructor of the deck. 
	 * @param p is the element in PermitsCard (array)
	 * @param r is the code of the region. can be 0,1,2 based on the region we considered
	 */

	public PermitsDeckInfo(ArrayList<PermitsCardInfo> p, int r) {
		setPermitsDeck(p);
	}

	/**
	 * @param index index is the number of the slot. can be 0,1 
	 * @return the permit in the slot
	 */
	public PermitsCardInfo getSlot(int index) {
			return this.slot[index];
	}

	/**
	 * @return the permitsDeck
	 */
	public ArrayList<PermitsCardInfo> getPermitsDeck() {
		return permitsDeck;
	}

	/**
	 * @param permitsDeck the permitsDeck to set
	 */
	public void setPermitsDeck(ArrayList<PermitsCardInfo> permitsDeck) {
		this.permitsDeck = permitsDeck;
	}


}

