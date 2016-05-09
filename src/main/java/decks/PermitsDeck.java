package decks ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.RegionName;



public class PermitsDeck extends Deck{



	private ArrayList<PermitsCard> permitsDeck;
	private PermitsCard[] slot = new PermitsCard[2];
	private int regionCode;//più facile per i confronti

	/**
	 * permitsDeck is the constructor of the deck. 
	 * @param p p is the element in PermitsCard (array)
	 * @param r r is the code of the region. can be 0,1,2 based on the region we considered
	 */

	public PermitsDeck(PermitsCard[] p, int r) {
		permitsDeck = new ArrayList<PermitsCard> (Arrays.asList(p));
		regionCode = r;
		Collections.shuffle(permitsDeck);
		this.draw();

	}

	/**
	 * @return the slot. can be 0,1 
	 * @param index index is the number of the slot
	 * @param draw is a boolen if true the slot is full else is empty
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
	 * @param card the slot to set
	 */
	public void setSlot1(PermitsCard card) {
		this.slot[0] = card;
	}

	/**
	 * @param card the slot to set
	 */
	public void setSlot2(PermitsCard card) {
		this.slot[1] = card;
	}


	/**
	 * you draw a card from permitsDeck 
	 */

	public void draw() {
		if(permitsDeck.isEmpty())
			throw new NullPointerException("Cards are over");
		if (slot[0] == null){
			slot[0] = permitsDeck.get(0);
			permitsDeck.remove(0);
		}
		if(slot[1] == null){
			slot[1] = permitsDeck.get(0);
			permitsDeck.remove(0);
		}
	}

	/**
	 * the function you have to use to change cards when you call the speedaction
	 */

	public void changeCards(){
		permitsDeck.add(getSlot(0,false));
		permitsDeck.add(getSlot(1,false));
		setSlot1(null);
		setSlot2(null);
		draw();

	}


}

