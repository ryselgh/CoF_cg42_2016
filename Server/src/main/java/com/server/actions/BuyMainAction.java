package com.server.actions;

import java.util.ArrayList;

import com.server.model.gamelogic.Game;

public class BuyMainAction extends Action{
	private Game game;
	private ArrayList<String> errors;
	private boolean disable = false;
	
	public BuyMainAction(){
		errors = new ArrayList<String>();};
	
	public void setGame(Game game){
		this.game = game;
		errors = new ArrayList<String>();
	}
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>=3)
			return true;
		errors.add("You have not enought assistants");
		disable=true;
		return false;
	}
	
	/**
	 * Buy an additional main action
	 */
	
	public ActionReturn execute() {
		for(int i=0;i<3;i++)
			game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
		return new ActionReturn(true,"",false,true);
	}
}
