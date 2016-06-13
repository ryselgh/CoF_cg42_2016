package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;
import com.server.model.board.Councilor;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ShiftCouncilSpeedTest {
	
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
	public void testShiftCouncilSpeed_1()
		throws Exception {
		int balconyIndex = 1;
		Councilor councilor = new Councilor(CouncilorColor.BLACK);

		ShiftCouncilSpeed result = new ShiftCouncilSpeed(balconyIndex, councilor);

		assertNotNull(result);
	}

	@Test
	public void testExecute_1()
		throws Exception {
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
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
	public void testExecute_2()
		throws Exception {
		Councilor[] balcony = game.getMap().getBalcony(1).getCouncilors();
		ShiftCouncilMain fixture = new ShiftCouncilMain(1, new Councilor(CouncilorColor.BLACK));
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotEquals(balcony,game.getMap().getBalcony(1).getCouncilors());
	}

	@Test
	public void testExecute_3()
		throws Exception {
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertTrue(game.getActualPlayer().getAvailableAssistants().isEmpty());
	}

//	@Test
//	public void testIsValid_1()
//		throws Exception {
//		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
//		fixture.setGame(new Game(1, true, (Document) null));
//
//		boolean result = fixture.isValid();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertTrue(result);
//	}
//
//	@Test
//	public void testIsValid_2()
//		throws Exception {
//		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
//		fixture.setGame(new Game(1, true, (Document) null));
//
//		boolean result = fixture.isValid();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertTrue(result);
//	}
//
//	@Test
//	public void testIsValid_3()
//		throws Exception {
//		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
//		fixture.setGame(new Game(1, true, (Document) null));
//
//		boolean result = fixture.isValid();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertTrue(result);
//	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ShiftCouncilSpeedTest.class);
	}
}