package com.communication.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class SellItemStateDTOTest {
	GameDTO gameDTO;
	@Before
	public void setUp()
		throws Exception {
		
		gameDTO = new GameDTO();
	}
	
	@Test
	public void testSellItemStateDTO_1()
		throws Exception {
		SellItemStateDTO result = new SellItemStateDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		SellItemStateDTO fixture = new SellItemStateDTO();
		fixture.setGame(gameDTO);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMarket());
		assertEquals(false, result.isFinalTurn());
		assertEquals(null, result.getMainAction());
		assertEquals(null, result.getPlayers());
		assertEquals(0, result.getPlayersQty());
		assertEquals(null, result.getSpeedAction());
		assertEquals(null, result.getActualPlayer());
	}

	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SellItemStateDTOTest.class);
	}
}