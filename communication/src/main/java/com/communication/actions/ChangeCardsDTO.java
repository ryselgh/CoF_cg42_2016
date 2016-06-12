package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.gamelogic.GameDTO;

public class ChangeCardsDTO extends ActionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1141199441417494594L;
	private int balconyIndex;
	
	public ChangeCardsDTO(){}
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
	
}
