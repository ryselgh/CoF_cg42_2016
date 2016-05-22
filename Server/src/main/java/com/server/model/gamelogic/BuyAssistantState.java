package com.server.model.gamelogic;

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
