package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.board.EmporiumDTO;
import com.communication.gamelogic.PlayerDTO;
import com.server.model.gamelogic.Player;

public class EmporiumTest {
	
	Player player;
	
	@Before
	public void setUp()
		throws Exception {
		
		player= new Player("1");
		
	}
	
	
	@Test
	public void testEmporium_1()
		throws Exception {
		
		Emporium result = new Emporium(player);

		assertNotNull(result);
	}

	@Test
	public void testGetPlayer_1()
		throws Exception {
		Emporium emp = new Emporium(player);

		Player result = emp.getPlayer();

		assertNotNull(result);
		assertEquals(1, result.getID());
		assertEquals(0, result.getScore());
		assertEquals(null, result.getPawn());
		assertEquals(false, result.hasUncoveredPermits());
		assertEquals(0, result.getCoins());
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		Emporium fixture = new Emporium(player);

		EmporiumDTO result = fixture.toDTO(new PlayerDTO());

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.gamelogic.Player.toDTO(Player.java:299)
		//       at com.server.model.board.Emporium.toDTO(Emporium.java:34)
		assertNotNull(result);
		assertTrue(result instanceof EmporiumDTO);
	}

	

//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EmporiumTest.class);
	}
}