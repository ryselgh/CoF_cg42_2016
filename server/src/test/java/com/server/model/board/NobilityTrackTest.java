package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.board.BonusDTO;
import com.communication.board.NobilityTrackDTO;
import com.communication.board.PawnDTO;
import com.communication.values.BonusType;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class NobilityTrackTest {
	
	
	Game game;
	String[] players;
	@Before
	public void setUp()
		throws Exception {
		
		players= new String[3];
		
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, true, null, players);
		
		
	}
		
		
		
	
	
	
	
	@Test
	public void testNobilityTrack_1()
		throws Exception {
		

		NobilityTrack result = game.getMap().getNobilityTrack();

		
		assertNotNull(result);
	}
	
	

	

	@Test
	public void testAdvance_2()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();
		

		Bonus[] result = fixture.advance(0, 3);

		
		assertNotNull(result);
		assertEquals(result.length,2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNoAdvance()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();
		int pawnIndex = 1;
		int av = 0;

		Bonus[] result = fixture.advance(pawnIndex, av);

		
		
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testAdvance_4()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();
		int pawnIndex = 7;
		int av = 1;

		Bonus[] result = fixture.advance(pawnIndex, av);

		
		
	}

	@Test
	public void testGetBonus()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();
		
		game.getMap().getNobilityTrack().getPawn()[0].setPos(1);

		Bonus[] result = fixture.getBonus(game.getMap().getNobilityTrack().getPawn()[0]);

		
		assertNotNull(result);
	}

	@Test
	public void testGetBonusVector_1()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();

		Bonus[][] result = fixture.getBonusVector();

		
		assertNotNull(result);
	}

	@Test
	public void testGetPawn()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();

		Pawn[] result = fixture.getPawn();

		
		assertNotNull(result);
	}

	@Test
	public void testToDTO()
		throws Exception {
		NobilityTrack fixture = game.getMap().getNobilityTrack();

		NobilityTrackDTO result = fixture.toDTO();
		
		

	
		assertNotNull(result);
		assertTrue (result instanceof NobilityTrackDTO);
		assertTrue(result.getBonusVector() instanceof BonusDTO[][]);
		assertTrue(result.getPawns() instanceof PawnDTO[]);
		
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(NobilityTrackTest.class);
	}
}