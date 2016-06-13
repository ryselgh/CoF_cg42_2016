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

public class SatisfyKing extends Action {
	/**
	 * Verify if you can satisfy the king's balcony
	 * @param politics the cards you want to use to satisfy the king's council
	 */
	private PoliticsCard[] politics; 
	private City destination;
	private int counter = 0;
    private int jollycnt = 0;
    private ArrayList<String> errors;
    private boolean disable;
    
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
	 * Moves the king
	 * @param toCity the city where you want to place the king
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
	    else if(!((counter==1 && tempCoin>=(10 + jollycnt)) ||
	        (counter==2 && tempCoin>=(7 + jollycnt)) ||
	        (counter==3 && tempCoin>=(4 + jollycnt)) ||
	        (counter==4 && tempCoin>=(0 + jollycnt))))
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
