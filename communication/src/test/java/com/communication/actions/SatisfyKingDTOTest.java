package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.EmporiumDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.communication.values.CouncilorColor;

public class SatisfyKingDTOTest {
	
	PoliticsCardDTO[] polDTO;
	CityDTO cityDTO;
	String[] closeCities;
	BonusDTO[] b;
	BonusTokenDTO btDTO;
	
	@Before
	public void setUp()
		throws Exception {
		
		//in the setUp there are the 4 cards used in the methods of the test
		
		polDTO = new PoliticsCardDTO[4];
		polDTO[0]= new PoliticsCardDTO();
		polDTO[0].setColor(CouncilorColor.JOLLY);
		polDTO[1]= new PoliticsCardDTO();
		polDTO[1].setColor(CouncilorColor.JOLLY);
		polDTO[2]= new PoliticsCardDTO();
		polDTO[2].setColor(CouncilorColor.JOLLY);
		polDTO[3]= new PoliticsCardDTO();
		polDTO[3].setColor(CouncilorColor.JOLLY);
		
		//the city CloseCities
		closeCities=new String[2];
		closeCities[0]="Osium";
		closeCities[1]="Karl";
		
		//the bonus on the permitsCard
		b = new BonusDTO[2];
		b[0]=new BonusDTO();
		b[0].setQuantity(1);
		b[0].setType(BonusType.ASSISTANT);
		b[1]=new BonusDTO();
		b[1].setQuantity(2);
		b[1].setType(BonusType.CARD);
		btDTO = new BonusTokenDTO();
		btDTO.setBonus(b);
		
		//the city
		cityDTO = new CityDTO();
		cityDTO.setName("Juvelar");
		cityDTO.setColor(CityColor.BLUE);
		cityDTO.setCloseCities(closeCities);
		cityDTO.setSlot(new EmporiumDTO[10] );
		cityDTO.setPlayerNum(1);
		cityDTO.setToken(btDTO);
	}
	
	
	@Test
	public void testSatisfyKingDTO()
		throws Exception {

		SatisfyKingDTO result = new SatisfyKingDTO();

		assertNotNull(result);
		
	}
	
	// getters and setters are tested together

	@Test
	public void testGetDestination()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setPolitics(polDTO);
		fixture.setDestination(cityDTO);

		CityDTO result = fixture.getDestination();

		assertNotNull(result);
		assertEquals(btDTO, result.getToken());
		assertEquals("Juvelar", result.getName());
		assertEquals(CityColor.BLUE, result.getColor());
		assertEquals(1, result.getPlayerNum());
		assertEquals(closeCities, result.getCloseCities());
	}

	@Test
	public void testGetPolitics()
		throws Exception {
		SatisfyKingDTO fixture = new SatisfyKingDTO();
		fixture.setPolitics(polDTO);
		fixture.setDestination(cityDTO);

		PoliticsCardDTO[] result = fixture.getPolitics();

		assertNotNull(result);
		assertEquals(4, result.length);
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SatisfyKingDTOTest.class);
	}
}