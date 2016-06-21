package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.communication.board.CityDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.server.model.gamelogic.Player;

import junit.framework.Assert;

public class CityTest {
	
	Bonus[] bonus;
	BonusToken bT;
	String[] close;
	String[] close2;

	@Before
	public void setUp(){
		close = new String[2];
		close[0] = "Framek";
		close[1] = "Osium";
		close2 = new String[1];
		close2[0] = "Juvelar";
		
		bonus = new Bonus[2];
		bonus[0]= new Bonus(BonusType.CARD, 2);
		bonus[1]= new Bonus(BonusType.ASSISTANT, 9);
		bT=new BonusToken(bonus);
	}

	@Test
	public void testCity_1()
		throws Exception {
		

		City result = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		assertNotNull(result);
		assertEquals("Juvelar", result.getName());
	}

	@Test
	public void testGetBonusToken_1()
		throws Exception {
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		BonusToken result = fixture.getBonusToken();

		assertEquals(bT,result);
	}

	@Test
	public void testGetCloseCity_1()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		String[] result = fixture.getCloseCity();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testGetColor_1()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		CityColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("PURPLE", result.name());
		assertEquals("PURPLE", result.toString());
		assertEquals(4, result.ordinal());
	}

	@Test
	public void testGetEmporium_1()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		Emporium[] result = fixture.getEmporium();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertEquals(null, result[0]);
	}

	@Test
	public void testGetName_1()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		String result = fixture.getName();

		assertEquals("Juvelar", result);
	}

	@Test
	public void testHasEmporium_1()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		Player p = new Player("1");

		boolean result = fixture.hasEmporium(p);

		assertFalse(result);
	}

	@Test
	public void testHasEmporium_2()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Player p = new Player("1");
		Emporium e = new Emporium(p);
		fixture.setEmporium(e);

		boolean result = fixture.hasEmporium(p);

		assertTrue(result);
	}

	

	@Test
	public void testIsCloseCityOf_1()
		throws Exception {
		 
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		City city = new City("Osium", CityColor.BLUE, close2, 3, bT);

		boolean result = fixture.isCloseCityOf(city);

		assertTrue(result);
	}

	@Test
	public void testIsCloseCityOf_2()
		throws Exception {
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		City city = new City("Hellar", CityColor.BLUE, close2, 3, bT);

		boolean result = fixture.isCloseCityOf(city);

		assertFalse(result);
	}



	@Test
	public void testSetEmporium_1()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Emporium e = new Emporium(new Player("1"));

		int result = fixture.setEmporium(e);

		assertEquals(1, result);
	}

	

	

	@Test
	public void testSetToken_1()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3,new BonusToken(new Bonus[] {}));
		

		fixture.setToken(bT);
		assertEquals(fixture.getBonusToken(),bT);

	}

	
	@Test
	public void testToDTO(){
		ArrayList<PlayerDTO> plsDTO = new ArrayList<PlayerDTO>(3);
		
		
		
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);		
		CityDTO cDTO = fixture.toDTO(plsDTO);
		
		assertEquals(cDTO.getName(),"Juvelar");
		
	}
	

	

	



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CityTest.class);
	}
}
