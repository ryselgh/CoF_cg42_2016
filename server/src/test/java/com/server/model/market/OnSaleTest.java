package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.gamelogic.PlayerDTO;
import com.communication.market.OnSaleDTO;
import com.server.model.gamelogic.Player;

public class OnSaleTest {
	String UID="";
	@Test
	public void testOnSale_1()
		throws Exception {

		OnSale result = new OnSale();

		assertNotNull(result);
		assertEquals("", result.printDetails());
		assertEquals(0, result.getPrice());
	}

	@Test
	public void testOnSale_2()
		throws Exception {
		Player p = new Player("1");
		Object o = new Object();
		int pr = 1;

		OnSale result = new OnSale(p, o, pr,UID);

		assertNotNull(result);
		assertEquals("", result.printDetails());
		assertEquals(0, result.getPrice());
	}


	@Test
	public void testGetPrice_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player("1"), new Object(), 1,UID);

		int result = fixture.getPrice();

		assertEquals(0, result);
	}

	@Test
	public void testObtain_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player("1"), new Object(), 1,UID);
		Player buyer = new Player("1");

		fixture.obtain(buyer);

	}

	@Test
	public void testPrintDetails_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player("1"), new Object(), 1,UID);

		String result = fixture.printDetails();

		assertEquals("", result);
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
		new org.junit.runner.JUnitCore().run(OnSaleTest.class);
	}
}