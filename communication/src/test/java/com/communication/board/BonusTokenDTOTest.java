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
	
//	@Test
//	public void testBonusTokenDTO_1()
//		throws Exception {
//		BonusTokenDTO result = new BonusTokenDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetBonus_1()
		throws Exception {
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(fixture);

		BonusDTO[] result = btDTO.getBonus();

		assertEquals(fixture, result);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusTokenDTOTest.class);
	}
}