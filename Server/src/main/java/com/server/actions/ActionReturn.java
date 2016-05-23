package com.server.actions;

public class ActionReturn {

	String error;
	boolean success;
	boolean disable; //se true il client non potrà più riproporre questa azione al giocatore
	
	public ActionReturn(boolean success, String error, boolean disable, boolean addMainBonus){
		this.success = success;
		this.error = error;
		this.disable = disable;
	}
}
