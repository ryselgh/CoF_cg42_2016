package board ;

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
	
	private City[] cities;
	private RegionName name;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Region(RegionName n, City[] c, BonusCard bc) {
		this.name = n;
		this.cities = c;
		this.bc = bc;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public City[] getCities() {
		return cities;	
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

