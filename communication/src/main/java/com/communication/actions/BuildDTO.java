package com.communication.actions;

import java.io.Serializable;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;


/**
 * The Class BuildDTO.
 */
public class BuildDTO extends ActionDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6895733758879038L;
	
	/** The cityDTO. */
	private CityDTO city;
	
	/** The permitCardDTO. */
	private PermitsCardDTO permit;
	
	/**
	 * Instantiates a new builds(DTO) .
	 */
	public BuildDTO(){}
	
	/**
	 * Gets the city(DTO).
	 *
	 * @return the city
	 */
	public CityDTO getCity() {
		return city;
	}
	
	/**
	 * Sets the city(DTO).
	 *
	 * @param city the city to set
	 */
	public void setCity(CityDTO city) {
		this.city = city;
	}
	
	/**
	 * Gets the permit(DTO).
	 *
	 * @return the permitCaRD
	 */
	public PermitsCardDTO getPermit() {
		return permit;
	}
	
	/**
	 * Sets the permitCard(DTO).
	 *
	 * @param permit the permit to set
	 */
	public void setPermit(PermitsCardDTO permit) {
		this.permit = permit;
	}

}
