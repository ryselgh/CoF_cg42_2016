package com.server.model.decks;

import org.junit.*;

import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.gamelogic.Player;

import static org.junit.Assert.*;

public class PoliticsCardTest {
	
	//test the construcotr
	@Test
	public void testPoliticsCard_1()
		throws Exception {
		CouncilorColor c = CouncilorColor.BLACK;

		PoliticsCard result = new PoliticsCard(c);

		assertNotNull(result);
	}
	
	@Test (expected= NullPointerException.class)
	public void testIfYouCanCreateNullPoliticsCards(){
		PoliticsCard pc = new PoliticsCard(null);
	}
// ------------------------------------------------------------
	
	//testing the methods
	
	@Test
	public void testGetColor()
		throws Exception {
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}
	
	
	// testing DTO methods
	
	@Test 
	public void testToDTO(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsCardDTO pcDTO = fixture.toDTO();
		
		assertTrue(pcDTO instanceof PoliticsCardDTO);
	}

	
	@Test
	public void testEqualsDTOTrueCondition(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(CouncilorColor.BLACK);
		
		assertTrue(fixture.equalsDTO(pcDTO));
		
		
	}
	
	@Test
	public void testEqualsDTOFalseCondition(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.WHITE);
		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(CouncilorColor.BLACK);
		
		assertFalse(fixture.equalsDTO(pcDTO));
	}
	
	@Test (expected=NullPointerException.class)
	public void testEqualsNull(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		
		
		fixture.equalsDTO(null);
		
	}
	
	
	@Test
	public void testFromDTO(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		Player player = new Player("1");
		player.addPolitics(fixture);

		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(CouncilorColor.BLACK);
		
		PoliticsCard pc2 = PoliticsCard.fromDTO(pcDTO, player);
		
		assertEquals(pc2,fixture);
		
		
		
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testIfFromDTOThrowsException(){
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		Player player = new Player("1");
		player.addPolitics(fixture);
		
		PoliticsCard.fromDTO(null, player);
		
		
	}
	
	@Test
	public void testFromDTO_ReturnNull(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.WHITE);
		Player player = new Player("1");
		player.addPolitics(fixture);

		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(CouncilorColor.BLACK);
	
		
		assertNull(PoliticsCard.fromDTO(pcDTO, player));
		
		
		
	}
	
	
	// --------------------------------------------------------------

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsCardTest.class);
	}
}