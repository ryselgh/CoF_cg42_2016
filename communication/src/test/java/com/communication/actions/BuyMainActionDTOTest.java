package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class BuyMainActionDTOTest {
	@Test
	public void testBuyMainActionDTO()
		throws Exception {

		BuyMainActionDTO result = new BuyMainActionDTO();

		assertNotNull(result);
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyMainActionDTOTest.class);
	}
}