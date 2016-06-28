package com.communication.board;

import org.junit.*;


import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;


import static org.junit.Assert.*;



public class NobilityTrackDTOTest {
	
	BonusDTO bonus;
	PlayerDTO player1;
	PlayerDTO player2;
	PlayerDTO player3;
	BonusDTO[][] bonusVector;
	PawnDTO[] pawns;
	
	@Before
	public void setUp()
		throws Exception {
		
		//all you need to set on a map
				//3 pawns
				pawns=new PawnDTO[3];
				pawns[0]=new PawnDTO();
				pawns[0].setHexColor("green");
				pawns[0].setPos(7);
				pawns[0].setP(player1);
				pawns[1]=new PawnDTO();
				pawns[1].setHexColor("yellow");
				pawns[1].setPos(7);
				pawns[1].setP(player2);
				pawns[2]=new PawnDTO();
				pawns[2].setHexColor("red");
				pawns[2].setPos(7);
				pawns[2].setP(player3);
				
				bonus = new BonusDTO();
				bonus.setQuantity(1);
				bonus.setType(BonusType.ASSISTANT);
				bonusVector=new BonusDTO[15][];
		
		
		
	}
	
	@Test
	public void testNobilityTrackDTO()
		throws Exception {
		NobilityTrackDTO result = new NobilityTrackDTO();
		assertNotNull(result);
	}
	// getters and setters are tested together


	@Test
	public void testGetBonusVector()
		throws Exception {
		NobilityTrackDTO fixture = new NobilityTrackDTO();
		fixture.setBonusVector(bonusVector);
		fixture.setPawns(pawns);

		BonusDTO[][] result = fixture.getBonusVector();

		
		assertNotNull(result);
	}

	@Test
	public void testGetPawns()
		throws Exception {
		NobilityTrackDTO fixture = new NobilityTrackDTO();
		fixture.setBonusVector(bonusVector);
		fixture.setPawns(pawns);

		PawnDTO[] result = fixture.getPawns();

		
		assertNotNull(result);
		assertEquals(result.length,3);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(NobilityTrackDTOTest.class);
	}
}