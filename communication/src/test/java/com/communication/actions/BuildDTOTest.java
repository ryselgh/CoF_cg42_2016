package com.communication.actions;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.EmporiumDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;

public class BuildDTOTest {
	
	CityDTO cityDTO;
	String[] closeCities;
	BonusDTO[] b;
	BonusTokenDTO btDTO;
	PermitsCardDTO pcDTO;
	String[] letters;
	
	@Before
	public void setUp()
		throws Exception {
		
		//there is instantiated what the action Build needs to be permormed  
		//the permits letters
		letters = new String[2];
		letters[0] = "f";
		letters[1] = "j";
		
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
		
		//the permitsCard
		pcDTO = new PermitsCardDTO();
		pcDTO.setBonuses(b);
		pcDTO.setCityLetter(letters);
		
	}
	
	@Test
	public void testBuildDTO()
		throws Exception {

		BuildDTO result = new BuildDTO();

		assertNotNull(result);
		assertEquals(null, result.getCity());
		assertEquals(null, result.getPermit());
	}
	
	// getters and setters are tested together

	@Test
	public void testGetCity()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setPermit(new PermitsCardDTO());
		fixture.setCity(cityDTO);

		CityDTO result = fixture.getCity();

		assertNotNull(result);
		assertEquals(btDTO, result.getToken());
		assertEquals("Juvelar", result.getName());
		assertEquals(CityColor.BLUE, result.getColor());
		assertEquals(1, result.getPlayerNum());
		assertEquals(closeCities, result.getCloseCities());
	}

	@Test
	public void testGetPermit()
		throws Exception {
		BuildDTO fixture = new BuildDTO();
		fixture.setPermit(pcDTO);
		fixture.setCity(cityDTO);

		PermitsCardDTO result = fixture.getPermit();

		assertNotNull(result);
		assertEquals(false, result.isFaceDown());
		assertEquals(letters, result.getCityLetter());
		assertEquals(b, result.getBonuses());
	}

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BuildDTOTest.class);
	}
}