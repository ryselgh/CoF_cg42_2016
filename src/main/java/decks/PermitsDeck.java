package decks ;

import java.util.ArrayList;
import java.util.Arrays;

import model.RegionName;



public class PermitsDeck extends Deck{



	// private PermitsCard slot1;
	// private PermitsCard slot2;


	private ArrayList<PermitsCard> permitsDeck;
	private PermitsCard[] slot = new PermitsCard[1];
	private int regionCode;//più facile per i confronti



	public PermitsDeck(PermitsCard[] p, int r) {
		permitsDeck = new ArrayList<PermitsCard> (Arrays.asList(p));
		regionCode = r;
		this.draw();

	}

	/**
	 * @return the slot1
	 */
	public PermitsCard getSlot(int index, boolean pesca) {
		if(!pesca)
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
	 * @param slot the slot to set
	 */
	public void setSlot1(PermitsCard card) {
		this.slot[0] = card;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot2(PermitsCard card) {
		this.slot[1] = card;
	}




	public void draw() {
		if(permitsDeck.isEmpty())
			throw new NullPointerException("Cards are over");
		if (slot[0] == null)
			slot[0]= permitsDeck.remove(0);
		else if(slot[1]==null)
			slot[1]=permitsDeck.remove(0);

	}

	public void changeCards(){
		permitsDeck.add(getSlot(0,false));
		permitsDeck.add(getSlot(1,false));
		setSlot1(null);
		setSlot2(null);
		draw();
		draw();

	}




	/*	}else if (region.MOUNTAIN != null){

			for (int i=0; i<2;i++){
				if (slot1==null){
					slot1=permitsDeck[0];
				}else if(slot2==null){
						slot2=permitsDeck[0];
					}else if (slot1==null && slot2==null){
						slot1=permitsDeck[0];
						slot2=permitsDeck[1];
						}
				}

		}else if (region.SEA != null){
			for (int i=0; i<2;i++){
				if (slot1==null){
					slot1.=permitsDeck[0];
				}else if(slot2==null){
						slot2=permitsDeck[0];
					}else if (slot1==null && slot2==null){
						slot1=permitsDeck[0];
						slot2=permitsDeck[1];
						}
				}
		}
		return;





	}*/












}

