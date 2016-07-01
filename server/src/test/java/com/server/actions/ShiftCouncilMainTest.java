package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;
import com.server.model.board.Councilor;
import com.server.model.gamelogic.Game;


public class ShiftCouncilMainTest {
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
		game = new Game(3,"Default1",null,players);
	}

	@Test
	public void testShiftCouncilMain()
		throws Exception {
		int balconyIndex = 1;
		Councilor councilor = new Councilor(CouncilorColor.BLACK);

		ShiftCouncilMain result = new ShiftCouncilMain(balconyIndex, councilor);

		assertNotNull(result);
	}

	@Test
	public void testExecute()
		throws Exception {
		ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
	}

	@Test
	public void testExecuteGain4Coins()
		throws Exception {
		ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));		
		fixture.setGame(game);

		fixture.execute();

		
		assertEquals(game.getActualPlayer().getCoins(),14);
	}

	@Test
	public void testExecuteChangesInBalcony()
		throws Exception {
		Councilor[] balcony = game.getMap().getBalcony(1).getCouncilors();
		ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));		
		fixture.setGame(game);
		

		fixture.execute();

		
		assertNotEquals(balcony,game.getMap().getBalcony(1).getCouncilors());
		
	}
		@Test
		public void testExecuteThePoolDoesntChangeNumber()
			throws Exception {
			
			ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
			fixture.setGame(game);
	
			
	
			
			assertEquals(game.getMap().getCouncilorsPool().size(),8);
		}
		
		@Test
		public void testExecuteGetBonus()
			throws Exception {
			ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
			fixture.setGame(game);
	
			ActionReturn result = fixture.execute();
			
			
	
			
			assertNull(result.getBonus());
		}
		
		
		@Test
		public void testExecuteNoErrors()
		throws Exception {
			ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
			fixture.setGame(game);
	
		ActionReturn result = fixture.execute();
			
			
	
			
			assertEquals(result.getError(),"");
		}
		
		@Test
		public void testExecuteGivesError()
			throws Exception {
			ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
			fixture.setGame(game);
			game.getMap().getCouncilorsPool().clear();
			fixture.isValid();
	
		ActionReturn result = fixture.execute();
			
			
			
	
			
			assertEquals(result.getError(), "\nInvalid input councilor");
		}

		@Test
		public void testIsValidReturnTrue()
			throws Exception {
			ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
			fixture.setGame(game);
	
			boolean result = fixture.isValid();
	
			
			assertTrue(result);
		}
		@Test
		public void testIsValidReturnFalse()
			throws Exception {
			
			ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
			fixture.setGame(game);
			game.getMap().getCouncilorsPool().clear();
					
			
			
	
			boolean result = fixture.isValid();
	
			
			assertFalse(result);
		}
		



	@Test
	public void testSetterFromDTO()
		throws Exception {
		CouncilorDTO councDTO = new CouncilorDTO();
		councDTO.setColor(CouncilorColor.BLACK);
		ShiftCouncilMain fixture = new ShiftCouncilMain(0, game.getMap().getCouncilorsPool().get(0));
		fixture.setGame(game);
		ShiftCouncilMainDTO scmDTO = new ShiftCouncilMainDTO();
		scmDTO.setBalconyIndex(1);
		scmDTO.setCouncilor(councDTO);
		
		fixture.setterFromDTO(scmDTO, game.getActualPlayer(), game);
		
		assertTrue(fixture instanceof ShiftCouncilMain);
	}
	@Test
	public void testSetterFromDTOWithNull()
		throws Exception {
		CouncilorDTO councDTO = new CouncilorDTO();
		councDTO.setColor(CouncilorColor.BLACK);
		ShiftCouncilMain fixture = new ShiftCouncilMain(0, null);
		fixture.setGame(game);
		ShiftCouncilMainDTO scmDTO = new ShiftCouncilMainDTO();
		scmDTO.setBalconyIndex(1);
		scmDTO.setCouncilor(councDTO);
		
		fixture.setterFromDTO(scmDTO, game.getActualPlayer(), game);
		
		assertTrue(fixture instanceof ShiftCouncilMain);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ShiftCouncilMainTest.class);
	}
}