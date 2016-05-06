package gamelogic ;

import board.Balcony;
import board.Councilor;
import decks.PoliticsCard;

public class MainAction {

	private int actionCounter;
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MainAction(Game game){
		
	}

	/**
	 * @param c is the number of actions you want to set. Default is 1, should be set when there is a condition that gives another Action to the player in the same turn
	 */
	
	public void setActionCounter(int c) {
		this.actionCounter = c;	
	}
	
	/**
	 * Obtain a permit by satisfying a council
	 */
	
	public boolean canObtainPermit(PoliticsCard[] politics, Balcony balcony) {
		int counter = 0;
		for(PoliticsCard p: politics){
			for(Councilor c: balcony.getBalcony()){
				if(c.getCouncilorColor().equals(p.getColor()))
					counter++;
			}
		}
		if(counter == politics.length)
			return true;
		else
			return false;
	}
	
	public void obtainPermit(Balcony balcony,int selection) {
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void satisfyKing() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void shiftCouncil() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void build() {
		// TODO implement me	
	}
	
}

