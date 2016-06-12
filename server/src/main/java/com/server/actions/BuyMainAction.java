package com.server.actions;

import java.util.ArrayList;

import com.server.model.board.Bonus;
import com.server.model.gamelogic.Game;
import com.communication.values.BonusType;

public class BuyMainAction extends Action{
	private ArrayList<String> errors;
	private boolean disable = false;
	
	public BuyMainAction(){
		errors = new ArrayList<String>();
		
	}
		
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>=3)
			return true;
		errors.add("You have not enought assistants");
		disable=true;
		return false;
	}
	
	/**
	 * Buy an additional main action
	 */
	
	public ActionReturn execute() {
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
		for(int i=0;i<3;i++)
			game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
		Bonus[] toRet = new Bonus[] {new Bonus(BonusType.MAINACTION,1)};
		return new ActionReturn(true,"",toRet);
	}
}
