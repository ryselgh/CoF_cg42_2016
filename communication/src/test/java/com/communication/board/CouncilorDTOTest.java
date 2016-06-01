package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.CouncilorColor;

public class CouncilorDTOTest {
	
	
//	@Test
//	public void testCouncilorDTO_1()
//		throws Exception {
//		CouncilorDTO result = new CouncilorDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetColor_1()
		throws Exception {
		CouncilorDTO fixture = new CouncilorDTO();
		fixture.setColor(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}

//	@Test
//	public void testSetColor_1()
//		throws Exception {
//		CouncilorDTO fixture = new CouncilorDTO();
//		fixture.setColor(CouncilorColor.BLACK);
//		CouncilorColor color = CouncilorColor.BLACK;
//
//		fixture.setColor(color);
//
//	}
//
//	@Before
//	public void setUp()
//		throws Exception {
//	}
//
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CouncilorDTOTest.class);
	}
}