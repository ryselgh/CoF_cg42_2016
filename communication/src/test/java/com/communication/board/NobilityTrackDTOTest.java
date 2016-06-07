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
	
	@Before
	public void setUp()
		throws Exception {
		
		bonus = new BonusDTO();
		bonus.setQuantity(1);
		bonus.setType(BonusType.ASSISTANT);
		bonusVector=new BonusDTO[15][];
		
		
		
	}
	
//	@Test
//	public void testNobilityTrackDTO_1()
//		throws Exception {
//		NobilityTrackDTO result = new NobilityTrackDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetBonusVector_1()
		throws Exception {
		NobilityTrackDTO fixture = new NobilityTrackDTO();
		fixture.setBonusVector(bonusVector);
		fixture.setPawns(new PawnDTO[] {});

		BonusDTO[][] result = fixture.getBonusVector();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
	}

	@Test
	public void testGetPawns_1()
		throws Exception {
		NobilityTrackDTO fixture = new NobilityTrackDTO();
		fixture.setBonusVector(new BonusDTO[][] {});
		fixture.setPawns(new PawnDTO[] {});

		PawnDTO[] result = fixture.getPawns();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
	}

//	@Test
//	public void testSetBonusVector_1()
//		throws Exception {
//		NobilityTrackDTO fixture = new NobilityTrackDTO();
//		fixture.setBonusVector(new BonusDTO[][] {});
//		fixture.setPawns(new PawnDTO[] {});
//		BonusDTO[][] bonusVector = new BonusDTO[][] {};
//
//		fixture.setBonusVector(bonusVector);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.IllegalArgumentException: argument type mismatch
//	}
//
//	@Test
//	public void testSetPawns_1()
//		throws Exception {
//		NobilityTrackDTO fixture = new NobilityTrackDTO();
//		fixture.setBonusVector(new BonusDTO[][] {});
//		fixture.setPawns(new PawnDTO[] {});
//		PawnDTO[] pawns = new PawnDTO[] {};
//
//		fixture.setPawns(pawns);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.IllegalArgumentException: argument type mismatch
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(NobilityTrackDTOTest.class);
	}
}