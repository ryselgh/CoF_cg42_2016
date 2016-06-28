package com.server.model.board;

import static org.junit.Assert.*;


import org.junit.Test;

import com.communication.board.BonusCardDTO;
import com.communication.values.BonusType;



public class BonusCardTest {

	@Test
	public void testBonusCard() {
		Bonus b = new Bonus(BonusType.POINT, 5);
		assertNotNull(new BonusCard(b));
	
	
	}
	@Test(expected=NullPointerException.class)
	public void testIfThereIsAnIllegalArgument(){
		
		new BonusCard(null);
		
	}
	

	@Test
	public void testGetBonus() {
		Bonus b = new Bonus(BonusType.POINT, 5);
		BonusCard bc = new BonusCard(b);
		assertEquals(bc.getBonus(), b );
		
	}
	
	@Test
	public void testToDTO(){
		Bonus b= new Bonus(BonusType.ASSISTANT,3);
		BonusCard bc = new BonusCard(b);
		BonusCardDTO bcDTO = bc.toDTO();
		
		assertTrue(bcDTO instanceof BonusCardDTO);
		
	}

}
