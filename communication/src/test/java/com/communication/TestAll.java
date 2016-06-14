package com.communication;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CommunicationObjectTest.class,
	RoomStatusTest.class,
	ItemOnSaleTest.class,
	LobbyStatusTest.class,
	com.communication.actions.TestAll.class,
	com.communication.board.TestAll.class,
	com.communication.decks.TestAll.class,
	com.communication.gamelogic.TestAll.class,
	com.communication.market.TestAll.class,
})
public class TestAll {

	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
