package com.server.model.gamelogic.useless;

import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.SpeedAction;
import com.server.model.gamelogic.State;

public class BuyAssistantState extends SpeedAction implements State{

	public BuyAssistantState(Game game) {
		super(game);
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.buyAssistant();
			return true;
		}
		return false;
	}
	
}
