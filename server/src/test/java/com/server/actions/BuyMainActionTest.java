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
	String[] players;
	@Before
	public void setUp()
		throws Exception {
		
		//sets for create the game
		
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		
		game= new Game(3, "Default map1.xml", null, players);
		
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

		
		assertNotNull(result.getError());
		assertNull(result.getBonus());
		
	}


	@Test
	public void testIsValidReturnsFalse()
		throws Exception {
		BuyMainAction fixture = new BuyMainAction();
		fixture.setGame(game);
		

		boolean result = fixture.isValid();

		
		assertFalse(result);
	}

	@Test
	public void testIsValidReturnsTrue()
		throws Exception {
		BuyMainAction fixture = new BuyMainAction();
		fixture.setGame(game);
		Assistant ass = new Assistant();
		Assistant ass2 = new Assistant();
		game.getActualPlayer().addAssistant(ass);
		game.getActualPlayer().addAssistant(ass2);

		

		boolean result = fixture.isValid();

		
		assertTrue(result);
	}



	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyMainActionTest.class);
	}
}