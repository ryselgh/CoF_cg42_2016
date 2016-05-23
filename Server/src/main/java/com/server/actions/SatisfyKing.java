package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.server.model.board.Balcony;
import com.server.model.board.City;
import com.server.model.board.Councilor;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.values.CouncilorColor;

public class SatisfyKing extends Action {
	/**
	 * Verify if you can satisfy the king's balcony
	 * @param politics the cards you want to use to satisfy the king's council
	 */
	private PoliticsCard[] politics; 
	private City destination;
	private Game game;
	private int counter = 0;
    private int jollycnt = 0;
    private ArrayList<String> errors;
    private boolean disable;
    
  //ActionReturn(boolean success, String error, boolean disable, boolean addMainBonus)
	public SatisfyKing(PoliticsCard[] politics, City destination){
		this.politics = politics;
		this.destination=destination;
		errors = new ArrayList<String>();
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	
	
	/**
	 * Moves the king
	 * @param toCity the city where you want to place the king
	 */
	
	public ActionReturn execute(){
		payCards();
		game.getMap().getKing().setLocation(destination);
		return new ActionReturn(true,"",false,false);
	}
	
	public boolean isValid(){
		isInputDataValid();
		isOperationValid();
		if(errors.size()>0)
			return false;
		return true;
	}
	
	private void isInputDataValid(){
		ArrayList<PoliticsCard> tempHand = game.getActualPlayer().getHand();
		boolean found;
		for(PoliticsCard c: politics){
			found = false;
			for(int i=0;i<tempHand.size();i++)
				if(c.equals(tempHand.get(i))){
					tempHand.remove(i);
					found = true;
					break;
				}
			if(found==false)
				errors.add("Invalid input cards");//anche questa da levare quando certi che funziona
		}
	}
	
	private void isOperationValid() {
		int tempCoin = 0;
		if(game.getGraphMap().shortestPathCost(destination)>game.getActualPlayer().getCoins())
			errors.add("You have not enought money");
		else
			tempCoin = game.getActualPlayer().getCoins() - game.getGraphMap().shortestPathCost(destination);
	    Balcony chosenBalcony = game.getMap().getBalcony(3);
	    ArrayList<Councilor> tmpBalcony = new ArrayList<Councilor>(Arrays.asList(chosenBalcony.getCouncilors()));
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
	    /* you have the right cards and enough money */
	    if(!(counter == politics.length))
	    	errors.add("Invalid input cards");
	    else if(!(counter==1 && tempCoin>=(10 + jollycnt)) ||
	        (counter==2 && tempCoin>=(7 + jollycnt)) ||
	        (counter==3 && tempCoin>=(4 + jollycnt)) ||
	        (counter==4 && tempCoin>=(0 + jollycnt)))
	    		errors.add("You have not enought money");
	  }
	  
	  private void payCards() {
		  int pathCost = game.getGraphMap().shortestPathCost(destination);
	    switch(counter){
	      case 1:
	        game.getActualPlayer().addCoins(-(10+jollycnt+pathCost));
	        break;
	      case 2:
	        game.getActualPlayer().addCoins(-(7+jollycnt+pathCost));
	        break;
	      case 3:
	        game.getActualPlayer().addCoins(-(4+jollycnt+pathCost));
	        break;
	      case 4:
	        game.getActualPlayer().addCoins(-(0+jollycnt+pathCost));
	        break;
	    }
	  }
}
