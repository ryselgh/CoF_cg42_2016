package com.server.actions;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Document;
import com.communication.actions.BuildDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

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
		game = new Game(3, true, null, players);
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

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertNotNull(result);
		
	}

	@Test
	public void testTheKingCity()
		throws Exception {
		Bonus[] bonusToken9 = game.getMap().getCity()[9].getBonusToken().getBonus();
		Build fixture = new Build(game.getMap().getCity()[9], new PermitsCard(b,l3));		
		fixture.setGame(game);

		ActionReturn result = fixture.execute();
		ArrayList<Bonus> arrayList = new ArrayList<Bonus>(Arrays.asList(result.getBonus()));

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertTrue(arrayList.isEmpty());
	}

	@Test
	public void testKingCityAndAnotherOne()
		throws Exception {
		
		Bonus[] bonusToken8 = game.getMap().getCity()[8].getBonusToken().getBonus();
		Bonus[] bonusToken9 = game.getMap().getCity()[9].getBonusToken().getBonus();
		
		Build fixture = new Build (game.getMap().getCity()[8], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn1 = fixture.execute();
		
		fixture = new Build (game.getMap().getCity()[9], new PermitsCard(b,l3));
		fixture.setGame(game);
		@SuppressWarnings("unused")
		ActionReturn bonusReturn2 = fixture.execute();
		
		assertTrue(Arrays.equals(bonusReturn1.getBonus(),bonusReturn2.getBonus()));

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testTwoCiyiesAndTheKingCityIntheMiddle()
		throws Exception {
		Bonus[] bonusToken8 = game.getMap().getCity()[8].getBonusToken().getBonus();
		Bonus[] bonusToken7 = game.getMap().getCity()[7].getBonusToken().getBonus();
		Bonus[] bonusToken9 = game.getMap().getCity()[9].getBonusToken().getBonus();
		
		
		
		Build fixture = new Build (game.getMap().getCity()[8], new PermitsCard(b,l2));
		fixture.setGame(game);
		ActionReturn bonusReturn1 = fixture.execute();
	
		fixture = new Build (game.getMap().getCity()[7], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn2 = fixture.execute();
		
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
	
	@Test
	public void testAnYConfigurationWithKingCityAndAnotherRandomCityNotLinked()
		throws Exception {
		Bonus[] bonusToken8 = game.getMap().getCity()[8].getBonusToken().getBonus();
		Bonus[] bonusToken7 = game.getMap().getCity()[7].getBonusToken().getBonus();
		Bonus[] bonusToken9 = game.getMap().getCity()[9].getBonusToken().getBonus();
		Bonus[] bonusToken11 = game.getMap().getCity()[11].getBonusToken().getBonus();
		Bonus[] bonusToken0 = game.getMap().getCity()[0].getBonusToken().getBonus();
 		
		
		
		Build fixture = new Build (game.getMap().getCity()[8], new PermitsCard(b,l2));
		fixture.setGame(game);
		ActionReturn bonusReturn1 = fixture.execute();
	
		fixture = new Build (game.getMap().getCity()[7], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn2 = fixture.execute();
		
		fixture = new Build (game.getMap().getCity()[9], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn3 = fixture.execute();
		
		fixture = new Build (game.getMap().getCity()[11], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn4 = fixture.execute();
		
		fixture = new Build (game.getMap().getCity()[0], new PermitsCard(b,l3));
		fixture.setGame(game);
		ActionReturn bonusReturn5 = fixture.execute();
		
		Bonus[] result = new Bonus[bonusToken8.length+bonusToken7.length+bonusToken11.length];
		
		
		
		for(int i=0; i<bonusToken8.length; i++)
			result[i] = bonusToken8[i];
		for(int i=0; i<bonusToken7.length; i++)
			result[bonusToken8.length+i] = bonusToken7[i];
		for(int i=0; i<bonusToken11.length; i++)
			result[bonusToken8.length+bonusToken7.length+i] = bonusToken11[i];
		
		ArrayList<Bonus> arrayList = new ArrayList<Bonus>(Arrays.asList(result));
		ArrayList<Bonus> arrayList2= new ArrayList<Bonus>(Arrays.asList(bonusReturn4.getBonus()));
		
		assertTrue(arrayList.containsAll(arrayList2));
		
		
	}
	
	

	@Test
	public void testIfIsValidReturnTrue()
		throws Exception {
		PermitsCard pec = new PermitsCard(b,l2);
		game.getActualPlayer().addPermits(pec);
		Build fixture = new Build(game.getMap().getCity()[4], game.getActualPlayer().getPermits().get(0));
		fixture.setGame(game);
		

		boolean result = fixture.isValid();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
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

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertFalse(result);
	}
	
	
	
	

	

	@Test
	public void testSetterFromDTO_1()
		throws Exception {
		PermitsCard pec = new PermitsCard(b,l3);
		game.getActualPlayer().addPermits(pec);
		Build fixture = new Build(game.getMap().getCity()[4], game.getActualPlayer().getPermits().get(0));
		fixture.setGame(game);
		BuildDTO buildDTO = new BuildDTO();
		buildDTO.setCity(game.getMap().getCity()[4].toDTO(new ArrayList<PlayerDTO>()));
		buildDTO.setPermit(game.getActualPlayer().getPermits().get(0).toDTO());
		
		

		fixture.setterFromDTO(buildDTO, game.getActualPlayer(), game);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:147)
		//       at com.server.model.gamelogic.Game.initializeObjects(Game.java:71)
		//       at com.server.model.gamelogic.Game.<init>(Game.java:49)
		assertTrue(fixture instanceof Build);
	}
//
//	
//
////	@After
////	public void tearDown()
////		throws Exception {
//	Build fixture2 = new Build(game.getMap().getCity()[8], new PermitsCard(b,l3));
//	fixture2.setGame(new Game(3,true,null));
//	
//	ActionReturn result2 = fixture2.execute();
//	
//	Build fixture = new Build(game.getMap().getCity()[9], new PermitsCard(b,l3));		
//	
//	fixture.setGame(new Game(3, true, null));
//
//	ActionReturn result = fixture.execute();
//	
//	fixture = new Build(game.getMap().getCity()[8], new PermitsCard(b,l3));
//	Bonus[] bonus = new Bonus[3];
//	bonus[0] = game.getMap().getCity()[9].getBonusToken().getBonus()[0];
//	bonus[1] = game.getMap().getCity()[8].getBonusToken().getBonus()[0];
//	bonus[2] = game.getMap().getCity()[8].getBonusToken().getBonus()[1];
////	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuildTest.class);
	}
}