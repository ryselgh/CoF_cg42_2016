package com.communication.actions;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;

public class ObtainPermitDTOTest {
	@Test
	public void testObtainPermitDTO_1()
		throws Exception {
		ObtainPermitDTO result = new ObtainPermitDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetCounter_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		int result = fixture.getCounter();

		assertEquals(1, result);
	}

	@Test
	public void testGetErrors_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		ArrayList<String> result = fixture.getErrors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetGame_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

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
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		int result = fixture.getJollycnt();

		assertEquals(1, result);
	}

	@Test
	public void testGetPolitics_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		PoliticsCardDTO[] result = fixture.getPolitics();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetRegionIndex_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		int result = fixture.getRegionIndex();

		assertEquals(1, result);
	}

	@Test
	public void testGetSlot_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		int result = fixture.getSlot();

		assertEquals(1, result);
	}

	@Test
	public void testIsDisable_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		boolean result = fixture.isDisable();

		assertEquals(true, result);
	}

	@Test
	public void testIsDisable_2()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(false);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);

		boolean result = fixture.isDisable();

		assertEquals(false, result);
	}

	@Test
	public void testSetCounter_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		int counter = 1;

		fixture.setCounter(counter);

	}

	@Test
	public void testSetDisable_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		boolean disable = true;

		fixture.setDisable(disable);

	}

	@Test
	public void testSetErrors_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		ArrayList<String> errors = new ArrayList();

		fixture.setErrors(errors);

	}

	@Test
	public void testSetGame_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		GameDTO game = new GameDTO();

		fixture.setGame(game);

	}

	@Test
	public void testSetJollycnt_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		int jollycnt = 1;

		fixture.setJollycnt(jollycnt);

	}

	@Test
	public void testSetPolitics_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		PoliticsCardDTO[] politics = new PoliticsCardDTO[] {};

		fixture.setPolitics(politics);

	}

	@Test
	public void testSetRegionIndex_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		int regionIndex = 1;

		fixture.setRegionIndex(regionIndex);

	}

	@Test
	public void testSetSlot_1()
		throws Exception {
		ObtainPermitDTO fixture = new ObtainPermitDTO();
		fixture.setDisable(true);
		fixture.setPolitics(new PoliticsCardDTO[] {});
		fixture.setGame(new GameDTO());
		fixture.setCounter(1);
		fixture.setErrors(new ArrayList());
		fixture.setRegionIndex(1);
		fixture.setSlot(1);
		fixture.setJollycnt(1);
		int slot = 1;

		fixture.setSlot(slot);

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
		new org.junit.runner.JUnitCore().run(ObtainPermitDTOTest.class);
	}
}