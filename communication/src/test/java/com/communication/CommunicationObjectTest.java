package com.communication;

import org.junit.*;
import static org.junit.Assert.*;

public class CommunicationObjectTest {
	
	String s;
	Object o;
	
	@Before
	public void setUp()
		throws Exception{
		
		s = "ciao";
		o = new Object();
	}
	@Test
	public void testCommunicationObject()
		throws Exception {
		
		

		CommunicationObject result = new CommunicationObject(s, o);

		assertNotNull(result);
		
	}
	
	// getters and setters are tested together


	@Test
	public void testGetMsg()
		throws Exception {
		CommunicationObject fixture =  new CommunicationObject(s, o);

		String result = fixture.getMsg();

		assertEquals("ciao", result);
	}

	@Test
	public void testGetObj()
		throws Exception {
		CommunicationObject fixture = new CommunicationObject(s,o);

		Object result = fixture.getObj();

		assertNotNull(result);
	}

	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CommunicationObjectTest.class);
	}
}