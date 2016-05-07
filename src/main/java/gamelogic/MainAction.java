package gamelogic ;

import java.util.ArrayList;
import java.util.Arrays;

import board.Balcony;
import board.Councilor;
import decks.PoliticsCard;
import model.RegionName;

public class MainAction {

	private int actionCounter;
	private Game game;
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MainAction(Game game){
		this.game = game;
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
		ArrayList <Councilor> tmpBalcony = new ArrayList<Councilor>(Arrays.asList(balcony.getBalcony()));
		for(PoliticsCard p: politics){
			for(Councilor c: tmpBalcony){
				if(c.getCouncilorColor().equals(p.getColor()))
					{
						tmpBalcony.remove(c);
						counter++;
					}
			}
		}
		if(
			counter == politics.length && (
				(counter==1 && game.getActualPlayer().getCoins()>=10) ||
				(counter==2 && game.getActualPlayer().getCoins()>=7) ||
				(counter==3 && game.getActualPlayer().getCoins()>=4) ||
				(counter==4 && game.getActualPlayer().getCoins()>=0)
			)
		)
			return true;
		else
			return false;
	}
	//per ottenere caete permesso: OBTPERM-REGIONE,SLOT ->  in pasto ad una funzione che smista i comandi e chiama le funzioni opportune(ad es obtainPermit)
	//public PermitCard obtainPermit(String region, String slot) {
		//map.getpolticsdeck(parseRegion(region)).getSlot(Integer.parseint(slot),true)
		
	//}
	
	private int parseRegion(String reg){
		for(int i=0;i<RegionName.values().length;i++){
			if(reg.toLowerCase().equals(RegionName.values()[i]))
			{
				return i;
			}
		}
		return -1;
			
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

