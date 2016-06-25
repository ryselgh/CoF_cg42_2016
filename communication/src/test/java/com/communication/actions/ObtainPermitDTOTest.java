package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;

public class ObtainPermitDTOTest {
	
	PoliticsCardDTO[] polDTO;
	
	@Before
	public void setUp()
		throws Exception {
		
	//in the setUp there are the 4 cards used in the methods of the test
		
		polDTO = new PoliticsCardDTO[4];
		polDTO[0]= new PoliticsCardDTO();
		polDTO[0].setColor(CouncilorColor.JOLLY);
		polDTO[1]= new PoliticsCardDTO();
		polDTO[1].setColor(CouncilorColor.JOLLY);
		polDTO[2]= new PoliticsCardDTO();
		polDTO[2].setColor(CouncilorColor.JOLLY);
		polDTO[3]= new PoliticsCardDTO();
		polDTO[3].setColor(CouncilorColor.JOLLY);
	}

	@Test
	public void testObtainPermitDTO()
		throws Exception {

		ObtainPermitDTO result = new ObtainPermitDTO();

		assertNotNull(result);
		
	}
	
	// getters and setters are tested together

	@Test
	public void testGetPolitics()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setSlot(1);
		fixture.setPolitics(polDTO);
		fixture.setRegionIndex(1);

		PoliticsCardDTO[] result = fixture.getPolitics();

		assertNotNull(result);
		assertEquals(4, result.length);
	}

	@Test
	public void testGetRegionIndex()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setSlot(1);
		fixture.setPolitics(polDTO);
		fixture.setRegionIndex(1);

		int result = fixture.getRegionIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetSlot()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setSlot(1);
		fixture.setPolitics(polDTO);
		fixture.setRegionIndex(1);

		int result = fixture.getSlot();

		assertEquals(1, result);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ObtainPermitDTOTest.class);
	}
}