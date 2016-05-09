package board;

import java.util.ArrayList;



public class NobilityTrack {
	

	private Pawn[] pawns;
	private Bonus[][] BonusVector;

	/**
	 * constructor of the nobility track
	 * @param pawn pawn element
	 * @param BonusVector one array for the position, another for the bonus in each position
	 */

	public NobilityTrack(Pawn[] pawn, Bonus[][] BonusVector) {
		this.pawns = pawn;
		this.BonusVector = BonusVector;
	}

	/**
	 * go on for a number av of steps and get the bonus you pass on
	 * @param p p is the pawn
	 * @param av av tells you how many step you have to do
	 * @return all the bonus you get during your progress
	 */
	
	public Bonus[] advance(Pawn p, int av) {
		int start = p.getPos(), i=0;
		ArrayList <Bonus> ret = new ArrayList <Bonus>();
		for(i=start;i<=start + av;i++)
		{
			for(int k=0;k<=1;k++)
				if(BonusVector[i][k]!=null)
					ret.add(BonusVector[i][k]);
		}
		if(ret.size()<1) return null;
		else{
			Bonus[] retArr = new Bonus[ret.size()];
			retArr = ret.toArray(retArr);
			return retArr;
		}
	}

	/**
	 * @return the bonus in the position
	 * @param p p is the pawn in that position
	 */

	public Bonus[] getBonus(Pawn p) {		
		return BonusVector[p.getPos()]; 
	}

	/**
	 * @return the bonusVector
	 */
	public Bonus[][] getBonusVector() {
		return BonusVector;
	}									

}
