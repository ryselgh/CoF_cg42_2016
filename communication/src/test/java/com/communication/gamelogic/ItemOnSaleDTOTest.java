package com.communication.gamelogic;

import org.junit.*;
import static org.junit.Assert.*;

public class ItemOnSaleDTOTest {
	
	Object obj;
	
	@Before
	public void setUp()
		throws Exception {
		
		obj = new Object();
		
		
		
	}
	
	@Test
	public void testItemOnSaleDTO()
		throws Exception {
		ItemOnSaleDTO result = new ItemOnSaleDTO();
		assertNotNull(result);
	}
	// getters and setters are tested together

	@Test
	public void testGetObj()
		throws Exception {
		ItemOnSaleDTO fixture = new ItemOnSaleDTO();
		fixture.setPrice(1);
		fixture.setObj(obj);

		Object result = fixture.getObj();

		assertEquals(result,obj);
	}

	@Test
	public void testGetPrice()
		throws Exception {
		ItemOnSaleDTO fixture = new ItemOnSaleDTO();
		fixture.setPrice(1);
		fixture.setObj(obj);

		int result = fixture.getPrice();

		assertEquals(1, result);
	}

	

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ItemOnSaleDTOTest.class);
	}
}