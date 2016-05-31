package com.communication.decks;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.CouncilorColor;

public class PoliticsCardDTOTest {
	
	@Before
	public void setUp()
		throws Exception {
	}
//	@Test
//	public void testPoliticsCardDTO_1()
//		throws Exception {
//		PoliticsCardDTO result = new PoliticsCardDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetColor_1()
		throws Exception {
		PoliticsCardDTO fixture = new PoliticsCardDTO();
		fixture.setColor(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}

//	@Test
//	public void testSetColor_1()
//		throws Exception {
//		PoliticsCardDTO fixture = new PoliticsCardDTO();
//		fixture.setColor(CouncilorColor.BLACK);
//		CouncilorColor color = CouncilorColor.BLACK;
//
//		fixture.setColor(color);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsCardDTOTest.class);
	}
}