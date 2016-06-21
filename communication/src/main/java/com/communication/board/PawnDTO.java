package com.communication.board ;

import java.io.Serializable;

import com.communication.gamelogic.PlayerDTO;




/**
 * The Class PawnDTO.
 */
public class PawnDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2979015724106097310L;
	
	/** The player(DTO). */
	private PlayerDTO p;
	
	/** The position. */
	private int pos;
	
	/** The hexadecimal color. */
	private String hexColor;
	
	
	/**
	 * Gets the player(DTO).
	 *
	 * @return the player(DTO)
	 */
	public PlayerDTO getP() {
		return p;
	}
	
	/**
	 * Sets the player(DTO).
	 *
	 * @param p the player(DTO) to set
	 */
	public void setP(PlayerDTO p) {
		this.p = p;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPos() {
		return pos;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param pos the position to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	/**
	 * Gets the hexadecimal color.
	 *
	 * @return the hexColor
	 */
	public String getHexColor() {
		return hexColor;
	}
	
	/**
	 * Sets the hexadecimal color.
	 *
	 * @param hexColor the hexColor to set
	 */
	public void setHexColor(String hexColor) {
		this.hexColor = hexColor;
	}
	
}

