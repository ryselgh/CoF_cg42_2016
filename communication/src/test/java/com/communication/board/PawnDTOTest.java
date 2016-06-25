package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PermitsDeckDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.decks.PoliticsDeckDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.communication.values.CouncilorColor;

public class PawnDTOTest {
	
	PlayerDTO player;
	
	@Before
	public void setUp()
		throws Exception {
		
		//all you need to test methods. most features of the player aren't instqntiated completly
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
	
	
	@Test
	public void testPawnDTO()
		throws Exception {
		PawnDTO result = new PawnDTO();
		assertNotNull(result);
	}
	// getters and setters are tested together

	@Test
	public void testGetHexColor()
		throws Exception {
		PawnDTO fixture = new PawnDTO();
		fixture.setP(player);
		fixture.setPos(7);
		fixture.setHexColor("verde");

		String result = fixture.getHexColor();

		assertEquals("verde", result);
	}

	@Test
	public void testGetPlayer()
		throws Exception {
		PawnDTO fixture = new PawnDTO();
		fixture.setP(player);
		fixture.setPos(7);
		fixture.setHexColor("verde");

		PlayerDTO result = fixture.getP();

		assertNotNull(result);
		assertEquals("5", result.getPlayerID());
		
	}

	@Test
	public void testGetPosition()
		throws Exception {
		PawnDTO fixture = new PawnDTO();
		fixture.setP(player);
		fixture.setPos(7);
		fixture.setHexColor("verde");

		int result = fixture.getPos();

		assertEquals(7, result);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PawnDTOTest.class);
	}
}