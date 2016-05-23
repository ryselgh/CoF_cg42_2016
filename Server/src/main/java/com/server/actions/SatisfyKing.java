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
	int counter = 0;
    int jollycnt = 0;
	
	public SatisfyKing(PoliticsCard[] politics, City destination){
		this.politics = politics;
		this.destination=destination;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	
	
	/**
	 * Moves the king
	 * @param toCity the city where you want to place the king
	 */
	
	public void execute(){
		payCards();
		game.getMap().getKing().setLocation(destination);
	}
	
	public boolean isValid(){
		return isInputDataValid() && isOperationValid();
	}
	
	private boolean isInputDataValid(){
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
				return false;
		}
		return true;
	}
	
	private boolean isOperationValid() {
		int tempCoin = 0;
		if(game.getGraphMap().shortestPathCost(destination)>game.getActualPlayer().getCoins())
			return false;
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
	    if( /* you have the right cards and enough money */
	      counter == politics.length && (
	        (counter==1 && tempCoin>=(10 + jollycnt)) ||
	        (counter==2 && tempCoin>=(7 + jollycnt)) ||
	        (counter==3 && tempCoin>=(4 + jollycnt)) ||
	        (counter==4 && tempCoin>=(0 + jollycnt))
	      )
	    )
	      return true;
	   return false;
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
