package com.communication.board;

import java.io.Serializable;


/**
 * The Class BalconyDTO.
 */
public class BalconyDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4390282457125801318L;
	
	/** The array of councilor(DTO). */
	private CouncilorDTO[] councilor;

	/**
	 * Gets the array of councilor(DTO).
	 *
	 * @return the array of councilor(DTO)
	 */
	public CouncilorDTO[] getCouncilor() {
		return councilor;
	}

	/**
	 * Sets the array of councilor(DTO).
	 *
	 * @param councilor the array of councilor(DTO) to set
	 */
	public void setCouncilor(CouncilorDTO[] councilor) {
		this.councilor = councilor;
	}

}

