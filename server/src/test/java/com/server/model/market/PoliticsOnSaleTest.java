package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.market.PoliticsOnSaleDTO;
import com.communication.values.CouncilorColor;
import com.server.model.board.Assistant;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class PoliticsOnSaleTest {
	
	PoliticsCard pol;
	String UID="1";
	Game game;
	Player player;
	String[] players;
	Player buyer;
	
	
	@Before
	
	public void setUp()
		throws Exception {
		//sets for the game and the market
		pol = new PoliticsCard(CouncilorColor.BLACK);
		players = new String[3];
		players[0] = "smemo";
		players[1] = "figlio";
		players[2] = "negro";
		game = new Game(3, "Default map1.xml", UID, players);
		player = game.getActualPlayer();
		buyer = game.getThatPlayer(2);
	}
	
	
	@Test
	public void testPoliticsOnSale()
		throws Exception {
		

		PoliticsOnSale result = new PoliticsOnSale(player, pol, 1,UID);

		assertNotNull(result);
		
	}

	@Test
	public void testGetPrice()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(player, pol, 1,UID);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testObtain()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(player, pol, 1,UID);
		Player buyer = game.getThatPlayer(2);
		buyer.setCoins(1);

		fixture.obtain(buyer);

	}

	@Test
	public void testPrintDetails()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(player, pol, 1,UID);

		String result = fixture.printDetails();

		assertEquals("Politic card: [Color= BLACK]\nPrice= 1\n\n", result);
	}

	@Test
	public void testToDTO()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(player, pol, 1,UID);

		PoliticsOnSaleDTO result = fixture.toDTO();

		assertNotNull(result);
		assertEquals(1, result.getPrice());
		assertTrue(result instanceof PoliticsOnSaleDTO);
	}

	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsOnSaleTest.class);
	}
}