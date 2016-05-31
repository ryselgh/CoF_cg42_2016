package com.communication.decks;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.BonusDTO;
import com.communication.values.BonusType;

public class KingBonusCardDTOTest {
	
	BonusDTO bonus;
	
	@Before
	public void setUp()
		throws Exception {
		
		bonus = new BonusDTO();
		bonus.setQuantity(3);
		bonus.setType(BonusType.POINT);
		
		
	}
	
	
//	@Test
//	public void testKingBonusCardDTO_1()
//		throws Exception {
//		KingBonusCardDTO result = new KingBonusCardDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetBonus_1()
		throws Exception {
		KingBonusCardDTO fixture = new KingBonusCardDTO();
		fixture.setBonus(bonus);
		fixture.setNumber(1);

		BonusDTO result = fixture.getBonus();

		assertNotNull(result);
		assertEquals(BonusType.POINT, result.getType());
		assertEquals(3, result.getQnt());
	}

	@Test
	public void testGetNumber_1()
		throws Exception {
		KingBonusCardDTO fixture = new KingBonusCardDTO();
		fixture.setBonus(bonus);
		fixture.setNumber(1);

		int result = fixture.getNumber();

		assertEquals(1, result);
	}

//	@Test
//	public void testSetBonus_1()
//		throws Exception {
//		KingBonusCardDTO fixture = new KingBonusCardDTO();
//		fixture.setBonus(new BonusDTO());
//		fixture.setNumber(1);
//		BonusDTO bonus = new BonusDTO();
//
//		fixture.setBonus(bonus);
//
//	}
//
//	@Test
//	public void testSetNumber_1()
//		throws Exception {
//		KingBonusCardDTO fixture = new KingBonusCardDTO();
//		fixture.setBonus(new BonusDTO());
//		fixture.setNumber(1);
//		int n = 1;
//
//		fixture.setNumber(n);
//
//	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusCardDTOTest.class);
	}
}