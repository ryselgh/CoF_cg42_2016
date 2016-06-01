package com.communication.board;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.communication.values.RegionName;

public class RegionDTOTest {
	
	CityDTO[] cities;
	CityDTO fixture1;
	CityDTO fixture2;
	String[] closeCities;
	String[] closeCities2;
	BonusDTO[] b;
	BonusDTO[] b2;
	BonusDTO bonus;
	ArrayList<CityDTO> regCities;
	BonusCardDTO bcDTO;
	
	
	@Before
	public void setUp()
		throws Exception {
		
		bonus = new BonusDTO();
		bonus.setQuantity(2);
		bonus.setType(BonusType.POINT);
		
		
		cities=new CityDTO[2];
		cities[0]= fixture1;
		cities[1]= fixture2;
		regCities = new ArrayList<CityDTO>(Arrays.asList(cities));

		closeCities=new String[2];
		closeCities[0]="Indur";
		closeCities[1]="Hellar";
		closeCities2=new String[2];
		closeCities2[0]="Kultos";
		closeCities2[1]="Juvelar";
		
		b = new BonusDTO[2];
		b[0]=new BonusDTO();
		b[0].setQuantity(1);
		b[0].setType(BonusType.ASSISTANT);
		b[1]=new BonusDTO();
		b[1].setQuantity(2);
		b[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(b);
		
		b2 = new BonusDTO[2];
		b2[0]=new BonusDTO();
		b2[0].setQuantity(1);
		b2[0].setType(BonusType.COIN);
		b2[1]=new BonusDTO();
		b2[1].setQuantity(2);
		b2[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO2 = new BonusTokenDTO();
		btDTO2.setBonus(b2);
		
		fixture1 = new CityDTO();
		fixture1.setName("Juvelar");
		fixture1.setColor(CityColor.BLUE);
		fixture1.setCloseCities(closeCities);
		fixture1.setSlot(new EmporiumDTO[10] );
		fixture1.setPlayerNum(1);
		fixture1.setToken(btDTO);
		
		
		fixture2 = new CityDTO();
		fixture2.setName("Indur");
		fixture2.setColor(CityColor.BLUE);
		fixture2.setCloseCities(closeCities2);
		fixture2.setSlot(new EmporiumDTO[10] );
		fixture2.setPlayerNum(2);
		fixture2.setToken(btDTO2);
		
		bcDTO = new BonusCardDTO();
		bcDTO.setBonus(bonus);
		
	}
	
//	@Test
//	public void testRegionDTO_1()
//		throws Exception {
//		RegionDTO result = new RegionDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetBonusCard_1()
		throws Exception {
		RegionDTO fixture = new RegionDTO();
		fixture.setName(RegionName.HILL);
		fixture.setCities(regCities);
		fixture.setBonusCard(bcDTO);

		BonusCardDTO result = fixture.getBonusCard();

		assertNotNull(result);
		assertEquals(bonus, result.getBonus());
	}

	@Test
	public void testGetCities_1()
		throws Exception {
		RegionDTO fixture = new RegionDTO();
		fixture.setName(RegionName.HILL);
		fixture.setCities(regCities);
		fixture.setBonusCard(bcDTO);

		ArrayList<CityDTO> result = fixture.getCities();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetName_1()
		throws Exception {
		RegionDTO fixture = new RegionDTO();
		fixture.setName(RegionName.HILL);
		fixture.setCities(regCities);
		fixture.setBonusCard(bcDTO);

		RegionName result = fixture.getName();

		assertNotNull(result);
		assertEquals("HILL", result.name());
		assertEquals("HILL", result.toString());
		
	}

//	@Test
//	public void testSetBonusCard_1()
//		throws Exception {
//		RegionDTO fixture = new RegionDTO();
//		fixture.setName(RegionName.HILL);
//		fixture.setCities(new ArrayList());
//		fixture.setBonusCard(new BonusCardDTO());
//		BonusCardDTO bc = new BonusCardDTO();
//
//		fixture.setBonusCard(bc);
//
//	}
//
//	@Test
//	public void testSetCities_1()
//		throws Exception {
//		RegionDTO fixture = new RegionDTO();
//		fixture.setName(RegionName.HILL);
//		fixture.setCities(new ArrayList());
//		fixture.setBonusCard(new BonusCardDTO());
//		ArrayList<CityDTO> cities = new ArrayList();
//
//		fixture.setCities(cities);
//
//	}
//
//	@Test
//	public void testSetName_1()
//		throws Exception {
//		RegionDTO fixture = new RegionDTO();
//		fixture.setName(RegionName.HILL);
//		fixture.setCities(new ArrayList());
//		fixture.setBonusCard(new BonusCardDTO());
//		RegionName name = RegionName.HILL;
//
//		fixture.setName(name);
//
//	}

	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(RegionDTOTest.class);
	}
}