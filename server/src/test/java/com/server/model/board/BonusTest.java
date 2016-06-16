package com.server.model.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.server.model.gamelogic.Game;
import com.communication.board.BonusDTO;
import com.communication.values.BonusType;

import junit.framework.Assert;

public class BonusTest {

	@Test
	public void testthecreationofaBonus() {
		assertNotNull(new Bonus(BonusType.ASSISTANT, 1));
	}
	
	
		
		
		
	
	@Test(expected=IllegalArgumentException.class)
	public void testAnIllegalArgumentInTheConstructorForTheQuantity(){
		
		Bonus b1= new Bonus(BonusType.ASSISTANT,0);
		
		
		
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
	public void testTheEqualMethod(){
		
		Bonus bn = new Bonus(BonusType.ASSISTANT,2);
		bn.equalsDTO(null);
	}
	
	@Test
	public void testingEqualMethod(){
			
		BonusDTO b = new BonusDTO();
		b.setQuantity(2);
		b.setType(BonusType.ASSISTANT);
		Bonus bn = new Bonus(BonusType.ASSISTANT,2);
		
		assertTrue(bn.equalsDTO(b));
	}
	
	@Test
	public void testingFalseEqualMethod(){
			
		BonusDTO b = new BonusDTO();
		b.setQuantity(2);
		b.setType(BonusType.CARD);
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
	public void testHasNobility(){
		Bonus bn = new Bonus(BonusType.NOBILITY,3);
		BonusType type= bn.getType();
		assertTrue(type.equals(BonusType.NOBILITY));
	}
	
	@Test
	public void testHasNoNobility(){
		Bonus bn = new Bonus(BonusType.ASSISTANT,3);
		BonusType type= bn.getType();
		assertFalse(type.equals(BonusType.NOBILITY));
	}
	
	

}
