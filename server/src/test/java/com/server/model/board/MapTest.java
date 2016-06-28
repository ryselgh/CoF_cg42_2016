package com.server.model.board;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;
import com.communication.board.MapDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.CityColor;
import com.communication.values.CouncilorColor;
import com.server.model.decks.PermitsDeck;
import com.server.model.decks.PoliticsDeck;
import com.server.model.gamelogic.Player;

public class MapTest {
	
	
	Player[]p;
	@Before
	public void setUp()
		throws Exception {
		//the Players
		p=new Player[3];
		p[0]=new Player("1");
		p[1]=new Player("2");
		p[2]=new Player("3");
	}
	
	
	@Test
	public void testMap()
		throws Exception {
		
		boolean _default = true;
		String rawMap = null;

		Map result = new Map(p, "Default map1.xml", rawMap);

		assertNotNull(result);
		
	}

	
	@Test
	public void testGetAssistant()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml", null);
		
		int qty = 1;

		ArrayList<Assistant> result = fixture.getAssistant(qty);

		
		assertNotNull(result);
	}

	

	@Test
	public void testGetAssistantsPool()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		

		ArrayList<Assistant> result = fixture.getAssistantsPool();

		
		assertNotNull(result);
	}

	@Test
	public void testGetAvailableColors()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		

		ArrayList<CouncilorColor> result = fixture.getAvailableColors();

		
		assertNotNull(result);
	}

	
	
	@Test
	public void testGetBalcony()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		int selection = 1;

		Balcony result = fixture.getBalcony(selection);

		
		assertNotNull(result);
	}

	@Test
	public void testGetCity()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		

		City[] result = fixture.getCity();

		
		assertNotNull(result);
	}

	@Test
	public void testGetColorGroup()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		int index = 1;

		ColorGroup result = fixture.getColorGroup(index);

		
		assertNotNull(result);
	}

	@Test
	public void testGetCouncilor()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		CouncilorColor col = CouncilorColor.BLACK;
		fixture.getCouncilorsPool().add(new Councilor(col));
		
		Councilor result = fixture.getCouncilor(col);

		
		assertNotNull(result);
	}

	

	@Test
	public void testGetCouncilorsPool_1()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
	

		ArrayList<Councilor> result = fixture.getCouncilorsPool();

		assertEquals(result.size(),8);
		assertNotNull(result);
	}

	@Test
	public void testGetKing()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		King result = fixture.getKing();

		assertNotNull(result);
	}

	@Test
	public void testGetKingBonus()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		fixture.setKingBonus(new ArrayList());
		

		ArrayList<Bonus> result = fixture.getKingBonus();

		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetNobilityTrack_1()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		

		NobilityTrack result = fixture.getNobilityTrack();

		
		assertNotNull(result);
	}

	@Test
	public void testGetPermitsDeck_1()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		int index = 1;

		PermitsDeck result = fixture.getPermitsDeck(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
		
	}

	@Test
	public void testGetPlayerEmporiums()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		
		int index = 1;

		ArrayList<Emporium> result = fixture.getPlayerEmporiums(index);

		assertNotNull(result);
		assertEquals(result.size(),10);
	}

	

	@Test
	public void testGetPoliticsDeck()
		throws Exception {
		Map fixture = new Map(p,"Default map1.xml",null);
		

		PoliticsDeck result = fixture.getPoliticsDeck();

		
		assertNotNull(result);
	}

	@Test
	public void testGetRegion()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "Default map1.xml", (String) null);
		
		int index = 1;

		Region result = fixture.getRegion(index);

		
		assertNotNull(result);
	}

	@Test
	public void testImportMap()
		throws Exception {
		Map fixture = new Map(p, "Default map1.xml", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		String file = null;

		int result = fixture.importMap(file, "Default map1.xml");
		

		
		assertEquals(1, result);
	}





	@Test
	public void testInsertCity()
		throws Exception {
		Map fixture = new Map(p, "Default map1.xml", (String) null);
		
		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		String regione = "sea";
		String color = "blue";

		int result = fixture.insertCity(c, regione, color);

		
		assertEquals(1, result);
	}



	@Test
	public void testToDTO()
		throws Exception {
		Map fixture = new Map(p, "Default map1.xml", (String) null);
		fixture.setKingBonus(new ArrayList());
		Pawn[] pawns = new Pawn[3];
		pawns[0] = new Pawn(p[0],"#FFFFFF");
		pawns[1] = new Pawn(p[1],"#FFFFFF");
		pawns[2] = new Pawn(p[2],"#FFFFFF");
		fixture.setNobilityTrack(new NobilityTrack(pawns, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		

		MapDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		
		assertNotNull(result);
	}

	
	


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MapTest.class);
	}
}