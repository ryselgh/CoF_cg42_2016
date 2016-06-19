package com.server.model.gamelogic;

import org.junit.*;

import com.communication.values.BonusType;
import com.communication.values.CouncilorColor;
import com.server.actions.ObtainPermit;
import com.server.model.board.Assistant;
import com.server.model.board.Balcony;
import com.server.model.board.Bonus;
import com.server.model.board.Councilor;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;

import static org.junit.Assert.*;

public class MainActionTest {
	
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
	public void testMainAction_1()
		throws Exception {
		
		MainAction result = new MainAction(game);

		
		assertNotNull(result);
	}
	
	@Test
	public void testAddActionCounter_1()
		throws Exception {
		MainAction fixture = new MainAction(game);
		fixture.setActionCounter(1);
		fixture.setActionCounter(1);
		int i = 1;

		fixture.addActionCounter(i);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ArrayIndexOutOfBoundsException: 0
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
		assertEquals(fixture.getActionCounter(),2);
	}
		
		@Test
		public void testGetActionCounter()
			throws Exception {
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);

			int result = fixture.getActionCounter();

			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.ArrayIndexOutOfBoundsException: 0
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
			assertEquals(1, result);
		}
		
		@Test
		public void canObtainPermitsTest(){
			
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card3= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card4= new PoliticsCard(CouncilorColor.JOLLY);
			
			game.getActualPlayer().getHand().add(0, card1);
			game.getActualPlayer().getHand().add(0, card2);
			game.getActualPlayer().getHand().add(0, card3);
			game.getActualPlayer().getHand().add(0, card4);
			
			PoliticsCard[] tempHand= new PoliticsCard[4];
			tempHand[0] = game.getActualPlayer().getHand().get(0);
			tempHand[1] = game.getActualPlayer().getHand().get(1);
			tempHand[2] = game.getActualPlayer().getHand().get(2);
			tempHand[3] = game.getActualPlayer().getHand().get(3);
			game.getActualPlayer().setCoins(20);
			Balcony balcony = game.getMap().getBalcony(1);
			
			boolean result = fixture.canObtainPermit(tempHand, balcony);

			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.NullPointerException
			//       at com.server.model.board.Map.<init>(Map.java:147)
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
			assertTrue(result);
		}
		
		@Test
		public void canObtainPermitsTest2(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
			
			
			game.getActualPlayer().getHand().add(0, card1);
			game.getActualPlayer().getHand().add(0, card2);
			
			
			PoliticsCard[] tempHand= new PoliticsCard[2];
			tempHand[0] = game.getActualPlayer().getHand().get(0);
			tempHand[1] = game.getActualPlayer().getHand().get(1);
			
			game.getActualPlayer().setCoins(2);
			Balcony balcony = game.getMap().getBalcony(1);
			
			boolean result = fixture.canObtainPermit(tempHand, balcony);

			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.NullPointerException
			//       at com.server.model.board.Map.<init>(Map.java:147)
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
			assertFalse(result);
		}
		
		@Test
		public void payCardTest(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			
			game.getActualPlayer().setCoins(20);
			
			fixture.payCards(2, 2);
			
			assertEquals(game.getActualPlayer().getCoins(),11);

			
		}
		
		@Test
		public void payCardTest2(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			
			game.getActualPlayer().setCoins(20);
			
			fixture.payCards(1, 1);
			
			assertEquals(game.getActualPlayer().getCoins(),9);

			
		}
		
		@Test
		public void ObtainPermitsTest(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			
			PermitsCard result = fixture.obtainPermit(2, 1);
			game.getActualPlayer().addPermits(result);
			
			assertEquals(game.getActualPlayer().getPermits().size(),1);
			
		}
		
		@Test
		public void canSatisfyKingTest(){
			
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
			
			
			game.getActualPlayer().getHand().add(0, card1);
			game.getActualPlayer().getHand().add(0, card2);
			
			
			PoliticsCard[] tempHand= new PoliticsCard[2];
			tempHand[0] = game.getActualPlayer().getHand().get(0);
			tempHand[1] = game.getActualPlayer().getHand().get(1);
			
			game.getActualPlayer().setCoins(2);
			;
			
			boolean result = fixture.canSatisfyKing(tempHand);
			assertFalse(result);
			
		}
		
		
		@Test
		public void canSatisfyKingTest2(){
			
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card3= new PoliticsCard(CouncilorColor.JOLLY);
			PoliticsCard card4= new PoliticsCard(CouncilorColor.JOLLY);
			
			game.getActualPlayer().getHand().add(0, card1);
			game.getActualPlayer().getHand().add(0, card2);
			game.getActualPlayer().getHand().add(0, card3);
			game.getActualPlayer().getHand().add(0, card4);
			
			PoliticsCard[] tempHand= new PoliticsCard[4];
			tempHand[0] = game.getActualPlayer().getHand().get(0);
			tempHand[1] = game.getActualPlayer().getHand().get(1);
			tempHand[2] = game.getActualPlayer().getHand().get(2);
			tempHand[3] = game.getActualPlayer().getHand().get(3);
			game.getActualPlayer().setCoins(20);
			
			boolean result = fixture.canSatisfyKing(tempHand);

			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.NullPointerException
			//       at com.server.model.board.Map.<init>(Map.java:147)
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
			assertTrue(result);
			
		}
		
		@Test
		public void canMoveKing(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			game.getActualPlayer().setCoins(20);
			boolean result = fixture.canMoveKing(game.getMap().getCity()[4]);
			assertTrue(result);
		}
		
		@Test
		public void canMoveKing2(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			game.getActualPlayer().setCoins(2);
			boolean result = fixture.canMoveKing(game.getMap().getCity()[0]);
			assertFalse(result);
		}
		
		@Test
		public void moveKing(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			game.getActualPlayer().setCoins(20);
			
			fixture.moveKing(game.getMap().getCity()[4]);
			assertEquals(game.getActualPlayer().getCoins(),16);
		
		}
		
		@Test
		public void shiftCouncilorTest(){
			MainAction fixture = new MainAction(game);
			
			fixture.setActionCounter(1);
			Councilor councilor = new Councilor(CouncilorColor.BLACK);
			int selection = 3;
			game.getMap().getCouncilorsPool().clear();
			game.getMap().getCouncilorsPool().add(councilor);

			fixture.shiftCouncil(selection, councilor);

			// An unexpected exception was thrown in user code while executing this test:
			//    java.lang.ArrayIndexOutOfBoundsException: 0
			//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:68)
			//       at com.server.model.gamelogic.Game.<init>(Game.java:51)
			assertEquals(game.getMap().getBalcony(3).getCouncilors()[3].getCouncilorColor(),CouncilorColor.BLACK);
			assertEquals(game.getActualPlayer().getCoins(),14);
			
			
		}
		
		@Test 
		public void canBuildTest(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			String[] l = new String[3];
			l[0] = "b";
			l[1] = "c";
			l[2] = "d";
			Bonus[] b = new Bonus[2];
			b[0] = new Bonus(BonusType.ASSISTANT,1);
			b[1] = new Bonus(BonusType.COIN,1);
			PermitsCard pc = new PermitsCard(b,l);
//			Assistant e1 = new Assistant();
//			Assistant e2 = new Assistant();
//			Assistant e3 = new Assistant();
//			game.getActualPlayer().getAvailableAssistants().add(e1);
//			game.getActualPlayer().getAvailableAssistants().add(e2);
//			game.getActualPlayer().getAvailableAssistants().add(e3);
			
			boolean result = fixture.canBuild(game.getMap().getCity()[1], pc);
			
			assertTrue(result);
			
		}
		
		@Test 
		public void canBuildTest2(){
			MainAction fixture = new MainAction(game);
			fixture.setActionCounter(1);
			String[] l = new String[3];
			l[0] = "b";
			l[1] = "c";
			l[2] = "d";
			Bonus[] b = new Bonus[2];
			b[0] = new Bonus(BonusType.ASSISTANT,1);
			b[1] = new Bonus(BonusType.COIN,1);
			PermitsCard pc = new PermitsCard(b,l);
			game.getActualPlayer().getAvailableAssistants().clear();
			Emporium e = new Emporium(game.getThatPlayer(2));
			game.getMap().getCity()[1].setEmporium(e);
			
			boolean result = fixture.canBuild(game.getMap().getCity()[1], pc);
			
			assertFalse(result);
			
		}
			
			
			@Test
			public void BuildTest(){
				MainAction fixture = new MainAction(game);
				fixture.setActionCounter(1);
				
				fixture.build(game.getMap().getCity()[3]);
				
				assertEquals(game.getActualPlayer().getAvailableEmporiums().size(),9);
			}
			
			
		
		
		
		
	
	
}