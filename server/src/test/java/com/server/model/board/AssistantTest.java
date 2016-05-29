package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

public class AssistantTest {
	@Test
	public void testAssistant_1()
		throws Exception {

		Assistant result = new Assistant();

		assertNotNull(result);
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
		new org.junit.runner.JUnitCore().run(AssistantTest.class);
	}
}