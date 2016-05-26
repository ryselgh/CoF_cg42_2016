package com.communication.decks ;

import java.io.Serializable;
import java.util.ArrayList;

public class KingBonusDeckDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5684984448425767593L;
	private ArrayList<KingBonusCardDTO> kingBonusDeck;
	private final static int KINGQTY=5;
	
	
	/**
	 * @return the kingBonusDeck
	 */
	public ArrayList<KingBonusCardDTO> getKingBonusDeck() {
		return kingBonusDeck;
	}
	
	
	/**
	 * @param kingBonusDeck the kingBonusDeck to set
	 */
	public void setKingBonusDeck(ArrayList<KingBonusCardDTO> kingBonusDeck) {
		this.kingBonusDeck = kingBonusDeck;
	}
	
	
	/**
	 * @return the kingqty
	 */
	public static int getKingqty() {
		return KINGQTY;
	}

	

}

