package com.communication.gamelogic;

import org.junit.*;

import com.communication.board.PawnDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionStateDTOTest {
	
		GameDTO gameDTO;
		PlayerDTO actualPlayer;
		PawnDTO pawn;
		PlayerDTO[] players;
		ArrayList<PlayerDTO> player;
		
		
	
	@Before
	public void setUp()
		throws Exception {
		
		//the settings of the game are non corrct. but in this case it doesn't mind

		players = new PlayerDTO[3];
		player = new ArrayList<PlayerDTO>(Arrays.asList(players));
		gameDTO = new GameDTO();
		gameDTO.setActualPlayer(players[0]);
		gameDTO.setDefaultMap(true);
		gameDTO.setFinalTurn(false);
		gameDTO.setMainAction(null);
		gameDTO.setMarket(null);
		gameDTO.setSpeedAction(null);
		gameDTO.setPlayers(player);
		gameDTO.setPlayersQty(3);
	}
	
	
	@Test
	public void testActionStateDTO_1()
		throws Exception {
		ActionStateDTO result = new ActionStateDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetGame()
		throws Exception {
		ActionStateDTO fixture = new ActionStateDTO();
		fixture.setSpeedCounter(1);
		fixture.setMainCounter(1);
		fixture.setGame(gameDTO);

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		
	}

	@Test
	public void testGetMainCounter()
		throws Exception {
		ActionStateDTO fixture = new ActionStateDTO();
		fixture.setSpeedCounter(1);
		fixture.setMainCounter(1);
		fixture.setGame(new GameDTO());

		int result = fixture.getMainCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetSpeedCounter()
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