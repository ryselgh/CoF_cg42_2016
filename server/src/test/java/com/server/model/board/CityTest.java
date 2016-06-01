package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.server.model.gamelogic.Player;

import junit.framework.Assert;

public class CityTest {
	
	Bonus[] bonus;
	BonusToken bonusToken;
	String[] close = {"Osium","Framek"};

	@Test
	public void testCity_1()
		throws Exception {
		String n = "";
		CityColor c = CityColor.BLUE;
		String[] close = new String[] {};
		int playerNo = 1;
		BonusToken bt = new BonusToken(new Bonus[] {});

		City result = new City(n, c, close, playerNo, bt);

		assertNotNull(result);
		assertEquals("", result.getName());
	}

	@Test
	public void testGetBonusToken_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		BonusToken result = fixture.getBonusToken();

		assertNotNull(result);
	}

	@Test
	public void testGetCloseCity_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		String[] result = fixture.getCloseCity();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		CityColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLUE", result.name());
		assertEquals("BLUE", result.toString());
		assertEquals(0, result.ordinal());
	}

	@Test
	public void testGetEmporium_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		Emporium[] result = fixture.getEmporium();

		assertNotNull(result);
		assertEquals(1, result.length);
		assertEquals(null, result[0]);
	}

	@Test
	public void testGetName_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		String result = fixture.getName();

		assertEquals("", result);
	}

	@Test
	public void testHasEmporium_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Player p = new Player(1);

		boolean result = fixture.hasEmporium(p);

		assertEquals(false, result);
	}

	@Test
	public void testHasEmporium_2()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Player p = new Player(1);

		boolean result = fixture.hasEmporium(p);

		assertEquals(false, result);
	}

	@Test
	public void testHasEmporium_3()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Player p = new Player(1);

		boolean result = fixture.hasEmporium(p);

		assertEquals(false, result);
	}

	@Test
	public void testHasEmporium_4()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Player p = new Player(1);

		boolean result = fixture.hasEmporium(p);

		assertEquals(false, result);
	}

	@Test
	public void testIsCloseCityOf_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		City city = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		boolean result = fixture.isCloseCityOf(city);

		assertEquals(false, result);
	}

	@Test
	public void testIsCloseCityOf_2()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		City city = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		boolean result = fixture.isCloseCityOf(city);

		assertEquals(false, result);
	}

	@Test
	public void testIsCloseCityOf_3()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		City city = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));

		boolean result = fixture.isCloseCityOf(city);

		assertEquals(false, result);
	}

	@Test
	public void testSetEmporium_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Emporium e = new Emporium(new Player(1));

		int result = fixture.setEmporium(e);

		assertEquals(1, result);
	}

	@Test
	public void testSetEmporium_2()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Emporium e = new Emporium(new Player(1));

		int result = fixture.setEmporium(e);

		assertEquals(1, result);
	}

	@Test
	public void testSetEmporium_3()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Emporium e = new Emporium(new Player(1));

		int result = fixture.setEmporium(e);

		assertEquals(1, result);
	}

	@Test
	public void testSetToken_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		BonusToken t = new BonusToken(new Bonus[] {});

		fixture.setToken(t);

	}

	@Before
	public void setUp() throws Exception {
		bonus = new Bonus[2];
		bonus[0]= new Bonus(BonusType.CARD, 2);
		bonus[1]= new Bonus(BonusType.ASSISTANT, 9);
		bonusToken=new BonusToken(bonus);
	}

	@Test
	public void testCity() {
		Assert.assertNotNull(new City("Juvelar", CityColor.BLUE, close, 4, bonusToken));
	}

	

//	@Test
//	public void testGetEmporium() {
//		Emporium[] slot= new Emporium[5];
//		Assert.assertEquals(expected, actual);
//	}

//	@Test
//	public void testGetColor() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCloseCity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsCloseCityOf() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetBonusToken() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetToken() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testHasEmporium() {
//		fail("Not yet implemented");
//	}


	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CityTest.class);
	}
}
