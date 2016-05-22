package com.server.model.gamelogic;

@FunctionalInterface
public interface State {

	public boolean doAction(Context context);
	
}