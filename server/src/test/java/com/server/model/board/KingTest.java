package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.communication.board.KingDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;

public class KingTest {
	
	
	String[] close;
	City city;
	Bonus[] b;
	City city2;
	String[] close2;
	
	
	@Before
	public void setUp()
		throws Exception {
		//all the sets for King Tests
		close = new String[1];
		close[0]= "Castrum";
		b = new Bonus[1]; 
		b[0] = new Bonus(BonusType.POINT, 1);
		city = new City("Arkon", CityColor.BLUE, close, 3, new BonusToken(b) );
		close2 = new String[3];
		close2[0] = "Dorful";
		close2[1] = "Framek";
		close2[2] = "Arkon";
		city2 = new City("Castrum", CityColor.BLUE, close2, 3 ,new BonusToken(b));
	

	}
	
	@Test(expected=NullPointerException.class)
	public void locationCannotBeNull(){
		King king= new King(null);
	}
	
	
	@Test
	public void testKing()
		throws Exception {
		
		King result = new King(city);

		assertNotNull(result);
	}

	@Test
	public void testGetLocation()
		throws Exception {
		King fixture = new King(city);

		City result = fixture.getLocation();

		assertNotNull(result);
		assertEquals(result,city);
		assertEquals("Arkon", result.getName());
	}

	@Test
	public void testSetLocation()
		throws Exception {
		King fixture = new King(city);
		fixture.setLocation(city2);
		City newCity = fixture.getLocation();
		
		assertEquals(newCity, city2);

	}

	@Test
	public void testToDTO()
		throws Exception {
		King fixture = new King(city);
		fixture.setLocation(city);

		KingDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		assertNotNull(result);
		assertTrue(result instanceof KingDTO);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingTest.class);
	}
}