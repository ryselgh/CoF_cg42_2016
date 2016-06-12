package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.board.BonusDTO;
import com.communication.board.NobilityTrackDTO;
import com.communication.board.PawnDTO;
import com.communication.values.BonusType;
import com.server.model.gamelogic.Player;

public class NobilityTrackTest {
	
	Pawn[] pawns;
	Bonus[][] bv;
	Player[] players;
	@Before
	public void setUp()
		throws Exception {
		
		players= new Player[3];
		
		players[0] = new Player("1");
		players[1] = new Player("2");
		players[2] = new Player("3");
		
		pawns = new Pawn[3];
		pawns[0] = new Pawn(players[0], "#FF0000");
		pawns[1] = new Pawn(players[1], "#66FF00");
		pawns[2] = new Pawn(players[2], "#FFFF33");
		
		bv= new Bonus[][] { {new Bonus(BonusType.ASSISTANT,3), new Bonus(BonusType.FREECARD,1)},
		{}, {},{new Bonus(BonusType.NOBILITY,2)}, {}, {} };
		
	}
		
		
		
	
	
	
	
	@Test
	public void testNobilityTrack_1()
		throws Exception {
		

		NobilityTrack result = new NobilityTrack(pawns, bv);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
	}
	
	

	

	@Test
	public void testAdvance_2()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns,bv);
		int pawnIndex = 1;
		int av = 1;

		Bonus[] result = fixture.advance(pawnIndex, av);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
		assertEquals(result.length,2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNoAdvance()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns,bv);
		int pawnIndex = 1;
		int av = 0;

		Bonus[] result = fixture.advance(pawnIndex, av);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testAdvance_4()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns,bv);
		int pawnIndex = 7;
		int av = 1;

		Bonus[] result = fixture.advance(pawnIndex, av);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		
	}

	@Test
	public void testGetBonus_1()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns, bv);
		
		pawns[0].setPos(1);

		Bonus[] result = fixture.getBonus(pawns[0]);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
	}

	@Test
	public void testGetBonusVector_1()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns, bv);

		Bonus[][] result = fixture.getBonusVector();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
	}

	@Test
	public void testGetPawn_1()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns, bv);

		Pawn[] result = fixture.getPawn();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		NobilityTrack fixture = new NobilityTrack(pawns, bv);

		NobilityTrackDTO result = fixture.toDTO();
		
		

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: argument type mismatch
		assertNotNull(result);
		assertTrue (result instanceof NobilityTrackDTO);
		assertTrue(result.getBonusVector() instanceof BonusDTO[][]);
		assertTrue(result.getPawns() instanceof PawnDTO[]);
		
	}

//	@Test
//	public void testToDTO_2()
//		throws Exception {
//		NobilityTrack fixture = new NobilityTrack(new Pawn[] {}, new Bonus[][] {});
//
//		NobilityTrackDTO result = fixture.toDTO();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.IllegalArgumentException: argument type mismatch
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testToDTO_3()
//		throws Exception {
//		NobilityTrack fixture = new NobilityTrack(new Pawn[] {}, new Bonus[][] {});
//
//		NobilityTrackDTO result = fixture.toDTO();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.IllegalArgumentException: argument type mismatch
//		assertNotNull(result);
//	}
//
//	
//
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(NobilityTrackTest.class);
	}
}