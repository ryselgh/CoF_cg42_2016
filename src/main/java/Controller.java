import board.Balcony;
import decks.PoliticsCard;
import gamelogic.Game;

public class Controller {
	
	private Game game;
	private int turn;
	
	public Controller(){
		this.setGame(new Game(4,true, null));
		this.setTurn(0);
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
	public void mainAction(int selection){
		
		PoliticsCard[] chosenPolitics = null; //temporary
		Balcony chosenBalcony = null; //temporary
		switch(selection){
			case 1:
				this.game.getMainAction().canObtainPermit(chosenPolitics, chosenBalcony);
				break;
			case 2:
				this.game.getMainAction().satisfyKing();
				break;
			case 3:
				this.game.getMainAction().shiftCouncil();
				break;
			case 4:
				this.game.getMainAction().build();
				break;
		}
				
	}
	
	/**
	 * Do the speed action
	 * @param selection int from 1 to 4, identify the speed action (1: buy an assistant 2: change permits on the ground 3: shift a council 4: build an emporium)
	 */
	
	public void speedAction(int selection){
		switch(selection){
			case 1:
				this.game.getSpeedAction().buyAssistant();
				break;
			case 2:
				this.game.getSpeedAction().changePermitsCards();
				break;
			case 3:
				this.game.getSpeedAction().shiftCouncil();
				break;
			case 4:
				this.game.getSpeedAction().buyMainAction();
				break;
		}
	}
}
