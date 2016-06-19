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
		closeCities=new String[2];
		closeCities[0]="Osium";
		closeCities[1]="Karl";
		
		b = new BonusDTO[2];
		b[0]=new BonusDTO();
		b[0].setQuantity(1);
		b[0].setType(BonusType.ASSISTANT);
		b[1]=new BonusDTO();
		b[1].setQuantity(2);
		b[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(b);
		
		fixture = new CityDTO();
		fixture.setName("Juvelar");
		fixture.setColor(CityColor.BLUE);
		fixture.setCloseCities(closeCities);
		
		fixture.setSlot(new EmporiumDTO[10] );
		fixture.setPlayerNum(1);
		fixture.setToken(btDTO);
		
		
	}

//	@Test
//	public void testCityDTO_1()
//		throws Exception {
//		CityDTO result = new CityDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetCloseCities_1()
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
	public void testGetName_1()
		throws Exception {
		

		String result = fixture.getName();

		assertEquals("Juvelar", result);
	}

	@Test
	public void testGetPlayerNum_1()
		throws Exception {
		

		int result = fixture.getPlayerNum();

		assertEquals(1, result);
	}

	@Test
	public void testGetSlot_1()
		throws Exception {
		
		EmporiumDTO[] result = fixture.getSlot();

		assertNotNull(result);
		assertEquals(10, result.length);
	}

	@Test
	public void testGetToken_1()
		throws Exception {
		

		BonusTokenDTO result = fixture.getToken();

		assertNotNull(result);
		assertEquals(b, result.getBonus());
	}
	
	@Test
	public void testCityEqualsDTO(){
		boolean result = fixture.equalsDTO(fixture);
		assertTrue(result);
		
	}
	
	@Test
	public void testCityEqualsDTO2(){
		CityDTO city = new  CityDTO();
		city.setName("Osium");
		boolean result = fixture.equalsDTO(city);
		assertFalse(result);
		
	}
//	@Test
//	public void testSetCloseCities_1()
//		throws Exception {
//		CityDTO fixture = new CityDTO();
//		fixture.setName("");
//		fixture.setColor(CityColor.BLUE);
//		fixture.setCloseCities(new String[] {});
//		fixture.setSlot(new EmporiumDTO[] {});
//		fixture.setPlayerNum(1);
//		fixture.setToken(new BonusTokenDTO());
//		String[] closeCities = new String[] {};
//
//		fixture.setCloseCities(closeCities);
//
//	}
//
//	@Test
//	public void testSetColor_1()
//		throws Exception {
//		CityDTO fixture = new CityDTO();
//		fixture.setName("");
//		fixture.setColor(CityColor.BLUE);
//		fixture.setCloseCities(new String[] {});
//		fixture.setSlot(new EmporiumDTO[] {});
//		fixture.setPlayerNum(1);
//		fixture.setToken(new BonusTokenDTO());
//		CityColor color = CityColor.BLUE;
//
//		fixture.setColor(color);
//
//	}
//
//	@Test
//	public void testSetName_1()
//		throws Exception {
//		CityDTO fixture = new CityDTO();
//		fixture.setName("");
//		fixture.setColor(CityColor.BLUE);
//		fixture.setCloseCities(new String[] {});
//		fixture.setSlot(new EmporiumDTO[] {});
//		fixture.setPlayerNum(1);
//		fixture.setToken(new BonusTokenDTO());
//		String name = "";
//
//		fixture.setName(name);
//
//	}
//
//	@Test
//	public void testSetPlayerNum_1()
//		throws Exception {
//		CityDTO fixture = new CityDTO();
//		fixture.setName("");
//		fixture.setColor(CityColor.BLUE);
//		fixture.setCloseCities(new String[] {});
//		fixture.setSlot(new EmporiumDTO[] {});
//		fixture.setPlayerNum(1);
//		fixture.setToken(new BonusTokenDTO());
//		int playerNum = 1;
//
//		fixture.setPlayerNum(playerNum);
//
//	}
//
//	@Test
//	public void testSetSlot_1()
//		throws Exception {
//		CityDTO fixture = new CityDTO();
//		fixture.setName("");
//		fixture.setColor(CityColor.BLUE);
//		fixture.setCloseCities(new String[] {});
//		fixture.setSlot(new EmporiumDTO[] {});
//		fixture.setPlayerNum(1);
//		fixture.setToken(new BonusTokenDTO());
//		EmporiumDTO[] slot = new EmporiumDTO[] {};
//
//		fixture.setSlot(slot);
//
//	}
//
//	@Test
//	public void testSetToken_1()
//		throws Exception {
//		CityDTO fixture = new CityDTO();
//		fixture.setName("");
//		fixture.setColor(CityColor.BLUE);
//		fixture.setCloseCities(new String[] {});
//		fixture.setSlot(new EmporiumDTO[] {});
//		fixture.setPlayerNum(1);
//		fixture.setToken(new BonusTokenDTO());
//		BonusTokenDTO token = new BonusTokenDTO();
//
//		fixture.setToken(token);
//
//	}
//
//	
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CityDTOTest.class);
	}
}