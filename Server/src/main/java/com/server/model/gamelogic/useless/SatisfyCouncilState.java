package com.server.model.gamelogic.useless;

import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.MainAction;
import com.server.model.gamelogic.State;

public class SatisfyCouncilState extends MainAction implements State{
	
	private int regionIndex;
	private int slot;

	public SatisfyCouncilState(Game game, int regionIndex, int slot) {
		super(game);
		this.regionIndex = regionIndex;
		this.slot = slot;
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.obtainPermit(regionIndex, slot);
			return true;
		}
		return false;
	}
	
}
