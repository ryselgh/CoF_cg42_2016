package gamelogic;

import java.util.ArrayList;
import java.util.Arrays;

import board.Councilor;

public class SpeedAction {

	
	private int actionCounter;
	private Game game;
	
	/**
	 * Constructs the speed action
	 * @param the main game
	 */
	public SpeedAction(Game game){
		this.game = game;
	}

	/**
	 * Sets how many speed actions you can do
	 * @param c is the number of action you want to set
	 */
	
	public void setActionCounter(int c) {
		this.actionCounter = c;	
	}
	
	/**
	 * Adds an action
	 * @param i the amount of actions you want to add
	 */
	
	public void addActionCounter(int i){
		this.actionCounter += i;
	}
	
	public boolean hasAtLeastOneAssistant(){
		if(game.getActualPlayer().getAvailableAssistants().size()>=1)
			return true;
		else
			return false;
	}
	
	/*----------------------- 1st Speed Action ----------------------*/
	
	/**
	 * Verify if you can buy an assistant
	 * @return true if you can buy, false if not
	 */
	
	public boolean canBuyAssistant(){
		if(game.getActualPlayer().getCoins()>=3)
			return true;
		else
			return false;
	}
	
	/**
	 * Buy ONE assistant
	 */
	
	public void buyAssistant() {
		if(canBuyAssistant()){
			game.getActualPlayer().addCoins(-3);
			game.getActualPlayer().addAssistant(1);
		}
	}
	
	/*------------------- END OF 1st Speed Action -------------------*/
	
	/*----------------------- 2nd Speed Action ----------------------*/
	
	/**
	 * Verify if you can change the permits (you must have at least one assistant)
	 * @return true if you can change the permits, false if not
	 */
	
	public boolean canChangeCards(){
		return hasAtLeastOneAssistant();
	}
	
	/**
	 * Change permits on the ground of the selected deck
	 * @param selection the selected deck; 0: sea, 1: hill, 2: mountain.
	 */
	
	public void changePermitsCards(int selection) {
		game.getMap().getPermitsDeck(selection).changeCards();
		game.getActualPlayer().getAvailableAssistants().remove(0);
	}
	
	/*------------------- END OF 2nd Speed Action -------------------*/
	
	/*----------------------- 3rd Speed Action ----------------------*/
	
	/**
	 * Verify if you can shift the council (you must have at least one assistant)
	 * @return true if you can shift the council, false if not
	 */
	
	public boolean canShiftCouncil(){
		return hasAtLeastOneAssistant();
	}
	
	/**
	 * Shift the council by using an assistant
	 * @param selection is the balcony you want to shift
	 * @param councilor the councilor chosen from the pool in the map
	 */
	
	public void shiftCouncil(int selection, Councilor councilor) {
		ArrayList<Councilor> tmpBalcony = new ArrayList<Councilor>(Arrays.asList(game.getMap().getBalcony(selection).getCouncilors()));
		game.getMap().getCouncilorsPool().add(tmpBalcony.get(0));
		tmpBalcony.remove(0);
		tmpBalcony.add(councilor);
		game.getMap().getCouncilorsPool().remove(councilor);
		Councilor[] c = new Councilor[4];
		game.getMap().getBalcony(selection).setCouncilor(tmpBalcony.toArray(c));
		game.getActualPlayer().getAvailableAssistants().remove(0);
	}
	
	/*------------------- END OF 3rd Speed Action -------------------*/
	
	/*----------------------- 4th Speed Action ----------------------*/
	
	/**
	 * Verify that you can buy an additional main action
	 * @return true if you can, false if not
	 */
	
	public boolean canBuyMainAction(){
		if(game.getActualPlayer().getAvailableAssistants().size()>=3)
			return true;
		else
			return false;
	}
	
	/**
	 * Buy an additional main action
	 */
	
	public void buyMainAction() {
		game.getMainAction().addActionCounter(1);
		for(int i=0;i<3;i++)
			game.getActualPlayer().getAvailableAssistants().remove(0);
	}
	
	/*------------------- END OF 4th Speed Action -------------------*/
	
}

