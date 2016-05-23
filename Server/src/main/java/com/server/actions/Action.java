package com.server.actions;

import com.server.model.gamelogic.Game;

public class Action {

	public void execute(){};
	public boolean isValid(){return true;};//alcune non hanno bisogno di verifica quindi true di default
	public void setGame(Game game){};
	
}
