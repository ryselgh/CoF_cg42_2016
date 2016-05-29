package com.server.model.board ;

import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.values.BonusType;
import com.server.model.gamelogic.Game;

public class BonusToken
{
	
	
	private Bonus[] b;
	

	/**
	 * constructor  of the token
	 * @param b b is the bonus on the token
	 */
	
	public BonusToken(Bonus[] b) {
		this.b = b;	
	}
	
	/**
	 * @return the bonus on token
	 */
	
	public Bonus[] getBonus() {
		return this.b;	
	}
	
	public void setterFromDTO(BonusTokenDTO btDTO){//non si collega all'istanza corrispondente in game, perchè serve solo a riscuotere i bonus
		Bonus[] bonus = new Bonus[btDTO.getBonus().length];
		for(int i = 0;i<bonus.length;i++){
			BonusDTO bonusDTO = btDTO.getBonus()[i];
			bonus[i] = new Bonus(bonusDTO.getType(),bonusDTO.getQnt());
		}
		this.b = bonus;
	}
}

