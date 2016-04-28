package decks ;

import java.util.ArrayList;
import java.util.Collections;

import model.CouncilorColor;

public class PoliticsDeck extends Deck{
	
	
	private ArrayList<PoliticsCard> politicsDeck;
	private ArrayList<PoliticsCard> garbage;
	private static final int CARDSQTY=20;
	private static final int JOLLYQTY=10;
	
	

	public PoliticsDeck() {
	
		politicsDeck = new ArrayList<PoliticsCard>(CARDSQTY*6+JOLLYQTY);
		garbage = new ArrayList<PoliticsCard>(CARDSQTY*6+JOLLYQTY);
		
		
		for(CouncilorColor c : CouncilorColor.values()){
			if(c == CouncilorColor.JOLLY)
				for(int i=CARDSQTY*6; i<CARDSQTY*6+JOLLYQTY;i++)
					politicsDeck.set(i, new PoliticsCard(c));
			else
				for(int i=0; i<CARDSQTY;i++)
					politicsDeck.set(i, new PoliticsCard(c));
		}
		Collections.shuffle(politicsDeck);
		
		
	}
	
	
	
	public PoliticsCard draw() {
		PoliticsCard drawnCard;
		if(politicsDeck.isEmpty()){
			Collections.shuffle(garbage);
			for(PoliticsCard p : garbage ){
				politicsDeck.add(p);
				garbage.remove(p);
			}
			drawnCard = politicsDeck.get(0);
			politicsDeck.remove(0);
			
		} else{
			drawnCard = politicsDeck.get(0);
			politicsDeck.remove(0);
		}
		
		return drawnCard;	
	}
	
	
	
	public void discard(PoliticsCard c) {
		garbage.add(c);
	}
	
}

