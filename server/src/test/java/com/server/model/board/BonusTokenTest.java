package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.values.BonusType;
public class BonusTokenTest {
	
	Bonus[] bonus;
	
	@Before
	public void setUp() throws Exception {
		
		//sets Bonus
		bonus = new Bonus[2];
		bonus[0]= new Bonus(BonusType.CARD, 2);
		bonus[1]= new Bonus(BonusType.ASSISTANT, 9);
	}

	@Test
	public void testBonusToken() {
		Assert.assertNotNull(new BonusToken(bonus));
	}

	

	@Test
	public void testGetBonus()
		throws Exception {
		BonusToken fixture = new BonusToken(bonus);

		Bonus[] result = fixture.getBonus();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	
	
	@Test(expected=NullPointerException.class)
	public void testBonusTokenIllegalValue() {
		new BonusToken(null);
	}
	
	@Test
	public void testTheDTOConversion(){
		
		BonusToken fixture = new BonusToken(bonus);

		Bonus[] result = fixture.getBonus();
		
		BonusTokenDTO btDTO = fixture.toDTO();
		
		assertTrue(btDTO instanceof BonusTokenDTO);
		
	}
	
	@Test 
	public void testTheBonusTokenDTOSetter(){
		Bonus[] b= new Bonus[2];
		b[0]= new Bonus(null, 1);
		b[1]= new Bonus(null, 1);
		
		
		BonusToken bt = new BonusToken(b);
		
		BonusDTO[] bDTO= new BonusDTO[2];
		bDTO[0]= new BonusDTO();
		bDTO[0].setQuantity(2);
		bDTO[0].setType(BonusType.ASSISTANT);
		bDTO[1] = new BonusDTO();
		bDTO[1].setQuantity(1);
		bDTO[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(bDTO);
		bt.setterFromDTO(btDTO);
		
		assertEquals(bt.getBonus()[0].getType(), BonusType.ASSISTANT);
		
	
		
	}
	
	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BonusTokenTest.class);
	}
}
