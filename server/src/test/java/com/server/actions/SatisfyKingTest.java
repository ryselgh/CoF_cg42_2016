package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.SatisfyKingDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.communication.values.CouncilorColor;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class SatisfyKingTest {
	
	
	Game game;
	String[] players;
	City destination;
	PoliticsCard[] politics;
	PoliticsCard[] tempHand;
	String[] l2;
	String[] l3;
	Bonus[] b;
	
	
	@Before
	public void setUp()
		throws Exception {
		l2 = new String[3];
		l2[0] = "a";
		l2[1] = "b";
		l2[2] = "e";
		l3 = new String[2];
		l3[0] = "h";
		l3[1] = "j";
		b = new Bonus[2];
		b[0] = new Bonus(BonusType.ASSISTANT,1);
		b[1] = new Bonus(BonusType.COIN,1);
		
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		
		game= new Game(3,true,null,players);
		
		destination = game.getMap().getCity()[2];
		politics = new PoliticsCard[4];
		politics[0] = new PoliticsCard(CouncilorColor.JOLLY);
		politics[1] = new PoliticsCard(CouncilorColor.JOLLY);
		politics[2] = new PoliticsCard(CouncilorColor.JOLLY);
		politics[3] = new PoliticsCard(CouncilorColor.JOLLY);
		
		tempHand = new PoliticsCard[4];
		
	}
	@Test
	public void testSatisfyKing_1()
		throws Exception {
		
		SatisfyKing result = new SatisfyKing(politics, destination);

		assertNotNull(result);
	}

	@Test
	public void testExecute_1()
		throws Exception {
		
		SatisfyKing fixture = new SatisfyKing(politics, destination);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotNull(result);
	}

	@Test(expected = NullPointerException.class)
	public void testExecute_2()
		throws Exception {
		SatisfyKing fixture = new SatisfyKing(null, destination);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
	}

	@Test(expected = NullPointerException.class)
	public void testExecute_3()
		throws Exception {
		SatisfyKing fixture = new SatisfyKing(politics,null);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
	}

	@Test
	public void testIsValid_1()
		throws Exception {
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
		
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertTrue(result);
	}

	
	@Test
	public void testExecute_4()
		throws Exception {
		
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
		
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		fixture.isValid();
		fixture.execute();
		assertEquals(game.getActualPlayer().getCoins(),20);
	}
	
	@Test
	public void testExecute_5()
		throws Exception {
		City kc = game.getMap().getCity()[9];
		
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
		
		SatisfyKing fixture = new SatisfyKing(tempHand,kc);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		fixture.isValid();
		fixture.execute();
		assertEquals(game.getActualPlayer().getCoins(),26);
	}
	
	@Test
	public void testExecute_6()
		throws Exception {
		Bonus[] bonusToken4 = game.getMap().getCity()[4].getBonusToken().getBonus();
		Bonus[] bonusToken7 = game.getMap().getCity()[7].getBonusToken().getBonus();
		City kc = game.getMap().getCity()[7];
		Build build = new Build(game.getMap().getCity()[4], new PermitsCard(b,l2));
		build.setGame(game);
		build.execute();
		
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
		
		SatisfyKing fixture = new SatisfyKing(tempHand,kc);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		fixture.isValid();
		
		ActionReturn bonusReturn=fixture.execute();
		
		Bonus[] result = new Bonus[bonusToken4.length+bonusToken7.length];
		for(int i=0; i<bonusToken7.length; i++)
			result[i] = bonusToken7[i];
		for(int i=0; i<bonusToken4.length; i++)
			result[bonusToken7.length+i] = bonusToken4[i];
		
		
		assertEquals(result,bonusReturn.getBonus());
		assertEquals(game.getActualPlayer().getCoins(),24);
	}
	
	@Test
	public void testExecute_7()
		throws Exception {
		Bonus[] bonusToken4 = game.getMap().getCity()[4].getBonusToken().getBonus();
		Bonus[] bonusToken7 = game.getMap().getCity()[7].getBonusToken().getBonus();
		City kc1 = game.getMap().getCity()[4];
		City kc2 = game.getMap().getCity()[7];
		City kc3 = game.getMap().getCity()[9];
		
		Build build = new Build(game.getMap().getCity()[4], new PermitsCard(b,l2));
		build.setGame(game);
		build.execute();
		Build build1 = new Build(game.getMap().getCity()[7], new PermitsCard(b,l3));
		build1.setGame(game);
		build1.execute();
		Build build2 = new Build(game.getMap().getCity()[9], new PermitsCard(b,l3));
		build2.setGame(game);
		build2.execute();
		
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
		
		SatisfyKing fixture = new SatisfyKing(tempHand,kc3);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		fixture.isValid();
		
		ActionReturn bonusReturn=fixture.execute();
		
		Bonus[] result = new Bonus[bonusToken4.length+bonusToken7.length];
		for(int i=0; i<bonusToken7.length; i++)
			result[i] = bonusToken7[i];
		for(int i=0; i<bonusToken4.length; i++)
			result[bonusToken7.length+i] = bonusToken4[i];
		
		
		assertEquals(result,bonusReturn.getBonus());
		assertEquals(game.getActualPlayer().getCoins(),26);
	}
	@Test
	public void testIsValid_2()
		throws Exception {
		PoliticsCard[] card = new PoliticsCard[4];
		card[0] = new PoliticsCard(CouncilorColor.BLACK);
		card[1] = new PoliticsCard(CouncilorColor.WHITE);
		card[2] = new PoliticsCard(CouncilorColor.BLUESKY);
		card[3] = new PoliticsCard(CouncilorColor.PURPLE);
		
		game.getActualPlayer().getHand().add(card[0]);
		game.getActualPlayer().getHand().add(card[1]);
		game.getActualPlayer().getHand().add(card[2]);
		game.getActualPlayer().getHand().add(card[3]);
		tempHand = new PoliticsCard[4];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		tempHand[1] = game.getActualPlayer().getHand().get(1);
		tempHand[2] = game.getActualPlayer().getHand().get(2);
		tempHand[3] = game.getActualPlayer().getHand().get(3);
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(0);

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertFalse(result);
	}

//	@Test
//	public void testSetGame_1()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		fixture.setGame(new Game(1, true, (Document) null));
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setGame(game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Test
//	public void testSetterFromDTO_1()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		fixture.setGame(new Game(1, true, (Document) null));
//		SatisfyKingDTO skDTO = new SatisfyKingDTO();
//		skDTO.setDestination(new CityDTO());
//		skDTO.setPolitics(new PoliticsCardDTO[] {});
//		Player player = new Player(1);
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(skDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Test
//	public void testSetterFromDTO_2()
//		throws Exception {
//		SatisfyKing fixture = new SatisfyKing(new PoliticsCard[] {}, new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		fixture.setGame(new Game(1, true, (Document) null));
//		SatisfyKingDTO skDTO = new SatisfyKingDTO();
//		skDTO.setDestination(new CityDTO());
//		skDTO.setPolitics(new PoliticsCardDTO[] {});
//		Player player = new Player(1);
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(skDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	
//
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SatisfyKingTest.class);
	}
}