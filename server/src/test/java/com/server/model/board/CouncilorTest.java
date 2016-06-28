package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;

public class CouncilorTest {
	
	
	
	
	@Test
	public void testCouncilor()
		throws Exception {
		CouncilorColor cc = CouncilorColor.BLACK;

		Councilor result = new Councilor(cc);

		assertNotNull(result);
	}

	
	@Test(expected= NullPointerException.class)
	public void aCouncilorCannotBeUncoloured(){
		
		Councilor result = new Councilor(null);
		
		
	}

	@Test
	public void testEqualsReturnTrue()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.BLACK);
		CouncilorDTO cDTO = new CouncilorDTO();
		cDTO.setColor(CouncilorColor.BLACK);

		boolean result = fixture.equalsDTO(cDTO);

		assertEquals(true, result);
	}
	
	@Test
	public void testEqualsReturnFalse()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.PINK);
		CouncilorDTO cDTO = new CouncilorDTO();
		cDTO.setColor(CouncilorColor.BLACK);

		boolean result = fixture.equalsDTO(cDTO);

		assertEquals(false, result);
	}

	@Test(expected = NullPointerException.class)
	public void testEqualsThrowExceptionIfSomethingIsFalse()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.BLACK);
		CouncilorDTO cDTO = null;

		fixture.equalsDTO(cDTO);

		
	}

	@Test
	public void testGetCouncilorColor()
		throws Exception {
		Councilor councilor = new Councilor(CouncilorColor.BLACK);

		CouncilorColor result = councilor.getCouncilorColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}

	@Test
	public void testToDTO()
		throws Exception {
		Councilor fixture = new Councilor(CouncilorColor.BLACK);

		CouncilorDTO result = fixture.toDTO();

		assertNotNull(result);
		assertTrue(result instanceof CouncilorDTO);
	}




	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CouncilorTest.class);
	}
}