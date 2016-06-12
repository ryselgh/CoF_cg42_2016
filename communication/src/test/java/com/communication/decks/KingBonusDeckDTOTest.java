package com.communication.decks;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import com.communication.board.BonusDTO;
import com.communication.values.BonusType;

import static org.junit.Assert.*;

public class KingBonusDeckDTOTest {
	
	
	BonusDTO bonus;
	KingBonusCardDTO[] kingBonusDeckDTO;
	int KINGQTY=5;
	ArrayList<KingBonusCardDTO> kingBonusDeck; 
	
	@Before
	public void setUp()
		throws Exception {
		
		bonus = new BonusDTO();
		bonus.setQuantity(3);
		bonus.setType(BonusType.POINT);
		kingBonusDeckDTO= new KingBonusCardDTO[KINGQTY];
		kingBonusDeckDTO[0]= new KingBonusCardDTO();
		kingBonusDeckDTO[0].setBonus(bonus);
		kingBonusDeckDTO[0].setNumber(1);
		kingBonusDeckDTO[1]=new KingBonusCardDTO();
		kingBonusDeckDTO[1].setBonus(bonus);
		kingBonusDeckDTO[1].setNumber(2);
		kingBonusDeckDTO[2]=new KingBonusCardDTO();
		kingBonusDeckDTO[2].setBonus(bonus);
		kingBonusDeckDTO[2].setNumber(3);
		kingBonusDeckDTO[3]=new KingBonusCardDTO();
		kingBonusDeckDTO[3].setBonus(bonus);
		kingBonusDeckDTO[3].setNumber(4);
		kingBonusDeckDTO[4]=new KingBonusCardDTO();
		kingBonusDeckDTO[4].setBonus(bonus);
		kingBonusDeckDTO[4].setNumber(5);
		
		kingBonusDeck = new ArrayList<KingBonusCardDTO>(Arrays.asList(kingBonusDeckDTO));
		
	}


	
	//	@Test
//	public void testKingBonusDeckDTO_1()
//		throws Exception {
//		KingBonusDeckDTO result = new KingBonusDeckDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetKingBonusDeck_1()
		throws Exception {
		KingBonusDeckDTO fixture = new KingBonusDeckDTO();
		fixture.setKingBonusDeck(kingBonusDeck);

		ArrayList<KingBonusCardDTO> result = fixture.getKingBonusDeck();

		assertNotNull(result);
		assertEquals(5, result.size());
	}

	@Test
	public void testGetKingqty_1()
		throws Exception {

		int result = KingBonusDeckDTO.getKingqty();

		assertEquals(5, result);
	}

//	@Test
//	public void testSetKingBonusDeck_1()
//		throws Exception {
//		KingBonusDeckDTO fixture = new KingBonusDeckDTO();
//		fixture.setKingBonusDeck(new ArrayList());
//		ArrayList<KingBonusCardDTO> kingBonusDeck = new ArrayList();
//
//		fixture.setKingBonusDeck(kingBonusDeck);
//
//	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KingBonusDeckDTOTest.class);
	}
}