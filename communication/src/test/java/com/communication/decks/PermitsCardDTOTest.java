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
		
		//in the setup there is what is needed to create a permitsCard
		
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
	
	@Test
	public void testPermitsCardDTO()
		throws Exception {
		PermitsCardDTO result = new PermitsCardDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBonuses()
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
	public void testGetCityLetter()
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
	public void testIsFaceDownReturnsTrue()
		throws Exception {
		PermitsCardDTO fixture = new PermitsCardDTO();
		fixture.setBonuses(bonuses);
		fixture.setFaceDown(true);
		fixture.setCityLetter(cityLetters);

		boolean result = fixture.isFaceDown();

		assertEquals(true, result);
	}

	@Test
	public void testIsFaceDownReturnsFalse()
		throws Exception {
		PermitsCardDTO fixture = new PermitsCardDTO();
		fixture.setBonuses(bonuses);
		fixture.setFaceDown(false);
		fixture.setCityLetter(cityLetters);

		boolean result = fixture.isFaceDown();

		assertEquals(false, result);
	}


	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsCardDTOTest.class);
	}
}