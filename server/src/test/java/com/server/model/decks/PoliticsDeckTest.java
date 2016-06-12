package com.server.model.decks;

import org.junit.*;

import com.communication.decks.PoliticsDeckDTO;
import com.communication.values.CouncilorColor;

import static org.junit.Assert.*;

public class PoliticsDeckTest {
	
	@Test
	public void testTheConstructor(){
		PoliticsDeck pd= new PoliticsDeck();
		
		assertNotNull(pd);
	}
	

	@Test(expected=NullPointerException.class)
	public void testDiscard_1()
		throws Exception {
		PoliticsDeck fixture = new PoliticsDeck();
		fixture.discard(null);

		}
	
	@Test
	public void testDraw(){
		
		PoliticsDeck pd= new PoliticsDeck();
		PoliticsCard pc= pd.draw();
		assertNotNull(pc);
	}
	
	@Test
	public void toDTO(){
		
		PoliticsDeck pd= new PoliticsDeck();
		PoliticsDeckDTO pcDTO = pd.toDTO();
		
		assertTrue(pcDTO instanceof PoliticsDeckDTO);
		
	}
	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsDeckTest.class);
	}
}