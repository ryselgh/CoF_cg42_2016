package com.communication.decks;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import com.communication.values.CouncilorColor;

import static org.junit.Assert.*;

public class PoliticsDeckDTOTest {
	
	PoliticsCardDTO[] politicsDeckDTO;
	ArrayList<PoliticsCardDTO> politicsDeck; 
	PoliticsCardDTO[] garbageDTO;
	ArrayList<PoliticsCardDTO> garbage; 
	
	
	@Before
	public void setUp()
		throws Exception {
		
		//all you need for the methods of the test
		
		politicsDeckDTO= new  PoliticsCardDTO[12];
		for (PoliticsCardDTO pc: politicsDeckDTO){
			pc= new PoliticsCardDTO();
			pc.setColor(CouncilorColor.ORANGE);
		}
		
		politicsDeck = new ArrayList<PoliticsCardDTO>(Arrays.asList(politicsDeckDTO));
		
		garbageDTO= new PoliticsCardDTO[3];
		for (PoliticsCardDTO pc: garbageDTO){
			pc= new PoliticsCardDTO();
			pc.setColor(CouncilorColor.PURPLE);
		}
		
		garbage = new ArrayList<PoliticsCardDTO>(Arrays.asList(garbageDTO));
		
	}
	
	
	@Test
	public void testPoliticsDeckDTO()
		throws Exception {
		PoliticsDeckDTO result = new PoliticsDeckDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetGarbage()
		throws Exception {
		PoliticsDeckDTO fixture = new PoliticsDeckDTO();
		fixture.setPoliticsDeck(politicsDeck);
		fixture.setGarbage(garbage);

		ArrayList<PoliticsCardDTO> result = fixture.getGarbage();

		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testGetPoliticsDeck()
		throws Exception {
		PoliticsDeckDTO fixture = new PoliticsDeckDTO();
		fixture.setPoliticsDeck(politicsDeck);
		fixture.setGarbage(new ArrayList<PoliticsCardDTO>());

		ArrayList<PoliticsCardDTO> result = fixture.getPoliticsDeck();

		assertNotNull(result);
		assertEquals(12, result.size());
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsDeckDTOTest.class);
	}
}