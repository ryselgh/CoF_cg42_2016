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
	public void testSpeedActionDTO_1()
		throws Exception {
		SpeedActionDTO result = new SpeedActionDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetActionCounter_1()
		throws Exception {
		SpeedActionDTO fixture = new SpeedActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

		int result = fixture.getActionCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		SpeedActionDTO fixture = new SpeedActionDTO();
		fixture.setGame(gameDTO);
		fixture.setActionCounter(1);

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
		new org.junit.runner.JUnitCore().run(SpeedActionDTOTest.class);
	}
}