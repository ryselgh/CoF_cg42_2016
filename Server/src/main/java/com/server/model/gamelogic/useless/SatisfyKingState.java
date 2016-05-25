package com.server.model.gamelogic.useless;

import com.server.model.board.City;
import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.MainAction;
import com.server.model.gamelogic.State;

public class SatisfyKingState extends MainAction implements State{

	private City toCity;
	
	public SatisfyKingState(Game game, City toCity) {
		super(game);
		this.toCity = toCity;
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.moveKing(toCity);
			return true;
		}
		return false;
	}

}
