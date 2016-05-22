package com.server.model.gamelogic;

import com.server.model.board.Councilor;

public class ShiftCouncilStateSA extends SpeedAction implements State{
	
	private int selection;
	private Councilor councilor;

	public ShiftCouncilStateSA(Game game, int selection, Councilor councilor) {
		super(game);
		this.selection = selection;
		this.councilor = councilor;
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.shiftCouncil(selection, councilor);
			return true;
		}
		return false;
	}
}
