package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class BuyAssistantTest {
	
		String[] players;
		Game game;
		
		@Before
		public void setUp()
			throws Exception {
			
			
			players = new String[3];
			players[0] = "1";
			players[1] = "2";
			players[2] = "3";
			game = new Game(3,"default1", "default1",players);
			
			
		}
		@Test
		public void testBuyAssistant_1()
			throws Exception {
	
			BuyAssistant result = new BuyAssistant();
	
			assertNotNull(result);
		}
	
		@Test
		public void testExecute_1()
			throws Exception {
			BuyAssistant fixture = new BuyAssistant();
			fixture.setGame(game);
	
			ActionReturn result = fixture.execute();
	
			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.NullPointerException
			//       at com.server.model.board.Map.<init>(Map.java:147)
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
			assertNotNull(result);
		}
	
		
		@Test
		public void testIsValid_1()
			throws Exception {
			BuyAssistant fixture = new BuyAssistant();
			fixture.setGame(game);
			game.getActualPlayer().setCoins(5);
			
			boolean result = fixture.isValid();
	
			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.NullPointerException
			//       at com.server.model.board.Map.<init>(Map.java:147)
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
			assertTrue(result);
		}
	
		@Test
		public void testIsValid_2()
			throws Exception {
			BuyAssistant fixture = new BuyAssistant();
			fixture.setGame(game);
			
			game.getActualPlayer().setCoins(1);
			boolean result = fixture.isValid();
			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.NullPointerException
			//       at com.server.model.board.Map.<init>(Map.java:147)
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
			assertFalse(result);
		}
		
		@Test
		public void testGetError()
				throws Exception {
				BuyAssistant fixture = new BuyAssistant();
				fixture.setGame(game);
				
				game.getActualPlayer().setCoins(1);
				fixture.isValid();
				
				
				ActionReturn result = fixture.execute();
				
				assertEquals(result.getError(),"\nYou have not enought coins");
				
		}
	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyAssistantTest.class);
	}
}