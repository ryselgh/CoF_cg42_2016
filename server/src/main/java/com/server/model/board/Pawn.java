package com.server.model.board ;

import com.communication.board.PawnDTO;
import com.server.model.gamelogic.Player;
import com.server.values.PawnColor;
import com.server.values.Track;



public class Pawn
{
	
	
	private Player p;
	private int pos;
	private String hexColor;
	
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Pawn(Player p, String hexColor) {
		this.p = p;
		this.pos = 0;
		this.hexColor = hexColor;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Player getPlayer() {
		return this.p;
	}
	
	
	public int getPos()
	{
		return this.pos;
	}

	public String getColor() {
		return this.hexColor;
	}
	
	public void setPos(int pos)
	{
		this.pos = pos;
	}
	
	public PawnDTO toDTO(){
		PawnDTO pDTO = new PawnDTO();
		pDTO.setHexColor(this.getColor());
		pDTO.setP(this.getPlayer().toDTO());
		pDTO.setPos(this.getPos());
		return pDTO;
	}
}

