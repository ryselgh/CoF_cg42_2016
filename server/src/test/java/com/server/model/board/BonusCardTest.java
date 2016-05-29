package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.server.model.gamelogic.Game;
import com.server.values.BonusType;

import junit.framework.Assert;

public class BonusCardTest {

	@Test
	public void testBonusCard() {
		Bonus b = new Bonus(BonusType.POINT, 5);
		assertNotNull(new BonusCard(b));
	}

	@Test
	public void testGetBonus() {
		Bonus b = new Bonus(BonusType.POINT, 5);
		BonusCard bc = new BonusCard(b);
		Assert.assertEquals(bc.getBonus(), b );
		
	}


	@Test
	public void testBonusCard_1()
		throws Exception {
		Bonus b = new Bonus(BonusType.ASSISTANT, 1);

		BonusCard result = new BonusCard(b);

		assertNotNull(result);
	}

	@Test
	public void testGetBonus_1()
		throws Exception {
		BonusCard fixture = new BonusCard(new Bonus(BonusType.ASSISTANT, 1));

		Bonus result = fixture.getBonus();

		assertNotNull(result);
		assertEquals(1, result.getQnt());
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
		new org.junit.runner.JUnitCore().run(BonusCardTest.class);
	}
}
