package com.server.model.board ;

import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.values.BonusType;
import com.server.model.gamelogic.Game;


/**
 * The Class BonusToken.
 */
public class BonusToken
{
	
	
	/** The b. */
	private Bonus[] b;
	

	/**
	 * constructor  of the token.
	 *
	 * @param b b is the bonus array on the token
	 */
	
	public BonusToken(Bonus[] b) {

		if(b==null)
			throw new NullPointerException("bonus token must have a bonus at least!");

		this.b = b;	
	}
	
	/**
	 * Gets the bonus array.
	 *
	 * @return the bonus on token
	 */
	
	public Bonus[] getBonus() {
		return this.b;	
	}
	
	/**
	 * Sets the bonus token from the DTO
	 *
	 * @param btDTO the dto to set in the normal bt
	 */
	public void setterFromDTO(BonusTokenDTO btDTO){//non si collega all'istanza corrispondente in game, perchè serve solo a riscuotere i bonus
		Bonus[] bonus = new Bonus[btDTO.getBonus().length];
		for(int i = 0;i<bonus.length;i++){
			BonusDTO bonusDTO = btDTO.getBonus()[i];
			bonus[i] = new Bonus(bonusDTO.getType(),bonusDTO.getQnt());
		}
		this.b = bonus;
	}
	
	/**
	 * To dto.
	 *
	 * @return the bonus token dto
	 */
	public BonusTokenDTO toDTO(){
		BonusTokenDTO ret = new BonusTokenDTO();
		BonusDTO[] bDTO = new BonusDTO[this.getBonus().length];
		for(int i=0;i<this.getBonus().length;i++)
			bDTO[i] = this.getBonus()[i].toDTO();
		ret.setBonus(bDTO);
		return ret;
	}
}

