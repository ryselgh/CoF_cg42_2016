package com.server.model.board;

public class Balcony
{

	private Councilor[] councilor;
	//private Region region;

	/**
	 * constructor of the balcony
	 * @param c c is the array of the councilors
	 */

	public Balcony(Councilor[] c) {
		if(c == null)
			throw new IllegalArgumentException("Argument not valid.");
		else
			this.councilor = c; 
		
		
	}

	/**
	 * @return the councilors in that balcony
	 */

	public Councilor[] getCouncilors() {
		return this.councilor; 
	}

	/**
	 * @param councilor the councilor to set
	 */
	public void setCouncilor(Councilor[] councilor) {
		this.councilor = councilor;
	}


	// public Region getRegion() {
	//  return region;
	// }

}

