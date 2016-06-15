package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.actions.ActionDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.board.Balcony;
import com.server.model.board.Councilor;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ObtainPermit extends Action {
	
	private PoliticsCard[] politics;
	private int regionIndex;
    private int slot;
	int counter = 0;
    int jollycnt = 0;
    private ArrayList<String> errors;
    
    
	public ObtainPermit(PoliticsCard[] politics, int regionIndex, int slot){
		if(politics==null)
			throw new NullPointerException();
		this.politics = politics;
		this.regionIndex = regionIndex;
		this.slot = slot;
		errors = new ArrayList<String>();
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
				errors.add("Invalid input cards");
		}
	}
	
	private void isOperationValid() {
	    Balcony chosenBalcony = game.getMap().getBalcony(regionIndex);
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
	    else if(!((counter==1 && game.getActualPlayer().getCoins()>=(10 + jollycnt)) ||
	        (counter==2 && game.getActualPlayer().getCoins()>=(7 + jollycnt)) ||
	        (counter==3 && game.getActualPlayer().getCoins()>=(4 + jollycnt)) ||
	        (counter==4 && game.getActualPlayer().getCoins()>=(0 + jollycnt))))
	    		errors.add("You have not enought money");
	  }
	  
	  private void payCards(int cards, int jolly) {
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
	
	public ActionReturn execute() {//il client una volta ricevuto l'ack deve eliminare le carte politica dalla mano
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
		
		payCards(counter, jollycnt);
		PermitsCard card = game.getMap().getPermitsDeck(regionIndex).getSlot(slot,true);
		game.getActualPlayer().addPermits(card);
		return new ActionReturn(true,"",card.getBonus());
	}
	
	public void setterFromDTO(ObtainPermitDTO opDTO, Player player, Game game){
		this.game = game;
		ArrayList<PoliticsCard> storage = new ArrayList<PoliticsCard>();
		for(PoliticsCardDTO pcDTO : opDTO.getPolitics()){
			PoliticsCard pc = PoliticsCard.fromDTO(pcDTO, player);
			storage.add(pc);
		}
		this.politics = new PoliticsCard[storage.size()];
		this.politics = storage.toArray(politics);
		this.regionIndex = opDTO.getRegionIndex();
		this.slot = opDTO.getSlot();
	}
}
