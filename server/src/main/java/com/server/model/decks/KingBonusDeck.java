package com.server.model.decks ;

import java.util.ArrayList;

import com.communication.decks.KingBonusCardDTO;
import com.communication.decks.KingBonusDeckDTO;
import com.server.model.board.Bonus;


/**
 * The Class KingBonusDeck.
 */
public class KingBonusDeck extends Deck{


	/** The king bonus deck. */
	private ArrayList<KingBonusCard> kingBonusDeck;
	
	/** The Constant KINGQTY. */
	private final static int KINGQTY=5;

	/**
	 * constructor of the kbd. create the 5 king bonus cards
	 *
	 * @param bonuses bonuses is one of the bonus token from the bonus array
	 */


	public KingBonusDeck(Bonus[] bonuses){
		if(bonuses.length != KINGQTY)
			throw new IllegalArgumentException();
		else{
		kingBonusDeck = new ArrayList<KingBonusCard>(KINGQTY);
		for (int i=0; i<KINGQTY; i++){
			kingBonusDeck.add(new KingBonusCard(i+1,bonuses[i]));
		}
		}

	}
	
	/**
	 * when you achieve a particular goal you draw a card from the kbd.
	 *
	 * @return null if the deck is empty else return the kbc you have to take
	 */

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
	 * Gets the kingqty.
	 *
	 * @return the size of the king bonus deck
	 */
	public static int getKingqty() {
		return KINGQTY;
	}
	
	/**
	 * To dto.
	 *
	 * @return the king bonus deck dto
	 */
	public KingBonusDeckDTO toDTO(){
		KingBonusDeckDTO kbdDTO = new KingBonusDeckDTO();
		ArrayList<KingBonusCardDTO> kbArrayDTO = new ArrayList<KingBonusCardDTO>();
		for(KingBonusCard kbc: kingBonusDeck)
			kbArrayDTO.add(kbc.toDTO());
		kbdDTO.setKingBonusDeck(kbArrayDTO);
		return kbdDTO;
	}


}

