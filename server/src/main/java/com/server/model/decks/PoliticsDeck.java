package com.server.model.decks ;

import java.util.ArrayList;
import java.util.Collections;

import com.communication.decks.PoliticsCardDTO;
import com.communication.decks.PoliticsDeckDTO;
import com.communication.values.CouncilorColor;

public class PoliticsDeck extends Deck{


	private ArrayList<PoliticsCard> politicsDeck;
	private ArrayList<PoliticsCard> garbage;
	private static final int CARDSQTY=13;
	private static final int JOLLYQTY=12;

	/**
	 * construcotr of the deck. it creates 13 cards for every color and 12 jolly cards. then shuffle the deck
	 * 
	 */

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

	/**
	 * if the deck is empty you shuffle the deck garbage. garbage become the new politics deck and the you can draw a card.
	 * else you draw a card normally
	 * @return the card drawn
	 */

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

	/**
	 * when you discard a card you add it in the garbage deck
	 * @param c c is the card discarded
	 */

	public void discard(PoliticsCard c) {
		garbage.add(c);
	}
	
	public PoliticsDeckDTO toDTO(){
		PoliticsDeckDTO pdDTO = new PoliticsDeckDTO();
		ArrayList<PoliticsCardDTO> deckDTO = new ArrayList<PoliticsCardDTO>();
		ArrayList<PoliticsCardDTO> garbageDTO = new ArrayList<PoliticsCardDTO>();
		for(PoliticsCard pc: politicsDeck)
			deckDTO.add(pc.toDTO());
		pdDTO.setPoliticsDeck(deckDTO);
		for(PoliticsCard gar: garbage)
			garbageDTO.add(gar.toDTO());
		pdDTO.setGarbage(garbageDTO);
		return pdDTO;
	}

}

