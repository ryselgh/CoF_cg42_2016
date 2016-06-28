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
	public void testMainActionDTO()
		throws Exception {
		MainActionDTO result = new MainActionDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together


	@Test
	public void testGetActionCounter()
		throws Exception {
		MainActionDTO fixture = new MainActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		int result = fixture.getActionCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetGame()
		throws Exception {
		MainActionDTO fixture = new MainActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MainActionDTOTest.class);
	}
}