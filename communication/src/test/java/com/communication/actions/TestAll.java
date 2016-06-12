package com.communication.actions;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	ShiftCouncilSpeedDTOTest.class,
	BuyAssistantDTOTest.class,
	ActionReturnDTOTest.class,
	BuildDTOTest.class,
	PassDTOTest.class,
	ChangeCardsDTOTest.class,
	ObtainPermitDTOTest.class,
	SatisfyKingDTOTest.class,
	ShiftCouncilMainDTOTest.class,
	BuyMainActionDTOTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
