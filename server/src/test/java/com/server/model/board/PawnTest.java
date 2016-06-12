package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.PawnDTO;
import com.server.model.gamelogic.Player;

public class PawnTest {
	
	Player player;
	String hexColor;
	
	@Before
	public void setUp()
		throws Exception {
		
		player = new Player("1");
		hexColor = "#66FF00";
	}
	
	@Test
	public void testPawn_1()
		throws Exception {
		
		Pawn result = new Pawn(player, hexColor);
		result.setPos(3);

		assertNotNull(result);
		assertEquals(3, result.getPos());
		assertEquals(hexColor, result.getColor());
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		Pawn fixture = new Pawn(player,hexColor);
		fixture.setPos(1);

		String result = fixture.getColor();

		assertEquals(hexColor, result);
	}

	@Test
	public void testGetPlayer_1()
		throws Exception {
		Pawn fixture = new Pawn(player,hexColor);
		fixture.setPos(1);

		Player result = fixture.getPlayer();

		assertNotNull(result);
		assertEquals(1, result.getID());
		assertEquals(0, result.getScore());
		assertEquals(null, result.getPawn());
		assertEquals(false, result.hasUncoveredPermits());
		assertEquals(0, result.getCoins());
	}

	@Test
	public void testGetPos_1()
		throws Exception {
		Pawn fixture = new Pawn(player,hexColor);
		fixture.setPos(1);

		int result = fixture.getPos();

		assertEquals(1, result);
	}

//	@Test
//	public void testSetPos_1()
//		throws Exception {
//		Pawn fixture = new Pawn(new Player(1), "");
//		fixture.setPos(1);
//		int pos = 1;
//
//		fixture.setPos(pos);
//
//	}

	@Test
	public void testToDTO_1()
		throws Exception {
		Pawn fixture = new Pawn(player,hexColor);
		fixture.setPos(1);

		PawnDTO result = fixture.toDTO();

		assertNotNull(result);
		assertEquals(1, result.getPos());
		assertEquals(hexColor, result.getHexColor());
		assertTrue(result instanceof PawnDTO);
	}

	
//
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PawnTest.class);
	}
}