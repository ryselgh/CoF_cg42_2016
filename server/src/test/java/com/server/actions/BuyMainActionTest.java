package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

import com.communication.values.BonusType;
import com.server.model.board.Assistant;
import com.server.model.board.Bonus;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class BuyMainActionTest {
	Game game;
	@Before
	public void setUp()
		throws Exception {
		
		game= new Game(3, true, null, null);
		
	}

	@Test
	public void testTheConstructor()
		throws Exception {

		BuyMainAction result = new BuyMainAction();
		assertNotNull(result);
	}

	@Test
	public void testIfTheMethodExecuteReturnsAnActionReturn()
		throws Exception {
		BuyMainAction fixture = new BuyMainAction();
		fixture.setGame(game);
		
		fixture.isValid();

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotNull(result);
	}

	@Test
	public void testIfTheMethodExecuteReturnsTheRightBonus()
		throws Exception {
		Bonus bonus = new Bonus(BonusType.MAINACTION,1);
		BuyMainAction fixture = new BuyMainAction();
		fixture.setGame(game);
		Assistant ass = new Assistant();
		Assistant ass2 = new Assistant();
		game.getActualPlayer().addAssistant(ass);
		game.getActualPlayer().addAssistant(ass2);
		
		fixture.isValid();
		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotNull(result);
		boolean eqType = result.getBonus()[0].getType().equals(bonus.getType());
		boolean eqQnt = result.getBonus()[0].getQnt() == bonus.getQnt();
		boolean eq = eqType && eqQnt;
		assertTrue(eq);
	}

	@Test
	public void testIfTheMethodReturnErrorsAndNotBonus()
		throws Exception {
		BuyMainAction fixture = new BuyMainAction();
		
		fixture.setGame(game);
		fixture.isValid();

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotNull(result.getError());
		assertNull(result.getBonus());
		
	}


	@Test
	public void testIsValid_1()
		throws Exception {
		BuyMainAction fixture = new BuyMainAction();
		fixture.setGame(game);
		

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertFalse(result);
	}

	@Test
	public void testIsValid_2()
		throws Exception {
		BuyMainAction fixture = new BuyMainAction();
		fixture.setGame(game);
		Assistant ass = new Assistant();
		Assistant ass2 = new Assistant();
		game.getActualPlayer().addAssistant(ass);
		game.getActualPlayer().addAssistant(ass2);

		

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertTrue(result);
	}



	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyMainActionTest.class);
	}
}