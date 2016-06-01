package com.communication.actions;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.CouncilorDTO;
import com.communication.gamelogic.GameDTO;

public class ShiftCouncilSpeedDTOTest {
	@Test
	public void testShiftCouncilSpeedDTO_1()
		throws Exception {
		ShiftCouncilSpeedDTO result = new ShiftCouncilSpeedDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetBalconyIndex_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);

		int result = fixture.getBalconyIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetCouncilor_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);

		CouncilorDTO result = fixture.getCouncilor();

		assertNotNull(result);
		assertEquals(null, result.getColor());
	}

	@Test
	public void testGetErrors_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);

		ArrayList<String> result = fixture.getErrors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);

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
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);

		boolean result = fixture.isDisable();

		assertEquals(true, result);
	}

	@Test
	public void testIsDisable_2()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(false);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);

		boolean result = fixture.isDisable();

		assertEquals(false, result);
	}

	@Test
	public void testSetBalconyIndex_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);
		int balconyIndex = 1;

		fixture.setBalconyIndex(balconyIndex);

	}

	@Test
	public void testSetCouncilor_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);
		CouncilorDTO councilor = new CouncilorDTO();

		fixture.setCouncilor(councilor);

	}

	@Test
	public void testSetDisable_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);
		boolean disable = true;

		fixture.setDisable(disable);

	}

	@Test
	public void testSetErrors_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);
		ArrayList<String> errors = new ArrayList();

		fixture.setErrors(errors);

	}

	@Test
	public void testSetGame_1()
		throws Exception {
		ShiftCouncilSpeedDTO fixture = new ShiftCouncilSpeedDTO();
		fixture.setCouncilor(new CouncilorDTO());
		fixture.setGame(new GameDTO());
		fixture.setDisable(true);
		fixture.setErrors(new ArrayList());
		fixture.setBalconyIndex(1);
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
		new org.junit.runner.JUnitCore().run(ShiftCouncilSpeedDTOTest.class);
	}
}