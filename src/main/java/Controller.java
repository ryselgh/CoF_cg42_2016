import board.Balcony;
import decks.PoliticsCard;
import gamelogic.Game;
import java.io.Console;
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
	
	public Controller(){
		this.setGame(new Game(4,true, null));
		this.setTurn(0);
		cnsl = System.console();
		
	}
	
	private String getInput(String message)
	{
		cnsl.printf(message);
		return cnsl.readLine();
		
	}
	
	private void turnCycle()
	{
		int action = waitCorrectInput("Hi there player" + turn + ", insert 1 for Main Action, insert 2 for Speed Action.\n",1,2);
		mainBonus=0;
		
		switch(action){
		case 1:
			mainAction();
			break;
		case 2:
			speedAction();
			break;
		}
		while(mainBonus !=0)
		{
			mainAction();
		}
	
	}
	/**
	 * @return the game
	 */
	
	private int waitCorrectInput(String msg, int min, int max)
	{
		int respInt = -1;
		do {
			String resp = getInput(msg);
			respInt = parseNum(resp, min,max);
		} while (respInt != -1);
		return respInt;
	}
	private int parseNum(String msg, int min, int max) {
		int read=0;
		try
		{ read = Integer.parseInt(msg);
		}
		catch (Exception e) 
		{
			cnsl.printf("Wrong input format. Try again\n");
			return -1;
		}
		if (read <= max && read >= min)
			return read;
		else {
			cnsl.printf("Input out of bounds. Try again\n");
			return -1;}
	}
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
	private void mainAction(){
		
		PoliticsCard[] chosenPolitics = null; //temporary
		Balcony chosenBalcony = null; //temporary
		/*switch(selection){
			case 1:
				//this.game.getMainAction().canObtainPermit(chosenPolitics, chosenBalcony);
				break;
			case 2:
				//this.game.getMainAction().canSatisfyKing(chosenPolitics);
				break;
			case 3:
				//this.game.getMainAction().shiftCouncil();
				break;
			case 4:
				//this.game.getMainAction().build();
				break;
		}*/
				
	}
	
	/**
	 * Do the speed action
	 * @param selection int from 1 to 4, identify the speed action (1: buy an assistant 2: change permits on the ground 3: shift a council 4: build an emporium)
	 */
	
	private void speedAction(){
		int selection = waitCorrectInput("Insert the number related to your action:\n"
				+ "1-Buy Assistants\n"
				+ "2-Change Permits Card\n"
				+ "3-Shift Council\n"
				+ "4-Buy Main Action\n",1,4);
		int regIndex=0;
		switch(selection){
			case 1:
				int assAmm = waitCorrectInput("Insert the ammount of assistants you want to buy:\n",1,99);
				break;
			case 2:
				regIndex = waitCorrectInput("Insert the index of the region containing the cards you want to change:\n1-Sea\n2-Hill\n3-Mountain\n",1,3);
				break;
			case 3://PURPLE, WHITE, BLACK, ORANGE, BLUESKY, PINK, JOLLY;
				regIndex = waitCorrectInput("Insert the index of the region containing the councilors you want to shift:\n"
						+ "1-Sea\n"
						+ "2-Hill\n"
						+ "3-Mountain\n",1,3);
				cnsl.printf("Insert the index of the color:\n");
				for(int i=0;i<CouncilorColor.values().length;i++)
				{
					cnsl.printf(i + "-" + CouncilorColor.values()[i].toString() + "\n");
				}
				int colIndex = waitCorrectInput("",1,3);
				//da controllare se disp
				//this.game.getSpeedAction().shiftCouncil();
				break;
			case 4:
				//this.game.getSpeedAction().buyMainAction();
				break;
		}
	}
}
