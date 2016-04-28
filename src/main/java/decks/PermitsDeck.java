package decks ;

import java.util.ArrayList;

import model.RegionName;



public class PermitsDeck extends Deck{



	// private PermitsCard slot1;
	// private PermitsCard slot2;


	private ArrayList<PermitsCard> permitsDeck;
	private PermitsCard[] slot = new PermitsCard[1];
	private RegionName regionName;



	public PermitsDeck(/*file*/ RegionName r) {
		permitsDeck = new ArrayList<PermitsCard>();
		regionName = r;
		//TODO inizializzare il file
		//		costruisce le carte permesso basandosi sul file
		//		costruire tre permitsDeck usando la descriminante delle regioni
		//		funzione di shuffle sul mazzo
		//		girare le prime due carte da mettere in slot 1,2 e settarle Faced Up

	}

	/**
	 * @return the slot1
	 */
	public PermitsCard getSlot1() {
		return this.slot[0];
	}



	/**
	 * @return the slot2
	 */
	public PermitsCard getSlot2() {
		return this.slot[1];
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
		if(permitsDeck.isEmpty()){
			throw new NullPointerException("Cards are over");
		}
		if (slot[0] == null){
			slot[0]= permitsDeck.get(0);
			permitsDeck.remove(0);
		}else if(slot[1]==null){
			slot[1]=permitsDeck.get(0);
			permitsDeck.remove(0);

		}

	}

	public void changeCards(){
		permitsDeck.add(getSlot1());
		permitsDeck.add(getSlot2());
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

