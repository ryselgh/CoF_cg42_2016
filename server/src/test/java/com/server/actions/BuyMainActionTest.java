package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

import com.communication.values.BonusType;
import com.server.model.board.Assistant;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class BuyMainActionTest {
//	Player p;
//	@Before
//	public void setUp()
//		throws Exception {
//		p = new Player(1);
//	}
//
//	@Test
//	public void testBuyMainAction_1()
//		throws Exception {
//
//		BuyMainAction result = new BuyMainAction();
//
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_1()
//		throws Exception {
//		BuyMainAction fixture = new BuyMainAction();
//		fixture.setGame(new Game(2, true, null));
//		Assistant ass = new Assistant();
//		p.addAssistant(ass);
//		
//		fixture.isValid();
//
//		ActionReturn result = fixture.execute();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_2()
//		throws Exception {
//		BuyMainAction fixture = new BuyMainAction();
//		fixture.setGame(new Game(1, true,  null));
//
//		ActionReturn result = fixture.execute();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertNotNull(result);
//		assertEquals(result.getBonus()[0],BonusType.MAINACTION);
//	}
//
////	@Test
////	public void testExecute_3()
////		throws Exception {
////		BuyMainAction fixture = new BuyMainAction();
////		fixture.setGame(new Game(1, true, (Document) null));
////
////		ActionReturn result = fixture.execute();
////
////		// An unexpected exception was thrown in user code while executing this test:
////		//    java.lang.NullPointerException
////		//       at com.server.model.board.Map.<init>(Map.java:147)
////		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
////		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
////		assertNotNull(result);
////	}
////
////	@Test
////	public void testExecute_4()
////		throws Exception {
////		BuyMainAction fixture = new BuyMainAction();
////		fixture.setGame(new Game(1, true, (Document) null));
////
////		ActionReturn result = fixture.execute();
////
////		// An unexpected exception was thrown in user code while executing this test:
////		//    java.lang.NullPointerException
////		//       at com.server.model.board.Map.<init>(Map.java:147)
////		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
////		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
////		assertNotNull(result);
////	}
//
//	@Test
//	public void testIsValid_1()
//		throws Exception {
//		BuyMainAction fixture = new BuyMainAction();
//		fixture.setGame(new Game(2, true,  null));
//		Assistant ass = new Assistant();
//		p.addAssistant(ass);
//
//		boolean result = fixture.isValid();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertFalse(result);
//	}
//
//	@Test
//	public void testIsValid_2()
//		throws Exception {
//		BuyMainAction fixture = new BuyMainAction();
//		fixture.setGame(new Game(1, true,  null));
//		Assistant ass1 = new Assistant();
//		Assistant ass2 = new Assistant();
//		Assistant ass3 = new Assistant();
//		p.addAssistant(ass1);
//		p.addAssistant(ass2);
//		p.addAssistant(ass3);
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
////	@Test
////	public void testSetGame_1()
////		throws Exception {
////		BuyMainAction fixture = new BuyMainAction();
////		fixture.setGame(new Game(1, true, (Document) null));
////		Game game = new Game(1, true, (Document) null);
////
////		fixture.setGame(game);
////
////		// An unexpected exception was thrown in user code while executing this test:
////		//    java.lang.NullPointerException
////		//       at com.server.model.board.Map.<init>(Map.java:147)
////		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
////		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
////	}
//
//	
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyMainActionTest.class);
	}
}