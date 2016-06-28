package com.communication.decks;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.CouncilorColor;

public class PoliticsCardDTOTest {
	
	
	
	@Test
	public void testPoliticsCardDTO()
		throws Exception {
		PoliticsCardDTO result = new PoliticsCardDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetColor()
		throws Exception {
		PoliticsCardDTO fixture = new PoliticsCardDTO();
		fixture.setColor(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsCardDTOTest.class);
	}
}