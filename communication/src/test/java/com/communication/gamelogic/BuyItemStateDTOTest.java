package com.communication.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class BuyItemStateDTOTest {
	GameDTO gameDTO;
	
	
	@Before
	public void setUp()
		throws Exception {
		
		//all you need to test the methods
		
	gameDTO = new GameDTO();
		
	}
	
	
	@Test
	public void testBuyItemStateDTO()
		throws Exception {
		BuyItemStateDTO result = new BuyItemStateDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetGame()
		throws Exception {
		BuyItemStateDTO fixture = new BuyItemStateDTO();
		fixture.setGame(gameDTO);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMarket());
		assertEquals(false, result.isFinalTurn());
		assertEquals(null, result.getPlayers());
		assertEquals(0, result.getPlayersQty());
		assertEquals(null, result.getActualPlayer());
	}

	

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyItemStateDTOTest.class);
	}
}