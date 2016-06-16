package com.server.actions;

public class Pass extends Action{
	public Pass(){}
	
	public ActionReturn execute() {
		return new ActionReturn(true,"",null);
	}
}
