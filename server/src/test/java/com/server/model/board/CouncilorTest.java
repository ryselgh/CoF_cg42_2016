package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;

public class CouncilorTest {
	
	
	

	@Before
	public void setUp()
		throws Exception {
	}
	
	
	@Test
	public void testCouncilor_1()
		throws Exception {
		CouncilorColor cc = CouncilorColor.BLACK;

		Councilor result = new Councilor(cc);

		assertNotNull(result);
	}

	
	@Test(expected= NullPointerException.class)
	public void aCouncilorCannotBeUncoloured(){
		
		Councilor result = new Councilor(null);
		
		
	}
//	@Test
//	public void testEquals_1()
//		throws Exception {
//		Councilor fixture = new Councilor(CouncilorColor.BLACK);
//		CouncilorDTO cDTO = new CouncilorDTO();
//		cDTO.setColor(CouncilorColor.BLACK);
//
//		boolean result = fixture.equals(cDTO);
//
//		assertEquals(true, result);
//	}
//
	@Test
	public void testEquals_2()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.BLACK);
		CouncilorDTO cDTO = new CouncilorDTO();
		cDTO.setColor(CouncilorColor.BLACK);

		boolean result = fixture.equalsDTO(cDTO);

		assertEquals(true, result);
	}

	@Test(expected = NullPointerException.class)
	public void testEquals_3()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.BLACK);
		CouncilorDTO cDTO = null;

		boolean result = fixture.equalsDTO(cDTO);

		assertTrue(result);
	}

	@Test
	public void testGetCouncilorColor_1()
		throws Exception {
		Councilor councilor = new Councilor(CouncilorColor.BLACK);

		CouncilorColor result = councilor.getCouncilorColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.BLACK);

		CouncilorDTO result = fixture.toDTO();

		assertNotNull(result);
		assertTrue(result instanceof CouncilorDTO);
	}


//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CouncilorTest.class);
	}
}