package board;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class NobilityTrack {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	private Pawn[] pawns;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	private Bonus[][] BonusVector;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public NobilityTrack(Pawn[] pawn, Bonus[][] BonusVector) {
		this.pawns = pawn;
		this.BonusVector = BonusVector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public Bonus[] getBonus(Pawn p) {		
		return BonusVector[p.getPos()]; 
	}									

}
