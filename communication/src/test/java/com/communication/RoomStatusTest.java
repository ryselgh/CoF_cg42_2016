package com.communication;

import java.util.ArrayList;
import org.junit.*;

import com.communication.values.RoomState;

import static org.junit.Assert.*;

public class RoomStatusTest {
	
	String rn;
	String an;
	ArrayList<String> players;
	int minpl;
	int maxpl; 
	
	@Before
	public void setUp()
		throws Exception {
		rn = "SecretChamber";
		an = "TomRiddle";
		players = new ArrayList<String>();
		minpl = 2;
		maxpl = 6;
	}
	
	
	@Test
	public void testRoomStatus()
		throws Exception {
		
		RoomStatus result = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);

		assertNotNull(result);
		
	}
	
	// getters and setters are tested together


	@Test
	public void testGetAdminName()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);

		String result = fixture.getAdminName();

		assertEquals("TomRiddle", result);
	}

	@Test
	public void testGetMaxPlayers()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);

		int result = fixture.getMaxPlayers();

		assertEquals(6, result);
	}

	@Test
	public void testGetMinPlayers()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);

		int result = fixture.getMinPlayers();

		assertEquals(2, result);
	}

	@Test
	public void testGetPlayers()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);

		ArrayList<String> result = fixture.getPlayers();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetRoomName()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);
		String result = fixture.getRoomName();

		assertEquals("SecretChamber", result);
	}

	@Test
	public void testGetMapName(){
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);
		String result = fixture.getMapName();
		assertEquals("Default map1.xml", result);
	}
		
		@Test 
		public void testGetTimerDelay(){
			RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "Default map1.xml",0,RoomState.WAITING_PLAYERS);
			int result = fixture.getTimerDelay();
			assertEquals(0,result);
	}
	
	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(RoomStatusTest.class);
	}
}