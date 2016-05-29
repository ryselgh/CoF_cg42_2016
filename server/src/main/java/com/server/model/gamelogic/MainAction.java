package com.server.model.gamelogic ;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.values.CouncilorColor;
import com.server.model.board.Balcony;
import com.server.model.board.City;
import com.server.model.board.Councilor;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.values.RegionName;

public class MainAction {

	private int actionCounter;
	protected Game game;
	
	
	/**
	 * Construct the main action
	 * @param the main game
	 */
	
	public MainAction(Game game){
		this.game = game;
	}

	/**
	 * @param c is the number of actions you want to set. Default is 1, should be set when there is a condition that gives another Action to the player in the same turn
	 */
	
	public void setActionCounter(int c){
		this.actionCounter = c;	
	}
	
	/**
	 * @return the actionCounter
	 */
	public int getActionCounter() {
		return actionCounter;
	}

	/**
	 * Adds an action
	 * @param i the amount of actions you want to add
	 */
	
	public void addActionCounter(int i){
		this.actionCounter += i;
	}
	
	/*----------------------- 1st Main Action ----------------------*/
	
	/**
	 * Verify if you can satisfy a council
	 * @param politics the cards you want to use to satisfy the council
	 * @param the balcony you want to satisfy
	 */
	
	public boolean canObtainPermit(PoliticsCard[] politics, Balcony balcony) {
	    int counter = 0;
	    int jollycnt = 0;
	    ArrayList<Councilor> tmpBalcony = new ArrayList<Councilor>(Arrays.asList(balcony.getCouncilors()));
	    for(PoliticsCard p: politics){
	      for(Councilor c: tmpBalcony){
	        if(c.getCouncilorColor().equals(p.getColor()))
	          {
	            tmpBalcony.remove(c);
	            counter++;
	            break;
	          }
	      }
	      if(p.getColor().equals(CouncilorColor.JOLLY)){
	        counter++;
	        jollycnt++;
	      }
	    }
	    if( /* you have the right cards and enough money */
	      counter == politics.length && (
	        (counter==1 && game.getActualPlayer().getCoins()>=(10 + jollycnt)) ||
	        (counter==2 && game.getActualPlayer().getCoins()>=(7 + jollycnt)) ||
	        (counter==3 && game.getActualPlayer().getCoins()>=(4 + jollycnt)) ||
	        (counter==4 && game.getActualPlayer().getCoins()>=(0 + jollycnt))
	      )
	    ){
	      payCards(counter, jollycnt);
	      return true;
	    }
	    else
	      return false;
	  }
	  
	  public void payCards(int cards, int jolly) {
	    switch(cards){
	      case 1:
	        game.getActualPlayer().addCoins(-(10+jolly));
	        break;
	      case 2:
	        game.getActualPlayer().addCoins(-(7+jolly));
	        break;
	      case 3:
	        game.getActualPlayer().addCoins(-(4+jolly));
	        break;
	      case 4:
	        game.getActualPlayer().addCoins(-(0+jolly));
	        break;
	    }
	  }
	/**
	 * Obtain a permit by satisfying a council
	 * @param politics the cards you want to use to satisfy the council
	 * @param the balcony you want to satisfy
	 */
	
	public PermitsCard obtainPermit(int regionIndex, int slot) {
		return game.getMap().getPermitsDeck(regionIndex).getSlot(slot,true);
		
	}
	
	/*------------------- END OF 1st Main Action -------------------*/
	
	/*----------------------- 2nd Main Action ----------------------*/
	
	/**
	 * Verify if you can satisfy the king's balcony
	 * @param politics the cards you want to use to satisfy the king's council
	 */
	
	public boolean canSatisfyKing(PoliticsCard[] politics) {
		return this.canObtainPermit(politics, game.getMap().getBalcony(3));
	}
	
	/**
	 * Verify if the player can move the king in an adiacent
	 * @param destination the location where you want to move the king
	 * @return true if you can move the king there, false if not
	 */
	
	public boolean canMoveKing(City destination){
		if(game.getGraphMap().shortestPathCost(destination)<=game.getActualPlayer().getCoins())
			return true;
		else
			return false;
	}
	
	/**
	 * Moves the king
	 * @param toCity the city where you want to place the king
	 */
	
	public void moveKing(City toCity){
		int cost = game.getGraphMap().shortestPathCost(toCity);
		game.getMap().getKing().setLocation(toCity);
		game.getActualPlayer().addCoins(-cost);
	}
	
	/*------------------- END OF 2nd Main Action -------------------*/
	
	/*----------------------- 3rd Main Action ----------------------*/
	
	/**
	 * Shift a council and earn 4 coins
	 * @param selection the balcony you want to shift
	 * @param councilor the councilor chosen from the pool in the map
	 */
	
	public void shiftCouncil(int selection, Councilor councilor) {
		ArrayList<Councilor> tmpBalcony = new ArrayList<Councilor>();
		Councilor[] temp = game.getMap().getBalcony(selection).getCouncilors();
		tmpBalcony.addAll(Arrays.asList(temp));
		game.getMap().getCouncilorsPool().add(tmpBalcony.get(0));
		tmpBalcony.remove(0);
		tmpBalcony.add(councilor);
		game.getMap().getCouncilorsPool().remove(councilor);
		game.getMap().getBalcony(selection).setCouncilor(tmpBalcony.toArray(new Councilor[0]));
		game.getActualPlayer().addCoins(4);
	}
	
	/*------------------- END OF 3rd Main Action -------------------*/
	
	/*----------------------- 4th Main Action ----------------------*/
	
	/**
	 * Verify if you have the right permit to build in the selected city
	 * @param city the city where you want to build
	 * @param permit the permit you want to use
	 */
	
	public boolean canBuild(City city, PermitsCard permit){
		for(String l: permit.getCityLetter())
			if(l.equals(Character.toString(city.getName().toLowerCase().charAt(0))) &&
				game.getActualPlayer().getAvailableAssistants().size()>=city.getEmporium().length){
				for(Emporium e: city.getEmporium())
					game.getActualPlayer().getAvailableAssistants().remove(0);
				return true;
			}
		return false;
	}
	
	/**
	 * Build an emporium in the specified city
	 * @param city the city where you want to build
	 */
	
	public void build(City city) {
		city.setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
		game.getActualPlayer().getAvailableEmporiums().remove(0);
	}
	
	/*------------------- END OF 4th Main Action -------------------*/
	
}

