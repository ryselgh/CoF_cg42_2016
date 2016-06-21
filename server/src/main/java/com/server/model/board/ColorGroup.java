package com.server.model.board ;

import java.util.ArrayList;

import com.communication.board.CityDTO;
import com.communication.board.ColorGroupDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.CityColor;




/**
 * The Class ColorGroup.
 */
public class ColorGroup
{
	
	
	/** The cities. */
	private ArrayList <City> cities;
    
    /** The color. */
    private CityColor color;
    
    /** The bc. */
    private BonusCard bc;
	
    /**
     * To dto.
     *
     * @param plsDTO an arrayList of PlayerDTO 
     * @return the color group dto
     */
    public ColorGroupDTO toDTO(ArrayList<PlayerDTO> plsDTO){
    	ColorGroupDTO cgDTO = new ColorGroupDTO();
    	ArrayList<CityDTO> cDTO = new ArrayList<CityDTO>(this.cities.size());
		for(int i=0;i< this.cities.size();i++)
			cDTO.add(this.cities.get(i).toDTO(plsDTO));
		cgDTO.setCities(cDTO);
		cgDTO.setBc(this.bc.toDTO());
		cgDTO.setColor(this.color);
		return cgDTO;
    }

	/**
	 * constructor of the citycolor group.
	 *
	 * @param col col is the color of the city
	 */
	
	public ColorGroup(CityColor col) {
		if(col==null)
			throw new NullPointerException();
		else	
			this.color = col;
		
		this.cities = new ArrayList <City>();
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the list of the cities which have the same color
	 */
	
	public ArrayList <City> getCity() {
		return cities;	
	}
	
	/**
	 * add a city in the list .
	 *
	 * @param c c is the city to add
	 */
	
	public void addCity(City c){
		if (c==null)
			throw new NullPointerException();
		else
			cities.add(c);
	}
	
	/**
	 * Sets the bonus.
	 *
	 * @param b b is the bonus for every colorgroup satisfied
	 */
	public void setBonus(BonusCard b)
	{
		if(b==null)
			throw new NullPointerException();
		else
			this.bc = b;
	}
	
	/**
	 * Gets the bonus.
	 *
	 * @return the bonus based on the color group
	 */
	
	public BonusCard getBonus()
	{
		return this.bc;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public CityColor getColor()
	{
		return this.color;
	}
	
}

