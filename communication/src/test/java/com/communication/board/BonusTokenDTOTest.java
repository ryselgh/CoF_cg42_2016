package com.communication.board;

import org.junit.*;

import com.communication.values.BonusType;

import static org.junit.Assert.*;

public class BonusTokenDTOTest {
	
	BonusDTO[] fixture;
	@Before
	public void setUp()
		throws Exception {
		
		fixture = new BonusDTO[2];
		fixture[0]= new BonusDTO();
		fixture[0].setQuantity(1);
		fixture[0].setType(BonusType.ASSISTANT);
		fixture[1]= new BonusDTO();
		fixture[1].setQuantity(2);
		fixture[1].setType(BonusType.CARD);
		
	}
	
	@Test
	public void testBonusTokenDTO()
		throws Exception {
		BonusTokenDTO result = new BonusTokenDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetBonus()
		throws Exception {
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(fixture);

		BonusDTO[] result = btDTO.getBonus();

		assertArrayEquals(fixture, result);
		
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusTokenDTOTest.class);
	}
}