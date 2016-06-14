package com.communication.gamelogic;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.MapDTO;
import com.communication.market.MarketDTO;

public class GameDTOTest {
	@Test
	public void testGameDTO_1()
		throws Exception {
		GameDTO result = new GameDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetActualPlayer_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		PlayerDTO result = fixture.getActualPlayer();

		assertNotNull(result);
		assertEquals(null, result.getPlayerID());
		assertEquals(null, result.getPermits());
		assertEquals(0, result.getScore());
		assertEquals(null, result.getHand());
		assertEquals(0, result.getCoins());
		assertEquals(null, result.getBonusCards());
		assertEquals(null, result.getAvailableAssistants());
		assertEquals(null, result.getAvailableEmporiums());
		assertEquals(null, result.getPawn());
	}

	@Test
	public void testGetAssistoffset_1()
		throws Exception {

		int result = GameDTO.getAssistoffset();

		assertEquals(1, result);
	}

	@Test
	public void testGetCoinoffset_1()
		throws Exception {

		int result = GameDTO.getCoinoffset();

		assertEquals(10, result);
	}

	@Test
	public void testGetInitialcards_1()
		throws Exception {

		int result = GameDTO.getInitialcards();

		assertEquals(6, result);
	}

	@Test
	public void testGetMainAction_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		MainActionDTO result = fixture.getMainAction();

		assertNotNull(result);
		assertEquals(0, result.getActionCounter());
		assertEquals(null, result.getGame());
	}

	@Test
	public void testGetMap_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		MapDTO result = fixture.getMap();

		assertNotNull(result);
		assertEquals(null, result.getNobilityTrack());
		assertEquals(null, result.getCity());
		assertEquals(null, result.getPoliticsDeck());
		assertEquals(null, result.getKingBonus());
		assertEquals(null, result.getKing());
		assertEquals(null, result.getAssistants());
		assertEquals(null, result.getCouncilors());
		assertEquals(null, result.getPawn());
	}

	@Test
	public void testGetMarket_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		MarketDTO result = fixture.getMarket();

		assertNotNull(result);
		assertEquals(null, result.getObjectsOnSale());
	}

	@Test
	public void testGetMaxplayers_1()
		throws Exception {

		int result = GameDTO.getMaxplayers();

		assertEquals(8, result);
	}

	@Test
	public void testGetPlayers_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		ArrayList<PlayerDTO> result = fixture.getPlayers();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetPlayersQty_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		int result = fixture.getPlayersQty();

		assertEquals(1, result);
	}

	@Test
	public void testGetSpeedAction_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		SpeedActionDTO result = fixture.getSpeedAction();

		assertNotNull(result);
		assertEquals(0, result.getActionCounter());
		assertEquals(null, result.getGame());
	}

	@Test
	public void testIsDefaultMap_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		boolean result = fixture.isDefaultMap();

		assertEquals(true, result);
	}

	@Test
	public void testIsDefaultMap_2()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(false);

		boolean result = fixture.isDefaultMap();

		assertEquals(false, result);
	}

	@Test
	public void testIsFinalTurn_1()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(true);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		boolean result = fixture.isFinalTurn();

		assertEquals(true, result);
	}

	@Test
	public void testIsFinalTurn_2()
		throws Exception {
		GameDTO fixture = new GameDTO();
		fixture.setActualPlayer(new PlayerDTO());
		fixture.setMainAction(new MainActionDTO());
		fixture.setMarket(new MarketDTO());
		fixture.setPlayersQty(1);
		fixture.setFinalTurn(false);
		fixture.setPlayers(new ArrayList());
		fixture.setMap(new MapDTO());
		fixture.setSpeedAction(new SpeedActionDTO());
		fixture.setDefaultMap(true);

		boolean result = fixture.isFinalTurn();

		assertEquals(false, result);
	}

	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GameDTOTest.class);
	}
}