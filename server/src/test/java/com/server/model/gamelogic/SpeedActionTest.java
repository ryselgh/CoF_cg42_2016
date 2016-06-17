package com.server.model.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.CouncilorColor;
import com.server.model.board.Assistant;
import com.server.model.board.Councilor;

public class SpeedActionTest {
	
	
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
	public void testSpeedAction_1()
		throws Exception {
		
		SpeedAction result = new SpeedAction(game);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testAddActionCounter_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
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
	public void testBuyAssistant_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		fixture.buyAssistant();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(game.getActualPlayer().getCoins(),7);
	}

	@Test
	public void testBuyAssistant_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		fixture.buyAssistant();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(game.getActualPlayer().getAvailableAssistants().size(),2);
		assertEquals(fixture.getActionCounter(),1);
	}

	@Test
	public void testBuyMainAction_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(3);
		Assistant a1 = new Assistant();
		Assistant a2 = new Assistant();
		game.getActualPlayer().getAvailableAssistants().add(a1);
		game.getActualPlayer().getAvailableAssistants().add(a2);
		fixture.buyMainAction();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(game.getMainAction().getActionCounter(),1);
		
	}

	@Test
	public void testBuyMainAction_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		Assistant a1 = new Assistant();
		Assistant a2 = new Assistant();
		game.getActualPlayer().getAvailableAssistants().add(a1);
		game.getActualPlayer().getAvailableAssistants().add(a2);

		fixture.buyMainAction();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(game.getActualPlayer().getAvailableAssistants().size(),0);
		
		
	}

	@Test
	public void testCanBuyAssistant_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		boolean result = fixture.canBuyAssistant();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result);
	}

	@Test
	public void testCanBuyAssistant_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		game.getActualPlayer().setCoins(2);

		boolean result = fixture.canBuyAssistant();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertFalse(result);
	}

	@Test
	public void testCanBuyMainAction_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		boolean result = fixture.canBuyMainAction();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertFalse(result);
	}

	@Test
	public void testCanBuyMainAction_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		Assistant a1 = new Assistant();
		Assistant a2 = new Assistant();
		game.getActualPlayer().getAvailableAssistants().add(a1);
		game.getActualPlayer().getAvailableAssistants().add(a2);

		boolean result = fixture.canBuyMainAction();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result);
	}

	@Test
	public void testCanChangeCards_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		boolean result = fixture.canChangeCards();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result);
	}

	@Test
	public void testCanChangeCards_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		game.getActualPlayer().getAvailableAssistants().remove(0);

		boolean result = fixture.canChangeCards();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertFalse(result);
	}

	@Test
	public void testCanShiftCouncil_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		boolean result = fixture.canShiftCouncil();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result);
	}

	@Test
	public void testCanShiftCouncil_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		game.getActualPlayer().getAvailableAssistants().remove(0);

		boolean result = fixture.canChangeCards();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertFalse(result);
	}

	@Test
	public void testChangePermitsCards_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		int selection = 1;

		fixture.changePermitsCards(selection);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(game.getActualPlayer().getAvailableAssistants().size(),0);
	}

	@Test
	public void testGetActionCounter_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		int result = fixture.getActionCounter();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(1, result);
	}

	@Test
	public void testHasAtLeastOneAssistant_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);

		boolean result = fixture.hasAtLeastOneAssistant();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result);
	}

	@Test
	public void testHasAtLeastOneAssistant_2()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		game.getActualPlayer().getAvailableAssistants().remove(0);

		boolean result = fixture.canChangeCards();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertFalse(result);
	}

//	@Test
//	public void testSetActionCounter_1()
//		throws Exception {
//		SpeedAction fixture = new SpeedAction(new Game(1, true, "", new String[] {}));
//		fixture.setActionCounter(1);
//		int c = 1;
//
//		fixture.setActionCounter(c);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.ArrayIndexOutOfBoundsException: 0
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
//	}

	@Test
	public void testShiftCouncil_1()
		throws Exception {
		SpeedAction fixture = new SpeedAction(game);
		fixture.setActionCounter(1);
		Councilor councilor = new Councilor(CouncilorColor.BLACK);
		int selection = 3;
		game.getMap().getCouncilorsPool().clear();
		game.getMap().getCouncilorsPool().add(councilor);

		fixture.shiftCouncil(selection, councilor);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(game.getMap().getBalcony(3).getCouncilors()[3].getCouncilorColor(),CouncilorColor.BLACK);
		assertEquals(game.getActualPlayer().getAvailableAssistants().size(),0);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SpeedActionTest.class);
	}
}