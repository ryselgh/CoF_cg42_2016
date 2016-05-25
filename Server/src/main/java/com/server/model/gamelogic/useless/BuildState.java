package com.server.model.gamelogic.useless;

import com.server.model.board.City;
import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.MainAction;
import com.server.model.gamelogic.State;

public class BuildState extends MainAction implements State{

	private City city;
	
	
	public BuildState(Game game, City city) {
		super(game);
		this.city = city;
	}

	@Override
	public boolean doAction(Context context) {
		if(super.getActionCounter()>0){
			super.build(city);
			return true;
		}
		return false;
	}
	

}
