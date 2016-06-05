package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.server.model.board.Bonus;

public class KingBonusDeckTest {
	
	
	
	Bonus[] kbDeck;
	KingBonusDeck kbd;
	
	@Before
	public void setUp(){
		

		
		
		
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testKingBonusDeckConstructor(){
		
		kbd = new KingBonusDeck(kbDeck);
		
		assertNotNull(kbd);
	}

	

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDraw_1()
		{
		kbDeck = new Bonus[4];
		kbDeck[0]= new Bonus(BonusType.POINT, 15);
		kbDeck[1]= new Bonus(BonusType.POINT, 10);
		kbDeck[2]= new Bonus(BonusType.POINT, 7);
		kbDeck[3]= new Bonus(BonusType.POINT, 5);
		kbDeck[4]= new Bonus(BonusType.POINT, 3);
		KingBonusDeck kingbDeck = new KingBonusDeck(kbDeck);

		KingBonusCard drawnCard = kingbDeck.draw();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.decks.KingBonusDeck.draw(KingBonusDeck.java:39)
		assertNotNull(drawnCard);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDraw(){
		KingBonusDeck kingbDeck = new KingBonusDeck(new Bonus[0]);
		KingBonusCard drawnCard = kingbDeck.draw();

		assertNull(drawnCard);
	}

	@Test
	public void testGetKingqty_1()
		throws Exception {

		int result = KingBonusDeck.getKingqty();
		

		assertEquals(5, result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusDeckTest.class);
	}
}