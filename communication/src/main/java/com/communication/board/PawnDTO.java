package com.communication.board ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;



public class PawnDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2979015724106097310L;
	private PlayerDTO p;
	private int pos;
	private String hexColor;
	
	
	/**
	 * @return the p
	 */
	public PlayerDTO getP() {
		return p;
	}
	/**
	 * @param p the p to set
	 */
	public void setP(PlayerDTO p) {
		this.p = p;
	}
	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}
	/**
	 * @param pos the pos to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}
	/**
	 * @return the hexColor
	 */
	public String getHexColor() {
		return hexColor;
	}
	/**
	 * @param hexColor the hexColor to set
	 */
	public void setHexColor(String hexColor) {
		this.hexColor = hexColor;
	}
	
}

