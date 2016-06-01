package com.server.model.decks;

import org.junit.*;

import com.communication.values.CouncilorColor;

import static org.junit.Assert.*;

public class PoliticsDeckTest {
	

	@Test(expected=IllegalArgumentException.class)
	public void testDiscard_1()
		throws Exception {
		PoliticsDeck fixture = new PoliticsDeck();
		fixture.discard(null);

		}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsDeckTest.class);
	}
}