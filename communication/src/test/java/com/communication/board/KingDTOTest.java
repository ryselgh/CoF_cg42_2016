package com.communication.board;

import org.junit.*;

import com.communication.values.BonusType;
import com.communication.values.CityColor;

import static org.junit.Assert.*;

public class KingDTOTest {
	
	
	
	CityDTO fixture1;
	String[] closeCities;
	BonusDTO[] b;
	
	
	@Before
	public void setUp()
		throws Exception {
		closeCities= new String[2];
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
		
		fixture1 = new CityDTO();
		fixture1.setName("Juvelar");
		fixture1.setColor(CityColor.BLUE);
		fixture1.setCloseCities(closeCities);
		
		fixture1.setSlot(new EmporiumDTO[10] );
		fixture1.setPlayerNum(1);
		fixture1.setToken(btDTO);
	}
//	@Test
//	public void testKingDTO_1()
//		throws Exception {
//		KingDTO result = new KingDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetLocation_1()
		throws Exception {
		KingDTO fixture = new KingDTO();
		fixture.setLocation(fixture1);

		CityDTO result = fixture.getLocation();

		assertNotNull(result);
		assertEquals("Juvelar", result.getName());
		
	}

//	@Test
//	public void testSetLocation_1()
//		throws Exception {
//		KingDTO fixture = new KingDTO();
//		fixture.setLocation(new CityDTO());
//		CityDTO l = new CityDTO();
//
//		fixture.setLocation(l);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingDTOTest.class);
	}
}