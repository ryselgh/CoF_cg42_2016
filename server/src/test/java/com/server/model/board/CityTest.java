package com.server.model.board;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.communication.board.CityDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.server.model.gamelogic.Player;



public class CityTest {
	
	Bonus[] bonus;
	BonusToken bT;
	String[] close;
	String[] close2;

	@Before
	public void setUp(){
		
		//sets closeCities and a bonusToken
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
	public void testCity()
		throws Exception {
		

		City result = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		assertNotNull(result);
		assertEquals("Juvelar", result.getName());
	}

	@Test
	public void testGetBonusToken()
		throws Exception {
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		Bonus[] bonus = new Bonus[1];
		bonus[0] = new Bonus(BonusType.ASSISTANT,5);
		BonusToken bt = new BonusToken(bonus);
		fixture.setToken(bt);
		BonusToken result = fixture.getBonusToken();

		assertEquals(bt,result);
	}

	@Test
	public void testGetCloseCity()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		String[] result = fixture.getCloseCity();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testGetColor()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		CityColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("PURPLE", result.name());
		assertEquals("PURPLE", result.toString());
		assertEquals(4, result.ordinal());
	}

	@Test
	public void testGetEmporium()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		Emporium e = new Emporium(new Player("1"));

		fixture.setEmporium(e);
		Emporium[] result = fixture.getEmporium();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertNotNull(result[0]);
	}

	@Test
	public void testGetName()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);

		String result = fixture.getName();

		assertEquals("Juvelar", result);
	}

	@Test
	public void testHasEmporiumReturnFalse()
		throws Exception {
		City fixture = new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		Player p = new Player("1");

		boolean result = fixture.hasEmporium(p);

		assertFalse(result);
	}

	@Test
	public void testHasEmporiumReturnTrue()
		throws Exception {
		City fixture = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		Player p = new Player("1");
		Emporium e = new Emporium(p);
		fixture.setEmporium(e);

		boolean result = fixture.hasEmporium(p);

		assertTrue(result);
	}

	

	@Test
	public void testIsCloseCityOfReturnsTrue()
		throws Exception {
		 
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		City city = new City("Osium", CityColor.BLUE, close2, 3, bT);

		boolean result = fixture.isCloseCityOf(city);

		assertTrue(result);
	}

	@Test
	public void testIsCloseCityOfReturnFalse()
		throws Exception {
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);
		City city = new City("Hellar", CityColor.BLUE, close2, 3, bT);

		boolean result = fixture.isCloseCityOf(city);

		assertFalse(result);
	}



	
	
	
	@Test
	public void testToDTO(){
		ArrayList<PlayerDTO> plsDTO = new ArrayList<PlayerDTO>(3);
		
		Emporium e = new Emporium(new Player("1"));

		
		
		City fixture =new City("Juvelar",CityColor.PURPLE, close, 3, bT);		
		fixture.setEmporium(e);
		CityDTO cDTO = fixture.toDTO(plsDTO);
		
		assertEquals(cDTO.getName(),"Juvelar");
		
	}
	

	

	



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CityTest.class);
	}
}
