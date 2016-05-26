package com.communication.decks ;

import java.io.Serializable;

import com.communication.board.BonusDTO;


public class KingBonusCardDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7351862483377228961L;
	private BonusDTO bonus;
	private int n;
	
	
	/**
	 * @return the bonus
	 */
	public BonusDTO getBonus() {
		return bonus;
	}
	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(BonusDTO bonus) {
		this.bonus = bonus;
	}
	/**
	 * @return the number of the card from 1 to 5
	 */
	public int getNumber() {
		return n;
	}
	/**
	 * @param n the n to set
	 */
	public void setNumber(int n) {
		this.n = n;
	}
	
	
	
}

