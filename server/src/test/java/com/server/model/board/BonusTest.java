package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.server.model.gamelogic.Game;
import com.server.values.BonusType;

import junit.framework.Assert;

public class BonusTest {

	@Test
	public void testthecreationofaBonus() {
		assertNotNull(new Bonus(BonusType.POINT, 1));
	}
	
	@Test
	public void testAnIllegalArgumentInTheCretor(){
		
	}
	
	@Test
	public void testingGetterQnt(){
		int i = 3;
		Bonus bonus=new Bonus(BonusType.COIN, i);
		assertEquals(bonus.getQnt(), 3);
		
		
	}
	
	@Test
	public void testingGetterType(){
		int i = 3;
		Bonus bonus=new Bonus(BonusType.COIN, i);
		assertEquals(bonus.getType(), BonusType.COIN);
	}


	@Test
	public void testBonus_1()
		throws Exception {
		BonusType t = BonusType.ASSISTANT;
		int q = 1;

		Bonus result = new Bonus(t, q);

		assertNotNull(result);
		assertEquals(1, result.getQnt());
	}

	@Test
	public void testGetQnt_1()
		throws Exception {
		Bonus fixture = new Bonus(BonusType.ASSISTANT, 1);

		int result = fixture.getQnt();

		assertEquals(1, result);
	}

	@Test
	public void testGetType_1()
		throws Exception {
		Bonus fixture = new Bonus(BonusType.ASSISTANT, 1);

		BonusType result = fixture.getType();

		assertNotNull(result);
		assertEquals("ASSISTANT", result.name());
		assertEquals("ASSISTANT", result.toString());
		assertEquals(3, result.ordinal());
	}

	@Test
	public void testHasNobilityBonus_1()
		throws Exception {
		Bonus[] b = new Bonus[] {};

		boolean result = Bonus.hasNobilityBonus(b);

		assertEquals(false, result);
	}

	@Test
	public void testHasNobilityBonus_2()
		throws Exception {
		Bonus[] b = new Bonus[] {};

		boolean result = Bonus.hasNobilityBonus(b);

		assertEquals(false, result);
	}

	@Test
	public void testHasNobilityBonus_3()
		throws Exception {
		Bonus[] b = new Bonus[] {};

		boolean result = Bonus.hasNobilityBonus(b);

		assertEquals(false, result);
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
		new org.junit.runner.JUnitCore().run(BonusTest.class);
	}
}
