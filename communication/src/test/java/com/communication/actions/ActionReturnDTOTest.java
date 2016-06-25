package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class ActionReturnDTOTest {
	
	
	@Test
	public void testTheConstrucotorOfActionReturn()
		throws Exception {
		ActionReturnDTO result = new ActionReturnDTO();
		assertNotNull(result);
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActionReturnDTOTest.class);
	}
}