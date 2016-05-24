package com.server.actions;

import java.util.ArrayList;

import com.server.model.gamelogic.Game;

public class BuyAssistant extends Action{

	private Game game;
	private ArrayList<String> errors;
	private boolean disable = false;
	
	public BuyAssistant(){
		errors = new ArrayList<String>();
	}
	
	public void setGame(Game game){
		this.game = game;
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
		game.getActualPlayer().addCoins(-3);
		game.getActualPlayer().addAssistant(game.getMap().getAssistant(1));
		return new ActionReturn(true,"",false,false);
	}
}
