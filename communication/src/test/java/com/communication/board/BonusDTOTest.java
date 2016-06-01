package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.BonusType;

public class BonusDTOTest {
	
//	@Test
//	public void testBonusDTO_1()
//		throws Exception {
//		BonusDTO result = new BonusDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetQnt_1()
		throws Exception {
		BonusDTO fixture = new BonusDTO();
		fixture.setQuantity(1);

		int result = fixture.getQnt();

		assertEquals(1, result);
	}

	@Test
	public void testGetType_1()
		throws Exception {
		BonusDTO fixture = new BonusDTO();
		fixture.setType(BonusType.ASSISTANT);
		BonusType result = fixture.getType();

		assertEquals(BonusType.ASSISTANT, result);
	}

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusDTOTest.class);
	}
}