package com.server.model.decks;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class DeckTest {
	@Test
	public void testHasNext()
		throws Exception {
		Deck fixture = new PoliticsDeck();

		boolean result = fixture.hasNext();

		assertEquals(false, result);
	}

	@Test
	public void testNext()
		throws Exception {
		Deck fixture = new PoliticsDeck();

		Object result = fixture.next();

		assertEquals(null, result);
	}

	@Test
	public void testShuffle()
		throws Exception {
		ArrayList<Object> deck = new ArrayList();

		Deck.shuffle(deck);

	}

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DeckTest.class);
	}
}