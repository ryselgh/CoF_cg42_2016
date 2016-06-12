package com.communication.market;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.board.AssistantDTO;
import com.communication.board.BonusCardDTO;
import com.communication.board.EmporiumDTO;
import com.communication.board.PawnDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;

public class AssistantOnSaleDTOTest {
	
	PlayerDTO player;
	AssistantDTO assistant;
	ArrayList<AssistantDTO> availableAssistants = new ArrayList();
	ArrayList<EmporiumDTO> availableEmporiums = new ArrayList();
	ArrayList<BonusCardDTO>bonusCards= new ArrayList();
	ArrayList<PoliticsCardDTO> hand = new ArrayList();
	ArrayList<PermitsCardDTO> permits = new ArrayList();
	PawnDTO pawn;
	@Before
	public void setUp()
		throws Exception {
		
		
		pawn=new PawnDTO();
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
		
		assistant= new AssistantDTO();
		
		
		
		
		
	}
	
	@Test
	public void testGetAssistant_1()
		throws Exception {
		AssistantOnSaleDTO fixture = new AssistantOnSaleDTO();
		fixture.setAssistant(assistant);
		fixture.setPrice(1);
		fixture.setSeller(player);

		AssistantDTO result = fixture.getAssistant();

		assertNotNull(result);
	}

	@Test
	public void testGetPrice_1()
		throws Exception {
		AssistantOnSaleDTO fixture = new AssistantOnSaleDTO();
		fixture.setAssistant(assistant);
		fixture.setPrice(1);
		fixture.setSeller(player);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testGetSeller_1()
		throws Exception {
		AssistantOnSaleDTO fixture = new AssistantOnSaleDTO();
		fixture.setAssistant(assistant);
		fixture.setPrice(1);
		fixture.setSeller(player);

		PlayerDTO result = fixture.getSeller();

		assertNotNull(result);
		assertEquals(permits, result.getPermits());
		assertEquals(20, result.getScore());
		assertEquals(pawn, result.getPawn());
		assertEquals(2, result.getCoins());
		assertEquals(availableAssistants, result.getAvailableAssistants());
		assertEquals(5, result.getPlayerID());
		assertEquals(availableEmporiums, result.getAvailableEmporiums());
		assertEquals(hand, result.getHand());
		assertEquals(bonusCards, result.getBonusCards());
	}

//	@Test
//	public void testSetAssistant_1()
//		throws Exception {
//		AssistantOnSaleDTO fixture = new AssistantOnSaleDTO();
//		fixture.setAssistant(new AssistantDTO());
//		fixture.setPrice(1);
//		fixture.setSeller(new PlayerDTO());
//		AssistantDTO assistant = new AssistantDTO();
//
//		fixture.setAssistant(assistant);
//
//	}
//
//	@Test
//	public void testSetPrice_1()
//		throws Exception {
//		AssistantOnSaleDTO fixture = new AssistantOnSaleDTO();
//		fixture.setAssistant(new AssistantDTO());
//		fixture.setPrice(1);
//		fixture.setSeller(new PlayerDTO());
//		int price = 1;
//
//		fixture.setPrice(price);
//
//	}
//
//	@Test
//	public void testSetSeller_1()
//		throws Exception {
//		AssistantOnSaleDTO fixture = new AssistantOnSaleDTO();
//		fixture.setAssistant(new AssistantDTO());
//		fixture.setPrice(1);
//		fixture.setSeller(new PlayerDTO());
//		PlayerDTO seller = new PlayerDTO();
//
//		fixture.setSeller(seller);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(AssistantOnSaleDTOTest.class);
	}
}