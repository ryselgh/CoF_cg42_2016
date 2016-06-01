package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;
import com.server.values.CouncilorColor;

public class PoliticsCardTest {
	@Test
	public void testPoliticsCard_1()
		throws Exception {
		CouncilorColor c = CouncilorColor.BLACK;

		PoliticsCard result = new PoliticsCard(c);

		assertNotNull(result);
	}
	
	@Test (expected= NullPointerException.class)
	public void testIfYouCanCreateNullPoliticsCards(){
		new PoliticsCard(null);
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsCardTest.class);
	}
}