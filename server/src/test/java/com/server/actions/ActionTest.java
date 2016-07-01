package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.actions.ActionDTO;
import com.server.model.gamelogic.Game;

public class ActionTest {
	
	Game game;
	String[] players;
	
	@Before
	public void setUp()
		throws Exception {
		//sets to create the game
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		
		game = new Game (3,"Default1",null, players);
	}

	@Test
	public void testExecute()
		throws Exception {
		Action fixture = new Action();

		ActionReturn result = fixture.execute();

		assertEquals(null, result);
	}

	@Test
	public void testIsValid()
		throws Exception {
		Action fixture = new Action();

		boolean result = fixture.isValid();

		assertTrue(result);
	}
	
	




	@Test
	public void testSetterFromDTO()
		throws Exception {
		Action fixture = new Action();
		ActionDTO actDTO = new ActionDTO();
		
		fixture.setterFromDTO(actDTO, game.getActualPlayer(), game);
		assertTrue(fixture instanceof Action);
	
	}

	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActionTest.class);
	}
}