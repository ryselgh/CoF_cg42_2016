package com.server.actions;


/**
 * The Class Pass.
 */
public class Pass extends Action{
	
	/**
	 * Instantiates a new pass.
	 */
	public Pass(){}
	
	/* 
	 * executes the Pass Action
	 */
	public ActionReturn execute() {
		return new ActionReturn(true,"",null);
	}
}
