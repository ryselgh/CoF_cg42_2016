package com.server.model.gamelogic;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.gamelogic.GameDTO;
import com.server.model.board.City;
import com.server.model.board.Map;
import com.server.model.market.Market;

public class GameTest {
	
	Game game;
	String[] players;
	
	@Before
	public void setUp()
		throws Exception {
		
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, "default1", null, players);
	}
	
//	@Test
//	public void checkWinTest(){
//		
//		
//		game.getMap().getCity()[0].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[1].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[2].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[3].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[4].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[5].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[6].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[7].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[8].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[9].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		
//		boolean res = game.checkWin();
//		assertTrue(res);
//		
//	}
//	@Test
//	public void checkWinTest2(){
//		
//		
//		game.getMap().getCity()[0].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[1].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[2].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[3].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[4].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[5].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[6].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[7].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		game.getMap().getCity()[8].setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
//		game.getActualPlayer().getAvailableEmporiums().remove(0);
//		
//		
//		boolean res = game.checkWin();
//		assertFalse(res);
//		
//	}
	@Test
	public void testGame_1()
		throws Exception {
		

		Game result = game;

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}
	
	@Test
	public void testGame_()
		throws Exception {
		String[] players2 = new String[5];
		players2[0] = "1";
		players2[1] = "2";
		players2[2] = "3";
		players2[3] = "4";
		players2[4] = "5";
		Game result = new Game(5, "default1", null, players2);
		

		

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testGetActualPlayer_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);
		game.setActualPlayer(2);

		Player result = fixture.getActualPlayer();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testGetActualPlayerIndex_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		int result = fixture.getActualPlayerIndex();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(0, result);
	}

	

	

	@Test
	public void testGetCityFromName_2()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);
		String name = "Juvelar";

		City result = fixture.getCityFromName(name);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	

	@Test
	public void testGetGraphMap_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		GraphMap result = fixture.getGraphMap();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testGetMap_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		Map result = fixture.getMap();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testGetMarket_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		Market result = fixture.getMarket();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testGetPlayers_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		ArrayList<Player> result = fixture.getPlayers();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}
	
	@Test
	public void testGetThatPlayer_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);
		int id = 1;

		Player result = fixture.getThatPlayer(id);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	@Test
	public void testIsFinalTurn_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(true);

		boolean result = fixture.isFinalTurn();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result);
	}

	@Test
	public void testIsFinalTurn_2()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		boolean result = fixture.isFinalTurn();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertFalse(result);
	}

//	@Test
//	public void testSetActualPlayer_1()
//		throws Exception {
//		Game fixture = new Game(1, true, "", new String[] {});
//		fixture.setFinalTurn(true);
//		int index = 1;
//
//		fixture.setActualPlayer(index);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.ArrayIndexOutOfBoundsException: 0
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
//	}
//
//	@Test
//	public void testSetFinalTurn_1()
//		throws Exception {
//		Game fixture = new Game(1, true, "", new String[] {});
//		fixture.setFinalTurn(true);
//		boolean finalTurn = true;
//
//		fixture.setFinalTurn(finalTurn);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.ArrayIndexOutOfBoundsException: 0
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
//	}

	@Test
	public void testToDto_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(true);

		GameDTO result = fixture.toDto();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertTrue(result instanceof GameDTO);
	}

	@Test
	public void testToDto_2()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(true);

		GameDTO result = fixture.toDto();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertNotNull(result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GameTest.class);
	}
}