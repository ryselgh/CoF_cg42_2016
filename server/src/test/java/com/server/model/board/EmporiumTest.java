package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.board.EmporiumDTO;
import com.communication.gamelogic.PlayerDTO;
import com.server.model.gamelogic.Player;

public class EmporiumTest {
	
	Player player;
	
	@Before
	public void setUp()
		throws Exception {
		//a player
		player= new Player("1");
		
	}
	
	
	@Test
	public void testEmporium()
		throws Exception {
		
		Emporium result = new Emporium(player);

		assertNotNull(result);
	}

	@Test
	public void testGetPlayer()
		throws Exception {
		Emporium emp = new Emporium(player);

		Player result = emp.getPlayer();

		assertNotNull(result);
		assertEquals("1", result.getID());
		assertEquals(0, result.getScore());
		assertEquals(null, result.getPawn());
		assertEquals(false, result.hasUncoveredPermits());
		assertEquals(0, result.getCoins());
	}

	@Test
	public void testToDTO()
		throws Exception {
		Emporium fixture = new Emporium(player);

		EmporiumDTO result = fixture.toDTO(new PlayerDTO());

		
		assertNotNull(result);
		assertTrue(result instanceof EmporiumDTO);
	}

	



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EmporiumTest.class);
	}
}