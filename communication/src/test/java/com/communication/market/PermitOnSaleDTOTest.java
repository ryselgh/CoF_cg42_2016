package com.communication.market;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.board.AssistantDTO;
import com.communication.board.BonusCardDTO;
import com.communication.board.BonusDTO;
import com.communication.board.EmporiumDTO;
import com.communication.board.PawnDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;

public class PermitOnSaleDTOTest {
	
	PlayerDTO player;
	AssistantDTO assistant;
	ArrayList<AssistantDTO> availableAssistants = new ArrayList();
	ArrayList<EmporiumDTO> availableEmporiums = new ArrayList();
	ArrayList<BonusCardDTO>bonusCards= new ArrayList();
	ArrayList<PoliticsCardDTO> hand = new ArrayList();
	ArrayList<PermitsCardDTO> permits = new ArrayList();
	PawnDTO pawn;
	BonusDTO[] bonuses;
	String[] cityLetters;
	PermitsCardDTO permitsCardDTO;
	
	

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
		
		bonuses = new BonusDTO[2];
		bonuses[0]= new BonusDTO();
		bonuses[0].setQuantity(1);
		bonuses[0].setType(BonusType.ASSISTANT);
		bonuses[1]= new BonusDTO();
		bonuses[1].setQuantity(2);
		bonuses[1].setType(BonusType.CARD);
		
		cityLetters=new String[2];
		cityLetters[0]="l";
		cityLetters[1]="m";
		
		permitsCardDTO= new PermitsCardDTO();
		permitsCardDTO.setBonuses(bonuses);
		permitsCardDTO.setCityLetter(cityLetters);
	}
	
//	
//	@Test
//	public void testPermitOnSaleDTO_1()
//		throws Exception {
//		PermitOnSaleDTO result = new PermitOnSaleDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetPermit_1()
		throws Exception {
		PermitOnSaleDTO fixture = new PermitOnSaleDTO();
		fixture.setPermit(permitsCardDTO);
		fixture.setSeller(player);
		fixture.setPrice(1);

		PermitsCardDTO result = fixture.getPermit();

		assertNotNull(result);
		assertEquals(false, result.isFaceDown());
		assertEquals(cityLetters, result.getCityLetter());
		assertEquals(bonuses, result.getBonuses());
	}

	@Test
	public void testGetPrice_1()
		throws Exception {
		PermitOnSaleDTO fixture = new PermitOnSaleDTO();
		fixture.setPermit(permitsCardDTO);
		fixture.setSeller(player);
		fixture.setPrice(1);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testGetSeller_1()
		throws Exception {
		PermitOnSaleDTO fixture = new PermitOnSaleDTO();
		fixture.setPermit(permitsCardDTO);
		fixture.setSeller(player);
		fixture.setPrice(1);

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
//	public void testSetPermit_1()
//		throws Exception {
//		PermitOnSaleDTO fixture = new PermitOnSaleDTO();
//		fixture.setPermit(new PermitsCardDTO());
//		fixture.setSeller(new PlayerDTO());
//		fixture.setPrice(1);
//		PermitsCardDTO permit = new PermitsCardDTO();
//
//		fixture.setPermit(permit);
//
//	}
//
//	@Test
//	public void testSetPrice_1()
//		throws Exception {
//		PermitOnSaleDTO fixture = new PermitOnSaleDTO();
//		fixture.setPermit(new PermitsCardDTO());
//		fixture.setSeller(new PlayerDTO());
//		fixture.setPrice(1);
//		int price = 1;
//
//		fixture.setPrice(price);
//
//	}
//
//	@Test
//	public void testSetSeller_1()
//		throws Exception {
//		PermitOnSaleDTO fixture = new PermitOnSaleDTO();
//		fixture.setPermit(new PermitsCardDTO());
//		fixture.setSeller(new PlayerDTO());
//		fixture.setPrice(1);
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
		new org.junit.runner.JUnitCore().run(PermitOnSaleDTOTest.class);
	}
}