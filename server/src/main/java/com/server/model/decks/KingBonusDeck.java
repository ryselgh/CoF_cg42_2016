package com.server.model.decks ;

import java.util.ArrayList;

import com.server.model.board.Bonus;

public class KingBonusDeck extends Deck{


	private ArrayList<KingBonusCard> kingBonusDeck;
	private final static int KINGQTY=5;

	/**
	 * constructor of the kbd. create the 5 king bonus cards
	 * @param bonuses is the array of bonus card
	 */


	public KingBonusDeck(Bonus[] bonuses) {
		for(Bonus b: bonuses)
			if(b == null)
				throw new NullPointerException("Bonuses cannot be null!");
			else{
				kingBonusDeck = new ArrayList<KingBonusCard>(KINGQTY);
				for (int i=0; i<KINGQTY; i++){
					kingBonusDeck.set(i, new KingBonusCard(i+1,bonuses[i]));
				}
			}

	}

	/**
	 * when you achieve a particular goal you draw a card from the kbd
	 * @return null if the deck is empty else return the kbc you have to take
	 */

	public KingBonusCard draw(){
		KingBonusCard drawnCard;
		if(kingBonusDeck.isEmpty()){
			throw new NullPointerException("Deck is empty!");
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

