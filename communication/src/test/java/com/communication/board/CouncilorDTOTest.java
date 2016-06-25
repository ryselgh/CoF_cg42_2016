package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.CouncilorColor;

public class CouncilorDTOTest {
	
	
	@Test
	public void testCouncilorDTO()
		throws Exception {
		CouncilorDTO result = new CouncilorDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetColor()
		throws Exception {
		CouncilorDTO fixture = new CouncilorDTO();
		fixture.setColor(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CouncilorDTOTest.class);
	}
}