package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.ActionDTO;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ActionTest {
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

//	@Test
//	public void testSetGame_1()
//		throws Exception {
//		Action fixture = new Action();
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setGame(game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}

//	@Test
//	public void testSetterFromDTO_1()
//		throws Exception {
//		Action fixture = new Action();
//		ActionDTO actDTO = new ActionDTO();
//		Player player = new Player(1);
//		
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(actDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ActionTest.class);
	}
}