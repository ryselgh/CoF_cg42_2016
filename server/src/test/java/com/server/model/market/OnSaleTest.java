package com.server.model.market;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.gamelogic.PlayerDTO;
import com.communication.market.OnSaleDTO;
import com.server.model.gamelogic.Player;

public class OnSaleTest {
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
		Player p = new Player(1);
		Object o = new Object();
		int pr = 1;

		OnSale result = new OnSale(p, o, pr);

		assertNotNull(result);
		assertEquals("", result.printDetails());
		assertEquals(0, result.getPrice());
	}

	@Test
	public void testEqualsDTO_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);
		OnSaleDTO osDTO = new OnSaleDTO();
		osDTO.setSeller(new PlayerDTO());

		boolean result = fixture.equalsDTO(osDTO);

		assertEquals(false, result);
	}

	@Test
	public void testEqualsDTO_2()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);
		OnSaleDTO osDTO = new OnSaleDTO();
		osDTO.setSeller(new PlayerDTO());

		boolean result = fixture.equalsDTO(osDTO);

		assertEquals(false, result);
	}

	@Test
	public void testEqualsDTO_3()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);
		OnSaleDTO osDTO = new OnSaleDTO();
		osDTO.setPrice(1);
		osDTO.setSeller(new PlayerDTO());

		boolean result = fixture.equalsDTO(osDTO);

		assertEquals(false, result);
	}

	@Test
	public void testEqualsDTO_4()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);
		OnSaleDTO osDTO = new OnSaleDTO();
		osDTO.setPrice(1);
		osDTO.setSeller(new PlayerDTO());

		boolean result = fixture.equalsDTO(osDTO);

		assertEquals(false, result);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void testEqualsDTO_5()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);
		OnSaleDTO osDTO = null;

		boolean result = fixture.equalsDTO(osDTO);

		assertTrue(result);
	}

	@Test
	public void testGetPrice_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);

		int result = fixture.getPrice();

		assertEquals(0, result);
	}

	@Test
	public void testObtain_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);
		Player buyer = new Player(1);

		fixture.obtain(buyer);

	}

	@Test
	public void testPrintDetails_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);

		String result = fixture.printDetails();

		assertEquals("", result);
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		OnSale fixture = new OnSale(new Player(1), new Object(), 1);

		OnSaleDTO result = fixture.toDTO();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ClassCastException: java.lang.Object cannot be cast to com.communication.SerObject
		//       at com.server.model.market.OnSale.toDTO(OnSale.java:63)
		assertNotNull(result);
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