package com.server.model.decks ;

import com.server.model.board.Bonus;
import com.server.values.Letter;
import com.server.values.RegionName;



public class PermitsCard {
	
	
	private Bonus[] bonuses;
	private String[] cityLetter;
	private boolean faceDown=true;
	
	


	
	/**
	 * @return the bonuses of the card
	 */
	public Bonus[] getBonus() {
		return this.bonuses;	
	}


	
	/**
	 * @return the cityLetter
	 */
	public String[] getCityLetter() {
		return cityLetter;
	}





	/**
	 * constructor of the permitscard
	 * @param b  the bonuses on the card
	 * @param l the letters of the city
	 */
	public PermitsCard(Bonus[] b, String[] l) {
		for(Bonus bn:b){
			for (String stn:l){
				if (bn==null || stn==null)
					throw new NullPointerException();
				else{
					bonuses= b;
					cityLetter = l;
				}
			}
		}

	}
	
	/**
	 * @return true if the card is faced down
	 */
	public boolean isFaceDown() {
		return faceDown;
	}



	/**
	 * @param faceDown set to true when the card should be faced down
	 */
	public void setFaceDown(boolean faceDown) {
		this.faceDown = faceDown;
 	}

	
		
	
	
	
	
	
}

