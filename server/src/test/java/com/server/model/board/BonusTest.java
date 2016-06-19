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
	public void testToDTO(){
		
		Bonus bn= new Bonus(BonusType.ASSISTANT,2);
		BonusDTO bnDTO=bn.toDTO();
		assertTrue(bnDTO instanceof BonusDTO);
		
		
	}
	
//	@Test
//	public void testHasNobility(){
//		
//		Bonus[] bn = new Bonus[2];
//		bn[0] = new Bonus(BonusType.NOBILITY,1);
//		bn[0] = new Bonus(BonusType.CARD,3);
//		boolean result = bn[0].hasNobilityBonus(bn);
//		
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testHasNobility2(){
//		
//		Bonus[] bn = new Bonus[2];
//		bn[0] = new Bonus(BonusType.ASSISTANT,3);
//		bn[0] = new Bonus(BonusType.CARD,3);
//		boolean result = bn[0].hasNobilityBonus(bn);
//		
//		assertFalse(result);
//	}






	
	
	

}
