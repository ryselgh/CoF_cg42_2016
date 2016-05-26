package com.server.model.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.server.model.gamelogic.Game;
import com.server.values.BonusType;

import junit.framework.Assert;

public class BonusCardTest {

	@Test
	public void testBonusCard() {
		Bonus b = new Bonus(BonusType.POINT, 5);
		assertNotNull(new BonusCard(b));
	}

	@Test
	public void testGetBonus() {
		Bonus b = new Bonus(BonusType.POINT, 5);
		BonusCard bc = new BonusCard(b);
		Assert.assertEquals(bc.getBonus(), b );
		
	}

}
