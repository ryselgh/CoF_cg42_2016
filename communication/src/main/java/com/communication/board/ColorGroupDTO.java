package com.communication.board ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.values.CityColor;



public class ColorGroupDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 917057890008909442L;
	private ArrayList <CityDTO> cities;
    private CityColor color;
    private BonusCardDTO bc;
    
    
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
	 * @return the color
	 */
	public CityColor getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(CityColor color) {
		this.color = color;
	}
	/**
	 * @return the bc
	 */
	public BonusCardDTO getBc() {
		return bc;
	}
	/**
	 * @param bc the bc to set
	 */
	public void setBc(BonusCardDTO bc) {
		this.bc = bc;
	}
    
    
	
}

