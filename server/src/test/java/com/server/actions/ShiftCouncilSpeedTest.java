package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.CouncilorDTO;
import com.communication.values.CouncilorColor;
import com.server.model.board.Councilor;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class ShiftCouncilSpeedTest {
	Game game;
 	String[] players;
 	
 	@Before
 	public void setUp()
 		throws Exception {
		//sets for create the game

 		players = new String[3];
 		players[0] = "1";
 		players[1] = "2";
 		players[2] = "3";
 		game = new Game(3,true,null,players);
 	}
 
 	@Test
 	public void testShiftCouncilSpeed()

 		throws Exception {
 		int balconyIndex = 1;
 		Councilor councilor = new Councilor(CouncilorColor.BLACK);
 
 		ShiftCouncilSpeed result = new ShiftCouncilSpeed(balconyIndex, councilor);
 
 		assertNotNull(result);
 	}
 
 	@Test
 	public void testExecute()
 		throws Exception {
 		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
 		fixture.setGame(game);
 
 		ActionReturn result = fixture.execute();
 
 		
 		assertNotNull(result);
 	}
 
 	@Test
 	public void testExecutethechangementInTheBalcony()
 		throws Exception {
 		Councilor[] balcony = game.getMap().getBalcony(1).getCouncilors();
 		ShiftCouncilMain fixture = new ShiftCouncilMain(1, new Councilor(CouncilorColor.BLACK));
 		fixture.setGame(game);
 
 		ActionReturn result = fixture.execute();
 
 		
 		assertNotEquals(balcony,game.getMap().getBalcony(1).getCouncilors());
 	}
 
 	@Test
 	public void testExecuteCheckIfYouPay()
 		throws Exception {
 		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, new Councilor(CouncilorColor.BLACK));
 		fixture.setGame(game);
 
 		ActionReturn result = fixture.execute();
 
 	
 		assertTrue(game.getActualPlayer().getAvailableAssistants().isEmpty());
 	}
	
	@Test
	public void testExecuteDoesntChangeTheSizeOnThePool()
		throws Exception {
		
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, game.getMap().getCouncilorsPool().get(0));
		fixture.setGame(game);
 

		ActionReturn result = fixture.execute();

		
		assertEquals(game.getMap().getCouncilorsPool().size(),8);
	}
	
	@Test
	public void testExecuteNoError()
		throws Exception {
		ShiftCouncilMain fixture = new ShiftCouncilMain(1, game.getMap().getCouncilorsPool().get(0));
		fixture.setGame(game);
		ActionReturn result = fixture.execute();
		
		

		
		assertEquals(result.getError(),"");
	}
	
	@Test
	public void testExecuteReturnErrors()
		throws Exception {
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, game.getMap().getCouncilorsPool().get(0));
		fixture.setGame(game);
		game.getMap().getCouncilorsPool().clear();
		fixture.isValid();

		ActionReturn result = fixture.execute();
		
		
		

		
		assertEquals(result.getError(), "\nInvalid input councilor");
			}

	@Test
	public void testIsValidReturnTrue()
		throws Exception {
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, game.getMap().getCouncilorsPool().get(0));
		fixture.setGame(game);

		boolean result = fixture.isValid();
		
		assertTrue(result);
	}
	
	@Test
	public void testIsValidReturnFalse()
		throws Exception {
		
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(1, game.getMap().getCouncilorsPool().get(0));
		fixture.setGame(game);
		game.getMap().getCouncilorsPool().clear();
				
		
		

		boolean result = fixture.isValid();

	
		assertFalse(result);
	}
	
	@Test
	public void testSetterFromDTO(){
		
		ShiftCouncilSpeed fixture = new ShiftCouncilSpeed(0, null);
		fixture.setGame(game);
		CouncilorDTO councDTO = new CouncilorDTO();
		councDTO.setColor(CouncilorColor.BLACK);
		ShiftCouncilSpeedDTO scmDTO = new ShiftCouncilSpeedDTO();
		scmDTO.setBalconyIndex(1);
		scmDTO.setCouncilor(councDTO);
		
		fixture.setterFromDTO(scmDTO, game.getActualPlayer(), game);
		assertTrue(fixture instanceof ShiftCouncilSpeed);
		
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ShiftCouncilSpeedTest.class);
	}
}