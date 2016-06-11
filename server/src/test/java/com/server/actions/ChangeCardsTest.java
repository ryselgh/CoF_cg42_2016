package com.server.actions;

import org.junit.*;

import com.server.model.gamelogic.Game;

import static org.junit.Assert.*;

public class ChangeCardsTest {
	
	
	Game game;
	
	
	@Before
	public void setUp()
		throws Exception {
		game = new Game(2,true,null);
	}
	
	@Test
	public void testChangeCards_1()
		throws Exception {
		int balconyIndex = 1;

		ChangeCards result = new ChangeCards(balconyIndex);

		assertNotNull(result);
		
	}
	
	@Test
	public void testChangeCards_2()
		throws Exception {
		int balconyIndex = 1;
		ChangeCards fixture = new ChangeCards(1);
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

	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ChangeCardsTest.class);
	}
}