package com.server.model.decks;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class DeckTest {
	

	@Test
	public void testShuffle()
		throws Exception {
		ArrayList<Object> deck = new ArrayList<Object>();
		int size = deck.size();
		Deck.shuffle(deck);
		int result = deck.size();
		assertEquals(size,result);
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DeckTest.class);
	}
}