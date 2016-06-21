package com.server.actions;

import java.util.ArrayList;

import com.server.model.board.Bonus;
import com.server.model.gamelogic.Game;
import com.communication.values.BonusType;


/**
 * The Class BuyMainAction.
 */
public class BuyMainAction extends Action{
	
	/** The errors. */
	private ArrayList<String> errors;
	
	/** The disable. */
	private boolean disable = false;
	
	/**
	 * Instantiates a new buy main action.
	 */
	public BuyMainAction(){
		errors = new ArrayList<String>();
		
	}
		
	/* 
	 * return true if the player satisfies the condition
	 * return false if the player doesn't satisfy the condition. add an error too
	 */
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>=3)
			return true;
		errors.add("You have not enought assistants");
		disable=true;
		return false;
	}
	
	/**
	 * if all it's ok Buy an additional main action.
	 *  if there is an error gives a string error
	 *
	 * @return the action return
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
