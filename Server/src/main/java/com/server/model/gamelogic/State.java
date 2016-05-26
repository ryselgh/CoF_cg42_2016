package com.server.model.gamelogic;


public interface State {

	public void doAction(Context context);
	
	public void restoreState();
}