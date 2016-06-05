package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.server.model.board.Bonus;

public class PermitsDeckTest {
	
	PermitsCard[] permCard;
	Bonus[]b;
	String[]l;
	PermitsCard[] slot;
	
	@Before
	public void setUp(){
		b=new Bonus[2];
		b[0]= new Bonus (BonusType.ASSISTANT,2);
		b[1]= new Bonus (BonusType.CARD,3);
		l=new String[2];
		l[0]= "l";
		l[1]= "m";
		permCard= new PermitsCard[15];
		permCard[0]=new PermitsCard(b,l);
		permCard[1]=new PermitsCard(b,l);	
		permCard[2]=new PermitsCard(b,l);
		permCard[3]=new PermitsCard(b,l);
		permCard[4]=new PermitsCard(b,l);
		permCard[5]=new PermitsCard(b,l);
		permCard[6]=new PermitsCard(b,l);
		permCard[7]=new PermitsCard(b,l);
		permCard[8]=new PermitsCard(b,l);
		permCard[9]=new PermitsCard(b,l);
		permCard[10]=new PermitsCard(b,l);
		permCard[11]=new PermitsCard(b,l);
		permCard[12]=new PermitsCard(b,l);
		permCard[13]=new PermitsCard(b,l);
		permCard[14]=new PermitsCard(b,l);
		
//		slot= new PermitsCrad[2];
//		slot[0]= 
	
    }
	
	@Test(expected=NullPointerException.class)
	public void testPermitsDeck_1()
		throws Exception {
		PermitsCard[] p = new PermitsCard[15] ;
		int r = 1;
		PermitsDeck result = new PermitsDeck(p, r);

		
	}

	@Test
	public void testConstructorOfPermitsDeck(){
		PermitsDeck fixture = new PermitsDeck( permCard, 1);
		assertNotNull(fixture);
	}
	
	public void testChangeCard(){
		PermitsCard[] p = new PermitsCard[] {};
		int r = 1;
		PermitsDeck result = new PermitsDeck(p, r);
		result.changeCards();
	}


	@Test(expected = NullPointerException.class)
	public void testDraw_5()
		throws Exception {
		PermitsDeck fixture = new PermitsDeck(new PermitsCard[] {}, 1);
		fixture.draw();

	}

	@Test
	public void testGetSlot_1()
		throws Exception {
		PermitsDeck fixture = new PermitsDeck(permCard, 1);
		
		boolean draw = false;
		PermitsCard result = fixture.getSlot(1, draw);
		assertNotNull(result);
	}

	@Test
	public void testGetSlot_2()
		throws Exception {
		PermitsDeck fixture = new PermitsDeck(permCard, 1);
		
		boolean draw = false;
		PermitsCard result = fixture.getSlot(0, draw);
		assertNotNull(result);
	}

//	@Test
//	public void testSetSlot1_1()
//		throws Exception {
//		PermitsDeck fixture = new PermitsDeck(new PermitsCard[] {}, 1);
//		PermitsCard card = new PermitsCard(new Bonus[] {}, new String[] {});
//
//		fixture.setSlot1(card);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException: Cards are over
//		//       at com.server.model.decks.PermitsDeck.draw(PermitsDeck.java:73)
//		//       at com.server.model.decks.PermitsDeck.<init>(PermitsDeck.java:29)
//	}
//
//	@Test
//	public void testSetSlot2_1()
//		throws Exception {
//		PermitsDeck fixture = new PermitsDeck(new PermitsCard[] {}, 1);
//		PermitsCard card = new PermitsCard(new Bonus[] {}, new String[] {});
//
//		fixture.setSlot2(card);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException: Cards are over
//		//       at com.server.model.decks.PermitsDeck.draw(PermitsDeck.java:73)
//		//       at com.server.model.decks.PermitsDeck.<init>(PermitsDeck.java:29)
//	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsDeckTest.class);
	}
}