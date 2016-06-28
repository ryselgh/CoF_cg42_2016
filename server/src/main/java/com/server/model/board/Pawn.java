package com.server.model.board ;

import com.communication.board.PawnDTO;
import com.server.model.gamelogic.Player;




/**
 * The Class Pawn.
 */
public class Pawn
{
	
	
	/** The p. */
	private Player p;
	
	/** The pos. */
	private int pos;
	
	/** The hex color. */
	private String hexColor;
	
	
	

	/**
	 * the construcor of the pawn
	 * @param p the p
	 * @param hexColor the hex color
	 * @generated 
	 * @ordered 
	 */
	
	public Pawn(Player p, String hexColor) {
		this.p = p;
		this.pos = 0;
		this.hexColor = hexColor;
	}
	
	/**
	 *
	 * @return the player
	 * @generated 
	 * @ordered 
	 */
	
	public Player getPlayer() {
		return this.p;
	}
	
	
	/**
	 * Gets the position
	 *
	 * @return the pos
	 */
	public int getPos()
	{
		return this.pos;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return this.hexColor;
	}
	
	/**
	 * Sets the position
	 *
	 * @param pos the new position
	 */
	public void setPos(int pos){
		this.pos = pos;
	}
	
	/**
	 * To dto.
	 *
	 * @return the pawn dto
	 */
	public PawnDTO toDTO(){
		PawnDTO pDTO = new PawnDTO();
		pDTO.setHexColor(this.getColor());
		pDTO.setP(this.getPlayer().toDTO());
		pDTO.setPos(this.getPos());
		return pDTO;
	}
}

