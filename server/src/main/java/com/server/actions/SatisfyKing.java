package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.board.Balcony;
import com.server.model.board.Bonus;
import com.server.model.board.City;
import com.server.model.board.Councilor;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;


/**
 * The Class SatisfyKing.
 */
public class SatisfyKing extends Action {
	
	/**
	 * Verify if you can satisfy the king's balcony.
	 *
	 */
	private PoliticsCard[] politics; 
	
	/** The destination. */
	private City destination;
	
	/** The counter. */
	private int counter = 0;
    
    /** The jollycnt. */
    private int jollycnt = 0;
    
    /** The errors. */
    private ArrayList<String> errors;
    
    /** The disable. */
    private boolean disable;
    
  /**
   * Instantiates a new satisfy king.
   *
   * @param politics the politics
   * @param destination the destination
   */
  //ActionReturn(boolean success, String error, boolean disable, boolean addMainBonus)
	public SatisfyKing(PoliticsCard[] politics, City destination){
		if(politics==null)
			throw new NullPointerException();
		else if(destination == null)
			throw new NullPointerException();
		this.politics = politics;
		this.destination=destination;
		errors = new ArrayList<String>();
	}
	
	
	
	/**
	 * Moves the king.
	 *  if there is an error gives a string error
	 *
	 * @return the action return
	 */
	
	public ActionReturn execute(){
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
		payCards();
		game.getMap().getKing().setLocation(destination);
		Bonus[] bonusToCollect = getCitiesBonus(destination);
		return new ActionReturn(true,"",bonusToCollect);
	}
	
	/* 
	 * check if the action is valid
	 */
	public boolean isValid(){
		isInputDataValid();
		isOperationValid();
		if(errors.size()>0)
			return false;
		return true;
	}
	
	/**
	 * Checks if is input data valid.
	 * if the input is not valid adds an error
	 */
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
	
	/**
	 * Checks if is operation valid.
	 * it counts how many cards match the balcony
	 */
	private void isOperationValid() {
		int tempCoin = 0;
		int needCoin = game.getGraphMap().shortestPathCost(destination);
		if(needCoin>game.getActualPlayer().getCoins())
			errors.add("You have not enought money for the move [" + game.getMap().getKing().getLocation().getName() + " --> " + destination.getName() + "s, you need " + needCoin);
		else
			tempCoin = needCoin;
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
	    else {
	    	if(counter==1) 
	    		needCoin= 10 + jollycnt;
	    	else if(counter==2) 
	    		needCoin= 7 + jollycnt;
	    	else if(counter==3) 
	    		needCoin= 4 + jollycnt;
	    	else if(counter==4) 
	    		needCoin= 0 + jollycnt;
	    	if(needCoin+tempCoin>game.getActualPlayer().getCoins())
	    		errors.add("You have not enought money, the action needs "+ tempCoin + " for the move and " + needCoin + " to pay the missing cards and jolly");
	    }
	        
	  }
	  
	  /**
  	 * Pay cards.
  	 */
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
	  
	  /**
  	 * Gets the cities bonus. 
	 * When you build in a city you get all the other bonus in the cities linked with the new one. 
	 * You must have an emporium in each city if you want the bonus.
  	 
  	 * @param startcity the startcity
  	 * @return the cities bonus
  	 */
  	private Bonus[] getCitiesBonus(City startcity){
			ArrayList<City> checked = new ArrayList<City>();
			ArrayList<City> tocheck = new ArrayList<City>();
			ArrayList<Bonus> found = new ArrayList<Bonus>();
			found.addAll(new ArrayList<Bonus>(Arrays.asList(startcity.getBonusToken().getBonus())));//aggiungo i bonus della città di partenza
			checked.add(startcity);
			for (String c : startcity.getCloseCity())
				tocheck.add(game.getCityFromName(c));//aggiungo le città vicine alla lista da controllare
			while (true) {
				if (tocheck.size() == 0)//se la lista è vuota break
					break;
				for (int i = 0; i < tocheck.size(); i++) {
					if (tocheck.get(i).hasEmporium(game.getActualPlayer())) {//se la città da controllare ha un emporio
						found.addAll(new ArrayList<Bonus>(Arrays.asList(tocheck.get(i).getBonusToken().getBonus())));//aggiungo i bonus del token di quella città
						checked.add(tocheck.get(i));//la aggiungo alla lista delle città controllate
						for (String c : tocheck.get(i).getCloseCity())//aggiungo i suoi vicini alle città da controllare SE NON LE HO GIà CONTROLLATE
							if (!checked.contains(game.getCityFromName(c)))
								tocheck.add(game.getCityFromName(c));
						tocheck.remove(i);//rimuovo la città appena controllata
					}else{
						checked.add(tocheck.get(i));
						tocheck.remove(i);
					}
				}
			}
			Bonus toRemove = null;
			for(Bonus b: found)
				if(b==null)
					toRemove = b;
			found.remove(toRemove);
			Bonus[] stockArr = new Bonus[found.size()];
			stockArr = found.toArray(stockArr);
			return stockArr;
		}
	  
	  /**
  	 * Setter from dto.
  	 *
  	 * @param skDTO the skdto
  	 * @param player the player
  	 * @param game the game
  	 */
  	public void setterFromDTO(SatisfyKingDTO skDTO, Player player, Game game){
			this.game = game;
			ArrayList<PoliticsCard> storage = new ArrayList<PoliticsCard>();
			for(PoliticsCardDTO pcDTO : skDTO.getPolitics()){
				PoliticsCard pc = PoliticsCard.fromDTO(pcDTO, player);
				storage.add(pc);
			}
			this.politics = new PoliticsCard[storage.size()];
			this.politics = storage.toArray(politics);
			this.destination = game.getCityFromName(skDTO.getDestination().getName());
		}
}
