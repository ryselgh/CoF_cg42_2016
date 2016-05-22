package com.server.model.gamelogic;

import com.server.model.board.City;

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
