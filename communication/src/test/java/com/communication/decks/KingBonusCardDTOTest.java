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
		
		//in the setUp there is the bonus used in the methods of the test
		
		bonus = new BonusDTO();
		bonus.setQuantity(3);
		bonus.setType(BonusType.POINT);
		
		
	}
	
	
	@Test
	public void testKingBonusCardDTO()
		throws Exception {
		KingBonusCardDTO result = new KingBonusCardDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBonus()
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
	public void testGetNumber()
		throws Exception {
		KingBonusCardDTO fixture = new KingBonusCardDTO();
		fixture.setBonus(bonus);
		fixture.setNumber(1);

		int result = fixture.getNumber();

		assertEquals(1, result);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusCardDTOTest.class);
	}
}