package com.communication.board;

import org.junit.*;

import com.communication.values.BonusType;

import static org.junit.Assert.*;

public class BonusCardDTOTest {
	
	
	
	
	BonusDTO fixture;
	@Before
	public void setUp()
		throws Exception {
		
		// all you need to test the methods
		
		fixture = new BonusDTO();
		fixture.setQuantity(1);
		fixture.setType(BonusType.ASSISTANT);
		
	}
	
	@Test
	public void testBonusCardDTO()
		throws Exception {
		BonusCardDTO result = new BonusCardDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBonus()
		throws Exception {
		
		BonusCardDTO bcDTO = new BonusCardDTO();
		bcDTO.setBonus(fixture);

		BonusDTO result = bcDTO.getBonus();

		assertNotNull(result);
		assertEquals(BonusType.ASSISTANT, result.getType());
		assertEquals(1, result.getQnt());
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusCardDTOTest.class);
	}
}