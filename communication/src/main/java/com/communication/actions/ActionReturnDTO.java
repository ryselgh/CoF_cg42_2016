package com.communication.actions;

import java.io.Serializable;

import com.communication.board.BonusDTO;

public class ActionReturnDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4504577755474956949L;
	String error;
	boolean success;
	BonusDTO[] bonus;
	
}
