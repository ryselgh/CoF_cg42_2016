package board ;

import java.util.ArrayList;

import model.CityColor;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ColorGroup
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList <City> cities;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private CityColor color;
	
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
	
	public ColorGroup(CityColor col) {
		this.color = col;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList <City> getCity() {
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
	
	public BonusCard getBonus()
	{
		return this.bc;
	}
	
}

