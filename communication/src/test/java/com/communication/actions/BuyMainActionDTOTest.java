package com.communication.actions;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.gamelogic.GameDTO;

public class BuyMainActionDTOTest {
	@Test
	public void testBuyMainActionDTO_1()
		throws Exception {
		BuyMainActionDTO result = new BuyMainActionDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetErrors_1()
		throws Exception {
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());

		ArrayList<String> result = fixture.getErrors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());

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
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());

		boolean result = fixture.isDisable();

		assertEquals(true, result);
	}

	@Test
	public void testIsDisable_2()
		throws Exception {
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(false);
		fixture.setGame(new GameDTO());

		boolean result = fixture.isDisable();

		assertEquals(false, result);
	}

	@Test
	public void testSetDisable_1()
		throws Exception {
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		boolean disable = true;

		fixture.setDisable(disable);

	}

	@Test
	public void testSetErrors_1()
		throws Exception {
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
		ArrayList<String> errors = new ArrayList();

		fixture.setErrors(errors);

	}

	@Test
	public void testSetGame_1()
		throws Exception {
		BuyMainActionDTO fixture = new BuyMainActionDTO();
		fixture.setErrors(new ArrayList());
		fixture.setDisable(true);
		fixture.setGame(new GameDTO());
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
		new org.junit.runner.JUnitCore().run(BuyMainActionDTOTest.class);
	}
}