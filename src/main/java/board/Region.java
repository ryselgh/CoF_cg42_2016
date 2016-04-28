package board ;

import java.util.ArrayList;

import model.RegionName;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Region
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private BonusCard bc;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList <City> cities;
	private RegionName name;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Region(RegionName n) {
		this.name = n;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList <City> getCities() {
		return cities;	
	}
	
	public void addCity(City c)
	{
		cities.add(c);
	}
	
	public void setBonus(BonusCard b)
	{
		this.bc = b;
	}
	
	public RegionName getName()
	{
		return this.name;
	}
	
	public BonusCard getBonus()
	{
		return this.bc;
	}
	
}

