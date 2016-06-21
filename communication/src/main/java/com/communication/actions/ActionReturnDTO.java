package com.communication.actions;

import java.io.Serializable;

import com.communication.board.BonusDTO;


/**
 * The Class ActionReturnDTO.
 */
public class ActionReturnDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4504577755474956949L;
	
	/** The error. */
	String error;
	
	/** The success. */
	boolean success;
	
	/** The bonusDTO array. */
	BonusDTO[] bonus;
	
}
