package com.server.actions;

import java.util.ArrayList;

import com.server.model.gamelogic.Game;

public class ChangeCards extends Action{

	private Game game;
	private int balconyIndex;
	private ArrayList<String> errors;
	private boolean disable = false;
	
	public ChangeCards(int balconyIndex){
		this.balconyIndex = balconyIndex;
		errors = new ArrayList<String>();
	}
	/**
	 * Verify if you can change the permits (you must have at least one assistant)
	 * @return true if you can change the permits, false if not
	 */
	
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>0)
			return true;
		errors.add("You have not enought assistants");
		disable=true;
		return false;
	}
	
	/**
	 * Change permits on the ground of the selected deck
	 * @param balconyIndex the selected deck; 0: sea, 1: hill, 2: mountain.
	 */
	
	public ActionReturn execute() {
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
		game.getMap().getPermitsDeck(balconyIndex).changeCards();
		game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
		return new ActionReturn(true,"",null);
	}
	
	public void setGame(Game game){
		this.game = game;
	}
}
