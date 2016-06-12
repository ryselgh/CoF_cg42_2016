package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;

public class PawnDTOTest {
	
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
//	public void testPawnDTO_1()
//		throws Exception {
//		PawnDTO result = new PawnDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetHexColor_1()
		throws Exception {
		PawnDTO fixture = new PawnDTO();
		fixture.setP(player);
		fixture.setPos(7);
		fixture.setHexColor("verde");

		String result = fixture.getHexColor();

		assertEquals("verde", result);
	}

	@Test
	public void testGetP_1()
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
	public void testGetPos_1()
		throws Exception {
		PawnDTO fixture = new PawnDTO();
		fixture.setP(player);
		fixture.setPos(7);
		fixture.setHexColor("verde");

		int result = fixture.getPos();

		assertEquals(7, result);
	}

//	@Test
//	public void testSetHexColor_1()
//		throws Exception {
//		PawnDTO fixture = new PawnDTO();
//		fixture.setP(new PlayerDTO());
//		fixture.setPos(1);
//		fixture.setHexColor("");
//		String hexColor = "";
//
//		fixture.setHexColor(hexColor);
//
//	}
//
//	@Test
//	public void testSetP_1()
//		throws Exception {
//		PawnDTO fixture = new PawnDTO();
//		fixture.setP(new PlayerDTO());
//		fixture.setPos(1);
//		fixture.setHexColor("");
//		PlayerDTO p = new PlayerDTO();
//
//		fixture.setP(p);
//
//	}
//
//	@Test
//	public void testSetPos_1()
//		throws Exception {
//		PawnDTO fixture = new PawnDTO();
//		fixture.setP(new PlayerDTO());
//		fixture.setPos(1);
//		fixture.setHexColor("");
//		int pos = 1;
//
//		fixture.setPos(pos);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PawnDTOTest.class);
	}
}