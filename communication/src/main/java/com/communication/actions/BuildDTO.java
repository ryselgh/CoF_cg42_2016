package com.communication.actions;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;

public class BuildDTO extends ActionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6895733758879038L;
	private CityDTO city;
	private PermitsCardDTO permit;
	
	public BuildDTO(){}
	
	/**
	 * @return the city
	 */
	public CityDTO getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(CityDTO city) {
		this.city = city;
	}
	/**
	 * @return the permit
	 */
	public PermitsCardDTO getPermit() {
		return permit;
	}
	/**
	 * @param permit the permit to set
	 */
	public void setPermit(PermitsCardDTO permit) {
		this.permit = permit;
	}

}
