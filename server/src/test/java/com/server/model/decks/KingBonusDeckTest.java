package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.decks.KingBonusDeckDTO;
import com.communication.values.BonusType;
import com.server.model.board.Bonus;

public class KingBonusDeckTest {
	
	
	
	Bonus[] kbDeck;
	KingBonusDeck kbd;
	
	@Before
	public void setUp(){
		kbDeck = new Bonus[5];
		kbDeck[0]= new Bonus(BonusType.POINT, 15);
		kbDeck[1]= new Bonus(BonusType.POINT, 10);
		kbDeck[2]= new Bonus(BonusType.POINT, 7);
		kbDeck[3]= new Bonus(BonusType.POINT, 5);
		kbDeck[4]= new Bonus(BonusType.POINT, 3);
		
}
	
	@Test
	public void testTheConstructor(){
		
		KingBonusDeck KingBD = new KingBonusDeck(kbDeck);
		
		assertNotNull(KingBD);
		
	

		
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testKingBonusDeckConstructor(){
		
		kbDeck = new Bonus[4];
		kbDeck[0]= new Bonus(BonusType.POINT, 15);
		kbDeck[1]= new Bonus(BonusType.POINT, 10);
		kbDeck[2]= new Bonus(BonusType.POINT, 7);
		kbDeck[3]= new Bonus(BonusType.POINT, 5);
	
		
		kbd = new KingBonusDeck(kbDeck);
		
		
	}

	

	@Test
	public void testDraw_1()
		{
		kbDeck = new Bonus[5];
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

	@Test
	public void testDraw(){
		KingBonusDeck kingbDeck = new KingBonusDeck(kbDeck);
		KingBonusCard drawnCard1 = kingbDeck.draw();
		KingBonusCard drawnCard2 = kingbDeck.draw();
		KingBonusCard drawnCard3 = kingbDeck.draw();
		KingBonusCard drawnCard4 = kingbDeck.draw();
		KingBonusCard drawnCard5 = kingbDeck.draw();
		KingBonusCard drawnCard = kingbDeck.draw();

		assertNull(drawnCard);
	}

	@Test
	public void testGetKingqty_1()
		throws Exception {

		int result = KingBonusDeck.getKingqty();
		

		assertEquals(5, result);
	}

	@Test
	public void testToDTO(){
		
		KingBonusDeck kd = new KingBonusDeck(kbDeck);
		
		KingBonusDeckDTO kdDTO = kd.toDTO();
		
		assertTrue(kdDTO instanceof KingBonusDeckDTO);
		
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusDeckTest.class);
	}
}