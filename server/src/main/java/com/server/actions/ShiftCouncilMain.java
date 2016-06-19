package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.actions.ActionDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.board.CouncilorDTO;
import com.server.model.board.Councilor;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ShiftCouncilMain extends Action{
	
	private int balconyIndex;
	private Councilor councilor;
	private ArrayList<String> errors;
	private boolean disable = false;
	
	public ShiftCouncilMain(int balconyIndex, Councilor councilor){
		this.balconyIndex = balconyIndex;
		this.councilor = councilor;
		errors = new ArrayList<String>();
	}
	
	public boolean isValid(){
		for(Councilor c : game.getMap().getCouncilorsPool()){
			if(c.equals(councilor)){
				councilor = c;//aggiorno l'istanza per l'execute
				game.getMap().getCouncilorsPool().remove(c);
				return true;
			}
		}
		errors.add("Invalid input councilor");
		return false;
	}
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
		game.getActualPlayer().addCoins(4);
		return new ActionReturn(true,"",null);
	}
	
	@Override
	public void setterFromDTO(ActionDTO DTO,Player player,Game game){
		this.game = game;
		ShiftCouncilMainDTO scmDTO = (ShiftCouncilMainDTO) DTO;
		this.balconyIndex = scmDTO.getBalconyIndex();
		this.councilor = getCouncilorFromDTO(scmDTO.getCouncilor());
	}
	//public boolean equals(stica)
	private Councilor getCouncilorFromDTO(CouncilorDTO cDTO){
		for(Councilor c : game.getMap().getCouncilorsPool())
			if(c.equalsDTO(cDTO))
				return c;
		return null;
	}
}
