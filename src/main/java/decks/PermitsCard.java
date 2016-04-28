package decks ;

import board.Bonus;
import model.Letter;
import model.RegionName;



public class PermitsCard {
	
	
	private Bonus[] bonuses;
	private String[] cityLetter = {"a","b","c","d","e","f","g","h","i","l","m","n","o"};
	private boolean faceDown=true;
	private RegionName regionName;
	
	


	
	/**
	 * @return the bonuses of the card
	 */
	public Bonus[] getBonus() {
		return this.bonuses;	
	}


	
	/**
	 * @return the cityLetter
	 */
	public String[] getCityLetter() {
		return cityLetter;
	}



	/**
	 * @return the regionName
	 */
	public RegionName getRegionName() {
		return regionName;
	}


	/**
	 * @return the cityLetter
	 */
	public PermitsCard(Bonus[] b, String[] l,RegionName r) {
		bonuses= b;
		cityLetter = l;
		regionName = r;
		
	}



	/**
	 * @return true if the card is faced down
	 */
	public boolean isFaceDown() {
		return faceDown;
	}



	/**
	 * @param faceDown set to true when the card should be faced down
	 */
	public void setFaceDown(boolean faceDown) {
		this.faceDown = faceDown;
	}
	 
	
		
	
	
	
	
	
}

