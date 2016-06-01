package com.server.model.decks;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PoliticsCardTest.class,
	PoliticsDeckTest.class,
	PermitsCardTest.class,
	PermitsDeckTest.class,
	KingBonusDeckTest.class,
	KingBonusCardTest.class,
	DeckTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
