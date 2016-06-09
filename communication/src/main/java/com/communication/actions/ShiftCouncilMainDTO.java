package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.CouncilorDTO;
import com.communication.gamelogic.GameDTO;

public class ShiftCouncilMainDTO extends ActionDTO implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -2181518839576549434L;
	private int balconyIndex;
	private CouncilorDTO councilor;
	
	public ShiftCouncilMainDTO(){}
	
	/**
	 * @return the balconyIndex
	 */
	public int getBalconyIndex() {
		return balconyIndex;
	}
	/**
	 * @param balconyIndex the balconyIndex to set
	 */
	public void setBalconyIndex(int balconyIndex) {
		this.balconyIndex = balconyIndex;
	}
	/**
	 * @return the councilor
	 */
	public CouncilorDTO getCouncilor() {
		return councilor;
	}
	/**
	 * @param councilor the councilor to set
	 */
	public void setCouncilor(CouncilorDTO councilor) {
		this.councilor = councilor;
	}
	
}
