package com.server.model.decks ;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.board.BonusDTO;
import com.communication.decks.PermitsCardDTO;
import com.server.model.board.Bonus;
import com.server.model.gamelogic.Player;



public class PermitsCard {


	private Bonus[] bonuses;
	private String[] cityLetter;
	private boolean faceDown=true;





	/**
	 * @return the bonuses of the card
	 */
	public Bonus[] getBonus() {
		return this.bonuses;	
	}



	/**
	 * @return the cityLetter
	 */
	public String[] getCityLetter() {
		return cityLetter;
	}





	/**
	 * constructor of the permitscard
	 * @param b  the bonus on the card
	 * @param l the letter of the city
	 */
	public PermitsCard(Bonus[] b, String[] l) {
		if(b == null || l == null)
			throw new NullPointerException();
		bonuses= b;
		cityLetter = l;

	}

	/**
	 * @return true if the card is faced down
	 */
	public boolean isFaceDown() {
		return faceDown;
	}



	/**
	 * @param faceDown set to true when the card should be faced down
	 */
	public void setFaceDown(boolean faceDown) {
		this.faceDown = faceDown;
	}

	public static PermitsCard fromDTO(PermitsCardDTO pcDTO, Player player){//ritorna il riferimento della carta permesso posseduta dal giocatore
		for(PermitsCard pc : player.getPermits())
			if(pc.equalsDTO(pcDTO))
				return pc;
		return null;
	}

	public boolean equalsDTO(PermitsCardDTO pcDTO){
		if (!(pcDTO == null)) {
			int count = 0;
			for(int i=0;i<bonuses.length;i++)
				if(bonuses[i].toDTO().getQnt()==pcDTO.getBonuses()[i].getQnt() && bonuses[i].toDTO().getType().equals(pcDTO.getBonuses()[i].getType()))
					count++;
			if (Arrays.equals(pcDTO.getCityLetter(), cityLetter) && count == bonuses.length)
				return true;
			return false;
		} else
			throw new NullPointerException("pcDTO cannot be null");
	}


	public PermitsCardDTO toDTO(){
		PermitsCardDTO pcDTO = new PermitsCardDTO();
		ArrayList<BonusDTO> bonusesDTO = new ArrayList<BonusDTO>();
		for(Bonus b: bonuses)
			bonusesDTO.add(b.toDTO());
		BonusDTO[] bonusesDTOArray = new BonusDTO[bonusesDTO.size()];
		bonusesDTO.toArray(bonusesDTOArray);
		pcDTO.setBonuses(bonusesDTOArray);
		pcDTO.setCityLetter(cityLetter);
		pcDTO.setFaceDown(faceDown);
		return pcDTO;
	}



}

