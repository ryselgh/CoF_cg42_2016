package com.server.model.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.server.model.gamelogic.Game;
import com.communication.values.BonusType;

import junit.framework.Assert;

public class BonusTest {

	@Test
	public void testthecreationofaBonus() {
		assertNotNull(new Bonus(BonusType.ASSISTANT, 1));
	}
	
	@Test
	public void testAnIllegalArgumentInTheCretor(){
		
	}
	
	@Test
	public void testingGetterQnt(){
		int i = 3;
		Bonus bonus=new Bonus(BonusType.COIN, i);
		Assert.assertEquals(bonus.getQnt(), 3);
		
		
	}
	
	@Test
	public void testingGetterType(){
		int i = 3;
		Bonus bonus=new Bonus(BonusType.COIN, i);
		Assert.assertEquals(bonus.getType(), BonusType.COIN);
	}

}
