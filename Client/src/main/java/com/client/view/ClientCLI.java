package com.client.view;

import com.client.ClientObserver;
import com.client.model.GameStatus;

public class ClientCLI implements ClientObserver{

	private GameStatus game;

	public ClientCLI(GameStatus game){
		this.game = game;
	}


	public void printMap(){

	}

	public void printCityBonus(GameStatus game){
		
	}

	public void printCouncilorPool(GameStatus game){
		
	}

	public void printAssistantPool(GameStatus game){
		
	}

	public void printKingLocation(GameStatus game){
		
	}

	public void printPermitsDecks(GameStatus game){
		
	}

	public void printCouncils(GameStatus game){
		
	}

	public void printNobilityTrack(GameStatus game){
		
	}

	public void printRegionBonuses(GameStatus game){
		
	}

	public void printColorBonuses(GameStatus game){
		
	}

	public void printKingBonuses(GameStatus game){
		
	}

	public void printPlacedEmporiums(GameStatus game){
		
	}

	public void printPlayersStatus(GameStatus game){
		
	}

	public void printPlayerHand(GameStatus player){
		
	}

	public void printMsg(String msg){
		
	}

	public void printBonusCollectionMsg(String type, int amm){
		
	}

	public void printGameStatusStatus(GameStatus game){
		this.printMap();
		this.printCouncils(game);
		this.printPlayerHand(game);
		this.printPlacedEmporiums(game);
		this.printNobilityTrack(game);
		this.printKingLocation(game);
		this.printCouncilorPool(game);
		this.printAssistantPool(game);
		this.printPermitsDecks(game);
		this.printRegionBonuses(game);
		this.printColorBonuses(game);
		this.printKingBonuses(game);
		this.printPlayersStatus(game);
	}

	/*Observer*/
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}


	@Override
	public <C> void update(C change) {
		this.printGameStatusStatus((GameStatus) change);

	}

}
