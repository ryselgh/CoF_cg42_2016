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
		for(PermitsCardDTO pc: permitsDeckDTO){
			pc= new PermitsCardDTO();
			pc.setBonuses(bonuses);
			pc.setCityLetter(cityLetters);
			
		}
		
		permitsDeck = new ArrayList<PermitsCardDTO>(Arrays.asList(permitsDeckDTO));
	}
	
	
//	@Test
//	public void testPermitsDeckDTO_1()
//		throws Exception {
//		PermitsDeckDTO result = new PermitsDeckDTO();
//		assertNotNull(result);
//	}

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

//	@Test
//	public void testGetSlot_1()
//		throws Exception {
//		PermitsDeckDTO fixture = new PermitsDeckDTO();
//		fixture.setRegionCode(1);
//		fixture.setSlot(new PermitsCardDTO[2] );
//		fixture.setPermitsDeck(permitsDeck);
//
//		PermitsCardDTO[] result = fixture.getSlot();
//
//		assertNotNull(result);
//		assertEquals(2, result.length);
//	}

//	@Test
//	public void testSetPermitsDeck_1()
//		throws Exception {
//		PermitsDeckDTO fixture = new PermitsDeckDTO();
//		fixture.setRegionCode(1);
//		fixture.setSlot(new PermitsCardDTO[] {});
//		fixture.setPermitsDeck(new ArrayList());
//		ArrayList<PermitsCardDTO> permitsDeck = new ArrayList();
//
//		fixture.setPermitsDeck(permitsDeck);
//
//	}
//
//	@Test
//	public void testSetRegionCode_1()
//		throws Exception {
//		PermitsDeckDTO fixture = new PermitsDeckDTO();
//		fixture.setRegionCode(1);
//		fixture.setSlot(new PermitsCardDTO[] {});
//		fixture.setPermitsDeck(new ArrayList());
//		int regionCode = 1;
//
//		fixture.setRegionCode(regionCode);
//
//	}
//
//	@Test
//	public void testSetSlot_1()
//		throws Exception {
//		PermitsDeckDTO fixture = new PermitsDeckDTO();
//		fixture.setRegionCode(1);
//		fixture.setSlot(new PermitsCardDTO[] {});
//		fixture.setPermitsDeck(new ArrayList());
//		PermitsCardDTO[] slot = new PermitsCardDTO[] {};
//
//		fixture.setSlot(slot);
//
//	}

	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PermitsDeckDTOTest.class);
	}
}