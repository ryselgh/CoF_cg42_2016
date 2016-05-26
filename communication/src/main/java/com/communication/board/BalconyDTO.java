package com.communication.board;

import java.io.Serializable;

public class BalconyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4390282457125801318L;
	private CouncilorDTO[] councilor;

	/**
	 * @return the councilor
	 */
	public CouncilorDTO[] getCouncilor() {
		return councilor;
	}

	/**
	 * @param councilor the councilor to set
	 */
	public void setCouncilor(CouncilorDTO[] councilor) {
		this.councilor = councilor;
	}

}

