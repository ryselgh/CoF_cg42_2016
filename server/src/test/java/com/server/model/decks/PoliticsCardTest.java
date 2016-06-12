package com.server.model.decks;

import org.junit.*;

import com.communication.decks.PoliticsCardDTO;
import com.communication.values.CouncilorColor;
import com.server.model.gamelogic.Player;

import static org.junit.Assert.*;

public class PoliticsCardTest {
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

	@Test
	public void testGetColor_1()
		throws Exception {
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);

		CouncilorColor result = fixture.getColor();

		assertNotNull(result);
		assertEquals("BLACK", result.name());
		assertEquals("BLACK", result.toString());
		
	}
	
	@Test 
	public void testToDTO(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsCardDTO pcDTO = fixture.toDTO();
		
		assertTrue(pcDTO instanceof PoliticsCardDTO);
	}

	
	@Test
	public void testEqualsDTO(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(CouncilorColor.BLACK);
		
		assertTrue(fixture.equalsDTO(pcDTO));
		
		
	}
	
	@Test (expected=NullPointerException.class)
	public void testEqualsNull(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		
		
		fixture.equalsDTO(null);
		
	}
	@Test
	public void testFromDTO(){
		
		PoliticsCard fixture = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsCard fixture2 = new PoliticsCard(CouncilorColor.WHITE);
		PoliticsCardDTO pcDTO = new PoliticsCardDTO();
		pcDTO.setColor(CouncilorColor.BLACK);
		Player player = new Player("1");
		player.addPolitics(fixture);
		PoliticsCard pc = new PoliticsCard(CouncilorColor.BLACK);
		PoliticsCard pc2 = pc.fromDTO(pcDTO, player);
		
		assertEquals(pc2,fixture);
		
		
		
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PoliticsCardTest.class);
	}
}