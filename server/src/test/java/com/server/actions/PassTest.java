package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class PassTest {
	
	@Before
	public void setUp()
		throws Exception {
	}
	
	
	@Test
	public void testPass_1()
		throws Exception {

		Pass result = new Pass();

		assertNotNull(result);
		assertEquals(true, result.isValid());
		assertNotNull(result.execute());
	}

	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PassTest.class);
	}
}