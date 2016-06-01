package com.communication.actions;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CityDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;

public class SatisfyKingDTOTest {
	@Test
	public void testSatisfyKingDTO_1()
		throws Exception {
		SatisfyKingDTO result = new SatisfyKingDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetCounter_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		int result = fixture.getCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetDestination_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		CityDTO result = fixture.getDestination();

		assertNotNull(result);
		assertEquals(null, result.getColor());
		assertEquals(null, result.getToken());
		assertEquals(null, result.getName());
		assertEquals(null, result.getSlot());
		assertEquals(null, result.getCloseCities());
		assertEquals(0, result.getPlayerNum());
	}

	@Test
	public void testGetErrors_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		ArrayList<String> result = fixture.getErrors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMainAction());
		assertEquals(0, result.getPlayersQty());
		assertEquals(null, result.getPlayers());
		assertEquals(null, result.getMarket());
		assertEquals(null, result.getSpeedAction());
		assertEquals(false, result.isFinalTurn());
		assertEquals(false, result.isDefaultMap());
		assertEquals(null, result.getActualPlayer());
	}

	@Test
	public void testGetJollycnt_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		int result = fixture.getJollycnt();

		assertEquals(1, result);
	}

	@Test
	public void testGetPolitics_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		PoliticsCardDTO[] result = fixture.getPolitics();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testIsDisable_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		boolean result = fixture.isDisable();

		assertEquals(true, result);
	}

	@Test
	public void testIsDisable_2()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(false);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});

		boolean result = fixture.isDisable();

		assertEquals(false, result);
	}

	@Test
	public void testSetCounter_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		int counter = 1;

		fixture.setCounter(counter);

	}

	@Test
	public void testSetDestination_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		CityDTO destination = new CityDTO();

		fixture.setDestination(destination);

	}

	@Test
	public void testSetDisable_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		boolean disable = true;

		fixture.setDisable(disable);

	}

	@Test
	public void testSetErrors_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		ArrayList<String> errors = new ArrayList();

		fixture.setErrors(errors);

	}

	@Test
	public void testSetGame_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		GameDTO game = new GameDTO();

		fixture.setGame(game);

	}

	@Test
	public void testSetJollycnt_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		int jollycnt = 1;

		fixture.setJollycnt(jollycnt);

	}

	@Test
	public void testSetPolitics_1()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setDestination(new CityDTO());
		fixture.setJollycnt(1);
		fixture.setCounter(1);
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setErrors(new ArrayList());
		fixture.setPolitics(new PoliticsCardDTO[] {});
		PoliticsCardDTO[] politics = new PoliticsCardDTO[] {};

		fixture.setPolitics(politics);

	}

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SatisfyKingDTOTest.class);
	}
}