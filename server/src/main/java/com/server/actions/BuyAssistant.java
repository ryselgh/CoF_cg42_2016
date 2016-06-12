package com.server.actions;

import java.util.ArrayList;

import com.server.model.gamelogic.Game;

public class BuyAssistant extends Action{

	private ArrayList<String> errors;
	private boolean disable = false;
	
	public BuyAssistant(){
		errors = new ArrayList<String>();
	}
	
	public boolean isValid(){
		if(game.getActualPlayer().getCoins()>=3)
			return true;
		errors.add("You have not enought coins");
		disable=true;
		return false;
	}
	
	/**
	 * Buy ONE assistant
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
