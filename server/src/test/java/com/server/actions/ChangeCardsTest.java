package com.server.actions;

import org.junit.*;

import com.server.model.decks.PermitsCard;
import com.server.model.decks.PermitsDeck;
import com.server.model.gamelogic.Game;

import static org.junit.Assert.*;

public class ChangeCardsTest {
	
	
	Game game;
	String[] players;
	
	
	@Before
	public void setUp()
		throws Exception {
		//sets for create the game
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3,"Default map1.xml",null,players);
	}
	
	@Test
	public void testTheConstructorOfChangeCards()
		throws Exception {
		int balconyIndex = 1;

		ChangeCards result = new ChangeCards(balconyIndex);
		result.setGame(game);

		assertNotNull(result);
		
	}
	
	@Test
	public void testChangeCardsGetError()
		throws Exception {
		int balconyIndex = 1;
		ChangeCards fixture = new ChangeCards(balconyIndex);
		fixture.setGame(game);
		game.getActualPlayer().getAvailableAssistants().remove(0);
		fixture.isValid();
		ActionReturn result = fixture.execute();

		assertNotNull(result.getError());
	}
	
	
	@Test
	public void testExecuteRegione0()
		throws Exception {
		ChangeCards fixture = new ChangeCards(0);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
	}
	
	

	@Test
	public void testExecuteRegion1()
		throws Exception {
		ChangeCards fixture = new ChangeCards(1);
		fixture.setGame(game);
		
		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
	}

	@Test
	public void testExecuteRegion2()
		throws Exception {
		ChangeCards fixture = new ChangeCards(2);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
	}

	

	@Test
	public void testIsValidReturnsFalse()
		throws Exception {
		ChangeCards fixture = new ChangeCards(1);
		fixture.setGame(game);
		game.getActualPlayer().getAvailableAssistants().remove(0);

		boolean result = fixture.isValid();

		
		assertFalse(result);
	}

	@Test
	public void testIsValidReturnsTrue()
		throws Exception {
		ChangeCards fixture = new ChangeCards(1);
		fixture.setGame(game);

		boolean result = fixture.isValid();

		
		assertTrue(result);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ChangeCardsTest.class);
	}
}