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
	
	Player player;
	ArrayList<Emporium> availableEmporiums;
	ArrayList<Assistant> availableAssistants;
	Pawn pawn;
	
	
	@Before
	public void setUp()
		throws Exception {
		availableEmporiums = new ArrayList<Emporium>();
		availableAssistants = new ArrayList<Assistant>();
		pawn = new Pawn(player, "FF0000");
		pawn.setPos(0);
		
		
		player = new Player("1");
		player.setScore(20);
		player.setCoins(10);
		player.setAvailableEmporiums(availableEmporiums);
		player.setAvailableAssistants(availableAssistants);
		
		
	}
	@Test
	public void testPlayer_1()
		throws Exception {
		

		Player result = player;

		assertNotNull(result);
		assertEquals("1", result.getID());
		assertEquals(20, result.getScore());
		assertEquals(false, result.hasUncoveredPermits());
		assertEquals(null, result.getPawn());
		assertEquals(10, result.getCoins());
	}

	@Test
	public void testAddAssistant_1()
		throws Exception {
		Player fixture = player;
		
		
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
		Assistant ass = new Assistant();

		fixture.addAssistant(ass);
		
		assertEquals(availableAssistants.size(),1);

	}

	@Test
	public void testAddAssistant_2()
		throws Exception {
		Player fixture = player;
		
		
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
		ArrayList<Assistant> assistants = new ArrayList(10);

		fixture.addAssistant(assistants);
		assertEquals(fixture.getAvailableAssistants().size(),0);
	}

	@Test
	public void testAddBonusCards_1()
		throws Exception {
		Player fixture = player;
		BonusCard bc = new BonusCard(new Bonus(BonusType.NOBILITY,3));
		
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
		ArrayList<BonusCard> bonusCards = new ArrayList(1);
		bonusCards.add(bc);

		fixture.addBonusCards(bonusCards);
		assertEquals(fixture.getBonusCards().size(),1);

	}

	@Test
	public void testAddCoins_1()
		throws Exception {
		Player fixture = player;
		
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));
		int coins = 1;

		fixture.addCoins(coins);
		
		assertEquals(fixture.getCoins(),11);

	}

	@Test
	public void testAddPermits_1()
		throws Exception {
		Player fixture = player;
		Bonus[] b = new Bonus[1];
		b[0] = new Bonus(BonusType.COIN, 5);
		String[] letter = new String[2];
		letter[0] ="l";
		letter[1] = "k";
		PermitsCard pec = new PermitsCard(b,letter);
		
		
		
		

		fixture.addPermits(pec);
		assertEquals(fixture.getPermits().size(),1);

	}

	@Test
	public void testAddPolitics_1()
		throws Exception {
		Player fixture = player;
	
		PoliticsCard poc = new PoliticsCard(CouncilorColor.BLACK);

		fixture.addPolitics(poc);
		
		assertEquals(fixture.getHand().size(),1);

	}

//	@Test
//	public void testCompareToDTOs_1()
//		throws Exception {
//		Player fixture = player;
//		PlayerDTO plDTO1 = fixture.toDTO();
//		PlayerDTO plDTO2 = new PlayerDTO();
//		
//		ArrayList<PlayerDTO> plsDTO = new ArrayList();
//		plsDTO.add(plDTO2);
//		plsDTO.add(plDTO1);
//
//		PlayerDTO result = fixture.compareToDTOs(plsDTO);
//
//		assertEquals(plDTO1, result);
//	}

	@Test
	public void testCompareToDTOs_2()
		throws Exception {
		Player fixture = player;
		
		ArrayList<PlayerDTO> plsDTO = new ArrayList();

		PlayerDTO result = fixture.compareToDTOs(plsDTO);

		assertEquals(null, result);
	}

	

	@Test
	public void testGetAvailableAssistants_1()
		throws Exception {
		Player fixture = player;
		fixture.setScore(1);
		fixture.setAvailableAssistants(availableAssistants);
		fixture.setCoins(1);
		fixture.setAvailableEmporiums(new ArrayList());
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));

		ArrayList<Assistant> result = fixture.getAvailableAssistants();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetAvailableEmporiums_1()
		throws Exception {
		Player fixture = player;
		fixture.setScore(1);
		fixture.setAvailableAssistants(new ArrayList());
		fixture.setCoins(1);
		fixture.setAvailableEmporiums(availableEmporiums);
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPermits(new PermitsCard(new Bonus[] {}, new String[] {}));

		ArrayList<Emporium> result = fixture.getAvailableEmporiums();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetBonusCards_1()
		throws Exception {
		Player fixture = player;
		

		ArrayList<BonusCard> result = fixture.getBonusCards();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetCoins_1()
		throws Exception {
		Player fixture = player;
	

		int result = fixture.getCoins();

		assertEquals(10, result);
	}

//	@Test
//	public void testGetColor_1()
//		throws Exception {
//		Player fixture = player;
//		
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
		Player fixture = player;
		PoliticsCard poc = new PoliticsCard(CouncilorColor.BLACK);

		fixture.addPolitics(poc);
		

		ArrayList<PoliticsCard> result = fixture.getHand();
	

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetID_1()
		throws Exception {
		Player fixture = player;
		

		String result = fixture.getID();

		assertEquals("1", result);
	}

	@Test
	public void testGetPawn_1()
		throws Exception {
		Player fixture = player;
		
		Pawn result = fixture.getPawn();

		assertEquals(null, result);
	}

	@Test
	public void testGetPermits_1()
		throws Exception {
		Player fixture = player;
		Bonus[] b = new Bonus[1];
		b[0] = new Bonus(BonusType.COIN, 5);
		String[] letter = new String[2];
		letter[0] ="l";
		letter[1] = "k";
		PermitsCard pec = new PermitsCard(b,letter);
		fixture.addPermits(pec);
		ArrayList<PermitsCard> result = fixture.getPermits();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetScore_1()
		throws Exception {
		Player fixture = player;
		

		int result = fixture.getScore();

		assertEquals(20, result);
	}

	@Test
	public void testHasUncoveredPermits_1()
		throws Exception {
		Player fixture = player;
		Bonus[] b = new Bonus[1];
		b[0] = new Bonus(BonusType.COIN, 5);
		String[] letter = new String[2];
		letter[0] ="l";
		letter[1] = "k";
		PermitsCard pec = new PermitsCard(b,letter);
		fixture.addPermits(pec);
		pec.setFaceDown(false);

		boolean result = fixture.hasUncoveredPermits();

		assertEquals(true, result);
	}

	@Test
	public void testHasUncoveredPermits_2()
		throws Exception {
		Player fixture = new Player("");
		

		boolean result = fixture.hasUncoveredPermits();

		assertEquals(false, result);
	}

	@Test
	public void testHasUncoveredPermits_3()
		throws Exception {
		Player fixture = player;
		Bonus[] b = new Bonus[1];
		b[0] = new Bonus(BonusType.COIN, 5);
		String[] letter = new String[2];
		letter[0] ="l";
		letter[1] = "k";
		PermitsCard pec = new PermitsCard(b,letter);
		fixture.addPermits(pec);
		pec.setFaceDown(true);

		boolean result = fixture.hasUncoveredPermits();

		assertEquals(false, result);
	}

//	@Test
//	public void testRemoveAssistant_1()
//		throws Exception {
//		Player fixture = player;
//		Assistant ass = new Assistant();
//		Assistant ass2 = new Assistant();
//		fixture.addAssistant(ass);
//		fixture.addAssistant(ass2);
//
//		fixture.removeAssistant(ass2);
//		
//		assertEquals(fixture.getAvailableAssistants().size(),1);
//
//	}

	

	

//	@Test
//	public void testRemovePermit_1()
//		throws Exception {
//		Player fixture = player;
//		
//		Bonus[] b = new Bonus[1];
//		b[0] = new Bonus(BonusType.COIN, 5);
//		String[] letter = new String[2];
//		letter[0] ="l";
//		letter[1] = "k";
//		PermitsCard pec = new PermitsCard(b,letter);
//		fixture.addPermits(pec);
//
//		fixture.removePermit(pec);
//		assertEquals(fixture.getPermits().size(),0);
//
//	}

	
	@Test
	public void testRemovePolitics_1()
		throws Exception {
		Player fixture = player;
		
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		fixture.addPolitics(new PoliticsCard(CouncilorColor.BLACK));
		
		int index = 1;

		fixture.removePolitics(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
		//       at java.util.ArrayList.rangeCheck(ArrayList.java:653)
		//       at java.util.ArrayList.remove(ArrayList.java:492)
		//       at com.server.model.gamelogic.Player.removePolitics(Player.java:190)
	assertEquals(fixture.getHand().size(),2);
	
	}
	
	

	

	@Test
	public void testRemovePolitics_4()
		throws Exception {
		Player fixture = player;
		
		PoliticsCard pc = new PoliticsCard(CouncilorColor.BLACK);
		fixture.addPolitics(pc);
		fixture.removePolitics(pc);
		assertEquals(fixture.getHand().size(),0);

	}

	
	

	

//	@Test
//	public void testSellAssistant_1()
//		throws Exception {
//		Player fixture = player;
//		Assistant a = new Assistant();
//		fixture.addAssistant(a);
//		fixture.sellAssistant(a);
//		assertEquals(fixture.getAvailableAssistants().size(),0);
//
//	}

//	@Test
//	public void testSellPermitsCard_1()
//		throws Exception {
//		Player fixture = player;
//		Bonus[] b = new Bonus[1];
//		b[0] = new Bonus(BonusType.COIN, 5);
//		String[] letter = new String[2];
//		letter[0] ="l";
//		letter[1] = "k";
//		PermitsCard pec = new PermitsCard(b,letter);
//		
//	
//		fixture.addPermits(pec);
//		
//
//		fixture.sellPermitsCard(pec);
//		
//		assertEquals(fixture.getPermits().size(),0);
//
//	}
//
//	@Test
//	public void testSellPoliticsCard_1()
//		throws Exception {
//		Player fixture = player;
//		PoliticsCard polc1 = new PoliticsCard(CouncilorColor.BLACK);
//		PoliticsCard polc2 = new PoliticsCard(CouncilorColor.BLACK);
//		PoliticsCard polc3 = new PoliticsCard(CouncilorColor.BLACK);
//		
//		fixture.addPolitics(polc3);
//		fixture.addPolitics(polc2);
//		fixture.addPolitics(polc1);
//		
//
//		fixture.sellPoliticsCard(polc1);
//		
//		assertEquals(fixture.getHand().size(),2);
//
//	}

	

	@Test
	public void testToDTO_1()
		throws Exception {
		Player fixture = player;
		
		PlayerDTO result = fixture.toDTO();

		assertTrue(result instanceof PlayerDTO);
	}

	

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PlayerTest.class);
	}
}