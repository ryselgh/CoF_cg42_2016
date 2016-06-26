package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.values.BonusType;
import com.server.model.board.Bonus;

public class ActionReturnTest {
	
	
	Bonus[] b;
	
	
	@Before
	public void setUp()
		throws Exception {
		
		b = new Bonus[2];
		b[0] = new Bonus(BonusType.ASSISTANT,2);
		b[1] = new Bonus(BonusType.CARD,3);
		
	}
	
	
	@Test
	public void testActionReturn()
		throws Exception {
		boolean success = true;
		String error = "";
		

		ActionReturn result = new ActionReturn(success, error, b);

		assertNotNull(result);
		assertEquals("", result.getError());
		assertEquals(true, result.isSuccess());
	}

	@Test
	public void testGetBonus()
		throws Exception {
		ActionReturn fixture = new ActionReturn(true, "", b);

		Bonus[] result = fixture.getBonus();

		assertNotNull(result);
		assertEquals(2, result.length);
	}

	@Test
	public void testGetError()
		throws Exception {
		ActionReturn fixture = new ActionReturn(false, "non prendi bonus", b);

		String result = fixture.getError();

		assertEquals("non prendi bonus", result);
	}

	@Test
	public void testIsSuccessReturnsTrue()
		throws Exception {
		ActionReturn fixture = new ActionReturn(true, "", b);

		boolean result = fixture.isSuccess();

		assertTrue(result);
	}

	@Test
	public void testIsSuccessReturnsFalse()
		throws Exception {
		ActionReturn fixture = new ActionReturn(false, "", new Bonus[] {});

		boolean result = fixture.isSuccess();

		assertFalse(result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActionReturnTest.class);
	}
}