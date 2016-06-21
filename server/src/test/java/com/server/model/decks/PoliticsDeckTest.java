package com.server.model.decks;

import org.junit.*;

import com.communication.decks.PoliticsDeckDTO;
import com.communication.values.CouncilorColor;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	public void testDiscard_2()
		throws Exception {
		PoliticsDeck fixture = new PoliticsDeck();
		fixture.discard(new PoliticsCard(CouncilorColor.PINK));
		assertEquals(fixture.getGarbage().size(),1);

		}
	
	@Test
	public void testDraw(){
		
		PoliticsDeck pd= new PoliticsDeck();
		PoliticsCard pc= pd.draw();
		assertNotNull(pc);
	}
	
	@Test
	public void testDraw2(){
		
		PoliticsDeck pd= new PoliticsDeck();
		PoliticsCard pc= pd.draw();
		assertEquals(pd.getPoliticsDeck().size(),89);
	}
	
//	@Test
//	public void testDraw3(){
//		
//		PoliticsDeck pd= new PoliticsDeck();
//		for(int i =0;i<(pd.getPoliticsDeck().size()+pd.getGarbage().size());i++){
//			PoliticsCard card= pd.draw();
//			pd.discard(card);
//		}
//		pd.draw();	
//		assertEquals(pd.getPoliticsDeck().size(),89);
//		assertTrue(pd.getGarbage().isEmpty());
//	}
	
	@Test
	public void toDTO(){
		
		PoliticsDeck pd= new PoliticsDeck();
		PoliticsDeckDTO pcDTO = pd.toDTO();
		
		assertTrue(pcDTO instanceof PoliticsDeckDTO);
		
	}
	
	@Test
	public void getPoliticsDeck(){
		
		PoliticsDeck pd = new PoliticsDeck();
		ArrayList<PoliticsCard> result = pd.getPoliticsDeck();
		
		assertEquals(result.size(),90);
	}
	
	@Test
	public void getGarbage(){
		
		PoliticsDeck pd = new PoliticsDeck();
		ArrayList<PoliticsCard> result = pd.getGarbage();
		
		assertTrue(result.isEmpty());
	}
	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsDeckTest.class);
	}
}