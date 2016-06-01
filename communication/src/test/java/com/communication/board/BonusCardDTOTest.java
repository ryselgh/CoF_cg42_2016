package com.communication.board;

import org.junit.*;

import com.communication.values.BonusType;

import static org.junit.Assert.*;

public class BonusCardDTOTest {
//	@Test
//	public void testBonusCardDTO_1()
//		throws Exception {
//		BonusCardDTO result = new BonusCardDTO();
//		assertNotNull(result);
//	}
	BonusDTO fixture;
	@Before
	public void setUp()
		throws Exception {
		
		fixture = new BonusDTO();
		fixture.setQuantity(1);
		fixture.setType(BonusType.ASSISTANT);
		
	}

	@Test
	public void testGetB_1()
		throws Exception {
		
		BonusCardDTO bcDTO = new BonusCardDTO();
		bcDTO.setBonus(fixture);

		BonusDTO result = bcDTO.getBonus();

		assertNotNull(result);
		assertEquals(BonusType.ASSISTANT, result.getType());
		assertEquals(1, result.getQnt());
	}

//	@Test
//	public void testSetB_1()
//		throws Exception {
//		BonusCardDTO fixture = new BonusCardDTO();
//		fixture.setB(new BonusDTO());
//		BonusDTO b = new BonusDTO();
//
//		fixture.setB(b);
//
//	}

	

//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusCardDTOTest.class);
	}
}