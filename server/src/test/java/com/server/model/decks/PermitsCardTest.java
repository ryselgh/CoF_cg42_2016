package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;
import com.server.model.board.Bonus;
import com.server.values.BonusType;

public class PermitsCardTest {
	
	Bonus[] b;
	String[]l;
	
	@Before
	public void setUp(){
		b=new Bonus[2];
		b[0]= new Bonus (BonusType.ASSISTANT,2);
		b[1]= new Bonus (BonusType.CARD,3);
		l=new String[2];
		l[0]= "l";
		l[1]= "m";
	}
	
	@Test
	public void testPermitsCard(){
		assertNotNull(new PermitsCard(b,l));
		}
	
	
	
	
	@Test(expected=NullPointerException.class)
	public void testPermitsCard_1(){
		Bonus[] b = new Bonus[2] ;
		String[] l = new String[3] ;
		PermitsCard result = new PermitsCard(b, l);

		
		
	}

	@Test
	public void testGetBonus_1(){
		PermitsCard fixture = new PermitsCard(b,l);
		Bonus[] result = fixture.getBonus();
		assertNotNull(result);
		assertEquals(b,result);
	}

	@Test
	public void testGetCityLetter_1(){
		PermitsCard fixture = new PermitsCard(b,l);
		String[] result = fixture.getCityLetter();
		assertNotNull(result);
		assertEquals(l,result);
	}

	@Test
	public void testIsFaceDown_1(){
		PermitsCard fixture = new PermitsCard(b,l);
		fixture.setFaceDown(true);
		boolean result = fixture.isFaceDown();
		Assert.assertTrue(result);
	}

	

	@Test
	public void testIsFaceDown_2(){
		PermitsCard fixture = new PermitsCard(b,l);
		fixture.setFaceDown(false);
		boolean result = fixture.isFaceDown();
		Assert.assertFalse(result);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsCardTest.class);
	}
}