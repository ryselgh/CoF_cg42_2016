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
	public void testItemOnSaleDTO_1()
		throws Exception {
		ItemOnSaleDTO result = new ItemOnSaleDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetObj_1()
		throws Exception {
		ItemOnSaleDTO fixture = new ItemOnSaleDTO();
		fixture.setPrice(1);
		fixture.setObj(obj);

		Object result = fixture.getObj();

		assertEquals(result,obj);
	}

	@Test
	public void testGetPrice_1()
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