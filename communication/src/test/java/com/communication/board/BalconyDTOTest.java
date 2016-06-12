package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

public class BalconyDTOTest {
	
	
	
	@Before
	public void setUp()
		throws Exception {
	}
	
	
	
//	@Test
//	public void testBalconyDTO_1()
//		throws Exception {
//		BalconyDTO result = new BalconyDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetCouncilor_1()
		throws Exception {
		BalconyDTO fixture = new BalconyDTO();
		fixture.setCouncilor(new CouncilorDTO[4] );

		CouncilorDTO[] result = fixture.getCouncilor();

		assertNotNull(result);
		assertEquals(4, result.length);
	}

//	@Test
//	public void testSetCouncilor_1()
//		throws Exception {
//		BalconyDTO fixture = new BalconyDTO();
//		fixture.setCouncilor(new CouncilorDTO[] {});
//		CouncilorDTO[] councilor = new CouncilorDTO[] {};
//
//		fixture.setCouncilor(councilor);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BalconyDTOTest.class);
	}
}