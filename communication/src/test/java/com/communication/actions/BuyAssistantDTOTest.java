package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class BuyAssistantDTOTest {
	@Test
	public void testBuyAssistantDTO()
		throws Exception {

		BuyAssistantDTO result = new BuyAssistantDTO();

		assertNotNull(result);
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuyAssistantDTOTest.class);
	}
}