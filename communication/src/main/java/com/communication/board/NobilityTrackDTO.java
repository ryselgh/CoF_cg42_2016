package com.communication.board;

import java.io.Serializable;


/**
 * The Class NobilityTrackDTO.
 */
public class NobilityTrackDTO implements Serializable{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6770222019663805479L;
	
	/** The array of pawns(DTO). */
	private PawnDTO[] pawns;
	
	/** The Bonus vector(DTO). */
	private BonusDTO[][] BonusVector;
	
	
	/**
	 * Gets the pawns(DTO).
	 *
	 * @return the pawns(DTO)
	 */
	public PawnDTO[] getPawns() {
		return pawns;
	}
	
	/**
	 * Sets the pawns(DTO).
	 *
	 * @param pawns the pawns(DTO) to set
	 */
	public void setPawns(PawnDTO[] pawns) {
		this.pawns = pawns;
	}
	
	/**
	 * Gets the bonus vector(DTO).
	 *
	 * @return the bonusVector(DTO)
	 */
	public BonusDTO[][] getBonusVector() {
		return BonusVector;
	}
	
	/**
	 * Sets the bonus vector(DTO).
	 *
	 * @param bonusVector the bonusVector(DTO) to set
	 */
	public void setBonusVector(BonusDTO[][] bonusVector) {
		BonusVector = bonusVector;
	}

}