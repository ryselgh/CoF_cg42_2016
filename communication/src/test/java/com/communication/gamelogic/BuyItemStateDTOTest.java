package com.communication.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class BuyItemStateDTOTest {
	GameDTO gameDTO;
	SpeedActionDTO sADTO;
	MainActionDTO mADTO;
	
	@Before
	public void setUp()
		throws Exception {
		
		sADTO = new SpeedActionDTO();
		sADTO.setActionCounter(1);
		sADTO.setGame(gameDTO);
		mADTO = new MainActionDTO();
		mADTO.setActionCounter(1);
		mADTO.setGame(gameDTO);
		gameDTO = new GameDTO();
		gameDTO.setSpeedAction(sADTO);
		gameDTO.setMainAction(mADTO);
		
	}
	
	
	@Test
	public void testBuyItemStateDTO_1()
		throws Exception {
		BuyItemStateDTO result = new BuyItemStateDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		BuyItemStateDTO fixture = new BuyItemStateDTO();
		fixture.setGame(gameDTO);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMarket());
		assertEquals(false, result.isFinalTurn());
		assertEquals(mADTO, result.getMainAction());
		assertEquals(null, result.getPlayers());
		assertEquals(0, result.getPlayersQty());
		assertEquals(sADTO, result.getSpeedAction());
		assertEquals(null, result.getActualPlayer());
	}

	

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyItemStateDTOTest.class);
	}
}