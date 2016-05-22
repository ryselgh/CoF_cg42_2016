package com.server.model.gamelogic;

import com.server.model.board.City;

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
