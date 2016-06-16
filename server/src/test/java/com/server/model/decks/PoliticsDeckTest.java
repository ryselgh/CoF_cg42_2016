package com.server.model.decks;

import org.junit.*;

import com.communication.decks.PoliticsDeckDTO;
import com.communication.values.CouncilorColor;
import com.server.model.gamelogic.Game;

import static org.junit.Assert.*;

public class PoliticsDeckTest {
	
	@Test
	public void testTheConstructor(){
		PoliticsDeck pd= new PoliticsDeck();
		
		assertNotNull(pd);
	}
	
	@Test
	public void testTheConstructor2(){
		PoliticsDeck pd= new PoliticsDeck();
		
			
	}
	
	

	@Test(expected=NullPointerException.class)
	public void testDiscard_1()
		throws Exception {
		PoliticsDeck fixture = new PoliticsDeck();
		fixture.discard(null);

		}
	@Test
	public void testDiscard_2()
		throws Exception {
		PoliticsCard pc = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsDeck fixture = new PoliticsDeck();
		fixture.discard(pc);
		
		
		

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
	
//	@Test
//	public void testTheEmptiness(){
//		PoliticsDeck pd = new PoliticsDeck();
//		for(PoliticsCard pc: pd)
//			pd.discard(pc);
//		
//		PoliticsCard pol = pd.draw();
//		
//		
//	}
//	
	
	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsDeckTest.class);
	}
}