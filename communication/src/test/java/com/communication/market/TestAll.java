package com.communication.market;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MarketDTOTest.class,
	AssistantOnSaleDTOTest.class,
	OnSaleDTOTest.class,
	PermitOnSaleDTOTest.class,
	PoliticsOnSaleDTOTest.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
