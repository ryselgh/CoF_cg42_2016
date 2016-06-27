package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.w3c.dom.Document;
import com.communication.actions.SatisfyKingDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;
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
		
		//sets for create the game
		//some letters for the permits
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
	public void testSatisfyKing()
		throws Exception {
		
		SatisfyKing result = new SatisfyKing(politics, destination);

		assertNotNull(result);
	}

	@Test
	public void testExecute()
		throws Exception {
		
		SatisfyKing fixture = new SatisfyKing(politics, destination);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
	}

	@Test(expected = NullPointerException.class)
	public void testExecuteThrowsExceptionWhenThereAreNoCards()
		throws Exception {
		SatisfyKing fixture = new SatisfyKing(null, destination);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
	}

	@Test(expected = NullPointerException.class)
	public void testExecuteThrowsExceptionWhenThereIsNoLocation()
		throws Exception {
		SatisfyKing fixture = new SatisfyKing(politics,null);
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
	}

	//These are some cases in which the KingMoveCan Be Done

	
	@Test
	public void testExecuteWithAMove()
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
	public void testExecuteWithAnotherMove()
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
	public void testExecuteTheBonusReturn()
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
	public void testExecuteAndAnotherBonusReturn()
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
	public void testExecuteWithOneCard()
		throws Exception {
		
		PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
		
		
		game.getActualPlayer().getHand().add(0, card1);
		
		
		PoliticsCard[] tempHand= new PoliticsCard[1];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		
		
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		fixture.isValid();
		fixture.execute();
		assertEquals(game.getActualPlayer().getCoins(),13);
	}
	@Test
	public void testExecuteWithTwoCards()
		throws Exception {
		
		PoliticsCard card1= new PoliticsCard(CouncilorColor.JOLLY);
		PoliticsCard card2= new PoliticsCard(CouncilorColor.JOLLY);
		
		
		game.getActualPlayer().getHand().add(0, card1);
		game.getActualPlayer().getHand().add(0, card2);
		
		
		PoliticsCard[] tempHand= new PoliticsCard[2];
		tempHand[0] = game.getActualPlayer().getHand().get(0);
		tempHand[1] = game.getActualPlayer().getHand().get(1);
		
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		fixture.isValid();
		fixture.execute();
		assertEquals(game.getActualPlayer().getCoins(),15);
	}
	@Test
	public void testExecuteWithThreeCards()
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
		
		
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		fixture.isValid();
		fixture.execute();
		assertEquals(game.getActualPlayer().getCoins(),17);
	}
	
	@Test
	public void testExecuteWithError()
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

		fixture.isValid();
		ActionReturn result = fixture.execute(); 
		
		assertEquals(result.getError(),"\nYou have not enought money for the move [Juvelar --> Castrums, you need 6\nInvalid input cards");
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
		
		SatisfyKing fixture = new SatisfyKing(tempHand,destination);
		fixture.setGame(game);
		game.getActualPlayer().setCoins(30);
		
		
		

		boolean result = fixture.isValid();

		
		assertTrue(result);
	}
	
	
	@Test
	public void testIsValidReturnFalse()
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

		
		assertFalse(result);
	}

	@Test
	public void testSetterFromDTO()
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
	
		SatisfyKing fixture =  new SatisfyKing(tempHand,destination);
		PoliticsCardDTO[] tempHandDTO = new PoliticsCardDTO[4];
		tempHandDTO[0] = new PoliticsCardDTO();
		tempHandDTO[0] = tempHand[0].toDTO();
		tempHandDTO[1] = new PoliticsCardDTO();
		tempHandDTO[1] = tempHand[0].toDTO();
		tempHandDTO[2] = new PoliticsCardDTO();
		tempHandDTO[2] = tempHand[0].toDTO();
		tempHandDTO[3] = new PoliticsCardDTO();
		tempHandDTO[3] = tempHand[0].toDTO();
		fixture.setGame(game);
		SatisfyKingDTO skDTO = new SatisfyKingDTO();
		skDTO.setDestination(destination.toDTO(new ArrayList<PlayerDTO>()));
		skDTO.setPolitics(tempHandDTO);
		
		

		fixture.setterFromDTO(skDTO, game.getActualPlayer(), game);
		assertTrue( fixture instanceof SatisfyKing);

		
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SatisfyKingTest.class);
	}
}