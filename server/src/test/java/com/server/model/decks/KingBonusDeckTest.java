package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.server.model.board.Bonus;

public class KingBonusDeckTest {
	
	
	Bonus[] bonuss;
	
	
	
	@Test
	public void testKingBonusDeckConstructor(){
		
		
	}

	@Test(expected= IndexOutOfBoundsException.class)
	public void testKingBonusDeck_2(){
		Bonus[] bonuses = new Bonus[5];
		KingBonusDeck result = new KingBonusDeck(bonuses);
		assertNotNull(result);
	}

	

//	@Test
//	public void testDraw_1()
//		throws Exception {
//		KingBonusDeck fixture = new KingBonusDeck(new Bonus[] {});
//
//		KingBonusCard result = fixture.draw();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.decks.KingBonusDeck.draw(KingBonusDeck.java:39)
//		assertNotNull(result);
//	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testDraw(){
		KingBonusDeck fixture = new KingBonusDeck(new Bonus[] {});
		KingBonusCard result = fixture.draw();

		assertNotNull(result);
	}

	@Test
	public void testGetKingqty_1()
		throws Exception {

		int result = KingBonusDeck.getKingqty();
		

		assertEquals(5, result);
	}

	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusDeckTest.class);
	}
}