package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

public class PassDTOTest {
	@Test
	public void testPassDTO()
		throws Exception {
		PassDTO result = new PassDTO();
		assertNotNull(result);
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PassDTOTest.class);
	}
}