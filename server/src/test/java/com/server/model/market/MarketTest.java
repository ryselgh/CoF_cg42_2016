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
	
	@Before
	public void setUp()
		throws Exception {
		p = new Player("1");
		o = new OnSale(p,new Assistant(), 3);
		o2 = new OnSale(p,new Assistant(), 2);
	}

	
	
	@Test
	public void testMarket_1()
		throws Exception {

		Market result = new Market();

		assertNotNull(result);
		assertEquals(false, result.hasNext());
		assertEquals(null, result.next());
		assertEquals(0, result.getObjNumber());
	}

	@Test
	public void testAddObj_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(new OnSale(p,new Assistant(), 3));
		OnSale o = new OnSale(p,new Assistant(), 3);

		fixture.addObj(o);

	}

	@Test
	public void testGetObjNumber_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);

		int result = fixture.getObjNumber();

		assertEquals(1, result);
	}

	@Test
	public void testGetObjOnSale_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		int arrayPointer = 1;

		OnSale result = fixture.getObjOnSale(arrayPointer);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
		//       at java.util.ArrayList.rangeCheck(ArrayList.java:653)
		//       at java.util.ArrayList.get(ArrayList.java:429)
		//       at com.server.model.market.Market.getObjOnSale(Market.java:28)
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
	public void testHasNext_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);

		boolean result = fixture.hasNext();

		assertEquals(false, result);
	}

	@Test
	public void testNext_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(new OnSale(p,new Assistant(), 3));

		Object result = fixture.next();

		assertEquals(null, result);
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

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
		//       at java.util.ArrayList.rangeCheck(ArrayList.java:653)
		//       at java.util.ArrayList.remove(ArrayList.java:492)
		//       at com.server.model.market.Market.removeObj(Market.java:42)
		
		assertEquals(result.size(),1);
	}

	@Test
	public void testToDTO_1()
		throws Exception {
		Market fixture = new Market();
		fixture.addObj(o);
		fixture.addObj(o2);
		

		MarketDTO result = fixture.toDTO();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.market.OnSale.toDTO(OnSale.java:65)
		//       at com.server.model.market.Market.toDTO(Market.java:66)
		assertNotNull(result);
		assertTrue(result instanceof MarketDTO);
	}

//	@Test
//	public void testToDTO_2()
//		throws Exception {
//		Market fixture = new Market();
//		fixture.addObj(new OnSale());
//
//		MarketDTO result = fixture.toDTO();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.market.OnSale.toDTO(OnSale.java:65)
//		//       at com.server.model.market.Market.toDTO(Market.java:66)
//		assertNotNull(result);
//	}

	
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MarketTest.class);
	}
}