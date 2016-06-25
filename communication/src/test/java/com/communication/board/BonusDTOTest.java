package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.BonusType;

public class BonusDTOTest {
	
	
	
	@Test
	public void testBonusDTO()
		throws Exception {
		BonusDTO result = new BonusDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together

	@Test
	public void testGetQnt()
		throws Exception {
		BonusDTO fixture = new BonusDTO();
		fixture.setQuantity(1);

		int result = fixture.getQnt();

		assertEquals(1, result);
	}

	@Test
	public void testGetType()
		throws Exception {
		BonusDTO fixture = new BonusDTO();
		fixture.setType(BonusType.ASSISTANT);
		BonusType result = fixture.getType();

		assertEquals(BonusType.ASSISTANT, result);
	}

	

	
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusDTOTest.class);
	}
}