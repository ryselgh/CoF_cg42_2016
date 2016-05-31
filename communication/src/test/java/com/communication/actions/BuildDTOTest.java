package com.communication.actions;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;

public class BuildDTOTest {
	@Test
	public void testBuildDTO_1()
		throws Exception {
		BuildDTO result = new BuildDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetCity_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());

		CityDTO result = fixture.getCity();

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
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());

		ArrayList<String> result = fixture.getErrors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());

		GameDTO result = fixture.getGame();

		assertNotNull(result);
		assertEquals(null, result.getMap());
		assertEquals(null, result.getMarket());
		assertEquals(null, result.getActualPlayer());
		assertEquals(null, result.getSpeedAction());
		assertEquals(null, result.getPlayers());
		assertEquals(0, result.getPlayersQty());
		assertEquals(false, result.isFinalTurn());
		assertEquals(false, result.isDefaultMap());
		assertEquals(null, result.getMainAction());
	}

	@Test
	public void testGetPermit_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());

		PermitsCardDTO result = fixture.getPermit();

		assertNotNull(result);
		assertEquals(null, result.getCityLetter());
		assertEquals(false, result.isFaceDown());
		assertEquals(null, result.getBonuses());
	}

	@Test
	public void testIsDisable_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());

		boolean result = fixture.isDisable();

		assertEquals(true, result);
	}

	@Test
	public void testIsDisable_2()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(false);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());

		boolean result = fixture.isDisable();

		assertEquals(false, result);
	}

	@Test
	public void testSetCity_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());
		CityDTO city = new CityDTO();

		fixture.setCity(city);

	}

	@Test
	public void testSetDisable_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());
		boolean disable = true;

		fixture.setDisable(disable);

	}

	@Test
	public void testSetErrors_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());
		ArrayList<String> errors = new ArrayList();

		fixture.setErrors(errors);

	}

	@Test
	public void testSetGame_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());
		GameDTO game = new GameDTO();

		fixture.setGame(game);

	}

	@Test
	public void testSetPermit_1()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(new CityDTO());
		PermitsCardDTO permit = new PermitsCardDTO();

		fixture.setPermit(permit);

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
		new org.junit.runner.JUnitCore().run(BuildDTOTest.class);
	}
}