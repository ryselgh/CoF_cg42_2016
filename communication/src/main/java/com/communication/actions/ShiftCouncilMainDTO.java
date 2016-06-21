package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.CouncilorDTO;
import com.communication.gamelogic.GameDTO;


/**
 * The Class ShiftCouncilMainDTO.
 */
public class ShiftCouncilMainDTO extends ActionDTO implements Serializable{
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2181518839576549434L;
	
	/** The balcony index. */
	private int balconyIndex;
	
	/** The councilor(DTO). */
	private CouncilorDTO councilor;
	
	/**
	 * Instantiates a new shift council main dto.
	 */
	public ShiftCouncilMainDTO(){}
	
	/**
	 * Gets the balcony index.
	 *
	 * @return the balconyIndex
	 */
	public int getBalconyIndex() {
		return balconyIndex;
	}
	
	/**
	 * Sets the balcony index.
	 *
	 * @param balconyIndex the balconyIndex to set
	 */
	public void setBalconyIndex(int balconyIndex) {
		this.balconyIndex = balconyIndex;
	}
	
	/**
	 * Gets the councilor.
	 *
	 * @return the councilor(DTO)
	 */
	public CouncilorDTO getCouncilor() {
		return councilor;
	}
	
	/**
	 * Sets the councilor(DTO).
	 *
	 * @param councilor the councilor(DTO) to set
	 */
	public void setCouncilor(CouncilorDTO councilor) {
		this.councilor = councilor;
	}
	
}
