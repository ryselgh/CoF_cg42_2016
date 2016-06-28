package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;

public class ShiftCouncilSpeedDTOTest {
	
	CouncilorDTO councilDTO;
	
	@Before
	public void setUp()
		throws Exception {
		
		//in the setUp there is the councilor used in the methods of the test
	
		councilDTO = new CouncilorDTO();
		councilDTO.setColor(CouncilorColor.BLACK);
	}
	@Test
	public void testShiftCouncilSpeedDTO_1()
		throws Exception {

		ShiftCouncilSpeedDTO result = new ShiftCouncilSpeedDTO();

		assertNotNull(result);
		
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBalconyIndex()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(councilDTO);
		fixture.setBalconyIndex(1);

		int result = fixture.getBalconyIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetCouncilor()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(councilDTO);
		fixture.setBalconyIndex(1);

		CouncilorDTO result = fixture.getCouncilor();

		assertNotNull(result);
		assertEquals(CouncilorColor.BLACK, result.getColor());
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ShiftCouncilSpeedDTOTest.class);
	}
}