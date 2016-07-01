package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

import com.server.model.gamelogic.Game;


public class BuyAssistantTest {
	
		String[] players;
		Game game;
		
		@Before
		public void setUp()
			throws Exception {
			
			//sets to create the game
			players = new String[3];
			players[0] = "1";
			players[1] = "2";
			players[2] = "3";
			game = new Game(3,"Default1", null,players);
			
			
		}
		@Test
		public void testBuyAssistant()
			throws Exception {
	
			BuyAssistant result = new BuyAssistant();
	
			assertNotNull(result);
		}
	
		@Test
		public void testExecute()
			throws Exception {
			BuyAssistant fixture = new BuyAssistant();
			fixture.setGame(game);
	
			ActionReturn result = fixture.execute();
	
			
			assertNotNull(result);
		}
	
		
		@Test
		public void testIsValidReturnTrue()
			throws Exception {
			BuyAssistant fixture = new BuyAssistant();
			fixture.setGame(game);
			game.getActualPlayer().setCoins(5);
			
			boolean result = fixture.isValid();
	
			
			assertTrue(result);
		}
	
		@Test
		public void testIsValidReturnFalse()
			throws Exception {
			BuyAssistant fixture = new BuyAssistant();
			fixture.setGame(game);
			
			game.getActualPlayer().setCoins(1);
			boolean result = fixture.isValid();
		
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