package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.market.PermitOnSaleDTO;
import com.server.model.board.Bonus;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Player;

public class PermitOnSaleTest {
	String UID="";
	@Test
	public void testPermitOnSale_1()
		throws Exception {
		Player pl = new Player("1");
		PermitsCard pc = new PermitsCard(new Bonus[] {}, new String[] {});
		int pr = 1;

		PermitOnSale result = new PermitOnSale(pl, pc, pr,UID);

		assertNotNull(result);
		assertEquals("Permit card: {[Letters= ], [Bonus= ]}\nPrice= 1\n\n", result.printDetails());
		assertEquals(1, result.getPrice());
	}

	@Test
	public void testGetPrice_1()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(new Player("1"), new PermitsCard(new Bonus[] {}, new String[] {}), 1,UID);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	@Test
	public void testObtain_1()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(new Player("1"), new PermitsCard(new Bonus[] {}, new String[] {}), 1,UID);
		Player buyer = new Player("1");
		buyer.setCoins(1);

		fixture.obtain(buyer);

	}

	@Test
	public void testPrintDetails_1()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(new Player("1"), new PermitsCard(new Bonus[] {}, new String[] {}), 1,UID);

		String result = fixture.printDetails();

		assertEquals("Permit card: {[Letters= ], [Bonus= ]}\nPrice= 1\n\n", result);
	}

	@Test
	public void testPrintDetails_2()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(new Player("1"), new PermitsCard(new Bonus[] {}, new String[] {}), 1,UID);

		String result = fixture.printDetails();

		assertEquals("Permit card: {[Letters= ], [Bonus= ]}\nPrice= 1\n\n", result);
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		PermitOnSale fixture = new PermitOnSale(new Player("1"), new PermitsCard(new Bonus[] {}, new String[] {}), 1,UID);

		PermitOnSaleDTO result = fixture.toDTO();

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
		new org.junit.runner.JUnitCore().run(PermitOnSaleTest.class);
	}
}