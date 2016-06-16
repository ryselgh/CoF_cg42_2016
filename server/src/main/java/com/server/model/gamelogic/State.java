package com.server.model.gamelogic;


public interface State {
	public String getStateID();
	public void doAction(Context context);
	
	public void restoreState();
}