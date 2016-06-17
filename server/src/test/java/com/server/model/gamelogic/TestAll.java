package com.server.model.gamelogic;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SpeedActionTest.class,
	GraphMapTest.class,
	GameTest.class,
	PlayerTest.class,
	MainActionTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
