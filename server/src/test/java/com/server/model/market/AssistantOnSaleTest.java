package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.board.AssistantDTO;
import com.communication.market.AssistantOnSaleDTO;
import com.server.model.board.Assistant;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class AssistantOnSaleTest {
	
	
	Assistant as;
	String UID="1";
	Game game;
	Player player;
	String[] players;
	Player buyer;
	
	
	@Before
	
	public void setUp()
		throws Exception {
		//sets for the game and the market
		as = new Assistant();
		players = new String[3];
		players[0] = "smemo";
		players[1] = "figlio";
		players[2] = "negro";
		game = new Game(3, "Default map1.xml", UID, players);
		player = game.getActualPlayer();
		buyer = game.getThatPlayer(2);
		
		
	}
	
	
	@Test
	public void testAssistantOnSale()
		throws Exception {
		
		int pr = 1;

		AssistantOnSale result = new AssistantOnSale(player, as, pr,UID);

		assertNotNull(result);
		assertEquals(1, result.getPrice());
	}
	
	@Test(expected=NullPointerException.class)
	public void youHaveToSellSomething(){
		
		int pr = 1;
		AssistantOnSale asOnSale = new AssistantOnSale(player,null,pr,UID);
	}

	@Test
	public void testGetPrice()
		throws Exception {
		AssistantOnSale fixture = new AssistantOnSale(player, as, 1,UID);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testObtain_checkIfThePlayerLosesMoney()
		throws Exception {
		AssistantOnSale fixture = new AssistantOnSale(player, as, 1,UID);
		buyer.setCoins(1);
		

		fixture.obtain(buyer);
		
		assertEquals(buyer.getCoins(),0);

		
	}

	@Test
	public void testPrintDetails()
		throws Exception {
		AssistantOnSale fixture = new AssistantOnSale(player, as, 1,UID);

		String result = fixture.printDetails();

		assertEquals("Assistant\nPrice= 1\n\n", result);
	}

	@Test
	public void testToDTO()
		throws Exception {
		AssistantOnSale fixture = new AssistantOnSale(player, as, 1,UID);

		AssistantOnSaleDTO result = fixture.toDTO();

		assertNotNull(result);
		assertEquals(1, result.getPrice());
		assertTrue(result.getObj() instanceof AssistantDTO);
		assertTrue(result instanceof AssistantOnSaleDTO);
	}

	
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(AssistantOnSaleTest.class);
	}
}