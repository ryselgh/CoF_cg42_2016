package com.server.model.board;

import static org.junit.Assert.*;

import org.junit.Test;

import com.communication.board.BonusDTO;
import com.communication.values.BonusType;

public class BonusTest {

	@Test
	public void testthecreationofaBonus() {
		assertNotNull(new Bonus(BonusType.ASSISTANT, 1));
	}
	
	
		
		
		
	
	@Test(expected=IllegalArgumentException.class)
	public void testAnIllegalArgumentInTheConstructorForTheQuantity(){
		
		new Bonus(BonusType.ASSISTANT,0);
		
		
		
	}
	
	@Test
	public void testingGetterQnt(){
		int i = 3;
		Bonus bonus=new Bonus(BonusType.COIN, i);
		assertEquals(bonus.getQnt(), 3);
		
		
	}
	
	@Test
	public void testingGetterType(){
		int i = 3;
		Bonus bonus=new Bonus(BonusType.COIN, i);
		assertEquals(bonus.getType(), BonusType.COIN);
	}
	
	@Test(expected=NullPointerException.class)
	public void testTheEqualMethodThrowAnExceptionIfTheArgumentIsNull(){
		
		Bonus bn = new Bonus(BonusType.ASSISTANT,2);
		bn.equalsDTO(null);
	}
	
	@Test
	public void testingEqualMethodReturnTrue(){
			
		BonusDTO b = new BonusDTO();
		b.setQuantity(2);
		b.setType(BonusType.ASSISTANT);
		Bonus bn = new Bonus(BonusType.ASSISTANT,2);
		
		assertTrue(bn.equalsDTO(b));
	}
	@Test
	public void testingEqualMethodReturnFalse(){
			
		BonusDTO b = new BonusDTO();
		b.setQuantity(3);
		b.setType(BonusType.ASSISTANT);
		Bonus bn = new Bonus(BonusType.ASSISTANT,2);
		
		assertFalse(bn.equalsDTO(b));
	}
	
	@Test
	public void testToDTO(){
		
		Bonus bn= new Bonus(BonusType.ASSISTANT,2);
		BonusDTO bnDTO=bn.toDTO();
		assertTrue(bnDTO instanceof BonusDTO);
		
		
	}
	
	@Test
	public void testHasNobilityReturnTrue(){
		
		Bonus[] bn = new Bonus[2];
		bn[0] = new Bonus(BonusType.NOBILITY,1);
		bn[1] = new Bonus(BonusType.CARD,3);
		boolean result = Bonus.hasNobilityBonus(bn);
		
		assertTrue(result);
	}
	
	@Test
	public void testHasNobilityReturnFalse(){
		
		Bonus[] bn = new Bonus[2];
		bn[0] = new Bonus(BonusType.ASSISTANT,3);
		bn[1] = new Bonus(BonusType.CARD,3);
		boolean result = Bonus.hasNobilityBonus(bn);
		
		assertFalse(result);
	}






	
	
	

}
