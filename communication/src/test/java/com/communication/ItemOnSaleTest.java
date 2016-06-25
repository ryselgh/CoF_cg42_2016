package com.communication;

import org.junit.*;
import static org.junit.Assert.*;

public class ItemOnSaleTest {
	
	Object obj;
	
	@Before
	public void setUp()
		throws Exception {
		obj = new Object();
	}
	
	@Test
	public void testItemOnSale()
		throws Exception {
		int pr = 1;
		

		ItemOnSale result = new ItemOnSale(pr, obj);

		assertNotNull(result);
		assertEquals(obj, result.getObj());
		assertEquals(1, result.getPrice());
	}
	
	// getters and setters are tested together


	@Test
	public void testGetObj()
		throws Exception {
		ItemOnSale fixture = new ItemOnSale(1, obj);

		Object result = fixture.getObj();

		assertNotNull(result);
	}

	@Test
	public void testGetPrice()
		throws Exception {
		ItemOnSale fixture = new ItemOnSale(1, obj);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ItemOnSaleTest.class);
	}
}