package com.server.model.decks;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.board.BonusDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.values.BonusType;
import com.server.model.board.Bonus;
import com.server.model.gamelogic.Player;

public class PermitsCardTest {
	
	Bonus[] b;
	Bonus[] b2;
	String[]l;
	BonusDTO[] bDTO;
	BonusDTO[] b1DTO;
	
	@Before
	public void setUp(){
		//declaring this array of pemitscards as an option to the creation of a game
		b=new Bonus[2];
		b[0]= new Bonus (BonusType.ASSISTANT,2);
		b[1]= new Bonus (BonusType.CARD,3);
		b2=new Bonus[2];
		b2[0]= new Bonus (BonusType.POINT,2);
		b2[1]= new Bonus (BonusType.CARD,3);
		l=new String[2];
		l[0]= "l";
		l[1]= "m";
		bDTO = new BonusDTO[2];
		bDTO[0]= b[0].toDTO();
		bDTO[1]= b[1].toDTO();
		
		b1DTO = new BonusDTO[2];
		b1DTO[0]= b[0].toDTO();
		b1DTO[1]= new BonusDTO();
		b1DTO[1].setQuantity(1);
		b1DTO[1].setType(BonusType.CARD);
		
		
		
		
		
	}
	
	@Test
	public void testPermitsCard(){
		assertNotNull(new PermitsCard(b,l));
		}
	
	
	@Test(expected=NullPointerException.class)
	public void testIfItsNullThrowsException(){
		new PermitsCard(null,null);
	}
	
	

	@Test
	public void testGetBonus(){
		PermitsCard fixture = new PermitsCard(b,l);
		Bonus[] result = fixture.getBonus();
		assertNotNull(result);
		assertEquals(b,result);
	}

	@Test
	public void testGetCityLetter(){
		PermitsCard fixture = new PermitsCard(b,l);
		String[] result = fixture.getCityLetter();
		assertNotNull(result);
		Assert.assertEquals(l,result);
	}

	@Test
	public void testIsFaceDownSetsTrue(){
		PermitsCard fixture = new PermitsCard(b,l);
		fixture.setFaceDown(true);
		boolean result = fixture.isFaceDown();
		Assert.assertTrue(result);
	}

	

	@Test
	public void testIsFaceDownSetsFalse(){
		PermitsCard fixture = new PermitsCard(b,l);
		fixture.setFaceDown(false);
		boolean result = fixture.isFaceDown();
		Assert.assertFalse(result);
	}
	
	@Test 
	public void testToDTO(){
		
		PermitsCard fixture = new PermitsCard(b,l);
		PermitsCardDTO pcDTO= fixture.toDTO();
		
		assertTrue(pcDTO instanceof PermitsCardDTO);
		
	}
	
	@Test
	public void testEqualsDTOReturnsTrue(){
		PermitsCard fixture = new PermitsCard(b,l);
		PermitsCardDTO pcDTO= new PermitsCardDTO();
		pcDTO.setBonuses(bDTO);
		pcDTO.setCityLetter(l);
		
		assertTrue(fixture.equalsDTO(pcDTO));
	}
	
	@Test
	public void testEqualsDTO2ReturnsFalse(){
		PermitsCard fixture = new PermitsCard(b,l);
		PermitsCardDTO pcDTO= new PermitsCardDTO();
		pcDTO.setBonuses(b1DTO);
		pcDTO.setCityLetter(l);
		
		assertFalse(fixture.equalsDTO(pcDTO));
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullInEquals(){
		PermitsCard fixture = new PermitsCard(b,l);
		fixture.equalsDTO(null);
	}
	
	@Test
	public void testFromDTO(){
		Player p = new Player("1");
		PermitsCard fixture = new PermitsCard(b,l);
		PermitsCard fixture2 = new PermitsCard(b2,l);
	
		PermitsCardDTO pcDTO = new PermitsCardDTO();
		pcDTO.setBonuses(bDTO);
		pcDTO.setCityLetter(l);
		
		p.addPermits(fixture);
		p.addPermits(fixture2);
		
		PermitsCard pc = new PermitsCard(b, l);
		PermitsCard pc2 = pc.fromDTO(pcDTO, p);
		assertEquals(pc2,fixture);
	
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsCardTest.class);
	}
}