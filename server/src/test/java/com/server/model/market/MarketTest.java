package com.server.model.market;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.market.MarketDTO;
import com.communication.values.BonusType;
import com.communication.values.CouncilorColor;
import com.server.model.board.Assistant;
import com.server.model.board.Bonus;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class MarketTest {
	
	OnSale o;
	OnSale o2;
	OnSale o3;
	String UID="1";
	Game game;
	Player player;
	String[] players;
	PermitsCard pc;
	Bonus[] b;
	String[] letters;
	PoliticsCard pol;
	
	@Before
	public void setUp()
		throws Exception {
		//sets for the game and the market
		
		players = new String[3];
		players[0] = "smemo";
		players[1] = "figlio";
		players[2] = "negro";
		game = new Game(3, "Default map1.xml", UID, players);
		player = game.getActualPlayer();
		b= new Bonus[1];
		b[0] = new Bonus(BonusType.NOBILITY,6);
		letters = new String[2];
		letters[0] = "l";
		letters[1] = "m";
		pc = new PermitsCard(b,letters);
		pol = new PoliticsCard(CouncilorColor.PINK);
		
		o = new AssistantOnSale(player,new Assistant(), 3,"4");
		o2 = new PermitOnSale(player,pc, 2,"2");
		o3 = new PoliticsOnSale(player,pol,3,"3");
	}

	
	
	@Test
	public void testMarket()
		throws Exception {

		Market result = new Market();

		assertNotNull(result);
		assertEquals(0, result.getObjNumber());
	}


	@Test
	public void testGetObjNumber()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);

		int result = fixture.getObjNumber();

		assertEquals(1, result);
	}

	@Test
	public void testGetObjOnSale()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		fixture.addObj(o3);
		

		OnSale result = fixture.getObjOnSale(2);
		
		

		
		assertNotNull(result);
		assertEquals(result.getPrice(),3);
		
	}

	@Test
	public void testGetObjectsOnSale()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		fixture.addObj(o3);

		ArrayList<OnSale> result = fixture.getObjectsOnSale();

		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testRemoveObj()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		fixture.addObj(o3);
		
		fixture.removeObj("2");
		fixture.removeObj("3");
		fixture.removeObj("4");
		
		ArrayList<OnSale> result = fixture.getObjectsOnSale();

		
		
		assertTrue(result.isEmpty());
	}

	@Test
	public void testToDTO()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		fixture.addObj(o3);
		MarketDTO result = fixture.toDTO();

		assertNotNull(result);
		assertTrue(result instanceof MarketDTO);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MarketTest.class);
	}
}