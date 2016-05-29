package com.server.model.board;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.server.values.CouncilorColor;

import junit.framework.Assert;
import junit.framework.TestCase;

public class BalconyTest{
	
	Councilor[] councilorsInTheBalcony;

	@Test
	public void testBalcony_1()
		throws Exception {
		Councilor[] c = new Councilor[] {};

		Balcony result = new Balcony(c);

		assertNotNull(result);
	}

	@Test
	public void testBalcony_2()
		throws Exception {
		Councilor[] c = new Councilor[] {};

		Balcony result = new Balcony(c);

		assertNotNull(result);
	}

	@Test
	public void testGetCouncilors_1()
		throws Exception {
		Balcony fixture = new Balcony(new Councilor[] {});
		fixture.setCouncilor(new Councilor[] {});

		Councilor[] result = fixture.getCouncilors();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testSetCouncilor_1()
		throws Exception {
		Balcony fixture = new Balcony(new Councilor[] {});
		fixture.setCouncilor(new Councilor[] {});
		Councilor[] councilor = new Councilor[] {};

		fixture.setCouncilor(councilor);

	}

	@Before
	public void setUp(){
		councilorsInTheBalcony = new Councilor[4];
		councilorsInTheBalcony[0] = new Councilor (CouncilorColor.BLACK);
		councilorsInTheBalcony[1] = new Councilor (CouncilorColor.BLUESKY);
		councilorsInTheBalcony[2] = new Councilor (CouncilorColor.BLACK);
		councilorsInTheBalcony[3] = new Councilor (CouncilorColor.BLUESKY);
	}
	
	@Test
    public void testpippo(){
    	assertNotNull(councilorsInTheBalcony);
    }
	@Test
	public void testthecreationofaBalcony() {
		assertNotNull(new Balcony(councilorsInTheBalcony));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBalconyIllegalValue() {
		new Balcony(null);
	}

	
	@Test
	public void testTheRightFunctionOfTheGetMethod() {
		
		Balcony balcony=new Balcony (councilorsInTheBalcony);
		assertEquals(balcony.getCouncilors(), councilorsInTheBalcony);
		
	}
	
	
		
		
		
	
	
	

		
		
	


	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BalconyTest.class);
	}
}
