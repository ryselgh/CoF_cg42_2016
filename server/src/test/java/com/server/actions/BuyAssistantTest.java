package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class BuyAssistantTest {
	
//	Player p;
//	
//	@Before
//	public void setUp()
//		throws Exception {
//		p = new Player(1);
//	}
//	@Test
//	public void testBuyAssistant_1()
//		throws Exception {
//
//		BuyAssistant result = new BuyAssistant();
//
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_1()
//		throws Exception {
//		BuyAssistant fixture = new BuyAssistant();
//		fixture.setGame(new Game(1, true, null));
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
//		BuyAssistant fixture = new BuyAssistant();
//		fixture.setGame(new Game(1, true, null));
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
//	public void testExecute_3()
//		throws Exception {
//		BuyAssistant fixture = new BuyAssistant();
//		fixture.setGame(new Game(1, true, (Document) null));
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
//	public void testIsValid_1()
//		throws Exception {
//		BuyAssistant fixture = new BuyAssistant();
//		fixture.setGame(new Game(1, true, null));
//		p.setCoins(5);
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
//		BuyAssistant fixture = new BuyAssistant();
//		fixture.setGame(new Game(1, true, null));
//		p.setCoins(2);
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
////	@Test
////	public void testSetGame_1()
////		throws Exception {
////		BuyAssistant fixture = new BuyAssistant();
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
////
////	
////
////	@After
////	public void tearDown()
////		throws Exception {
////	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyAssistantTest.class);
	}
}