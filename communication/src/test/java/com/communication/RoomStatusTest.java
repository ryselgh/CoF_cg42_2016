package com.communication;

import java.util.ArrayList;
import org.junit.*;
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
		players = new ArrayList();
		minpl = 2;
		maxpl = 6;
	}
	
	
	@Test
	public void testRoomStatus_1()
		throws Exception {
		
		
		
		boolean defMap = true;

		RoomStatus result = new RoomStatus(rn, an, minpl, maxpl, players,  "default1",0);

		assertNotNull(result);
		
	}

	@Test
	public void testGetAdminName_1()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players,  "default1",0);

		String result = fixture.getAdminName();

		assertEquals("TomRiddle", result);
	}

	@Test
	public void testGetMaxPlayers_1()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "default1",0);

		int result = fixture.getMaxPlayers();

		assertEquals(6, result);
	}

	@Test
	public void testGetMinPlayers_1()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players,  "default1",0);

		int result = fixture.getMinPlayers();

		assertEquals(2, result);
	}

	@Test
	public void testGetPlayers_1()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players,  "default1",0);

		ArrayList<String> result = fixture.getPlayers();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetRoomName_1()
		throws Exception {
		RoomStatus fixture = new RoomStatus(rn, an, minpl, maxpl, players, "default1",0);
		String result = fixture.getRoomName();

		assertEquals("SecretChamber", result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(RoomStatusTest.class);
	}
}