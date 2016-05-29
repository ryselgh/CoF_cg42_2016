package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.server.values.BonusType;
public class BonusTokenTest {
	
	Bonus[] bonus;

	@Test
	public void testBonusToken_1()
		throws Exception {
		Bonus[] b = new Bonus[] {};

		BonusToken result = new BonusToken(b);

		assertNotNull(result);
	}

	@Test
	public void testGetBonus_1()
		throws Exception {
		BonusToken fixture = new BonusToken(new Bonus[] {});

		Bonus[] result = fixture.getBonus();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Before
	public void setUp() throws Exception {
		bonus = new Bonus[2];
		bonus[0]= new Bonus(BonusType.CARD, 2);
		bonus[1]= new Bonus(BonusType.ASSISTANT, 9);
	}

	@Test
	public void testBonusToken() {
		Assert.assertNotNull(new BonusToken(bonus));
	}
	
//	@Test(expected=IllegalArgumentException.class)
//	public void testBonusTokenIllegalValue() {
//		new BonusToken(null);
//	}
	
	

	@Test
	public void testGetBonus() {
		BonusToken bonusToken =new BonusToken(bonus);
		Assert.assertArrayEquals(bonusToken.getBonus(), bonus);
	}


	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusTokenTest.class);
	}
}
