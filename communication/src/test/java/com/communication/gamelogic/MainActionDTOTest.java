package com.communication.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class MainActionDTOTest {
	
		GameDTO gameDTO;
	@Before
	public void setUp()
		throws Exception {
		
		gameDTO = new GameDTO();
	}
	@Test
	public void testMainActionDTO_1()
		throws Exception {
		MainActionDTO result = new MainActionDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetActionCounter_1()
		throws Exception {
		MainActionDTO fixture = new MainActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		int result = fixture.getActionCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		MainActionDTO fixture = new MainActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMarket());
		assertEquals(false, result.isFinalTurn());
		assertEquals(false, result.isDefaultMap());
		assertEquals(null, result.getMainAction());
		assertEquals(null, result.getPlayers());
		assertEquals(0, result.getPlayersQty());
		assertEquals(null, result.getSpeedAction());
		assertEquals(null, result.getActualPlayer());
	}

	

	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MainActionDTOTest.class);
	}
}