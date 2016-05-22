package com.client.view;

import com.client.ClientObserver;
import com.client.model.GameStatus;

public class ClientCLI implements ClientObserver{

	private GameStatus game;
	
	/**
	 * constructor of the class
	 * @param game is the GameStatus
	 */

	public ClientCLI(GameStatus game){
		this.game = game;
	}

	/**
	 * print the map
	 */
	public void printMap(){

	}
	
	/**
	 *print the city bonus, for each  city 
	 * @param game is the game status
	 */
	public void printCityBonus(GameStatus game){
		
	}
	
	/**
	 * print the councilor pool
	 * @param game is the game status
	 */

	public void printCouncilorPool(GameStatus game){
		
	}
	
	/**
	 * print the assistant pool
	 * @param game is the game status
	 */

	public void printAssistantPool(GameStatus game){
		
	}
	
	/**
	 * print the location of the king
	 * @param game is the game status
	 */

	public void printKingLocation(GameStatus game){
		
	}
	
	/**
	 * print the permitsdeck
	 * @param game is the game status
	 */

	public void printPermitsDecks(GameStatus game){
		
	}
	
	/**
	 * print the balcony
	 * @param game is the game status
	 */

	public void printCouncils(GameStatus game){
		
	}
	
	/**
	 * print the nobility track
	 * @param game is the game status
	 */

	public void printNobilityTrack(GameStatus game){
		
	}
	
	/**
	 * print the region bonuses
	 * @param game is the game status
	 */

	public void printRegionBonuses(GameStatus game){
		
	}
	
	/**
	 * print the color bonuses
	 * @param game is the game status
	 */

	public void printColorBonuses(GameStatus game){
		
	}
	
	/**
	 * print the king bonuses
	 * @param game is the game status
	 */

	public void printKingBonuses(GameStatus game){
		
	}
	
	/**
	 * print the placed emporiums for each player
	 * @param game is the game status
	 */

	public void printPlacedEmporiums(GameStatus game){
		
	}
	
	/**
	 * print the player status
	 * @param game is the game status
	 */

	public void printPlayersStatus(GameStatus game){
		
	}
	
	/**
	 *print the player hand
	 * @param player is the game status
	 */

	public void printPlayerHand(GameStatus player){
		
	}
	
	/**
	 * print a message
	 * @param msg is a Stirng 
	 */

	public void printMsg(String msg){
		
	}
	
	/**
	 * print the bonus you're receiving
	 * @param type the type of the bonus
	 * @param amm the ammount of the bonus
	 */

	public void printBonusCollectionMsg(String type, int amm){
		
	}
	
	/**
	 * print all the gamestatus
	 * @param game is the game status
	 */

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

	/**
	 * print the new GameStatus after an update
	 */
	@Override
	public <C> void update(C change) {
		this.printGameStatusStatus((GameStatus) change);

	}

}
