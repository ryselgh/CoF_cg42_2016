package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.market.PoliticsOnSaleDTO;
import com.communication.values.CouncilorColor;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Player;

public class PoliticsOnSaleTest {
	@Test
	public void testPoliticsOnSale_1()
		throws Exception {
		Player pl = new Player(1);
		PoliticsCard pc = new PoliticsCard(CouncilorColor.BLACK);
		int pr = 1;

		PoliticsOnSale result = new PoliticsOnSale(pl, pc, pr);

		assertNotNull(result);
		assertEquals("Politic card: [Color= BLACK]\nPrice= 1\n\n", result.printDetails());
		assertEquals(1, result.getPrice());
	}

	@Test
	public void testGetPrice_1()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(new Player(1), new PoliticsCard(CouncilorColor.BLACK), 1);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testObtain_1()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(new Player(1), new PoliticsCard(CouncilorColor.BLACK), 1);
		Player buyer = new Player(1);
		buyer.setCoins(1);

		fixture.obtain(buyer);

	}

	@Test
	public void testPrintDetails_1()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(new Player(1), new PoliticsCard(CouncilorColor.BLACK), 1);

		String result = fixture.printDetails();

		assertEquals("Politic card: [Color= BLACK]\nPrice= 1\n\n", result);
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		PoliticsOnSale fixture = new PoliticsOnSale(new Player(1), new PoliticsCard(CouncilorColor.BLACK), 1);

		PoliticsOnSaleDTO result = fixture.toDTO();

		assertNotNull(result);
		assertEquals(1, result.getPrice());
		assertEquals(null, result.getObj());
	}

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsOnSaleTest.class);
	}
}