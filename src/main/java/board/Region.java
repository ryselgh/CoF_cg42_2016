package board ;

import java.util.ArrayList;

import model.RegionName;



public class Region
{
	
	
	private BonusCard bc;
	private ArrayList <City> cities;
	private RegionName name;
	

	/**
	 * constructor of the region
	 * @param n n is the name of the region
	 */
	
	public Region(RegionName n) {
		this.name = n;
		this.cities = new ArrayList <City>();
	}
	
	/**
	 * @return the cities in a region
	 */
	
	public ArrayList <City> getCities() {
		return cities;	
	}
	/**
	 * add a city in a region
	 * @param c c is the city to add
	 */
	
	public void addCity(City c)
	{
		cities.add(c);
	}
	
	/**
	 * set the bonus for each region
	 * @param b b is the bonus for each region
	 */
	public void setBonus(BonusCard b)
	{
		this.bc = b;
	}
	
	/**
	 * @return the name of the region
	 */
	public RegionName getName()
	{
		return this.name;
	}
	
	/**
	 * @return the bonus in each region
	 */
	
	public BonusCard getBonus()
	{
		return this.bc;
	}
	
}

