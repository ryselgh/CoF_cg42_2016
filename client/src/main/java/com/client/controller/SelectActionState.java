package com.client.controller;

import java.util.ArrayList;

import com.client.view.ClientCLI;
import com.communication.actions.ActionDTO;
import com.communication.actions.BuildDTO;
import com.communication.actions.BuyAssistantDTO;
import com.communication.actions.BuyMainActionDTO;
import com.communication.actions.ChangeCardsDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.PassDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.CityDTO;
import com.communication.board.CouncilorDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.values.CouncilorColor;

public class SelectActionState implements Runnable{
	private GameDTO game;
	private boolean[] availableActions;
	private ClientCLI cli;
	private SocketConnection connection;
	private int playerID;
	
	public SelectActionState(GameDTO game, boolean[] availableActions, 
			ClientCLI cli, SocketConnection connection, int plID){
		this.game = game;
		this.availableActions = availableActions;
		this.cli = cli;
		this.connection = connection;
		this.playerID = plID;
	}

	@Override
	public void run() {
		int selectedAction = cli.getAction(availableActions);
		ActionDTO compiledAction = getActionInstance(selectedAction);
		connection.sendToServer("INPUT_ACTION", compiledAction);
	}
	
	private ActionDTO getActionInstance(int selectedAction) {
		ArrayList<PoliticsCardDTO> polCards = new ArrayList<PoliticsCardDTO>();
		PoliticsCardDTO[] cardsRet;
		switch(selectedAction){
		case 3:
			BuildDTO build = new BuildDTO();
			PermitsCardDTO usedPermit = game.getActualPlayer().getPermits().get(cli.getBuildPermit(playerID));
			CityDTO[] avCity = new CityDTO[usedPermit.getCityLetter().length];
			int count = 0;
			for(int i=0;i<game.getMap().getCity().length;i++){
				for(String lett : usedPermit.getCityLetter())
					if(game.getMap().getCity()[i].getName().substring(0, 1).toLowerCase().equals(lett)){
						avCity[count] = game.getMap().getCity()[i];
						count++;
					}
			}
			cli.printMsg("Where do you want to build?");
			int buildHere = cli.getInputCity(avCity);
			build.setCity(avCity[buildHere]);
			build.setPermit(usedPermit);
			return build;
		case 0:
			ObtainPermitDTO obtPerm = new ObtainPermitDTO();
			int reg = cli.getTargetRegion(2);
			int slot = cli.waitCorrectIntInput("Insert slot number: 0= left  1=right", 0, 1);
			polCards = cli.waitInputCards(this.game.getActualPlayer().getHand());
			cardsRet = new PoliticsCardDTO[polCards.size()];
			cardsRet = polCards.toArray(cardsRet);
			obtPerm.setPolitics(cardsRet);
			obtPerm.setRegionIndex(reg);
			obtPerm.setSlot(slot);
			return obtPerm;
		case 1:
			SatisfyKingDTO satKing = new SatisfyKingDTO();
			polCards = cli.waitInputCards(this.game.getActualPlayer().getHand());
			cardsRet = new PoliticsCardDTO[polCards.size()];
			cardsRet = polCards.toArray(cardsRet);
			CityDTO[] cities = this.game.getMap().getCity();
			CityDTO[] validCities = new CityDTO[cities.length-1];
			int i=0;
			for(CityDTO c : cities){
				CityDTO kingLoc = this.game.getMap().getKing().getLocation();
				if(!cityDTOEquals(c,kingLoc)){
					validCities[i]=c;
					i++;
				}
			}
			CityDTO dest = validCities[cli.getInputCity(validCities)];
			satKing.setDestination(dest);
			satKing.setPolitics(cardsRet);
			return satKing;
		case 2:
			ShiftCouncilMainDTO shiftMain = new ShiftCouncilMainDTO();
			ArrayList<CouncilorColor> avColors = new ArrayList<CouncilorColor>();
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(!avColors.contains(c.getColor()))
					avColors.add(c.getColor());
			int balIndex = cli.getTargetBalcony();
			CouncilorColor targetColor = avColors.get(cli.getColorIndex(avColors));
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(c.getColor().equals(targetColor))
					shiftMain.setCouncilor(c);
			shiftMain.setBalconyIndex(balIndex);
			return shiftMain;
		case 4:
			return new BuyAssistantDTO();
		case 7:
			return new BuyMainActionDTO();
		case 5:
			ChangeCardsDTO changeDTO = new ChangeCardsDTO();
			changeDTO.setBalconyIndex(cli.getTargetBalcony());
			return changeDTO;
		case 6:
			ShiftCouncilSpeedDTO shiftSpeed = new ShiftCouncilSpeedDTO();
			ArrayList<CouncilorColor> availColors = new ArrayList<CouncilorColor>();
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(!availColors.contains(c.getColor()))
					availColors.add(c.getColor());
			int balcIndex = cli.getTargetBalcony();
			CouncilorColor targColor = availColors.get(cli.getColorIndex(availColors));
			for(CouncilorDTO c : this.game.getMap().getCouncilors())
				if(c.getColor().equals(targColor))
					shiftSpeed.setCouncilor(c);
			shiftSpeed.setBalconyIndex(balcIndex);
			return shiftSpeed;
		case 8:
			PassDTO pass = new PassDTO();
			return pass;
		}
		return null;
	}
	
	public boolean cityDTOEquals(CityDTO c1, CityDTO c2){
		if(c1.getName().equals(c2.getName()))
			return true;
		return false;
	}
}
