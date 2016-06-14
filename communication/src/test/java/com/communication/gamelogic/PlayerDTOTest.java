package com.communication.gamelogic;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.AssistantDTO;
import com.communication.board.BonusCardDTO;
import com.communication.board.EmporiumDTO;
import com.communication.board.PawnDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;

public class PlayerDTOTest {
	
	PlayerDTO player;
	ArrayList<PoliticsCardDTO> polCDTO;
	PoliticsCardDTO pcDTO1;
	PoliticsCardDTO pcDTO2;
	ArrayList<BonusCardDTO> bonCardDTO;
	BonusCardDTO bcDTO; 
	ArrayList<AssistantDTO> assDTO;
	AssistantDTO asDTO;
	PermitsCardDTO perDTO;
	ArrayList<PermitsCardDTO> permiDTO;
	
	
	
	
	@Before
	public void setUp()
		throws Exception {
		permiDTO = new ArrayList<PermitsCardDTO>();
		
		perDTO = new PermitsCardDTO();
		perDTO.setBonuses(null);
		perDTO.setCityLetter(null);
		perDTO.setFaceDown(false);
		permiDTO.add(perDTO);
		
		assDTO = new ArrayList<AssistantDTO>();
		asDTO = new AssistantDTO();
		assDTO.add(asDTO);
		
		bonCardDTO = new ArrayList<BonusCardDTO>();
		bcDTO = new BonusCardDTO();
		bonCardDTO.add(bcDTO);
		
		pcDTO1 = new PoliticsCardDTO();
		pcDTO1.setColor(CouncilorColor.BLACK);
		pcDTO2 = new PoliticsCardDTO();
		pcDTO2.setColor(CouncilorColor.BLUESKY);
		
		polCDTO = new ArrayList<PoliticsCardDTO>();
		polCDTO.add(pcDTO1);
		polCDTO.add(pcDTO2);
		player = new PlayerDTO();
	}

	@Test
	public void testPlayerDTO_1()
		throws Exception {
		PlayerDTO result = new PlayerDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetAvailableAssistants_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(assDTO);
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		ArrayList<AssistantDTO> result = fixture.getAvailableAssistants();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetAvailableEmporiums_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		ArrayList<EmporiumDTO> result = fixture.getAvailableEmporiums();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetBonusCards_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(bonCardDTO);
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		ArrayList<BonusCardDTO> result = fixture.getBonusCards();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetCoins_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(20);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		int result = fixture.getCoins();

		assertEquals(20, result);
	}

	@Test
	public void testGetHand_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(polCDTO);
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		ArrayList<PoliticsCardDTO> result = fixture.getHand();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetPawn_1()
		throws Exception {
		
		PawnDTO pawn = new PawnDTO();
		
		PlayerDTO fixture = new PlayerDTO();
		pawn.setHexColor("FF0000");
		pawn.setP(fixture);
		pawn.setPos(2);
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(pawn);
		fixture.setPlayerID("");

		PawnDTO result = fixture.getPawn();

		assertNotNull(result);
		assertEquals(2, result.getPos());
		assertEquals(fixture, result.getP());
		assertEquals("FF0000", result.getHexColor());
	}

	@Test
	public void testGetPermits_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(permiDTO);
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		ArrayList<PermitsCardDTO> result = fixture.getPermits();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetPlayerID_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("1");

		String result = fixture.getPlayerID();

		assertEquals("1", result);
	}

	@Test
	public void testGetScore_1()
		throws Exception {
		PlayerDTO fixture = new PlayerDTO();
		fixture.setBonusCards(new ArrayList());
		fixture.setHand(new ArrayList());
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setPermits(new ArrayList());
		fixture.setCoins(1);
		fixture.setScore(10);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.setPawn(new PawnDTO());
		fixture.setPlayerID("");

		int result = fixture.getScore();

		assertEquals(10, result);
	}

	
	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PlayerDTOTest.class);
	}
}