package com.server.actions;

import java.util.ArrayList;




/**
 * The Class BuyAssistant.
 */
public class BuyAssistant extends Action{

	/** The errors. */
	private ArrayList<String> errors;
	
	/** The disable. */
	private boolean disable = false;
	
	/**
	 * Instantiates a new buy assistant.
	 */
	public BuyAssistant(){
		errors = new ArrayList<String>();
	}
	
	/* 
	 * checks if the action is valid.(true)
	 * adds an error if the condition is not respected(false) 
	 *
	 */
	public boolean isValid(){
		if(game.getActualPlayer().getCoins()>=3)
			return true;
		errors.add("You have not enought coins");
		disable=true;
		return false;
	}
	
	/**
	 * If all it's ok you Buy ONE assistant.
	 *  if there is an error gives a string error
	 *
	 * @return the action return
	 */
	
	public ActionReturn execute() {
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
		game.getActualPlayer().addCoins(-3);
		game.getActualPlayer().addAssistant(game.getMap().getAssistant(1));
		return new ActionReturn(true,"",null);
	}
}
