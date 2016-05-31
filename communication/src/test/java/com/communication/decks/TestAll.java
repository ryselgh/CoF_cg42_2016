package com.communication.decks;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PoliticsDeckDTOTest.class,
	PermitsDeckDTOTest.class,
	KingBonusDeckDTOTest.class,
	KingBonusCardDTOTest.class,
	PoliticsCardDTOTest.class,
	PermitsCardDTOTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
