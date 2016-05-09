package clientserverlogic;
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
 * SME
 * Il controller dovrà gestire le azioni in questo modo:
 * - L'utente deve vedere qualcosa -> cli.metodiDiOutput();
 * - L'utente vuole fare qualcosa -> cli.outputScelte(); | cli.inputSceltaFatta(); | game.faiAzione(input_dalla_cli);
 * ... e così via
 * 
 * cerca di essere leggibile, NON SCRIVERE IN ITALIAN, BITCH.
 * 
 * 
 * 
 */
public class Controller {
	
	private Game game;
	private int turn;
	private Console cnsl;
	private int mainCount, speedCount;
	private CLI cli;
	
	public Controller(){
		this.setGame(new Game(4,true, null));
		this.setTurn(0);
		cli = new CLI();
	}
	
	private ArrayList<CouncilorColor> getAvailableCouncilors(){
		ArrayList<CouncilorColor> availables = new ArrayList<CouncilorColor>();
		for(Councilor c: game.getMap().getCouncilorsPool())
			if(!availables.contains(c.getCouncilorColor()))
				availables.add(c.getCouncilorColor());
		return availables;
	}

	private void turnCycle() {
		ArrayList<CouncilorColor> avail = getAvailableCouncilors();
		mainCount = 1;
		speedCount = 1;
		int action;
		while (mainCount > 0 || speedCount > 0) {
			if(mainCount==0)
				action = 2;
			else if(speedCount==0)
				action = 1;
			else
				action = cli.getAction(turn);
			
			int regIndex;
			switch (action) {
			case 1://MAIN ACTION WIP


				
				
				
				
				break;
			case 2:// SPEED ACTION
				int choice = cli.speedActionChoice();
				switch (choice) {// non c'è il default perchè non ci arriverà comunque
				case 1:
					// compra aiutante
					speedCount--;
					break;
				case 2:
					regIndex = cli.speedActionSubChoice(choice, avail)[0];
					// cambia permessi
					speedCount--;
					break;
				case 3:
					regIndex = cli.speedActionSubChoice(choice, avail)[0];
					int colIndex = cli.speedActionSubChoice(choice, avail)[1];
					// shifta consigliere
					speedCount--;
					break;
				case 4:
					// compra mainaction
					speedCount--;
					break;
				case 5:
					break;//torna indietro
				}
			}
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
		mainCount++;
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
	
	

