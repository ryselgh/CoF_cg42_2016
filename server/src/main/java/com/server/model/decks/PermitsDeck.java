package com.server.model.decks ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PermitsDeckDTO;





/**
 * The Class PermitsDeck.
 */
public class PermitsDeck extends Deck{



	/** The permits deck. */
	private ArrayList<PermitsCard> permitsDeck;
	
	/** The slot. */
	private PermitsCard[] slot = new PermitsCard[2];
	
	/** The region code. */
	private int regionCode;//più facile per i confronti

	/**
	 * permitsDeck is the constructor of the deck. 
	 * @param p p is the element in PermitsCard (array)
	 * @param r r is the code of the region. can be 0,1,2 based on the region we considered
	 */

	public PermitsDeck(PermitsCard[] p, int r) {
		if(p==null)
			throw new NullPointerException();
		permitsDeck = new ArrayList<PermitsCard> (Arrays.asList(p));
		regionCode = r;
		Collections.shuffle(permitsDeck);
		this.draw();

	}

	/**
	 * Gets the slot.
	 *
	 * @param index index is the number of the slot
	 * @param draw, if it's true you get the card
	 * @return the slot. can be 0,1
	 */
	public PermitsCard getSlot(int index, boolean draw) {
		if(!draw)
			return this.slot[index];
		else
		{
			PermitsCard tmp = this.slot[index];
			this.slot[index] = null;
			draw();
			return tmp;
		}
	}



	/**
	 * Sets the slot1.
	 *
	 * @param card the slot to set
	 */
	public void setSlot1(PermitsCard card) {
		this.slot[0] = card;
	}

	/**
	 * Sets the slot2.
	 *
	 * @param card the slot to set
	 */
	public void setSlot2(PermitsCard card) {
		this.slot[1] = card;
	}


	/**
	 * you draw a card from permitsDeck.
	 */

	public void draw() {
		if(permitsDeck.isEmpty())
			throw new NullPointerException("Cards are over");
		if (slot[0] == null){
			slot[0] = permitsDeck.get(0);
			slot[0].setFaceDown(false);
			permitsDeck.remove(0);
		}
		if(slot[1] == null){
			slot[1] = permitsDeck.get(0);
			permitsDeck.remove(0);
			slot[1].setFaceDown(false);
		}
	}

	/**
	 * the function you have to use to change cards when you call the speedaction.
	 */

	public void changeCards(){
		permitsDeck.add(getSlot(0,false));
		permitsDeck.add(getSlot(1,false));
		setSlot1(null);
		setSlot2(null);
		draw();

	}
	
	/**
	 * To dto.
	 *
	 * @return the permits deck dto
	 */
	public PermitsDeckDTO toDTO(){
		PermitsDeckDTO pdDTO = new PermitsDeckDTO();
		ArrayList<PermitsCardDTO> deckDTO = new ArrayList<PermitsCardDTO>();
		for(PermitsCard pc: permitsDeck)
			deckDTO.add(pc.toDTO());
		pdDTO.setPermitsDeck(deckDTO);
		pdDTO.setRegionCode(regionCode);
		PermitsCardDTO[] slotDTO = new PermitsCardDTO[2];
		slotDTO[0] = slot[0].toDTO();
		slotDTO[1] = slot[1].toDTO();
		pdDTO.setSlot(slotDTO);
		return pdDTO;
	}


}

