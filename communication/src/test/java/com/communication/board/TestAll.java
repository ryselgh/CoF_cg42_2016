package com.communication.board;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MapDTOTest.class,
	BonusDTOTest.class,
	BonusTokenDTOTest.class,
	EmporiumDTOTest.class,
	CityDTOTest.class,
	NobilityTrackDTOTest.class,
	PawnDTOTest.class,
	RegionDTOTest.class,
	BalconyDTOTest.class,
	KingDTOTest.class,
	BonusCardDTOTest.class,
	CouncilorDTOTest.class,
	AssistantDTOTest.class,
	ColorGroupDTOTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
