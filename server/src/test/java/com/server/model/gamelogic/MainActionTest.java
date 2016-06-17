package com.server.model.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class MainActionTest {
	
	Game game;
	String[] players;
	@Before

	
	public void setUp()
		throws Exception {
		
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, true, null, players);
	}
	
	@Test
	public void testMainAction_1()
		throws Exception {
		
		MainAction result = new MainAction(game);

		
		assertNotNull(result);
	}
	
	@Test
	public void testAddActionCounter_1()
		throws Exception {
		MainAction fixture = new MainAction(game);
		fixture.setActionCounter(1);
		int i = 1;

		fixture.addActionCounter(i);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(fixture.getActionCounter(),2);
	}
		
		@Test
		public void testGetActionCounter()
			throws Exception {
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);

			int result = fixture.getActionCounter();

			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.ArrayIndexOutOfBoundsException: 0
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
			assertEquals(1, result);
		}
	
	
}