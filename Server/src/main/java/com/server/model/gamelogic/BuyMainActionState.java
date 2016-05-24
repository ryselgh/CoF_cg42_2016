package com.server.model.gamelogic;

public class BuyMainActionState extends SpeedAction implements State{


	public BuyMainActionState(Game game) {
		super(game);
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.buyMainAction();
			return true;
		}
		return false;
	}
}
