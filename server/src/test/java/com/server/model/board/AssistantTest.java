package com.server.model.board;

import org.junit.*;

import com.communication.board.AssistantDTO;

import static org.junit.Assert.*;

public class AssistantTest {
	@Test
	public void testAssistant()
		throws Exception {

		Assistant result = new Assistant();

		assertNotNull(result);
	}
	
	@Test 
	public void toDTO(){
		
		Assistant assistant= new Assistant();
		AssistantDTO assDTO = assistant.toDTO();
		assertTrue(assDTO instanceof AssistantDTO);
		
		
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(AssistantTest.class);
	}
}