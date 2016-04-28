package decks ;

import java.util.ArrayList;

import board.Bonus;

public class KingBonusDeck extends Deck{


	private ArrayList<KingBonusCard> kingBonusDeck;
	private final static int KINGQTY=5;



	public KingBonusDeck(Bonus[] bonuses) {
		kingBonusDeck = new ArrayList<KingBonusCard>(KINGQTY);
		for (int i=0; i<KINGQTY; i++){
			kingBonusDeck.set(i, new KingBonusCard(i+1,bonuses[i]));
		}

	}

	public KingBonusCard draw(){
		KingBonusCard drawnCard;
		if(kingBonusDeck.isEmpty()){
			return null;
		}else{
			drawnCard = kingBonusDeck.get(0);
			kingBonusDeck.remove(0);
			return drawnCard;
		}

	}

	/**
	 * @return the size of the king bonus deck
	 */
	public static int getKingqty() {
		return KINGQTY;
	}



}

