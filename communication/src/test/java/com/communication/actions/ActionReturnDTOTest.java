package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class ActionReturnDTOTest {
	@Test
	public void testActionReturn_1()
		throws Exception {
		ActionReturnDTO result = new ActionReturnDTO();
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
		new org.junit.runner.JUnitCore().run(ActionReturnDTOTest.class);
	}
}