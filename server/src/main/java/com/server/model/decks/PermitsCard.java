package com.server.model.decks ;

import com.communication.decks.PermitsCardDTO;
import com.server.model.board.Bonus;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;
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
	 * @param b  the bonus on the card
	 * @param l the letter of the city
	 */
	public PermitsCard(Bonus[] b, String[] l) {
		bonuses= b;
		cityLetter = l;
		
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

	public static PermitsCard fromDTO(PermitsCardDTO pcDTO, Player player){//ritorna il riferimento della carta permesso posseduta dal giocatore
		for(PermitsCard pc : player.getPermits())
			if(pc.equals(pcDTO))
				return pc;
		return null;
	}
		
	
	
	
	
	
}

