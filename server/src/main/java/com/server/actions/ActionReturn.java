package com.server.actions;

import com.server.model.board.Bonus;


/**
 * The Class ActionReturn.
 */
public class ActionReturn {

	/** The error. */
	String error;
	
	/** The success. */
	boolean success;
	
	/** The bonus. */
	Bonus[] bonus;
	
	/**
	 * Instantiates a new action return.
	 *
	 * @param success the success
	 * @param error the error
	 * @param bonus the bonus
	 */
	public ActionReturn(boolean success, String error, Bonus[] bonus){
		this.success = success;
		this.error = error;
		this.bonus = bonus;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Gets the bonus.
	 *
	 * @return the bonus
	 */
	public Bonus[] getBonus() {
		return bonus;
	}
	
}
