package com.communication.board;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.communication.values.CityColor;



public class ColorGroupDTOTest {
	
	CityDTO[] cities;
	CityDTO fixture1;
	CityDTO fixture2;
	String[] closeCities;
	String[] closeCities2;
	BonusDTO[] b;
	BonusDTO[] b2;
	BonusDTO bonus;
	ArrayList<CityDTO> colCities;
	BonusCardDTO bcDTO;
	
	
	@Before
	public void setUp()
		throws Exception {
		
		//all you need to test methods
		
		//bonus on the bonsCard
		bonus = new BonusDTO();
		bonus.setQuantity(1);
		bonus.setType(BonusType.POINT);
		//the bonusCard
		bcDTO = new BonusCardDTO();
		bcDTO.setBonus(bonus);
		//theCitiesWithTheSameColor; a list of 2 cities
		cities=new CityDTO[2];
		cities[0]= fixture1;
		cities[1]= fixture2;
		colCities = new ArrayList<CityDTO>(Arrays.asList(cities));
		//theCloseCitiesOfTheCities
		closeCities=new String [2];
		closeCities[0]="Indur";
		closeCities[1]="Hellar";
		closeCities2=new String[2];
		closeCities2[0]="Kultos";
		closeCities2[1]="Juvelar";
		
		//theBonusTokenOnTheFirstCity
		b = new BonusDTO[2];
		b[0]=new BonusDTO();
		b[0].setQuantity(1);
		b[0].setType(BonusType.ASSISTANT);
		b[1]=new BonusDTO();
		b[1].setQuantity(2);
		b[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(b);
		//theBonusTokenOnTheSecondCity
		b2 = new BonusDTO[2];
		b2[0]=new BonusDTO();
		b2[0].setQuantity(1);
		b2[0].setType(BonusType.COIN);
		b2[1]=new BonusDTO();
		b2[1].setQuantity(2);
		b2[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO2 = new BonusTokenDTO();
		btDTO2.setBonus(b2);
		//the first city
		fixture1 = new CityDTO();
		fixture1.setName("Juvelar");
		fixture1.setColor(CityColor.BLUE);
		fixture1.setCloseCities(closeCities);
		fixture1.setSlot(new EmporiumDTO[10] );
		fixture1.setPlayerNum(1);
		fixture1.setToken(btDTO);
		
		//the second city
		fixture2 = new CityDTO();
		fixture2.setName("Indur");
		fixture2.setColor(CityColor.BLUE);
		fixture2.setCloseCities(closeCities2);
		fixture2.setSlot(new EmporiumDTO[10] );
		fixture2.setPlayerNum(2);
		fixture2.setToken(btDTO2);
		
		
		
		
	}
	
	
	@Test
	public void testColorGroupDTO()
		throws Exception {
		ColorGroupDTO result = new ColorGroupDTO();
		result.setCities(colCities);
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBc()
		throws Exception {
		ColorGroupDTO fixture = new ColorGroupDTO();
		
		fixture.setColor(CityColor.BLUE);
		fixture.setBc(bcDTO);
		fixture.setCities(colCities);

		BonusCardDTO result = fixture.getBonusCard();

		assertNotNull(result);
		assertEquals(bonus, result.getBonus());
	}

	@Test
	public void testGetCities()
		throws Exception {
		ColorGroupDTO fixture = new ColorGroupDTO();
		fixture.setColor(CityColor.BLUE);
		fixture.setBc(bcDTO);
		fixture.setCities(colCities);

		ArrayList<CityDTO> result = fixture.getCities();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetColor()
		throws Exception {
		ColorGroupDTO fixture = new ColorGroupDTO();
		fixture.setColor(CityColor.BLUE);
		fixture.setBc(bcDTO);
		fixture.setCities(colCities);

		CityColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLUE", result.name());
		assertEquals("BLUE", result.toString());
		
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ColorGroupDTOTest.class);
	}
}