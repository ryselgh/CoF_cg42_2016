package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.ObtainPermitDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ObtainPermitTest {
	
	Game game;
	String[] players;
	@Before
	public void setUp()
		throws Exception {
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		
		game= new Game(3, true, null, players);

	}
	
	@Test
	public void testExecute_1()
		throws Exception {
		PermitsCard pc = game.getMap().getPermitsDeck(1).getSlot(1, false);
		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[5] , 1, 1);
		fixture.setGame(game);
		
		
		
		fixture.counter = 1;
		fixture.jollycnt = 1;

		ActionReturn result = fixture.execute();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotNull(result);
		assertEquals(result.getBonus(),pc.getBonus());
	}

	@Test
	public void testExecute_2()
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
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(20);
		
		fixture.isValid();
		fixture.execute();
		assertEquals(game.getActualPlayer().getCoins(),16);
		
	}

	@Test
	public void testExecute_3()
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
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		fixture.setGame(game);
		PermitsCard pc = game.getMap().getPermitsDeck(1).getSlot(1, false);
		game.getActualPlayer().setCoins(20);
		
		fixture.isValid();
		fixture.execute();
		
		assertEquals(game.getActualPlayer().getPermits().get(0),pc);
	}

	@Test
	public void testIsValid_1()
		throws Exception {
		PoliticsCard[] tempHand= new PoliticsCard[3];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		tempHand[1] = game.getActualPlayer().getHand().get(1);
		tempHand[2] = game.getActualPlayer().getHand().get(2);
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		
		fixture.setGame(game);
		game.getActualPlayer().setCoins(1);
		
		boolean result = fixture.isValid();
		

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertFalse(result);
	}

	@Test
	public void testIsValid_2()
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
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(20);
		
		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertTrue(result);
	}

//	@Test
//	public void testSetterFromDTO_1()
//		throws Exception {
//		
//		PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
//		PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
//		PoliticsCard card3= new PoliticsCard(CouncilorColor.JOLLY);
//		PoliticsCard card4= new PoliticsCard(CouncilorColor.JOLLY);
//		
//		game.getActualPlayer().getHand().add(0, card1);
//		game.getActualPlayer().getHand().add(0, card2);
//		game.getActualPlayer().getHand().add(0, card3);
//		game.getActualPlayer().getHand().add(0, card4);
//		
//		PoliticsCard[] tempHand= new PoliticsCard[4];
//		tempHand[0] = game.getActualPlayer().getHand().get(0);
//		tempHand[1] = game.getActualPlayer().getHand().get(1);
//		tempHand[2] = game.getActualPlayer().getHand().get(2);
//		tempHand[3] = game.getActualPlayer().getHand().get(3);
//		
//		PoliticsCardDTO[] tempHandDTO= new PoliticsCardDTO[4];
//		tempHandDTO[0] = game.getActualPlayer().getHand().get(0).toDTO();
//		tempHandDTO[1] = game.getActualPlayer().getHand().get(1).toDTO();
//		tempHandDTO[2] = game.getActualPlayer().getHand().get(2).toDTO();
//		tempHandDTO[3] = game.getActualPlayer().getHand().get(3).toDTO();
//		
//		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
//		fixture.setGame(game);
//		
//		
//		
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(tempHandDTO);
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, game.getActualPlayer(), game);
//		
//		assertEquals(fixture.)



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ObtainPermitTest.class);
	}
}