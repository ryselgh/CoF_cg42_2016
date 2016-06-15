package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;

public class EmporiumDTOTest {
	
	PlayerDTO player;
	
	@Before
	public void setUp()
		throws Exception {
		ArrayList<AssistantDTO> availableAssistants = new ArrayList();
		ArrayList<EmporiumDTO> availableEmporiums = new ArrayList();
		ArrayList<BonusCardDTO>bonusCards= new ArrayList();
		ArrayList<PoliticsCardDTO> hand = new ArrayList();
		ArrayList<PermitsCardDTO> permits = new ArrayList();
		PawnDTO pawn=new PawnDTO();
		pawn.setHexColor("verde");
		pawn.setPos(7);
		pawn.setP(player);
		player=new PlayerDTO();
		player.setAvailableAssistants(availableAssistants);
		player.setAvailableEmporiums(availableEmporiums);
		player.setBonusCards(bonusCards);
		player.setCoins(2);
		player.setHand(hand);
		player.setPawn(pawn);
		player.setPermits(permits);
		player.setPlayerID("5");
		player.setScore(20);
	}
	
//	@Test
//	public void testEmporiumDTO_1()
//		throws Exception {
//		EmporiumDTO result = new EmporiumDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetPlayer_1()
		throws Exception {
		EmporiumDTO fixture = new EmporiumDTO();
		fixture.setPlayer(player);

		PlayerDTO result = fixture.getPlayer();

		assertNotNull(result);
		
		assertEquals("5", result.getPlayerID());
		
	}

//	@Test
//	public void testSetPlayer_1()
//		throws Exception {
//		EmporiumDTO fixture = new EmporiumDTO();
//		fixture.setPlayer(new PlayerDTO());
//		PlayerDTO player = new PlayerDTO();
//
//		fixture.setPlayer(player);
//
//	}

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
		new org.junit.runner.JUnitCore().run(EmporiumDTOTest.class);
	}
}