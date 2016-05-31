package com.communication.board;

import java.io.Serializable;

public class NobilityTrackDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6770222019663805479L;
	private PawnDTO[] pawns;
	private BonusDTO[][] BonusVector;
	
	
	/**
	 * @return the pawns
	 */
	public PawnDTO[] getPawns() {
		return pawns;
	}
	/**
	 * @param pawns the pawns to set
	 */
	public void setPawns(PawnDTO[] pawns) {
		this.pawns = pawns;
	}
	/**
	 * @return the bonusVector
	 */
	public BonusDTO[][] getBonusVector() {
		return BonusVector;
	}
	/**
	 * @param bonusVector the bonusVector to set
	 */
	public void setBonusVector(BonusDTO[][] bonusVector) {
		BonusVector = bonusVector;
	}

}