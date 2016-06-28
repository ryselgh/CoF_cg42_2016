package com.communication.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	PlayerDTO fixture;
	ArrayList<PoliticsCardDTO> polCDTO;
	PoliticsCardDTO pcDTO1;
	PoliticsCardDTO pcDTO2;
	ArrayList<BonusCardDTO> bonCardDTO;
	BonusCardDTO bcDTO; 
	ArrayList<AssistantDTO> assDTO;
	AssistantDTO asDTO;
	PermitsCardDTO perDTO;
	ArrayList<PermitsCardDTO> permiDTO;
	PawnDTO pawn;
	EmporiumDTO[] empDTO;
	ArrayList<EmporiumDTO> emp;
	
	
	
	
	@Before
	public void setUp()
		throws Exception {
		
		
		//all sets of the player
		
		
		
		
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
		
		pawn = new PawnDTO();
		
		empDTO= new EmporiumDTO[10];
		for(int i=0; i<empDTO.length;i++){
			empDTO[i] = new EmporiumDTO();
			empDTO[i].setPlayer(fixture);
		}
		emp = new ArrayList<EmporiumDTO>(Arrays.asList(empDTO));
		
		
		
		fixture = new PlayerDTO();
		fixture.setBonusCards(bonCardDTO);
		fixture.setHand(polCDTO);
		fixture.setAvailableAssistants(assDTO);
		fixture.setPermits(permiDTO);
		fixture.setCoins(10);
		fixture.setScore(1);
		fixture.setAvailableEmporiums(emp);
		fixture.setPawn(pawn);
		fixture.setPlayerID("1");
		
		pawn.setHexColor("FF0000");
		pawn.setP(fixture);
		pawn.setPos(2);
		
		
		
		
		
	}

	@Test
	public void testPlayerDTO()
		throws Exception {
		PlayerDTO result = new PlayerDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together


	@Test
	public void testGetAvailableAssistants()
		throws Exception {
		

		ArrayList<AssistantDTO> result = fixture.getAvailableAssistants();

		assertNotNull(result);
		assertEquals(1, result.size());
	}
	
	

	@Test
	public void testGetAvailableEmporiums()
		throws Exception {
		

		ArrayList<EmporiumDTO> result = fixture.getAvailableEmporiums();

		assertNotNull(result);
		assertEquals(10, result.size());
	}

	@Test
	public void testGetBonusCards()
		throws Exception {
		

		ArrayList<BonusCardDTO> result = fixture.getBonusCards();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetCoins()
		throws Exception {
		

		int result = fixture.getCoins();

		assertEquals(10, result);
	}

	@Test
	public void testGetHand()
		throws Exception {
		

		ArrayList<PoliticsCardDTO> result = fixture.getHand();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetPawn()
		throws Exception {
		
		PawnDTO result = fixture.getPawn();

		assertNotNull(result);
		assertEquals(2, result.getPos());
		assertEquals(fixture, result.getP());
		assertEquals("FF0000", result.getHexColor());
	}

	@Test
	public void testGetPermits()
		throws Exception {
		

		ArrayList<PermitsCardDTO> result = fixture.getPermits();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetPlayerID()
		throws Exception {
		

		String result = fixture.getPlayerID();

		assertEquals("1", result);
	}

	@Test
	public void testGetScore()
		throws Exception {
		
		int result = fixture.getScore();

		assertEquals(1, result);
	}

	
	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PlayerDTOTest.class);
	}
}