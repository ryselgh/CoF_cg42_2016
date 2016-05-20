package board ;

import java.util.ArrayList;

import values.CityColor;



public class ColorGroup
{
	
	
	private ArrayList <City> cities;
    private CityColor color;
    private BonusCard bc;
	

	/**
	 * constructor of the citycolor group
	 *@param col col is the color of the city
	 
	 */
	
	public ColorGroup(CityColor col) {
		this.color = col;
		this.cities = new ArrayList <City>();
	}
	
	/**
	 * @return the list of the cities which have the same color
	 */
	
	public ArrayList <City> getCity() {
		return cities;	
	}
	
	/**
	 * add a city in the list 
	 * @param c c is the city to add
	 */
	
	public void addCity(City c)
	{
		cities.add(c);
	}
	
	/**
	 * 
	 * @param b b is the bonus for every colorgropu satisfied
	 */
	public void setBonus(BonusCard b)
	{
		this.bc = b;
	}
	
	/**
	 * 
	 * @return the bonus based on the color group
	 */
	
	public BonusCard getBonus()
	{
		return this.bc;
	}
	
	public CityColor getColor()
	{
		return this.color;
	}
	
}

