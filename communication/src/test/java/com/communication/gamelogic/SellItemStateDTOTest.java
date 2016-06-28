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
	// getters and setters are tested together


	@Test
	public void testGetGame()
		throws Exception {
		SellItemStateDTO fixture = new SellItemStateDTO();
		fixture.setGame(gameDTO);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		
	}

	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SellItemStateDTOTest.class);
	}
}