package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class BuyMainActionDTOTest {
	@Test
	public void testBuyMainActionDTO_1()
		throws Exception {

		BuyMainActionDTO result = new BuyMainActionDTO();

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
		new org.junit.runner.JUnitCore().run(BuyMainActionDTOTest.class);
	}
}