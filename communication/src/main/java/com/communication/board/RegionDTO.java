package com.communication.board ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.values.RegionName;




/**
 * The Class RegionDTO.
 */
public class RegionDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3020015835312025900L;
	
	/** The bonuscard(DTO). */
	private BonusCardDTO bc;
	
	/** The list of cities(DTO). */
	private ArrayList <CityDTO> cities;
	
	/** The name. */
	private RegionName name;
	
	
	/**
	 * Gets the bonus card(DTO).
	 *
	 * @return the bonus card(DTO)
	 */
	public BonusCardDTO getBonusCard() {
		return bc;
	}
	
	/**
	 * Sets the bonus card(DTO).
	 *
	 * @param bc the bonus card (DTO)to set
	 */
	public void setBonusCard(BonusCardDTO bc) {
		this.bc = bc;
	}
	
	/**
	 * Gets the list of cities(DTO).
	 *
	 * @return the list of cities(DTO)
	 */
	public ArrayList<CityDTO> getCities() {
		return cities;
	}
	
	/**
	 * Sets the list of cities(DTO).
	 *
	 * @param cities the list of cities(DTO) to set
	 */
	public void setCities(ArrayList<CityDTO> cities) {
		this.cities = cities;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public RegionName getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(RegionName name) {
		this.name = name;
	}
	
	
	
}

