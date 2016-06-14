package com.communication.gamelogic;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ItemOnSaleDTOTest.class,
	MainActionDTOTest.class,
	ActionStateDTOTest.class,
	GameDTOTest.class,
	SpeedActionDTOTest.class,
	PlayerDTOTest.class,
	SellItemStateDTOTest.class,
	BuyItemStateDTOTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
