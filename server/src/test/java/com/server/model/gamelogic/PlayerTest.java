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
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, true, null, players);
		
		
	}
	@Test
	public void testPlayer_1()
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
	public void testAddAssistant_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		Assistant ass = new Assistant();

		fixture.addAssistant(ass);
		assertEquals(fixture.getAvailableAssistants().size(),2);

	}

	@Test
	public void testAddAssistant_2()
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
	public void testAddBonusCards_1()
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
	public void testAddCoins_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		int coins = 1;

		fixture.addCoins(coins);
		assertEquals(fixture.getCoins(),11);

	}

	@Test
	public void testAddPermits_1()
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
	public void testAddPolitics_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		PoliticsCard poc = new PoliticsCard(CouncilorColor.BLACK);

		fixture.addPolitics(poc);
		
		assertEquals(fixture.getHand().size(),7);

	}

	@Test
	public void testCompareToDTOs_1()
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
	public void testCompareToDTOs_2()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		ArrayList<PlayerDTO> plsDTO = new ArrayList();

		PlayerDTO result = fixture.compareToDTOs(plsDTO);

		assertEquals(null, result);
	}

	

	@Test
	public void testGetAvailableAssistants_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		ArrayList<Assistant> result = fixture.getAvailableAssistants();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetAvailableEmporiums_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<Emporium> result = fixture.getAvailableEmporiums();

		assertNotNull(result);
		assertEquals(10, result.size());
	}

	@Test
	public void testGetBonusCards_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<BonusCard> result = fixture.getBonusCards();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetCoins_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		fixture.setCoins(1);
		

		int result = fixture.getCoins();

		assertEquals(1, result);
	}

//	@Test
//	public void testGetColor_1()
//		throws Exception {
//		Player fixture = new Player("1");
//		Pawn pawn = new Pawn(fixture, "FF0000");
//		
//		
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//
//		String result = fixture.getColor();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.gamelogic.Player.getColor(Player.java:109)
//		assertNotNull(result);
//	}

	@Test
	public void testGetHand_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<PoliticsCard> result = fixture.getHand();

		assertNotNull(result);
		assertEquals(6, result.size());
	}

	@Test
	public void testGetID_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		String result = fixture.getID();

		assertEquals("1", result);
	}

	@Test
	public void testGetPawn_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		Pawn result = fixture.getPawn();

		assertEquals(null, result);
	}

	@Test
	public void testGetPermits_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		ArrayList<PermitsCard> result = fixture.getPermits();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetScore_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		fixture.setScore(11);
		

		int result = fixture.getScore();

		assertEquals(11, result);
	}

	@Test
	public void testHasUncoveredPermits_1()
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
	public void testHasUncoveredPermits_2()
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
	public void testHasUncoveredPermits_3()
		throws Exception {
		Player fixture = game.getActualPlayer();
		

		boolean result = fixture.hasUncoveredPermits();

		assertEquals(false, result);
	}

	

	@Test
	public void testRemoveAssistant_1()
		throws Exception {
		
		Player fixture = game.getActualPlayer();
		

		fixture.removeAssistant(fixture.getAvailableAssistants().get(0));
		
		assertEquals(fixture.getAvailableAssistants().size(),0);

	}

	

	

//	@Test
//	public void testRemovePermit_1()
//		throws Exception {
//		Player fixture = game.getActualPlayer();
//		Bonus[] b= new Bonus[1];
//		b[0] = new Bonus(BonusType.POINT,5);
//		String[] letters = new String[2];
//		letters[0] = "l";
//		letters[1] = "m";
//		Bonus[] b2= new Bonus[1];
//		b2[0] = new Bonus(BonusType.POINT,5);
//		String[] letterss = new String[2];
//		letterss[0] = "j";
//		letterss[1] = "k";
//		PermitsCard pec1 = new PermitsCard(b,letters);
//		PermitsCard pec2 = new PermitsCard(b2,letterss);
//		fixture.addPermits(pec1);
//		fixture.addPermits(pec2);
//		fixture.removePermit(fixture.getPermits().get(1));
//
//		assertEquals(fixture.getPermits().size(),1);
//
//	}

//	@Test
//	public void testRemovePermit_2()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		PermitsCard pc = new PermitsCard(new Bonus[] {}, new String[] {});
//
//		fixture.removePermit(pc);
//
//	}
//
//	@Test
//	public void testRemovePermit_3()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		PermitsCard pc = new PermitsCard(new Bonus[] {}, new String[] {});
//
//		fixture.removePermit(pc);
//
//	}

	@Test
	public void testRemovePolitics_1()
		throws Exception {
		Player fixture = game.getActualPlayer();
		
		

		fixture.removePolitics(fixture.getHand().get(0));

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
		//       at java.util.ArrayList.rangeCheck(ArrayList.java:653)
		//       at java.util.ArrayList.remove(ArrayList.java:492)
		//       at com.server.model.gamelogic.Player.removePolitics(Player.java:190)
		
		assertEquals(fixture.getHand().size(),5);
	}

	

	

	

	

//	@Test
//	public void testSellAssistant_1()
//		throws Exception {
//		Player fixture = game.getActualPlayer();
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		Assistant a = new Assistant();
//
//		fixture.sellAssistant(fixture.getAvailableAssistants().get(0));
//		
//
//	}
//
//	@Test
//	public void testSellPermitsCard_1()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		PermitsCard perc = new PermitsCard(new Bonus[] {}, new String[] {});
//
//		fixture.sellPermitsCard(perc);
//
//	}
//
//	@Test
//	public void testSellPoliticsCard_1()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		PoliticsCard polc = new PoliticsCard(CouncilorColor.BLACK);
//
//		fixture.sellPoliticsCard(polc);
//
//	}
//
//	@Test
//	public void testSetAvailableAssistants_1()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		ArrayList<Assistant> availableAssistants = new ArrayList();
//
//		fixture.setAvailableAssistants(availableAssistants);
//
//	}
//
//	@Test
//	public void testSetAvailableEmporiums_1()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		ArrayList<Emporium> availableEmporiums = new ArrayList();
//
//		fixture.setAvailableEmporiums(availableEmporiums);
//
//	}
//
//	@Test
//	public void testSetCoins_1()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		int coins = 1;
//
//		fixture.setCoins(coins);
//
//	}
//
//	@Test
//	public void testSetScore_1()
//		throws Exception {
//		Player fixture = new Player("");
//		fixture.setCoins(1);
//		fixture.setAvailableEmporiums(new ArrayList());
//		fixture.setScore(1);
//		fixture.setAvailableAssistants(new ArrayList());
//		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
//		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
//		int score = 1;
//
//		fixture.setScore(score);
//
//	}

	@Test
	public void testToDTO_1()
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