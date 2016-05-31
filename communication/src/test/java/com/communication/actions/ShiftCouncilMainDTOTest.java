package com.communication.actions;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CouncilorDTO;
import com.communication.gamelogic.GameDTO;

public class ShiftCouncilMainDTOTest {
	@Test
	public void testShiftCouncilMainDTO_1()
		throws Exception {
		ShiftCouncilMainDTO result = new ShiftCouncilMainDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetBalconyIndex_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());

		int result = fixture.getBalconyIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetCouncilor_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());

		CouncilorDTO result = fixture.getCouncilor();

		assertNotNull(result);
		assertEquals(null, result.getColor());
	}

	@Test
	public void testGetErrors_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());

		ArrayList<String> result = fixture.getErrors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());

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
	public void testIsDisable_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());

		boolean result = fixture.isDisable();

		assertEquals(true, result);
	}

	@Test
	public void testIsDisable_2()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(false);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());

		boolean result = fixture.isDisable();

		assertEquals(false, result);
	}

	@Test
	public void testSetBalconyIndex_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());
		int balconyIndex = 1;

		fixture.setBalconyIndex(balconyIndex);

	}

	@Test
	public void testSetCouncilor_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());
		CouncilorDTO councilor = new CouncilorDTO();

		fixture.setCouncilor(councilor);

	}

	@Test
	public void testSetDisable_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());
		boolean disable = true;

		fixture.setDisable(disable);

	}

	@Test
	public void testSetErrors_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());
		ArrayList<String> errors = new ArrayList();

		fixture.setErrors(errors);

	}

	@Test
	public void testSetGame_1()
		throws Exception {
		ShiftCouncilMainDTO fixture = new ShiftCouncilMainDTO();
		fixture.setBalconyIndex(1);
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setErrors(new ArrayList());
		GameDTO game = new GameDTO();

		fixture.setGame(game);

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
		new org.junit.runner.JUnitCore().run(ShiftCouncilMainDTOTest.class);
	}
}