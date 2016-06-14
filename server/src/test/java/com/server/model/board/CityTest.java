package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.communication.board.CityDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.server.model.gamelogic.Player;

import junit.framework.Assert;

public class CityTest {
	
	Bonus[] bonus;
	BonusToken bonusToken;
	String[] close;
	String[] close2;
	String[] close3;
	CityColor c;
	
	
	@Before
	public void setUp() throws Exception {
		bonus = new Bonus[2];
		bonus[0]= new Bonus(BonusType.CARD, 2);
		bonus[1]= new Bonus(BonusType.ASSISTANT, 9);
		bonusToken=new BonusToken(bonus);
		close = new String[2];
		close[0] ="Osium";
		close[1] ="Framek";
		c= CityColor.BLUE;
		
		close2 = new String[1];
		close2[0] = "Juvelar";
		
		close3 = new String[1];
		close3[0] = "Osium";
	}

	@Test
	public void testCity_1()
		throws Exception {
		

		City result = new City("Juvelar", c, close, 1, bonusToken);

		assertNotNull(result);
	
	}

	@Test
	public void testGetBonusToken_1()
		throws Exception {
		City fixture = new City("Juvelar", c, close, 1, bonusToken);

		BonusToken result = fixture.getBonusToken();

		assertNotNull(result);
	}

	@Test
	public void testGetCloseCity_1()
		throws Exception {
		City fixture = new City("Juvelar", c, close, 1, bonusToken);

		String[] result = fixture.getCloseCity();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		City fixture = new City("Juvelar", c, close, 1, bonusToken);

		CityColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLUE", result.name());
		
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
		City fixture = new City("Juvelar", c, close, 1, bonusToken);

		String result = fixture.getName();

		assertEquals("Juvelar", result);
	}

	@Test
	public void testHasEmporium_1()
		throws Exception {
		City fixture = new City("Juvelar", c, close, 1, bonusToken);
		Player p = new Player("1");

		boolean result = fixture.hasEmporium(p);

		assertEquals(false, result);
	}

	@Test
	public void testHasEmporium_2()
		throws Exception {
		Player p = new Player("1");
		Emporium e = new Emporium(p);
		City fixture = new City("Juvelar", c, close, 1, bonusToken);
		
		fixture.setEmporium(e);

		boolean result = fixture.hasEmporium(p);

		assertEquals(true, result);
	}

	@Test
	public void testIsCloseCityOf_1()
		throws Exception {
		City fixture = new City("Juvelar", c, close, 1, bonusToken);
		City city = new City("Framek", CityColor.BLUE,  close2, 1, bonusToken);

		boolean result = fixture.isCloseCityOf(city);

		assertEquals(true, result);
	}

	@Test
	public void testIsCloseCityOf_2()
		throws Exception {
		City fixture = new City("Juvelar", c, close, 1, bonusToken);
		City city = new City("Castrum", CityColor.BLUE, close3, 1, bonusToken);

		boolean result = fixture.isCloseCityOf(city);

		assertEquals(false, result);
	}
	
	@Test
	public void toDTO(){
		City fixture = new City("Juvelar", c, close, 1, bonusToken);
		
		CityDTO result = fixture.toDTO(null);
		
		assertTrue(result instanceof CityDTO);
	}


	



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CityTest.class);
	}
}
