package com.communication.board;

import org.junit.*;
import static org.junit.Assert.*;

public class AssistantDTOTest {
	
	
	@Test
	public void testAssistantDTO()
		throws Exception {
		AssistantDTO result = new AssistantDTO();
		assertNotNull(result);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(AssistantDTOTest.class);
	}
}