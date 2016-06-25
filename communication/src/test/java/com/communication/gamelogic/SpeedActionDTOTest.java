package com.communication.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class SpeedActionDTOTest {
	
	
	GameDTO gameDTO;
	
	
	@Before
	public void setUp()
		throws Exception {
		
		gameDTO = new GameDTO();
	}
	@Test
	public void testSpeedActionDTO()
		throws Exception {
		SpeedActionDTO result = new SpeedActionDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together


	@Test
	public void testGetActionCounter()
		throws Exception {
		SpeedActionDTO fixture = new SpeedActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		int result = fixture.getActionCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetGame()
		throws Exception {
		SpeedActionDTO fixture = new SpeedActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		
	}

	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SpeedActionDTOTest.class);
	}
}