package board;

public class Balcony
{
	
	private Councilor[] councilor;
	//private Region region;

	/**
	 * constructor of the balcony
	 * @param c c is the array of the councilors
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

	
//	public Region getRegion() {
//		return region;
//	}
	
}

