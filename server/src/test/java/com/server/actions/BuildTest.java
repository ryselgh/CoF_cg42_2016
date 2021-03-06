package com.server.actions;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.actions.BuildDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.server.model.board.Assistant;
import com.server.model.board.Bonus;
import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Game;

public class BuildTest {
	
	String[] l;
	String[] l2;
	String[] l3;
	Bonus[] b;
	Game game;
	String[] players;
	
	
	@Before
	public void setUp()
		throws Exception {
		
		//sets to create the game
		//some letters for the permits
		l = new String[3];
		l[0] = "b";
		l[1] = "c";
		l[2] = "d";
		l2 = new String[3];
		l2[0] = "a";
		l2[1] = "b";
		l2[2] = "e";
		l3 = new String[2];
		l3[0] = "i";
		l3[1] = "j";
		b = new Bonus[2];
		b[0] = new Bonus(BonusType.ASSISTANT,1);
		b[1] = new Bonus(BonusType.COIN,1);
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, "Default1", null, players);
	}

	@Test
	public void testBuildTheConstructor()
		throws Exception {
		Build result = new Build(game.getMap().getCity()[4], new PermitsCard(b,l2));

		assertNotNull(result);
	}

	@Test
	public void testAReturn()
		throws Exception {
		Build fixture = new Build(game.getMap().getCity()[4], new PermitsCard(b,l2));		
		fixture.setGame(game);

		ActionReturn result = fixture.execute();

		
		assertNotNull(result);
		
	}
	
	@Test
	public void testGetCity(){
		
		Build fixture = new Build(game.getMap().getCity()[4], new PermitsCard(b,l2));		
		fixture.setGame(game);
		City city = fixture.getCity();
		assertEquals(city,game.getMap().getCity()[4]);
		
	}
	
	@Test
	public void testAReturnWithError()
		throws Exception {
		PermitsCard e = new PermitsCard(b,l2);
		game.getActualPlayer().getPermits().add(e);
		Build fixture2 = new Build(game.getMap().getCity()[4], e);
		game.getActualPlayer().getAvailableAssistants().clear();
		game.getMap().getCity()[4].setEmporium(new Emporium(game.getActualPlayer()));
		fixture2.setGame(game);
		fixture2.isValid();
		ActionReturn result = fixture2.execute();

		
		assertEquals(result.getError(),"\nYou already have an emporium in the selected city\nYou have not enought assistants");
		
	}
	
	
	
	

	@Test
	public void testTheKingCity()
		throws Exception {
		game.getMap().getCity()[9].getBonusToken().getBonus();
		Build fixture = new Build(game.getMap().getCity()[9], new PermitsCard(b,l3));		
		fixture.setGame(game);

		ActionReturn result = fixture.execute();
		ArrayList<Bonus> arrayList = new ArrayList<Bonus>(Arrays.asList(result.getBonus()));

		
		assertTrue(arrayList.isEmpty());
	}

	@Test
	public void testKingCityAndAnotherOne()
		throws Exception {
		Assistant ass = new Assistant();
		Assistant ass1 = new Assistant();
		
		game.getActualPlayer().addAssistant(ass);
		game.getActualPlayer().addAssistant(ass1);
		game.getMap().getCity()[8].getBonusToken().getBonus();
		game.getMap().getCity()[9].getBonusToken().getBonus();
		
		Build fixture = new Build (game.getMap().getCity()[8], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn1 = fixture.execute();
		
		fixture = new Build (game.getMap().getCity()[9], new PermitsCard(b,l3));
		fixture.setGame(game);
		
		ActionReturn bonusReturn2 = fixture.execute();
		
		assertTrue(Arrays.equals(bonusReturn1.getBonus(),bonusReturn2.getBonus()));

	}

	@Test
	public void testTwoCiyiesAndTheKingCityIntheMiddle()
		throws Exception {
		Bonus[] bonusToken8 = game.getMap().getCity()[8].getBonusToken().getBonus();
		Bonus[] bonusToken7 = game.getMap().getCity()[7].getBonusToken().getBonus();
		Assistant ass = new Assistant();
		Assistant ass1 = new Assistant();
		
		game.getActualPlayer().addAssistant(ass);
		game.getActualPlayer().addAssistant(ass1);
		
		
		
		Build fixture = new Build (game.getMap().getCity()[8], new PermitsCard(b,l2));
		fixture.setGame(game);
		fixture.execute();
	
		fixture = new Build (game.getMap().getCity()[7], new PermitsCard(b,l3));
		fixture.setGame(game);
		fixture.execute();
		
		fixture = new Build (game.getMap().getCity()[9], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn3 = fixture.execute();
		
		Bonus[] result = new Bonus[bonusToken8.length+bonusToken7.length];
		for(int i=0; i<bonusToken8.length; i++)
			result[i] = bonusToken8[i];
		for(int i=0; i<bonusToken7.length; i++)
			result[bonusToken8.length+i] = bonusToken7[i];
		
		assertArrayEquals(result,bonusReturn3.getBonus());
		
		
	}
	
//	@Test
//	public void testAnYConfigurationWithKingCityAndAnotherRandomCityNotLinked()
//		throws Exception {
//		Assistant ass = new Assistant();
//		Assistant ass1 = new Assistant();
//		game.getActualPlayer().addAssistant(ass);
//		game.getActualPlayer().addAssistant(ass1);
//		Bonus[] bonusToken8 = game.getMap().getCity()[8].getBonusToken().getBonus();
//		Bonus[] bonusToken7 = game.getMap().getCity()[7].getBonusToken().getBonus();
//		game.getMap().getCity()[9].getBonusToken().getBonus();
//		Bonus[] bonusToken11 = game.getMap().getCity()[11].getBonusToken().getBonus();
//		game.getMap().getCity()[0].getBonusToken().getBonus();
// 		
//		
//		
//		Build fixture = new Build (game.getMap().getCity()[8], new PermitsCard(b,l2));
//		fixture.setGame(game);
//		fixture.execute();
//	
//		Build fixture1 = new Build (game.getMap().getCity()[7], new PermitsCard(b,l3));
//		fixture1.setGame(game);
//		fixture1.execute();
//		
//		Build fixture2 = new Build (game.getMap().getCity()[9], new PermitsCard(b,l3));
//		fixture2.setGame(game);
//		fixture2.execute();
//		
//		Build fixture3 = new Build (game.getMap().getCity()[11], new PermitsCard(b,l3));
//		fixture3.setGame(game);
//		ActionReturn bonusReturn4 = fixture3.execute();
//		
//		Build fixture4 = new Build (game.getMap().getCity()[0], new PermitsCard(b,l3));
//		fixture4.setGame(game);
//		fixture4.execute();
//		
//		Bonus[] result = new Bonus[bonusToken8.length+bonusToken7.length+bonusToken11.length];
//		
//		
//		
//		for(int i=0; i<bonusToken8.length; i++)
//			result[i] = bonusToken8[i];
//		for(int i=0; i<bonusToken7.length; i++)
//			result[bonusToken8.length+i] = bonusToken7[i];
//		for(int i=0; i<bonusToken11.length; i++)
//			result[bonusToken8.length+bonusToken7.length+i] = bonusToken11[i];
//		
//		ArrayList<Bonus> arrayList = new ArrayList<Bonus>(Arrays.asList(result));
//		ArrayList<Bonus> arrayList2= new ArrayList<Bonus>(Arrays.asList(bonusReturn4.getBonus()));
//		
//		assertTrue(arrayList.containsAll(arrayList2));
//		
//		
//	}
	
	

	@Test
	public void testIfIsValidReturnTrue()
		throws Exception {
		PermitsCard pec = new PermitsCard(b,l2);
		game.getActualPlayer().addPermits(pec);
		Build fixture = new Build(game.getMap().getCity()[4], game.getActualPlayer().getPermits().get(0));
		fixture.setGame(game);
		

		boolean result = fixture.isValid();

		assertTrue(result);
	}

	@Test
	public void testIfIsValidReturnFalse()
		throws Exception {
		PermitsCard pec = new PermitsCard(b,l3);
		game.getActualPlayer().addPermits(pec);
		Build fixture = new Build(game.getMap().getCity()[4], game.getActualPlayer().getPermits().get(0));
		fixture.setGame(game);

		boolean result = fixture.isValid();

		
		assertFalse(result);
	}
	
	@Test
	public void testSetterFromDTO()
		throws Exception {
		PermitsCard pec = new PermitsCard(b,l3);
		game.getActualPlayer().addPermits(pec);
		Build fixture = new Build(game.getMap().getCity()[4], game.getActualPlayer().getPermits().get(0));
		fixture.setGame(game);
		BuildDTO buildDTO = new BuildDTO();
		buildDTO.setCity(game.getMap().getCity()[4].toDTO(new ArrayList<PlayerDTO>()));
		buildDTO.setPermit(game.getActualPlayer().getPermits().get(0).toDTO());
		
		

		fixture.setterFromDTO(buildDTO, game.getActualPlayer(), game);

		
		assertTrue(fixture instanceof Build);
	}
	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuildTest.class);
	}
}