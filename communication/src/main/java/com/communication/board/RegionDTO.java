package com.communication.board ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.values.RegionName;



public class RegionDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3020015835312025900L;
	private BonusCardDTO bc;
	private ArrayList <CityDTO> cities;
	private RegionName name;
	
	
	/**
	 * @return the bonus card
	 */
	public BonusCardDTO getBonusCard() {
		return bc;
	}
	/**
	 * @param bc the bonus card to set
	 */
	public void setBonusCard(BonusCardDTO bc) {
		this.bc = bc;
	}
	/**
	 * @return the cities
	 */
	public ArrayList<CityDTO> getCities() {
		return cities;
	}
	/**
	 * @param cities the cities to set
	 */
	public void setCities(ArrayList<CityDTO> cities) {
		this.cities = cities;
	}
	/**
	 * @return the name
	 */
	public RegionName getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(RegionName name) {
		this.name = name;
	}
	
	
	
}

