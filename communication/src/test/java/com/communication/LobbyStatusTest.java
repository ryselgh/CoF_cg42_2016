package com.communication;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class LobbyStatusTest {
	
	ArrayList<String> freeClients;
	ArrayList<RoomStatus> rooms;
	String client1;
	String client2;
	RoomStatus rs;
	
	@Before
	public void setUp()
		throws Exception {
		
		//all the sets for the tests
		freeClients = new ArrayList();
		rooms = new ArrayList();
		client1="ilfiglio";
		client2="smemo";
		freeClients.add(client1);
		freeClients.add(client2);
		
	}
	
	@Test
	public void testLobbyStatus()
		throws Exception {
		

		LobbyStatus result = new LobbyStatus(freeClients, rooms);

		assertNotNull(result);
	}
	
	// getters and setters are tested together


	@Test
	public void testGetFreeClients()
		throws Exception {
		LobbyStatus fixture = new LobbyStatus(freeClients, rooms);

		ArrayList<String> result = fixture.getFreeClients();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetRooms()
		throws Exception {
		LobbyStatus fixture = new LobbyStatus(freeClients, rooms);

		ArrayList<RoomStatus> result = fixture.getRooms();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(LobbyStatusTest.class);
	}
}