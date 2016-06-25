package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

public class BalconyDTOTest {
	
	@Test
	public void testBalconyDTO()
		throws Exception {
		BalconyDTO result = new BalconyDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetCouncilor()
		throws Exception {
		BalconyDTO fixture = new BalconyDTO();
		fixture.setCouncilor(new CouncilorDTO[4] );

		CouncilorDTO[] result = fixture.getCouncilor();

		assertNotNull(result);
		assertEquals(4, result.length);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BalconyDTOTest.class);
	}
}