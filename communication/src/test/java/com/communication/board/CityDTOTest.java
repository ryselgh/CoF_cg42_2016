package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.communication.values.CityColor;

public class CityDTOTest {
	
	
	CityDTO fixture;
	String[] closeCities;
	BonusDTO[] b;
	
	@Before
	public void setUp()
		throws Exception {
		
		// all you need to test the methods
		
		//the closeCities
		closeCities=new String[2];
		closeCities[0]="Osium";
		closeCities[1]="Karl";
		//bonuses on the BonusToken
		b = new BonusDTO[2];
		b[0]=new BonusDTO();
		b[0].setQuantity(1);
		b[0].setType(BonusType.ASSISTANT);
		b[1]=new BonusDTO();
		b[1].setQuantity(2);
		b[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(b);
		
		//the City
		fixture = new CityDTO();
		fixture.setName("Juvelar");
		fixture.setColor(CityColor.BLUE);
		fixture.setCloseCities(closeCities);
		fixture.setSlot(new EmporiumDTO[10] );
		fixture.setPlayerNum(1);
		fixture.setToken(btDTO);
		
		
	}

	@Test
	public void testCityDTO()
		throws Exception {
		CityDTO result = new CityDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetCloseCities()
		throws Exception {
		
		String[] result = fixture.getCloseCities();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		

		CityColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLUE", result.name());
		assertEquals("BLUE", result.toString());
		
	}

	@Test
	public void testGetName()
		throws Exception {
		

		String result = fixture.getName();

		assertEquals("Juvelar", result);
	}

	@Test
	public void testGetPlayerNum()
		throws Exception {
		

		int result = fixture.getPlayerNum();

		assertEquals(1, result);
	}

	@Test
	public void testGetSlot()
		throws Exception {
		
		EmporiumDTO[] result = fixture.getSlot();

		assertNotNull(result);
		assertEquals(10, result.length);
	}

	@Test
	public void testGetToken()
		throws Exception {
		

		BonusTokenDTO result = fixture.getToken();

		assertNotNull(result);
		assertArrayEquals(b, result.getBonus());
	}
	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CityDTOTest.class);
	}
}