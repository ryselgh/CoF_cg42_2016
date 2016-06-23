package com.communication.gamelogic;

import org.junit.*;

import com.communication.board.PawnDTO;

import static org.junit.Assert.*;

public class ActionStateDTOTest {
	
		GameDTO gameDTO;
		PlayerDTO actualPlayer;
		PawnDTO pawn;
		
	
	@Before
	public void setUp()
		throws Exception {
//		pawn = new PawnDTO();
//		pawn.setHexColor("FF0000");
//		pawn.setP(actualPlayer);
//		pawn.setPos(0);
//		actualPlayer = new PlayerDTO();
//		actualPlayer.setAvailableAssistants(null);
//		actualPlayer.setAvailableEmporiums(null);
//		actualPlayer.setBonusCards(null);
//		actualPlayer.setHand(null);
//		actualPlayer.setPawn(pawn);
//		actualPlayer.setPermits(null);
//		actualPlayer.setPlayerID("1");
//		actualPlayer.setScore(10);
		
		gameDTO = new GameDTO();
//		gameDTO.setActualPlayer(actualPlayer);
//		gameDTO.setFinalTurn(false);
//		gameDTO.setMainAction(null);
//		gameDTO.setMarket(null);
//		gameDTO.setSpeedAction(null);
//		gameDTO.setPlayers(players);
		gameDTO.setPlayersQty(3);
	}
	
	
	@Test
	public void testActionStateDTO_1()
		throws Exception {
		ActionStateDTO result = new ActionStateDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		ActionStateDTO fixture = new ActionStateDTO();
		fixture.setSpeedCounter(1);
		fixture.setMainCounter(1);
		fixture.setGame(gameDTO);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMarket());
		assertEquals(false, result.isFinalTurn());
		assertEquals(null, result.getMainAction());
		assertEquals(null, result.getPlayers());
		assertEquals(3, result.getPlayersQty());
		assertEquals(null, result.getSpeedAction());
		assertEquals(null, result.getActualPlayer());
	}

	@Test
	public void testGetMainCounter_1()
		throws Exception {
		ActionStateDTO fixture = new ActionStateDTO();
		fixture.setSpeedCounter(1);
		fixture.setMainCounter(1);
		fixture.setGame(new GameDTO());

		int result = fixture.getMainCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetSpeedCounter_1()
		throws Exception {
		ActionStateDTO fixture = new ActionStateDTO();
		fixture.setSpeedCounter(1);
		fixture.setMainCounter(1);
		fixture.setGame(new GameDTO());

		int result = fixture.getSpeedCounter();

		assertEquals(1, result);
	}



	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActionStateDTOTest.class);
	}
}