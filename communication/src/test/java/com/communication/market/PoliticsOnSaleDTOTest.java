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
import com.communication.values.CouncilorColor;

public class PoliticsOnSaleDTOTest {
	
	PlayerDTO player;
	AssistantDTO assistant;
	ArrayList<AssistantDTO> availableAssistants = new ArrayList();
	ArrayList<EmporiumDTO> availableEmporiums = new ArrayList();
	ArrayList<BonusCardDTO>bonusCards= new ArrayList();
	ArrayList<PoliticsCardDTO> hand = new ArrayList();
	ArrayList<PermitsCardDTO> permits = new ArrayList();
	PawnDTO pawn;
	PoliticsCardDTO politicsCard;
	
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
		player.setPlayerID(5);
		player.setScore(20);
		
		politicsCard=new PoliticsCardDTO();
		politicsCard.setColor(CouncilorColor.BLACK);
	}
	
	
//	@Test
//	public void testPoliticsOnSaleDTO_1()
//		throws Exception {
//		PoliticsOnSaleDTO result = new PoliticsOnSaleDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetPoliticsCard_1()
		throws Exception {
		PoliticsOnSaleDTO fixture = new PoliticsOnSaleDTO();
		fixture.setPrice(1);
		fixture.setPoliticsCard(politicsCard);
		fixture.setSeller(player);

		PoliticsCardDTO result = fixture.getPoliticsCard();

		assertNotNull(result);
		assertEquals(CouncilorColor.BLACK, result.getColor());
	}

	@Test
	public void testGetPrice_1()
		throws Exception {
		PoliticsOnSaleDTO fixture = new PoliticsOnSaleDTO();
		fixture.setPrice(1);
		fixture.setPoliticsCard(politicsCard);
		fixture.setSeller(player);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testGetSeller_1()
		throws Exception {
		PoliticsOnSaleDTO fixture = new PoliticsOnSaleDTO();
		fixture.setPrice(1);
		fixture.setPoliticsCard(politicsCard);
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
//	public void testSetPoliticsCard_1()
//		throws Exception {
//		PoliticsOnSaleDTO fixture = new PoliticsOnSaleDTO();
//		fixture.setPrice(1);
//		fixture.setPoliticsCard(new PoliticsCardDTO());
//		fixture.setSeller(new PlayerDTO());
//		PoliticsCardDTO politicsCard = new PoliticsCardDTO();
//
//		fixture.setPoliticsCard(politicsCard);
//
//	}
//
//	@Test
//	public void testSetPrice_1()
//		throws Exception {
//		PoliticsOnSaleDTO fixture = new PoliticsOnSaleDTO();
//		fixture.setPrice(1);
//		fixture.setPoliticsCard(new PoliticsCardDTO());
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
//		PoliticsOnSaleDTO fixture = new PoliticsOnSaleDTO();
//		fixture.setPrice(1);
//		fixture.setPoliticsCard(new PoliticsCardDTO());
//		fixture.setSeller(new PlayerDTO());
//		PlayerDTO seller = new PlayerDTO();
//
//		fixture.setSeller(seller);
//
//	}

	
	
	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsOnSaleDTOTest.class);
	}
}