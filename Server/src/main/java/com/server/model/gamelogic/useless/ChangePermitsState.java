package com.server.model.gamelogic.useless;

import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.SpeedAction;
import com.server.model.gamelogic.State;

public class ChangePermitsState extends SpeedAction implements State{
	
	private int selection;

	public ChangePermitsState(Game game, int selection) {
		super(game);
		this.selection = selection;
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.changePermitsCards(selection);
			return true;
		}
		return false;
	}
}
