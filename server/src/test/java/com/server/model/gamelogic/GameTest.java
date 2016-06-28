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
		//sets for the game
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, true, null, players);
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
	public void testGame()
		throws Exception {
		

		Game result = game;

		
		assertNotNull(result);
	}
	
	@Test
	public void testGame_5Players()
		throws Exception {
		String[] players2 = new String[5];
		players2[0] = "1";
		players2[1] = "2";
		players2[2] = "3";
		players2[3] = "4";
		players2[4] = "5";
		Game result = new Game(5, true, null, players2);
		

	
		assertNotNull(result);
	}

	@Test
	public void testGetActualPlayer()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);
		game.setActualPlayer(2);

		Player result = fixture.getActualPlayer();

		
		assertNotNull(result);
	}

	@Test
	public void testGetActualPlayerIndex()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		int result = fixture.getActualPlayerIndex();

		
		assertEquals(0, result);
	}

	

	

	@Test
	public void testGetCityFromName()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);
		String name = "Juvelar";

		City result = fixture.getCityFromName(name);

		
		assertNotNull(result);
	}
	
	@Test
	public void testGetCityFromNameReturnsNull(){
		Game fixture = game;
		fixture.setFinalTurn(false);
		String name = "Otranto";
		City result = fixture.getCityFromName(name);
		assertNull(result);
	}
	

	@Test
	public void testGetGraphMap()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		GraphMap result = fixture.getGraphMap();

		
		assertNotNull(result);
	}

	@Test
	public void testGetMap()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		Map result = fixture.getMap();

		
		assertNotNull(result);
	}

	@Test
	public void testGetMarket()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		Market result = fixture.getMarket();

		
		assertNotNull(result);
	}

	@Test
	public void testGetPlayers()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		ArrayList<Player> result = fixture.getPlayers();

		
		assertNotNull(result);
	}
	
	@Test
	public void testGetThatPlayer()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);
		int id = 1;

		Player result = fixture.getThatPlayer(id);

		
		assertNotNull(result);
	}

	@Test
	public void testIsFinalTurnReturnTrue()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(true);

		boolean result = fixture.isFinalTurn();

		
		assertTrue(result);
	}

	@Test
	public void testIsFinalTurnReturnFalse()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(false);

		boolean result = fixture.isFinalTurn();

		
		assertFalse(result);
	}



	@Test
	public void testToDto_1()
		throws Exception {
		Game fixture = game;
		fixture.setFinalTurn(true);

		GameDTO result = fixture.toDto();

		
		assertTrue(result.isFinalTurn());
	}

	

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GameTest.class);
	}
}