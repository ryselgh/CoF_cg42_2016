package decks ;

import board.Bonus;
import model.Letter;
import model.RegionName;



public class PermitsCard {
	
	
	private Bonus[] bonuses;
	private String[] cityLetter = {"a","b","c","d","e","f","g","h","i","l","m","n","o"};
	
	


	
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
	 * constructor of the permitscard
	 * @param b  the bonus on the card
	 * @param l the letter of the city
	 */
	public PermitsCard(Bonus[] b, String[] l) {
		bonuses= b;
		cityLetter = l;
		
	}

	
		
	
	
	
	
	
}

