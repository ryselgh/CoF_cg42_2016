package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.decks.PermitsDeckDTO;
import com.communication.values.BonusType;
import com.server.model.board.Bonus;

public class PermitsDeckTest {
	
	PermitsCard[] permCard;
	Bonus[]b;
	String[]l;
	PermitsCard[] slot;
	
	@Before
	public void setUp(){
		//declaring this array of pemitscards as an option to the creation of a game
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
		

    }
	
	@Test(expected=NullPointerException.class)
	public void testTheThrowingOfTheExceptionByTheConstructor()
		throws Exception {
		PermitsCard[] p = null ;
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
	public void testDraw_ThrowsExceptionIfTheDeckIsEmpty()
		throws Exception {
		PermitsDeck fixture = new PermitsDeck(new PermitsCard[] {}, 1);
		fixture.draw();

	}

	@Test
	public void testGetSlot1()
		throws Exception {
		PermitsDeck fixture = new PermitsDeck(permCard, 1);
		
		boolean draw = false;
		PermitsCard result = fixture.getSlot(1, draw);
		assertNotNull(result);
	}

	@Test
	public void testGetSlot2()
		throws Exception {
		PermitsDeck fixture = new PermitsDeck(permCard, 1);
		
		boolean draw = false;
		
		PermitsCard result = fixture.getSlot(0, draw);
		assertNotNull(result);
	}



	@Test
	public void toDTO(){
		
		PermitsDeck fixture = new PermitsDeck(permCard, 1);
		PermitsDeckDTO pdDTO = fixture.toDTO();
		
		assertTrue(pdDTO instanceof PermitsDeckDTO);
		
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsDeckTest.class);
	}
}