package com.communication.board ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.values.CityColor;




/**
 * The Class ColorGroupDTO.
 */
public class ColorGroupDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 917057890008909442L;
	
	/** The list of cities(DTO). */
	private ArrayList <CityDTO> cities;
    
    /** The color. */
    private CityColor color;
    
    /** The bc. */
    private BonusCardDTO bc;
    
    
	/**
	 * Gets The list of cities(DTO).
	 *
	 * @return The list of cities(DTO)
	 */
	public ArrayList<CityDTO> getCities() {
		return cities;
	}
	
	/**
	 * Sets The list of cities(DTO).
	 *
	 * @param cities The list of cities(DTO) to set
	 */
	public void setCities(ArrayList<CityDTO> cities) {
		this.cities = cities;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public CityColor getColor() {
		return color;
	}
	
	/**
	 * Sets the color.
	 *
	 * @param color the color to set
	 */
	public void setColor(CityColor color) {
		this.color = color;
	}
	
	/**
	 * Gets the bonus card(DTO).
	 *
	 * @return the bonuscard(DTO)
	 */
	public BonusCardDTO getBonusCard() {
		return bc;
	}
	
	/**
	 * Sets the bonuscard(DTO).
	 *
	 * @param bc the bonuscard(DTO) to set
	 */
	public void setBc(BonusCardDTO bc) {
		this.bc = bc;
	}
    
    
	
}

