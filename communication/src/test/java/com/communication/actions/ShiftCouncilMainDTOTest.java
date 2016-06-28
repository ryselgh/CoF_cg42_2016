package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;

public class ShiftCouncilMainDTOTest {
	
	
CouncilorDTO councilDTO;
	
	@Before
	public void setUp()
		throws Exception {
		
		//in the setUp there is the councilor used in the methods of the test
		
		councilDTO = new CouncilorDTO();
		councilDTO.setColor(CouncilorColor.BLACK);
	}
	
	
	@Test
	public void testShiftCouncilMainDTO()
		throws Exception {

		ShiftCouncilMainDTO result = new ShiftCouncilMainDTO();

		assertNotNull(result);
		
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBalconyIndex()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setCouncilor(councilDTO);

		int result = fixture.getBalconyIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetCouncilor()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setCouncilor(councilDTO);

		CouncilorDTO result = fixture.getCouncilor();

		assertNotNull(result);
		assertEquals(CouncilorColor.BLACK, result.getColor());
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ShiftCouncilMainDTOTest.class);
	}
}