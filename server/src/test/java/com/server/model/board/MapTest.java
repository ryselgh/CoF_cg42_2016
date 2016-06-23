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
		p=new Player[3];
		p[0]=new Player("1");
		p[1]=new Player("2");
		p[2]=new Player("3");
	}
	
	
	@Test
	public void testMap_1()
		throws Exception {
		
		String mapName = "default1";
		String rawMap = null;

		Map result = new Map(p, mapName, rawMap);

		assertNotNull(result);
		
	}

	@Test
	public void testMap_2()
		throws Exception {
		
		String mapName = "default1";
		String rawMap = null;

		Map result = new Map(p, mapName, rawMap);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testMap_3()
		throws Exception {
		Player[] p = new Player[] {};
		String mapName = "default1";
		String rawMap = null;

		Map result = new Map(p, mapName, rawMap);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testMap_4()
		throws Exception {
		Player[] p = new Player[] {};
		String mapName = "default1";
		String rawMap = null;

		Map result = new Map(p, mapName, rawMap);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetAssistant_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int qty = 1;

		ArrayList<Assistant> result = fixture.getAssistant(qty);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetAssistant_2()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int qty = 0;

		ArrayList<Assistant> result = fixture.getAssistant(qty);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetAssistantsPool_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		ArrayList<Assistant> result = fixture.getAssistantsPool();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetAvailableColors_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		ArrayList<CouncilorColor> result = fixture.getAvailableColors();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetAvailableColors_2()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		ArrayList<CouncilorColor> result = fixture.getAvailableColors();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetAvailableColors_3()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		ArrayList<CouncilorColor> result = fixture.getAvailableColors();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetBalcony_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int selection = 1;

		Balcony result = fixture.getBalcony(selection);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetCity_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		City[] result = fixture.getCity();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetColorGroup_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int index = 1;

		ColorGroup result = fixture.getColorGroup(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetCouncilor_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		CouncilorColor col = CouncilorColor.BLACK;
		fixture.getCouncilorsPool().add(new Councilor(col));
		
		Councilor result = fixture.getCouncilor(col);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetCouncilor_2()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		CouncilorColor col = CouncilorColor.WHITE;
		fixture.getCouncilorsPool().add(new Councilor(col));
		Councilor result = fixture.getCouncilor(col);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetCouncilorsPool_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		ArrayList<Councilor> result = fixture.getCouncilorsPool();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetKing_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		King result = fixture.getKing();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetKingBonus_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		ArrayList<Bonus> result = fixture.getKingBonus();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetNobilityTrack_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		NobilityTrack result = fixture.getNobilityTrack();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetPermitsDeck_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int index = 1;

		PermitsDeck result = fixture.getPermitsDeck(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetPlayerEmporiums_1()
		throws Exception {
		Map fixture = new Map(p, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int index = 1;

		ArrayList<Emporium> result = fixture.getPlayerEmporiums(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetPlayerEmporiums_2()
		throws Exception {
		Map fixture = new Map(p, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int index = 1;

		ArrayList<Emporium> result = fixture.getPlayerEmporiums(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetPoliticsDeck_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		PoliticsDeck result = fixture.getPoliticsDeck();

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testGetRegion_1()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		int index = 1;

		Region result = fixture.getRegion(index);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testImportMap_1()
		throws Exception {
		Map fixture = new Map(p, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		String file = null;
		String mapName = "default1";

		int result = fixture.importMap(file, "default1");
		

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertEquals(1, result);
	}

//	@Test
//	public void testImportMap_2()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		Document file = null;
//		boolean _default = false;
//
//		int result = fixture.importMap(file, _default);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}

//	@Test
//	public void testImportMap_3()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		Document file = null;
//		String mapName = "default1";
//
//		int result = fixture.importMap(file, _default);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testImportMap_4()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		Document file = null;
//		String mapName = "default1";
//
//		int result = fixture.importMap(file, _default);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testImportMap_5()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		Document file = null;
//		String mapName = "default1";
//
//		int result = fixture.importMap(file, _default);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testImportMap_6()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		Document file = null;
//		String mapName = "default1";
//
//		int result = fixture.importMap(file, _default);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}

//	@Test
//	public void testInitializeMapObjects_1()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//
//		fixture.initializeMapObjects();
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		
//	}



	@Test
	public void testInsertCity_1()
		throws Exception {
		Map fixture = new Map(p, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
		String regione = "sea";
		String color = "blue";

		int result = fixture.insertCity(c, regione, color);

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertEquals(1, result);
	}

//	@Test
//	public void testInsertCity_2()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//		String regione = "";
//		String color = "";
//
//		int result = fixture.insertCity(c, regione, color);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testInsertCity_3()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//		String regione = "";
//		String color = "";
//
//		int result = fixture.insertCity(c, regione, color);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testInsertCity_4()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//		String regione = "";
//		String color = "";
//
//		int result = fixture.insertCity(c, regione, color);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testInsertCity_5()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//		String regione = "";
//		String color = "";
//
//		int result = fixture.insertCity(c, regione, color);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testInsertCity_6()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//		String regione = "";
//		String color = "";
//
//		int result = fixture.insertCity(c, regione, color);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testInsertCity_7()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City c = new City("", CityColor.BLUE, new String[] {}, 1, new BonusToken(new Bonus[] {}));
//		String regione = "";
//		String color = "";
//
//		int result = fixture.insertCity(c, regione, color);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void testSetCity_1()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		City[] city = new City[] {};
//
//		fixture.setCity(city);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//	}
//
//	@Test
//	public void testSetKingBonus_1()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		ArrayList<Bonus> kingBonus = new ArrayList();
//
//		fixture.setKingBonus(kingBonus);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//	}
//
//	@Test
//	public void testSetNobilityTrack_1()
//		throws Exception {
//		Map fixture = new Map(new Player[] {}, true, (Document) null);
//		fixture.setKingBonus(new ArrayList());
//		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
//		fixture.setCity(new City[] {});
//		NobilityTrack nobilityTrack = new NobilityTrack(new Pawn[] {}, new Bonus[][] {});
//
//		fixture.setNobilityTrack(nobilityTrack);
//
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.server.model.board.Map.<init>(Map.java:145)
//	}

	@Test
	public void testToDTO_1()
		throws Exception {
		Map fixture = new Map(p, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		Pawn[] pawns = new Pawn[3];
		pawns[0] = new Pawn(p[0],"#FFFFFF");
		pawns[1] = new Pawn(p[1],"#FFFFFF");
		pawns[2] = new Pawn(p[2],"#FFFFFF");
		fixture.setNobilityTrack(new NobilityTrack(pawns, new Bonus[][] {}));
		fixture.setCity(new City[] {});
		

		MapDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	@Test
	public void testToDTO_2()
		throws Exception {
		Map fixture = new Map(new Player[] {}, "default1", (String) null);
		fixture.setKingBonus(new ArrayList());
		fixture.setNobilityTrack(new NobilityTrack(new Pawn[] {}, new Bonus[][] {}));
		fixture.setCity(new City[] {});

		MapDTO result = fixture.toDTO(new ArrayList<PlayerDTO>());

		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.server.model.board.Map.<init>(Map.java:145)
		assertNotNull(result);
	}

	

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MapTest.class);
	}
}