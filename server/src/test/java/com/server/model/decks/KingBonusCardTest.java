package com.server.model.decks;

import static org.junit.Assert.*;

import org.junit.*;

import com.communication.values.BonusType;
import com.server.model.board.Bonus;



public class KingBonusCardTest {
	@Test
	public void testKingBonusCardConstructor(){
		int n = 2;
		Bonus bonus = new Bonus(BonusType.POINT, 1);
		KingBonusCard result = new KingBonusCard(n, bonus);
		assertNotNull(result);
	}
	
	
	@Test
	public void testIfTheConstructorAcceptsOnlyPointBonus(){
		int n=1;
		Bonus bonus= new Bonus (BonusType.ASSISTANT,1);
		new KingBonusCard(n,bonus);
		assertNotEquals(bonus.getType(), BonusType.POINT);
	}
	
	@Test
	public void testGetBonus(){
		Bonus bonus= new Bonus(BonusType.POINT,2);
		KingBonusCard kingbc = new KingBonusCard(3, bonus);
		assertEquals(kingbc.getBonus(),bonus);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusCardTest.class);
	}
}