package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class PassTest {
	

	
	
	@Test
	public void testPass()
		throws Exception {

		Pass result = new Pass();

		assertNotNull(result);
		assertEquals(true, result.isValid());
		assertNotNull(result.execute());
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PassTest.class);
	}
}