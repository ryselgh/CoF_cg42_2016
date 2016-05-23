package com.server.actions;

import com.server.model.gamelogic.Game;

public class BuyMainAction {
	private Game game;
	
	public BuyMainAction(){};
	
	public void setGame(Game game){
		this.game = game;
	}
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>=3)
			return true;
		else
			return false;
	}
	
	/**
	 * Buy an additional main action
	 */
	
	public void execute() {
		//da ritornare +1 main
		for(int i=0;i<3;i++)
			game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
	}
}
