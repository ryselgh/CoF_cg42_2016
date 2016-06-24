package com.server.model.market;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.market.MarketDTO;
import com.server.model.board.Assistant;
import com.server.model.gamelogic.Player;

public class MarketTest {
	
	OnSale o;
	OnSale o2;
	Player p;
	String UID="";
	@Before
	public void setUp()
		throws Exception {
		p = new Player("1");
		o = new OnSale(p,new Assistant(), 3,UID);
		o2 = new OnSale(p,new Assistant(), 2,UID);
	}

	
	
	@Test
	public void testMarket()
		throws Exception {

		Market result = new Market();

		assertNotNull(result);
		assertEquals(0, result.getObjNumber());
	}

	
	@Test
	public void testGetObjNumber()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);

		int result = fixture.getObjNumber();

		assertEquals(1, result);
	}

	@Test
	public void testGetObjOnSale()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		int arrayPointer = 1;

		OnSale result = fixture.getObjOnSale(arrayPointer);

		
		assertNotNull(result);
	}

	@Test
	public void testGetObjectsOnSale_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		fixture.addObj(null);

		ArrayList<OnSale> result = fixture.getObjectsOnSale();

		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testRemoveObj_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		int n = 1;

		fixture.removeObj(n);
		ArrayList<OnSale> result = fixture.getObjectsOnSale();

		
		
		assertEquals(result.size(),1);
	}

	@Test
	public void testToDTO()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		MarketDTO result = fixture.toDTO();

		assertNotNull(result);
		assertTrue(result instanceof MarketDTO);
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MarketTest.class);
	}
}