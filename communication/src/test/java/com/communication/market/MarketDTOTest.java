package com.communication.market;

import java.util.ArrayList;
import org.junit.*;

import com.communication.decks.PermitsCardDTO;

import static org.junit.Assert.*;

public class MarketDTOTest {
	
	ArrayList<OnSaleDTO> objectsOnSale= new ArrayList();
	
	@Before
	public void setUp()
		throws Exception {
	}
			
	@Test
	public void testMarketDTO_1()
		throws Exception {
		MarketDTO result = new MarketDTO();
		
		assertNotNull(result);
	}

	@Test
	public void testGetObjectsOnSale_1()
		throws Exception {
		MarketDTO fixture = new MarketDTO();

		ArrayList<OnSaleDTO> result = fixture.getObjectsOnSale();

		assertEquals(null, result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MarketDTOTest.class);
	}
}