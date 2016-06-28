package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;

import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class OnSaleTest {
	String UID="1";
	Game game;
	Player player;
	String[] players;
	Player buyer;
	Object obj;
	
	
	@Before
	
	public void setUp()
		throws Exception {
		//sets for the game and the market
		
		obj = new Object();
		players = new String[3];
		players[0] = "smemo";
		players[1] = "figlio";
		players[2] = "negro";
		game = new Game(3, "Default map1.xml", UID, players);
		player = game.getActualPlayer();
		buyer = game.getThatPlayer(2);
	}
	
	
	@Test
	public void testOnSale()
		throws Exception {

		OnSale result = new OnSale();

		assertNotNull(result);
		
	}

	


	@Test
	public void testGetPrice()
		throws Exception {
		OnSale fixture = new OnSale(player, obj, 1,UID);

		int result = fixture.getPrice();

		assertEquals(0, result);
	}

	@Test
	public void testObtain()
		throws Exception {
		OnSale fixture = new OnSale(player, obj, 1,UID);
		Player buyer = new Player("1");

		fixture.obtain(buyer);

	}

	@Test
	public void testPrintDetails()
		throws Exception {
		OnSale fixture = new OnSale(player, obj,1,UID);

		String result = fixture.printDetails();

		assertEquals("", result);
	}

	@Test
	public void testGetUID(){
		OnSale fixture = new OnSale(player, obj,1,UID);
		String result = fixture.getUID();
		
		assertEquals(result, "1");
		
	}
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(OnSaleTest.class);
	}
}