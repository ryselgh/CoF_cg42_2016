package com.server.model.gamelogic;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CouncilorColor;
import com.server.model.board.Assistant;
import com.server.model.board.Bonus;
import com.server.model.board.BonusCard;
import com.server.model.board.Emporium;
import com.server.model.board.Pawn;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;

public class PlayerTest {
	
	Game game;
	String[] players;
	
	@Before
	public void setUp()
		throws Exception {
		
		//sets for the game
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, "Default map1.xml", null, players);
		
		
	}
	@Test
	public void testPlayer()
		throws Exception {
		

		Player result = game.getActualPlayer();

		assertNotNull(result);
		assertEquals("1", result.getID());
		assertEquals(0, result.getScore());
		assertEquals(null, result.getPawn());
		assertEquals(10, result.getCoins());
		assertEquals(false, result.hasUncoveredPermits());
	}

	@Test
	public void testAddAssistant()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		Assistant ass = new Assistant();

		fixture.addAssistant(ass);
		assertEquals(fixture.getAvailableAssistants().size(),2);

	}

	@Test
	public void testAddAssistantMoreThanOne()
		throws Exception {
		Player fixture = game.getActualPlayer();
		ArrayList<Assistant> assistants = new ArrayList();
		Assistant ass1 = new Assistant();
		Assistant ass2 = new Assistant();
		Assistant ass3 = new Assistant();
		assistants.add(ass1);
		assistants.add(ass2);
		assistants.add(ass3);
		
		fixture.addAssistant(assistants);
		
		assertEquals(fixture.getAvailableAssistants().size(),4);

	}

	@Test
	public void testAddBonusCards()
		throws Exception {
		Player fixture = game.getActualPlayer();
		Bonus b= new Bonus(BonusType.POINT,5);
		ArrayList<BonusCard> bonusCards = new ArrayList();
		BonusCard bc1 = new BonusCard(b);
		BonusCard bc2 = new BonusCard(b);
		BonusCard bc3 = new BonusCard(b);
		bonusCards.add(bc1);
		bonusCards.add(bc2);	
		bonusCards.add(bc3);
		fixture.addBonusCards(bonusCards);
		
		assertEquals(fixture.getBonusCards().size(),3);

	}

	@Test
	public void testAddCoins()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		int coins = 1;

		fixture.addCoins(coins);
		assertEquals(fixture.getCoins(),11);

	}

	@Test
	public void testAddPermits()
		throws Exception {
		Player fixture = game.getActualPlayer();
		Bonus[] b= new Bonus[1];
		b[0] = new Bonus(BonusType.POINT,5);
		String[] letters = new String[2];
		letters[0] = "l";
		letters[1] = "m";
		
		
		
		PermitsCard pec = new PermitsCard(b,letters);

		fixture.addPermits(pec);
		assertEquals(fixture.getPermits().size(),1);

	}

	@Test
	public void testAddPolitics()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		PoliticsCard poc = new PoliticsCard(CouncilorColor.BLACK);

		fixture.addPolitics(poc);
		
		assertEquals(fixture.getHand().size(),7);

	}

	@Test
	public void testCompareToDTOs()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		ArrayList<PlayerDTO> plsDTO = new ArrayList();
		PlayerDTO pDTO1= new PlayerDTO();
		pDTO1.setPlayerID("1");
		PlayerDTO pDTO2= new PlayerDTO();
		pDTO2.setPlayerID("2");
		PlayerDTO pDTO3= new PlayerDTO();
		pDTO3.setPlayerID("3");
		plsDTO.add(pDTO1);
		plsDTO.add(pDTO2);
		plsDTO.add(pDTO3);

		PlayerDTO result = fixture.compareToDTOs(plsDTO);

		assertEquals(pDTO1, result);
	}

	@Test
	public void testCompareToDTOsReturnsNull()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		ArrayList<PlayerDTO> plsDTO = new ArrayList();

		PlayerDTO result = fixture.compareToDTOs(plsDTO);

		assertEquals(null, result);
	}

	

	@Test
	public void testGetAvailableAssistants()
		throws Exception {
		Assistant e1 = new Assistant();
		Assistant e2 = new Assistant();
		Player fixture = game.getActualPlayer();
		ArrayList<Assistant> ass = new ArrayList<Assistant>();
		ass.add(e1);
		ass.add(e2);
		game.getActualPlayer().setAvailableAssistants(ass);
		ArrayList<Assistant> result = fixture.getAvailableAssistants();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetAvailableEmporiums()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<Emporium> result = fixture.getAvailableEmporiums();

		assertNotNull(result);
		assertEquals(10, result.size());
	}

	@Test
	public void testGetBonusCards()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<BonusCard> result = fixture.getBonusCards();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetCoins()
		throws Exception {
		Player fixture = game.getActualPlayer();
		fixture.setCoins(1);
		

		int result = fixture.getCoins();

		assertEquals(1, result);
	}

	

	@Test
	public void testGetHand()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<PoliticsCard> result = fixture.getHand();

		assertNotNull(result);
		assertEquals(6, result.size());
	}

	@Test
	public void testGetID()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		String result = fixture.getID();

		assertEquals("1", result);
	}

	@Test
	public void testGetPawn()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		Pawn result = fixture.getPawn();

		assertEquals(null, result);
	}

	@Test
	public void testGetPermits()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<PermitsCard> result = fixture.getPermits();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetScore()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		fixture.setScore(11);
		

		int result = fixture.getScore();

		assertEquals(11, result);
	}

	@Test
	public void testHasUncoveredPermitsReturnTrue()
		throws Exception {
		Player fixture = game.getActualPlayer();
		Bonus[] b= new Bonus[1];
		b[0] = new Bonus(BonusType.POINT,5);
		String[] letters = new String[2];
		letters[0] = "l";
		letters[1] = "m";
		
		
		
		PermitsCard pec = new PermitsCard(b,letters);

		fixture.addPermits(pec);
		pec.setFaceDown(false);

		boolean result = fixture.hasUncoveredPermits();
		

		assertEquals(true, result);
	}
	
	@Test
	public void testHasUncoveredPermitsReturnFalse()
		throws Exception {
		Player fixture = game.getActualPlayer();
		Bonus[] b= new Bonus[1];
		b[0] = new Bonus(BonusType.POINT,5);
		String[] letters = new String[2];
		letters[0] = "l";
		letters[1] = "m";
		
		
		
		PermitsCard pec = new PermitsCard(b,letters);

		fixture.addPermits(pec);
		pec.setFaceDown(true);

		boolean result = fixture.hasUncoveredPermits();
		

		assertEquals(false, result);
	}

	@Test
	public void testHasUncoveredPermitsReturnFalseBeacuseHeHasntPermits()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		boolean result = fixture.hasUncoveredPermits();

		assertEquals(false, result);
	}

	

	@Test
	public void testRemoveAssistant()
		throws Exception {
		
		Player fixture = game.getActualPlayer();
		

		fixture.removeAssistant(fixture.getAvailableAssistants().get(0));
		
		assertEquals(fixture.getAvailableAssistants().size(),0);

	}

	



	@Test
	public void testRemovePolitics()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		

		fixture.removePolitics(fixture.getHand().get(0));

		
		
		assertEquals(fixture.getHand().size(),5);
	}


	@Test
	public void testToDTO()
		throws Exception {
		Player fixture = game.getActualPlayer();
		PlayerDTO result = fixture.toDTO();

		assertNotNull(result);
		assertEquals(0, result.getScore());
		assertEquals(null, result.getPawn());
		assertEquals(10, result.getCoins());
		assertEquals("1", result.getPlayerID());
		
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PlayerTest.class);
	}
}