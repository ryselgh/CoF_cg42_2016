package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.SatisfyKingDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CityColor;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class SatisfyKingTest {
//	@Test
//	public void testSatisfyKing_1()
//		throws Exception {
//		PoliticsCard[] politics = new PoliticsCard[] {};
//		City destination = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//
//		SatisfyKing result = new SatisfyKing(politics, destination);
//
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_1()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
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
//	public void testExecute_2()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
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
//	public void testExecute_3()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
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
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
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
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
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
//	public void testSetGame_1()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		fixture.setGame(new Game(1, true, (Document) null));
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setGame(game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Test
//	public void testSetterFromDTO_1()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		fixture.setGame(new Game(1, true, (Document) null));
//		SatisfyKingDTO skDTO = new SatisfyKingDTO();
//		skDTO.setDestination(new CityDTO());
//		skDTO.setPolitics(new PoliticsCardDTO[] {});
//		Player player = new Player(1);
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(skDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Test
//	public void testSetterFromDTO_2()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		fixture.setGame(new Game(1, true, (Document) null));
//		SatisfyKingDTO skDTO = new SatisfyKingDTO();
//		skDTO.setDestination(new CityDTO());
//		skDTO.setPolitics(new PoliticsCardDTO[] {});
//		Player player = new Player(1);
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(skDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Before
//	public void setUp()
//		throws Exception {
//	}
//
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SatisfyKingTest.class);
	}
}