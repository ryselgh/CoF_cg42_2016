package com.server.model.board;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.RegionDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.communication.values.RegionName;

public class RegionTest {
	
	String[] close;
	String[] close2;
	Bonus[] b;
	City city;
	City city2;
	BonusCard bonC;
	
	@Before
	public void setUp()
		throws Exception {
		
		close = new String[1];
		close[0]= "Castrum";
		close2 = new String[3];
		close2[0] = "Dorful";
		close2[1] = "Framek";
		close2[2] = "Arkon";
		b = new Bonus[1]; 
		b[0] = new Bonus(BonusType.POINT, 1);
		city = new City("Arkon", CityColor.BLUE, close, 3, new BonusToken(b) );
		city2 = new City("Castrum", CityColor.BLUE, close2,3,new BonusToken(b));
		bonC= new BonusCard(new Bonus(BonusType.POINT,3));
		
	}
	
	
	@Test
	public void testRegion_1()
		throws Exception {
		RegionName n = RegionName.HILL;

		Region result = new Region(n);

		assertNotNull(result);
		
	}

	@Test
	public void testAddCity_1()
		throws Exception {
		Region fixture = new Region(RegionName.HILL);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));
		fixture.addCity(city);
		fixture.addCity(city2);
		
		assertEquals(fixture.getCities().size(),2);
		

		

	}

	@Test
	public void testGetBonus_1()
		throws Exception {
		Region fixture = new Region(RegionName.HILL);
		fixture.setBonus(bonC);
		fixture.addCity(city);
		fixture.addCity(city2);

		BonusCard result = fixture.getBonus();

		assertEquals(result, bonC);
	}

	@Test
	public void testGetCities_1()
		throws Exception {
		Region fixture = new Region(RegionName.HILL);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));
		fixture.addCity(city);
		fixture.addCity(city2);
		
		

		ArrayList<City> result = fixture.getCities();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetName_1()
		throws Exception {
		Region fixture = new Region(RegionName.HILL);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));
		fixture.addCity(city);
		fixture.addCity(city2);
		RegionName result = fixture.getName();

		assertNotNull(result);
		assertEquals("HILL", result.name());
		assertEquals("HILL", result.toString());
		
	}

//	@Test
//	public void testSetBonus_1()
//		throws Exception {
//		Region fixture = new Region(RegionName.HILL);
//		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));
//		fixture.addCity(new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));
//		BonusCard b = new BonusCard(new Bonus(BonusType.ASSISTANT, 1));
//
//		fixture.setBonus(b);
//
//	}

	@Test
	public void testToDTO_1()
		throws Exception {
		Region fixture = new Region(RegionName.HILL);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));
		fixture.addCity(new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {})));

		RegionDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.City.toDTO(City.java:27)
		//       at com.server.model.board.Region.toDTO(Region.java:23)
		assertNotNull(result);
		assertTrue(result instanceof RegionDTO);
	}

	@Test
	public void testToDTO_2()
		throws Exception {
		Region fixture = new Region(RegionName.HILL);
		fixture.setBonus(new BonusCard(new Bonus(BonusType.ASSISTANT, 1)));

		RegionDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		assertNotNull(result);
	}

	

//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(RegionTest.class);
	}
}