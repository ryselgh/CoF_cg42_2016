package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class ChangeCardsDTOTest {
	
	
	@Test
	public void testChangeCardsDTO()
		throws Exception {

		ChangeCardsDTO result = new ChangeCardsDTO();

		assertNotNull(result);
		assertEquals(0, result.getBalconyIndex());
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBalconyIndex()
		throws Exception {
		ChangeCardsDTO fixture = new ChangeCardsDTO();
		fixture.setBalconyIndex(1);

		int result = fixture.getBalconyIndex();

		assertEquals(1, result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ChangeCardsDTOTest.class);
	}
}