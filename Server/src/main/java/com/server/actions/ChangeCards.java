package com.server.actions;

import com.server.model.gamelogic.Game;

public class ChangeCards {

	private Game game;
	private int balconyIndex;
	
	public ChangeCards(int balconyIndex){
		this.balconyIndex = balconyIndex;
	}
	/**
	 * Verify if you can change the permits (you must have at least one assistant)
	 * @return true if you can change the permits, false if not
	 */
	
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>0)
			return true;
		return false;
	}
	
	/**
	 * Change permits on the ground of the selected deck
	 * @param balconyIndex the selected deck; 0: sea, 1: hill, 2: mountain.
	 */
	
	public void execute() {
		game.getMap().getPermitsDeck(balconyIndex).changeCards();
		game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
	}
}
