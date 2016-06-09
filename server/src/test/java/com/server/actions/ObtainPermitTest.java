package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.ObtainPermitDTO;
import com.communication.decks.PoliticsCardDTO;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ObtainPermitTest {
//	@Test
//	public void testObtainPermit_1()
//		throws Exception {
//		PoliticsCard[] politics = new PoliticsCard[] {};
//		int regionIndex = 1;
//		int slot = 1;
//
//		ObtainPermit result = new ObtainPermit(politics, regionIndex, slot);
//
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_1()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//
//		ActionReturn result = fixture.execute();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_2()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//
//		ActionReturn result = fixture.execute();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testExecute_3()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//
//		ActionReturn result = fixture.execute();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testIsValid_1()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//
//		boolean result = fixture.isValid();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertTrue(result);
//	}
//
//	@Test
//	public void testIsValid_2()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//
//		boolean result = fixture.isValid();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//		assertTrue(result);
//	}
//
//	@Test
//	public void testSetGame_1()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
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
//
//	@Test
//	public void testSetterFromDTO_1()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//		ObtainPermitDTO opDTO = new ObtainPermitDTO();
//		opDTO.setPolitics(new PoliticsCardDTO[] {});
//		opDTO.setRegionIndex(1);
//		opDTO.setSlot(1);
//		Player player = new Player(1);
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(opDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Test
//	public void testSetterFromDTO_2()
//		throws Exception {
//		ObtainPermit fixture = new ObtainPermit(new PoliticsCard[] {}, 1, 1);
//		ObtainPermitDTO obtainPermitDTO = new ObtainPermitDTO();
//		obtainPermitDTO.setPolitics(new PoliticsCardDTO[] {});
//		obtainPermitDTO.setRegionIndex(1);
//		obtainPermitDTO.setSlot(1);
//		fixture.setterFromDTO(obtainPermitDTO, new Player(1), new Game(1, true, (Document) null));
//		fixture.counter = 1;
//		fixture.jollycnt = 1;
//		ObtainPermitDTO opDTO = new ObtainPermitDTO();
//		opDTO.setPolitics(new PoliticsCardDTO[] {});
//		opDTO.setRegionIndex(1);
//		opDTO.setSlot(1);
//		Player player = new Player(1);
//		Game game = new Game(1, true, (Document) null);
//
//		fixture.setterFromDTO(opDTO, player, game);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:147)
//		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
//		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
//	}
//
//	@Before
//	public void setUp()
//		throws Exception {
//	}
//
//	@After
//	public void tearDown()
//		throws Exception {
//	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ObtainPermitTest.class);
	}
}