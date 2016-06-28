package com.server.actions;

import java.util.ArrayList;




/**
 * The Class ChangeCards.
 */
public class ChangeCards extends Action{

	/** The balcony index. */
	private int balconyIndex;
	
	/** The errors. */
	private ArrayList<String> errors;
	
	/** The disable. */
	private boolean disable = false;
	
	/**
	 * Instantiates a new change cards.
	 *
	 * @param balconyIndex the balcony index
	 */
	public ChangeCards(int balconyIndex){
		this.balconyIndex = balconyIndex;
		errors = new ArrayList<String>();
	}
	
	/**
	 * Verify if you can change the permits (you must have at least one assistant).
	 *
	 * @return true if you can change the permits, false if not
	 */
	
	public boolean isValid(){
		if(game.getActualPlayer().getAvailableAssistants().size()>0)
			return true;
		errors.add("You have not enought assistants");
		disable=true;
		return false;
	}
	
	/**
	 * Change permits on the ground of the selected deck
	 *  if there is an error gives a string error.
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
		game.getMap().getPermitsDeck(balconyIndex).changeCards();
		game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
		return new ActionReturn(true,"",null);
	}
}
