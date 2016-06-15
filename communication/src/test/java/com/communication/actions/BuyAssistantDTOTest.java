package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class BuyAssistantDTOTest {
	@Test
	public void testBuyAssistantDTO_1()
		throws Exception {

		BuyAssistantDTO result = new BuyAssistantDTO();

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
		new org.junit.runner.JUnitCore().run(BuyAssistantDTOTest.class);
	}
}