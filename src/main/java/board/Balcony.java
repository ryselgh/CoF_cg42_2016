package board;

public class Balcony
{
	
	private Councilor[] councilor;
	//private Region region;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Balcony(Councilor[] c) {
		this.councilor = c;	
		//this.region = region;
	}
	
	/**
	 * @return the councilors in that balcony
	 */
	
	public Councilor[] getCouncilors() {
		return this.councilor;	
	}

	/**
	 * @return the region
	 */
//	public Region getRegion() {
//		return region;
//	}
	
}

