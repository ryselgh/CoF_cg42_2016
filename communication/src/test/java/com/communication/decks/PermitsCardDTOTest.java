package com.communication.decks;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.BonusDTO;
import com.communication.values.BonusType;

public class PermitsCardDTOTest {
	
	BonusDTO[] bonuses;
	String[] cityLetters;
	
	@Before
	public void setUp()
		throws Exception {
		
		bonuses = new BonusDTO[2];
		bonuses[0]= new BonusDTO();
		bonuses[0].setQuantity(1);
		bonuses[0].setType(BonusType.ASSISTANT);
		bonuses[1]= new BonusDTO();
		bonuses[1].setQuantity(2);
		bonuses[1].setType(BonusType.CARD);
		
		cityLetters=new String[2];
		cityLetters[0]="l";
		cityLetters[1]="m";
		
		
		
		
	}
//	@Test
//	public void testPermitsCardDTO_1()
//		throws Exception {
//		PermitsCardDTO result = new PermitsCardDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetBonuses_1()
		throws Exception {
		PermitsCardDTO fixture = new PermitsCardDTO();
		fixture.setBonuses(bonuses);
		fixture.setFaceDown(true);
		fixture.setCityLetter(cityLetters);

		BonusDTO[] result = fixture.getBonuses();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testGetCityLetter_1()
		throws Exception {
		PermitsCardDTO fixture = new PermitsCardDTO();
		fixture.setBonuses(bonuses);
		fixture.setFaceDown(true);
		fixture.setCityLetter(cityLetters);

		String[] result = fixture.getCityLetter();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testIsFaceDown_1()
		throws Exception {
		PermitsCardDTO fixture = new PermitsCardDTO();
		fixture.setBonuses(bonuses);
		fixture.setFaceDown(true);
		fixture.setCityLetter(cityLetters);

		boolean result = fixture.isFaceDown();

		assertEquals(true, result);
	}

	@Test
	public void testIsFaceDown_2()
		throws Exception {
		PermitsCardDTO fixture = new PermitsCardDTO();
		fixture.setBonuses(bonuses);
		fixture.setFaceDown(false);
		fixture.setCityLetter(cityLetters);

		boolean result = fixture.isFaceDown();

		assertEquals(false, result);
	}

//	@Test
//	public void testSetBonuses_1()
//		throws Exception {
//		PermitsCardDTO fixture = new PermitsCardDTO();
//		fixture.setBonuses(new BonusDTO[] {});
//		fixture.setFaceDown(true);
//		fixture.setCityLetter(new String[] {});
//		BonusDTO[] bonuses = new BonusDTO[] {};
//
//		fixture.setBonuses(bonuses);
//
//	}
//
//	@Test
//	public void testSetCityLetter_1()
//		throws Exception {
//		PermitsCardDTO fixture = new PermitsCardDTO();
//		fixture.setBonuses(new BonusDTO[] {});
//		fixture.setFaceDown(true);
//		fixture.setCityLetter(new String[] {});
//		String[] cityLetter = new String[] {};
//
//		fixture.setCityLetter(cityLetter);
//
//	}
//
//	@Test
//	public void testSetFaceDown_1()
//		throws Exception {
//		PermitsCardDTO fixture = new PermitsCardDTO();
//		fixture.setBonuses(new BonusDTO[] {});
//		fixture.setFaceDown(true);
//		fixture.setCityLetter(new String[] {});
//		boolean faceDown = true;
//
//		fixture.setFaceDown(faceDown);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsCardDTOTest.class);
	}
}