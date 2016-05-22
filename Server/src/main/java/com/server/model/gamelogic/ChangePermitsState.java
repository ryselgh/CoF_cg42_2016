package com.server.model.gamelogic;

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
