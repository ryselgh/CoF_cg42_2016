package com.server.actions;

import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;

public class ActionReturn {

	String error;
	boolean success;
	Bonus[] bonus;
	
	public ActionReturn(boolean success, String error, Bonus[] bonus){
		this.success = success;
		this.error = error;
		this.bonus = bonus;
	}

	public String getError() {
		return error;
	}

	public boolean isSuccess() {
		return success;
	}

	public Bonus[] getBonus() {
		return bonus;
	}
	
}
