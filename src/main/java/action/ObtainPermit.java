package action;

import java.util.ArrayList;
import java.util.Arrays;

import board.Balcony;
import board.Councilor;
import decks.PermitsCard;
import decks.PoliticsCard;
import gamelogic.Game;
import model.CouncilorColor;

public class ObtainPermit extends Action {
	
	private Game game;
	private PoliticsCard[] politics;
	private int regionIndex;
    private int slot;
    
    
	public ObtainPermit(PoliticsCard[] politics, int regionIndex, int slot){
		this.politics = politics;
		this.regionIndex = regionIndex;
		this.slot = slot;
	}
	
	public void setGame(Game game){
		this.game = game;
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
		int counter = 0;
	    int jollycnt = 0;
	    Balcony chosenBalcony = game.getMap().getBalcony(regionIndex);//ok perchè il balcone del re non va considerato in questa funzione
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
	
	public PermitsCard execute() {
		return game.getMap().getPermitsDeck(regionIndex).getSlot(slot,true);
		
	}
}
