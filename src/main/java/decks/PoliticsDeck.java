package decks ;

import java.util.ArrayList;
import java.util.Collections;

import model.CouncilorColor;

public class PoliticsDeck extends Deck{
	
	
	private ArrayList<PoliticsCard> politicsDeck;
	private ArrayList<PoliticsCard> garbage;
	private static final int CARDSQTY=13;
	private static final int JOLLYQTY=12;
	
	

	public PoliticsDeck() {
		
		int dim = CARDSQTY * CouncilorColor.values().length +JOLLYQTY;
		politicsDeck = new ArrayList<PoliticsCard>(dim);
		garbage = new ArrayList<PoliticsCard>(dim);
		
		
		for(CouncilorColor c : CouncilorColor.values()){
			if(c == CouncilorColor.JOLLY)
				for(int i=0; i<JOLLYQTY;i++)
					politicsDeck.add(new PoliticsCard(c));
			else
				for(int i=0; i<CARDSQTY;i++)
					politicsDeck.add(new PoliticsCard(c));
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
			drawnCard = politicsDeck.remove(0);
			
		} else
			drawnCard = politicsDeck.remove(0);
		
		return drawnCard;	
	}
	
	
	
	public void discard(PoliticsCard c) {
		garbage.add(c);
	}
	
}

