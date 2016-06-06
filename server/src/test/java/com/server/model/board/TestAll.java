package com.server.model.board;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CityTest.class,
	MapTest.class,
	KingTest.class,
	NobilityTrackTest.class,
	BonusCardTest.class,
	BonusTokenTest.class,
	EmporiumTest.class,
	PawnTest.class,
	RegionTest.class,
	CouncilorTest.class,
	ColorGroupTest.class,
	AssistantTest.class,
	BonusTest.class,
	BalconyTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
