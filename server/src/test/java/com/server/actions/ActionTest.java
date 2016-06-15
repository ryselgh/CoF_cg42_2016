package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.ActionDTO;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ActionTest {
	
	Game game;
	String[] players;
	
	@Before
	public void setUp()
		throws Exception {
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		
		game = new Game (3,true,null, players);
	}

	@Test
	public void testExecute_1()
		throws Exception {
		Action fixture = new Action();

		ActionReturn result = fixture.execute();

		assertEquals(null, result);
	}

	@Test
	public void testIsValid_1()
		throws Exception {
		Action fixture = new Action();

		boolean result = fixture.isValid();

		assertTrue(result);
	}




	@Test
	public void testSetterFromDTO_1()
		throws Exception {
		Action fixture = new Action();
		ActionDTO actDTO = new ActionDTO();
		
		fixture.setterFromDTO(actDTO, game.getActualPlayer(), game);

	
	}

	
	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActionTest.class);
	}
}