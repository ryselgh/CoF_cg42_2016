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
		//sets for create the game
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		
		game= new Game(3, true, null, players);

	}
	
	
	@Test
	public void testObtainPermit()
		throws Exception {
		
		int regionIndex = 1;
		int slot = 1;

		ObtainPermit result = new ObtainPermit(null, regionIndex, slot);

		assertNotNull(result);
	}

	@Test
	public void testExecute()
		throws Exception {
		PermitsCard pc = game.getMap().getPermitsDeck(1).getSlot(1, false);
		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[5] , 1, 1);
		fixture.setGame(game);
		
		
		
		fixture.counter = 1;
		fixture.jollycnt = 1;

		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
		assertEquals(result.getBonus(),pc.getBonus());
	}

	@Test
	public void testExecuteOnlyJolly()
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
	public void testExecuteTwoCards()
		throws Exception {
		PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
		
		
		game.getActualPlayer().getHand().add(0, card1);
		game.getActualPlayer().getHand().add(0, card2);
		
		
		PoliticsCard[] tempHand= new PoliticsCard[2];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		tempHand[1] = game.getActualPlayer().getHand().get(1);
		
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		fixture.setGame(game);
		PermitsCard pc = game.getMap().getPermitsDeck(1).getSlot(1, false);
		game.getActualPlayer().setCoins(20);
		
		fixture.isValid();
		fixture.execute();
		
		assertEquals(game.getActualPlayer().getCoins(),11);
	}
	@Test
	public void testExecuteThreeCards()
		throws Exception {
		PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card3= new PoliticsCard(CouncilorColor.JOLLY);
		
		
		game.getActualPlayer().getHand().add(0, card1);
		game.getActualPlayer().getHand().add(0, card2);
		game.getActualPlayer().getHand().add(0, card3);
		
		
		PoliticsCard[] tempHand= new PoliticsCard[3];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		tempHand[1] = game.getActualPlayer().getHand().get(1);
		tempHand[2] = game.getActualPlayer().getHand().get(2);
		
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		fixture.setGame(game);
		PermitsCard pc = game.getMap().getPermitsDeck(1).getSlot(1, false);
		game.getActualPlayer().setCoins(20);
		
		fixture.isValid();
		fixture.execute();
		
		assertEquals(game.getActualPlayer().getCoins(),13);
	}

	@Test
	public void testIsValidReturnFalse()
		throws Exception {
		PoliticsCard[] tempHand= new PoliticsCard[3];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		tempHand[1] = game.getActualPlayer().getHand().get(1);
		tempHand[2] = game.getActualPlayer().getHand().get(2);
		
		ObtainPermit fixture = new ObtainPermit(tempHand, 1, 1);
		
		fixture.setGame(game);
		game.getActualPlayer().setCoins(1);
		
		boolean result = fixture.isValid();
		

		
		assertFalse(result);
	}

	@Test
	public void testIsValidReturnTrue()
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

		
		assertTrue(result);
	}
	

	@Test
	public void testSetterFromDTO()
		throws Exception {
		ObtainPermit perm = new ObtainPermit(null, 0, 0);
		perm.setGame(game);
		game.getActualPlayer().setCoins(20);
		
		PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card3= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card4= new PoliticsCard(CouncilorColor.JOLLY);
		
		game.getActualPlayer().getHand().add(0, card1);
		game.getActualPlayer().getHand().add(0, card2);
		game.getActualPlayer().getHand().add(0, card3);
		game.getActualPlayer().getHand().add(0, card4);
		
		PoliticsCardDTO[] tempHandDTO= new PoliticsCardDTO[4];
		tempHandDTO[0] = new PoliticsCardDTO();
		tempHandDTO[0].setColor(CouncilorColor.JOLLY);
		tempHandDTO[1] = new PoliticsCardDTO();
		tempHandDTO[1].setColor(CouncilorColor.JOLLY);
		tempHandDTO[2] = new PoliticsCardDTO();
		tempHandDTO[2].setColor(CouncilorColor.JOLLY);
		tempHandDTO[3] = new PoliticsCardDTO();
		tempHandDTO[3].setColor(CouncilorColor.JOLLY);
		
		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
		obtainPermitDTO.setPolitics(tempHandDTO);
		obtainPermitDTO.setRegionIndex(1);
		obtainPermitDTO.setSlot(1);
		
		perm.setterFromDTO(obtainPermitDTO, game.getActualPlayer(), game);
		perm.isValid();
		perm.execute();
		assertTrue(perm instanceof ObtainPermit);
		
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ObtainPermitTest.class);
	}
}