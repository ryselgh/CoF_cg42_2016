package com.communication.decks ;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The Class KingBonusDeckDTO.
 */
public class KingBonusDeckDTO implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5684984448425767593L;
	
	/** The king bonus deck(DTO). */
	private ArrayList<KingBonusCardDTO> kingBonusDeck;
	
	/** The Constant KINGQTY. */
	private final static int KINGQTY=5;
	
	
	/**
	 * Gets the king bonus deck(DTO).
	 *
	 * @return the kingBonusDeck(DTO)
	 */
	public ArrayList<KingBonusCardDTO> getKingBonusDeck() {
		return kingBonusDeck;
	}
	
	
	/**
	 * Sets the king bonus deck(DTO).
	 *
	 * @param kingBonusDeck the kingBonusDeck(DTO) to set
	 */
	public void setKingBonusDeck(ArrayList<KingBonusCardDTO> kingBonusDeck) {
		this.kingBonusDeck = kingBonusDeck;
	}
	
	
	/**
	 * Gets the kingqty.
	 *
	 * @return the kingqty
	 */
	public static int getKingqty() {
		return KINGQTY;
	}

	

}

