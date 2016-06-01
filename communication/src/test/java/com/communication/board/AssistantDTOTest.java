package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

public class AssistantDTOTest {
	@Test
	public void testAssistantDTO_1()
		throws Exception {
		AssistantDTO result = new AssistantDTO();
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
		new org.junit.runner.JUnitCore().run(AssistantDTOTest.class);
	}
}