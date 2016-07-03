package com.server.model.board ;

import com.communication.board.EmporiumDTO;
import com.communication.board.PawnDTO;
import com.communication.gamelogic.PlayerDTO;
import com.server.model.gamelogic.Player;




/**
 * The Class Emporium.
 */
public class Emporium
{
	
	
	/** The player. */
	private Player player;
	

	/**
	 * the constructor of the emporium.
	 *
	 * @param p p is the player
	 */
	
	public Emporium(Player p) {
		this.player = p;	
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player of the emporium
	 */
	
	public Player getPlayer() {
		return this.player;	
	}
	
	/**
	 * To dto.
	 *
	 * @param plDTO the playerDTO
	 * @return the emporium dto
	 */
	public EmporiumDTO toDTO(PlayerDTO plDTO){
		if(this.player.getID().equals("_FakePlayer_")){
			EmporiumDTO eDTO = new EmporiumDTO();
			PawnDTO pawnDTO = new PawnDTO();
			pawnDTO.setHexColor(this.getPlayer().getPawn().getColor());
			PlayerDTO fakePl = new PlayerDTO();
			fakePl.setPlayerID("_FakePlayer_");
			fakePl.setPawn(pawnDTO);
			pawnDTO.setP(fakePl);
			eDTO.setPlayer(fakePl);
			return eDTO;
		}
		EmporiumDTO eDTO = new EmporiumDTO();
		eDTO.setPlayer(plDTO);
		return eDTO;
	}
	
}

