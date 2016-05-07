import board.Balcony;
import board.Councilor;
import decks.PoliticsCard;
import gamelogic.Game;
import java.io.Console;
import java.util.ArrayList;

import model.CouncilorColor;
/*AZIONI: 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Controller {
	
	private Game game;
	private int turn;
	private Console cnsl;
	private int mainBonus;
	private CLI cli;
	
	public Controller(){
		this.setGame(new Game(4,true, null));
		this.setTurn(0);
		cli = new CLI();
		
	}
	
	
	
	private void turnCycle()
	{
		int action = cli.getAction(turn);
		mainBonus=0;
		
		switch(action){
		case 1:
			cli.mainAction();
			break;
		case 2:
			cli.speedAction(game.getMap().getAvailableColors());
			break;
		}
		while(mainBonus !=0)
		{
			cli.mainAction();
			mainBonus--;
		}
	
	}
	/**
	 * @return the game
	 */
	
	
	private Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	private void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}
	
	public void addMainBonus()
	{
		mainBonus++;
	}
	/**
	 * @param turn the turn to set
	 */
	private void setTurn(int turn) {
		this.turn = turn;
	}
	
	/**
	 * adds a politics card to the player's hand
	 */
	public void playerDrawsPoliticsCard(){
		this.game.getActualPlayer().addPolitics(this.game.getMap().getPoliticsDeck().draw());
	}
	
	/**
	 * Do the main action
	 * @param selection int from 1 to 4, identify the main action (1: obtain a permit 2: satisfy the king 3: shift a council 4: build an emporium)
	 */
	
	
	/**
	 * Do the speed action
	 * @param selection int from 1 to 4, identify the speed action (1: buy an assistant 2: change permits on the ground 3: shift a council 4: build an emporium)
	 */
	
	
	}
	
	

