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

public class OnSaleDTOTest {
	
	PlayerDTO player;
	AssistantDTO assistant;
	ArrayList<AssistantDTO> availableAssistants = new ArrayList<AssistantDTO>();
	ArrayList<EmporiumDTO> availableEmporiums = new ArrayList<EmporiumDTO>();
	ArrayList<BonusCardDTO>bonusCards= new ArrayList<BonusCardDTO>();
	ArrayList<PoliticsCardDTO> hand = new ArrayList<PoliticsCardDTO>();
	ArrayList<PermitsCardDTO> permits = new ArrayList<PermitsCardDTO>();
	PawnDTO pawn;
	Object obj;
	
	
	@Before
	public void setUp()
		throws Exception {
		

		pawn=new PawnDTO();
		pawn.setHexColor("FF0000");
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
		obj=new Object();
		
		
		
	}
	
	@Test
	public void testOnSaleDTO()
		throws Exception {
		OnSaleDTO result = new OnSaleDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together


	@Test
	public void testGetObj()
		throws Exception {
		OnSaleDTO fixture = new OnSaleDTO();
		fixture.setPrice(1);
		fixture.setObj(obj);
		fixture.setSeller(player);
		fixture.setUID("1");

		Object result = fixture.getObj();

		assertNotNull(result);
	}

	@Test
	public void testGetPrice()
		throws Exception {
		OnSaleDTO fixture = new OnSaleDTO();
		fixture.setPrice(1);
		fixture.setObj(obj);
		fixture.setSeller(player);
		fixture.setUID("1");

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testGetSeller()
		throws Exception {
		OnSaleDTO fixture = new OnSaleDTO();
		fixture.setPrice(1);
		fixture.setObj(obj);
		fixture.setSeller(player);
		fixture.setUID("1");

		PlayerDTO result = fixture.getSeller();

		assertNotNull(result);
		assertEquals(permits, result.getPermits());
		assertEquals(20, result.getScore());
		assertEquals(pawn, result.getPawn());
		assertEquals(2, result.getCoins());
		assertEquals(availableAssistants, result.getAvailableAssistants());
		assertEquals("5", result.getPlayerID());
		assertEquals(availableEmporiums, result.getAvailableEmporiums());
		assertEquals(hand, result.getHand());
		assertEquals(bonusCards, result.getBonusCards());
	}
	
	@Test 
	public void testGetUID(){
		OnSaleDTO fixture = new OnSaleDTO();
		fixture.setObj(obj);
		fixture.setPrice(1);
		fixture.setSeller(player);
		fixture.setUID("1");
		
		String result = fixture.getUID();
		assertNotNull(result);
		assertEquals(result,"1");
	}


	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(OnSaleDTOTest.class);
	}
}