package com.server.actions;

import com.server.model.gamelogic.Game;

public class BuyAssistant {

	private Game game;
	
	public BuyAssistant(){}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public boolean isValid(){
		if(game.getActualPlayer().getCoins()>=3)
			return true;
		else
			return false;
	}
	
	/**
	 * Buy ONE assistant
	 */
	
	public void execute() {
		game.getActualPlayer().addCoins(-3);
		game.getActualPlayer().addAssistant(game.getMap().getAssistant(1));
	}
}
