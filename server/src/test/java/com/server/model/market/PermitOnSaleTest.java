package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.market.PermitOnSaleDTO;
import com.communication.values.BonusType;
import com.server.model.board.Bonus;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class PermitOnSaleTest {
	
	
	String UID="1";
	Game game;
	Player player;
	PermitsCard pc;
	Bonus[] b;
	String[] letters;
	String[] players;
	Player buyer;
	
	
	
	@Before
	public void setUp()
		throws Exception {
		
		players = new String[3];
		players[0] = "smemo";
		players[1] = "figlio";
		players[2] = "negro";
		b= new Bonus[1];
		b[0] = new Bonus(BonusType.NOBILITY,6);
		letters = new String[2];
		letters[0] = "l";
		letters[1] = "m";
		game = new Game(3, true, UID, players);
		player = game.getActualPlayer();
		pc = new PermitsCard(b,letters);
		game.getActualPlayer().addPermits(pc);
		buyer = game.getThatPlayer(2);
		
		
		
	}
	
	
	
	@Test
	public void testPermitOnSale()
		throws Exception {
		

		PermitOnSale result = new PermitOnSale(player, pc, 1,UID);

		assertNotNull(result);
		assertEquals("Permit card: {[Letters= l m ], [Bonus= NOBILITY(6) ]}\nPrice= 1\n\n", result.printDetails());
		assertEquals(1, result.getPrice());
	}

	@Test
	public void testGetPrice()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(player, pc, 1,UID);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testObtain()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(player, pc, 1,UID);
		fixture.obtain(buyer);

		
		assertEquals(buyer.getCoins(),11);
		assertEquals(player.getCoins(), 11);

	}

	@Test
	public void testPrintDetails()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(player, pc, 1,UID);

		String result = fixture.printDetails();

		assertEquals("Permit card: {[Letters= l m ], [Bonus= NOBILITY(6) ]}\nPrice= 1\n\n", result);
	}

	

	@Test
	public void testToDTO()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(player, pc, 1,UID);

		PermitOnSaleDTO result = fixture.toDTO();

		assertNotNull(result);
		assertTrue(result instanceof PermitOnSaleDTO);
	}
	
	@Test
	public void testGetUID(){
		PermitOnSale fixture = new PermitOnSale(player, pc, 1,UID);
		String result = fixture.getUID();
		assertEquals(result,"1");
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitOnSaleTest.class);
	}
}