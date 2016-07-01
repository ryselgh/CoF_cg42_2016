package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.CouncilorDTO;
import com.server.model.board.Councilor;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;


/**
 * The Class ShiftCouncilSpeed.
 */
public class ShiftCouncilSpeed extends Action{
	
	/** The balcony index. */
	private int balconyIndex;
	
	/** The councilor. */
	private Councilor councilor;
	
	/** The errors. */
	private ArrayList<String> errors;
	

	
	/**
	 * Instantiates a new shift council speed.
	 *
	 * @param balconyIndex the balcony index
	 * @param councilor the councilor
	 */
	public ShiftCouncilSpeed(int balconyIndex, Councilor councilor){
		this.balconyIndex = balconyIndex;
		this.councilor = councilor;
		errors = new ArrayList<String>();
	}
	
	/* 
	 * check if the action is valid
	 * if it's not adds an error
	 */
	public boolean isValid(){
		for(Councilor c : game.getMap().getCouncilorsPool()){
			if(c.isEquals(councilor)){
				councilor = c;//aggiorno l'istanza per l'execute
				game.getMap().getCouncilorsPool().remove(c);
				return true;
			}
		}
		errors.add("Invalid input councilor");
		return false;
	}
	
	/* 
	 * shifts the councilor(pay one assistant)
	 * if there is an error gives a string error
	 */
	public ActionReturn execute() {
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
		
		ArrayList<Councilor> tmpBalcony = new ArrayList<Councilor>();
		Councilor[] temp = game.getMap().getBalcony(balconyIndex).getCouncilors();
		tmpBalcony.addAll(Arrays.asList(temp));
		game.getMap().getCouncilorsPool().add(tmpBalcony.get(0));
		tmpBalcony.remove(0);
		tmpBalcony.add(councilor);
		game.getMap().getCouncilorsPool().remove(councilor);
		game.getMap().getBalcony(balconyIndex).setCouncilor(tmpBalcony.toArray(new Councilor[0]));
		game.getMap().getAssistantsPool().add(game.getActualPlayer().getAvailableAssistants().remove(0));
		return new ActionReturn(true,"",null);
		
	}
	
	/**
	 * Setter from dto.
	 *
	 * @param scmDTO the scm dto
	 * @param player the player
	 * @param game the game
	 */
	public void setterFromDTO(ShiftCouncilSpeedDTO scmDTO,Player player,Game game){
		this.game = game;
		this.balconyIndex = scmDTO.getBalconyIndex();
		this.councilor = getCouncilorFromDTO(scmDTO.getCouncilor());
	}
	
	/**
	 * Gets the councilor from dto.
	 *
	 * @param cDTO the c dto
	 * @return the councilor from dto
	 */
	private Councilor getCouncilorFromDTO(CouncilorDTO cDTO){
		for(Councilor c : game.getMap().getCouncilorsPool())
			if(c.equalsDTO(cDTO))
				return c;
		return null;
	}
}
