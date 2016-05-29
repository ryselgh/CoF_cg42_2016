package com.server.model.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.communication.values.CouncilorColor;

import junit.framework.Assert;
import junit.framework.TestCase;

public class BalconyTest{
	
	Councilor[] councilorsInTheBalcony;

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
    	Assert.assertNotNull(councilorsInTheBalcony);
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
		Assert.assertEquals(balcony.getCouncilors(), councilorsInTheBalcony);
		
	}
	
	
		
		
		
	
	
	

		
		
	

}
