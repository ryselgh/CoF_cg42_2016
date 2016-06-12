package com.communication.decks;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import com.communication.values.CouncilorColor;

import static org.junit.Assert.*;

public class PoliticsDeckDTOTest {
	
	PoliticsCardDTO[] politicsDeckDTO;
	ArrayList<PoliticsCardDTO> politicsDeck; 
	@Before
	public void setUp()
		throws Exception {
		
		politicsDeckDTO= new  PoliticsCardDTO[12];
		for (PoliticsCardDTO pc: politicsDeckDTO){
			pc= new PoliticsCardDTO();
			pc.setColor(CouncilorColor.ORANGE);
		}
		
		politicsDeck = new ArrayList<PoliticsCardDTO>(Arrays.asList(politicsDeckDTO));
	}
	
	
//	@Test
//	public void testPoliticsDeckDTO_1()
//		throws Exception {
//		PoliticsDeckDTO result = new PoliticsDeckDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetGarbage_1()
		throws Exception {
		PoliticsDeckDTO fixture = new PoliticsDeckDTO();
		fixture.setPoliticsDeck(politicsDeck);
		fixture.setGarbage(new ArrayList());

		ArrayList<PoliticsCardDTO> result = fixture.getGarbage();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetPoliticsDeck_1()
		throws Exception {
		PoliticsDeckDTO fixture = new PoliticsDeckDTO();
		fixture.setPoliticsDeck(politicsDeck);
		fixture.setGarbage(new ArrayList());

		ArrayList<PoliticsCardDTO> result = fixture.getPoliticsDeck();

		assertNotNull(result);
		assertEquals(12, result.size());
	}

//	@Test
//	public void testSetGarbage_1()
//		throws Exception {
//		PoliticsDeckDTO fixture = new PoliticsDeckDTO();
//		fixture.setPoliticsDeck(new ArrayList());
//		fixture.setGarbage(new ArrayList());
//		ArrayList<PoliticsCardDTO> garbage = new ArrayList();
//
//		fixture.setGarbage(garbage);
//
//	}
//
//	@Test
//	public void testSetPoliticsDeck_1()
//		throws Exception {
//		PoliticsDeckDTO fixture = new PoliticsDeckDTO();
//		fixture.setPoliticsDeck(new ArrayList());
//		fixture.setGarbage(new ArrayList());
//		ArrayList<PoliticsCardDTO> politicsDeck = new ArrayList();
//
//		fixture.setPoliticsDeck(politicsDeck);
//
//	}

	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsDeckDTOTest.class);
	}
}