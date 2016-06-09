package com.server.actions;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
//	BuyMainActionTest.class,
//	ChangeCardsTest.class,
//	ShiftCouncilSpeedTest.class,
	ActionReturnTest.class,
	ActionTest.class,
//	ObtainPermitTest.class,
	BuildTest.class,
//	PassTest.class,
//	SatisfyKingTest.class,
//	ShiftCouncilMainTest.class,
//	BuyAssistantTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
