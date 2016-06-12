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
		councilDTO = new CouncilorDTO();
		councilDTO.setColor(CouncilorColor.BLACK);
	}
	
	
	@Test
	public void testShiftCouncilMainDTO_1()
		throws Exception {

		ShiftCouncilMainDTO result = new ShiftCouncilMainDTO();

		assertNotNull(result);
		assertEquals(null, result.getCouncilor());
		assertEquals(0, result.getBalconyIndex());
	}

	@Test
	public void testGetBalconyIndex_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setCouncilor(councilDTO);

		int result = fixture.getBalconyIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetCouncilor_1()
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