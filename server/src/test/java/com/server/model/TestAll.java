package com.server.model;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	com.server.model.board.TestAll.class,
	com.server.model.decks.TestAll.class,
	com.server.model.gamelogic.TestAll.class,
	com.server.model.market.TestAll.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
