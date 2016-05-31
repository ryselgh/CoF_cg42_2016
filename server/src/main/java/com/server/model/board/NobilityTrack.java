package com.server.model.board;

import java.util.ArrayList;

import com.communication.board.BonusDTO;
import com.communication.board.NobilityTrackDTO;
import com.communication.board.PawnDTO;



public class NobilityTrack {
	

	private Pawn[] pawns;
	private Bonus[][] BonusVector;

	
	public NobilityTrackDTO toDTO(){
		NobilityTrackDTO ntDTO = new NobilityTrackDTO();
		BonusDTO[][] bvDTO = new BonusDTO[BonusVector.length][];
		for(int i=0;i<BonusVector.length;i++){
			bvDTO[i] = new BonusDTO[BonusVector[i].length];
			for(int j=0;j<BonusVector[i].length;j++)
				bvDTO[i][j] = BonusVector[i][j].toDTO();
		}
		PawnDTO[] pDTO = new PawnDTO[pawns.length];
			for(int k=0;k<pawns.length;k++)
				pDTO[k] = pawns[k].toDTO();
		ntDTO.setBonusVector(bvDTO);
		ntDTO.setPawns(pDTO);
		return ntDTO;
	}
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
	
	public Bonus[] advance(int pawnIndex, int av) {
		Pawn p = this.pawns[pawnIndex];
		int start = p.getPos(), i=0;
		p.setPos(start + av);
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
	
	public Pawn[] getPawn()
	{
		return this.pawns;
	}

}
