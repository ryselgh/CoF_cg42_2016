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
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3,true,null,players);
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
	public void testChangeCards_2()
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
	public void testExecute_1()
		throws Exception {
		ChangeCards fixture = new ChangeCards(1);
		fixture.setGame(game);
		
		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.actions.ChangeCards.execute(ChangeCards.java:43)
		assertNotNull(result);
	}

	@Test
	public void testExecute_2()
		throws Exception {
		ChangeCards fixture = new ChangeCards(2);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.actions.ChangeCards.execute(ChangeCards.java:43)
		assertNotNull(result);
	}

	@Test
	public void testExecute_3()
		throws Exception {
		ChangeCards fixture = new ChangeCards(0);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.actions.ChangeCards.execute(ChangeCards.java:43)
		assertNotNull(result);
	}

	@Test
	public void testIsValid_1()
		throws Exception {
		ChangeCards fixture = new ChangeCards(1);
		fixture.setGame(game);
		game.getActualPlayer().getAvailableAssistants().remove(0);

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.actions.ChangeCards.isValid(ChangeCards.java:24)
		assertFalse(result);
	}

	@Test
	public void testIsValid_2()
		throws Exception {
		ChangeCards fixture = new ChangeCards(1);
		fixture.setGame(game);

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.actions.ChangeCards.isValid(ChangeCards.java:24)
		assertTrue(result);
	}

	

//	@Test
//	public void testTheCorrectChangementOfTheCards(){
//		PermitsCard slot0 = game.getMap().getPermitsDeck(index)
//	
//	}
	
//	@Test
//	public void testIfTheNumberOfCardsInThePermitsDeckDoesntChange(){
//		ChangeCards fixture = new ChangeCards(1);
//		fixture.setGame(game);
//		PermitsDeck permD = game.getMap().getPermitsDeck(1);
//		permD
//		
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ChangeCardsTest.class);
	}
}