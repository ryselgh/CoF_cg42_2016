package com.communication.decks;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import com.communication.board.BonusDTO;
import com.communication.values.BonusType;

import static org.junit.Assert.*;

public class PermitsDeckDTOTest {
	
	BonusDTO[] bonuses;
	String[] cityLetters;
	PermitsCardDTO[] permitsDeckDTO;
	ArrayList<PermitsCardDTO> permitsDeck; 
	PermitsCardDTO[] slot;
	
	@Before
	public void setUp()
		throws Exception {
		
		bonuses = new BonusDTO[2];
		bonuses[0]= new BonusDTO();
		bonuses[0].setQuantity(1);
		bonuses[0].setType(BonusType.ASSISTANT);
		bonuses[1]= new BonusDTO();
		bonuses[1].setQuantity(2);
		bonuses[1].setType(BonusType.CARD);
		
		cityLetters=new String[2];
		cityLetters[0]="l";
		cityLetters[1]="m";
		permitsDeckDTO=new PermitsCardDTO[15];
		permitsDeck = new ArrayList<PermitsCardDTO>(Arrays.asList(permitsDeckDTO));
		slot = new PermitsCardDTO[2];
		slot[0] = new PermitsCardDTO();
		slot[0].setBonuses(bonuses);
		slot[0].setCityLetter(cityLetters);
		slot[0].setFaceDown(false);
				
	}
	
	
	@Test
	public void testPermitsDeckDTO_1()
		throws Exception {
		PermitsDeckDTO result = new PermitsDeckDTO();
		assertNotNull(result);
	}

	@Test
	public void testGetPermitsDeck_1()
		throws Exception {
		PermitsDeckDTO fixture = new PermitsDeckDTO();
		fixture.setRegionCode(1);
		fixture.setSlot(new PermitsCardDTO[2] );
		fixture.setPermitsDeck(permitsDeck);

		ArrayList<PermitsCardDTO> result = fixture.getPermitsDeck();

		assertNotNull(result);
		assertEquals(15, result.size());
	}

	@Test
	public void testGetRegionCode_1()
		throws Exception {
		PermitsDeckDTO fixture = new PermitsDeckDTO();
		fixture.setRegionCode(1);
		fixture.setSlot(new PermitsCardDTO[2] );
		fixture.setPermitsDeck(permitsDeck);

		int result = fixture.getRegionCode();

		assertEquals(1, result);
	}

	@Test
	public void testGetSlot_1()
		throws Exception {
		PermitsDeckDTO fixture = new PermitsDeckDTO();
		fixture.setRegionCode(1);
		fixture.setSlot(slot);
		fixture.setPermitsDeck(permitsDeck);

		PermitsCardDTO result = fixture.getSlot(0);

		assertNotNull(result);
		assertEquals(slot[0], result);
	}



	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsDeckDTOTest.class);
	}
}