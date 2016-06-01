package com.communication.board;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.decks.PermitsDeckDTO;
import com.communication.decks.PoliticsDeckDTO;
import com.communication.gamelogic.PlayerDTO;

public class MapDTOTest {
	
	
	

	@Before
	public void setUp()
		throws Exception {
	}
	
	
//	@Test
//	public void testMapDTO_1()
//		throws Exception {
//		MapDTO result = new MapDTO();
//		assertNotNull(result);
//	}

	@Test
	public void testGetAssistants_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		ArrayList<AssistantDTO> result = fixture.getAssistants();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetBalcony_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		BalconyDTO[] result = fixture.getBalcony();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetCity_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		CityDTO[] result = fixture.getCity();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetColorGroups_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		ColorGroupDTO[] result = fixture.getColorGroups();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetCouncilors_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		ArrayList<CouncilorDTO> result = fixture.getCouncilors();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetEmporiums_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		EmporiumDTO[] result = fixture.getEmporiums();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetKing_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		KingDTO result = fixture.getKing();

		assertNotNull(result);
		assertEquals(null, result.getLocation());
	}

	@Test
	public void testGetKingBonus_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		ArrayList<BonusDTO> result = fixture.getKingBonus();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetNobilityTrack_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		NobilityTrackDTO result = fixture.getNobilityTrack();

		assertNotNull(result);
		assertEquals(null, result.getBonusVector());
		assertEquals(null, result.getPawns());
	}

	@Test
	public void testGetPawn_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		PawnDTO[] result = fixture.getPawn();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetPermitsDeck_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		PermitsDeckDTO[] result = fixture.getPermitsDeck();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetPlayers_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		PlayerDTO[] result = fixture.getPlayers();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void testGetPoliticsDeck_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		PoliticsDeckDTO result = fixture.getPoliticsDeck();

		assertNotNull(result);
		assertEquals(null, result.getPoliticsDeck());
		assertEquals(null, result.getGarbage());
	}

	@Test
	public void testGetRegions_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(new PawnDTO[] {});
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(new KingDTO());
		fixture.setPlayers(new PlayerDTO[] {});
		fixture.setCouncilors(new ArrayList());
		fixture.setRegions(new RegionDTO[] {});
		fixture.setEmporiums(new EmporiumDTO[] {});
		fixture.setCity(new CityDTO[] {});
		fixture.setPoliticsDeck(new PoliticsDeckDTO());
		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
		fixture.setBalcony(new BalconyDTO[] {});
		fixture.setColorGroups(new ColorGroupDTO[] {});
		fixture.setAssistants(new ArrayList());

		RegionDTO[] result = fixture.getRegions();

		assertNotNull(result);
		assertEquals(0, result.length);
	}

//	@Test
//	public void testSetAssistants_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		ArrayList<AssistantDTO> assistants = new ArrayList();
//
//		fixture.setAssistants(assistants);
//
//	}
//
//	@Test
//	public void testSetBalcony_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		BalconyDTO[] balcony = new BalconyDTO[] {};
//
//		fixture.setBalcony(balcony);
//
//	}
//
//	@Test
//	public void testSetCity_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		CityDTO[] city = new CityDTO[] {};
//
//		fixture.setCity(city);
//
//	}
//
//	@Test
//	public void testSetColorGroups_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		ColorGroupDTO[] colorGroups = new ColorGroupDTO[] {};
//
//		fixture.setColorGroups(colorGroups);
//
//	}
//
//	@Test
//	public void testSetCouncilors_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		ArrayList<CouncilorDTO> councilors = new ArrayList();
//
//		fixture.setCouncilors(councilors);
//
//	}
//
//	@Test
//	public void testSetEmporiums_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		EmporiumDTO[] emporiums = new EmporiumDTO[] {};
//
//		fixture.setEmporiums(emporiums);
//
//	}
//
//	@Test
//	public void testSetKing_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		KingDTO king = new KingDTO();
//
//		fixture.setKing(king);
//
//	}
//
//	@Test
//	public void testSetKingBonus_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		ArrayList<BonusDTO> kingBonus = new ArrayList();
//
//		fixture.setKingBonus(kingBonus);
//
//	}
//
//	@Test
//	public void testSetNobilityTrack_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		NobilityTrackDTO nobilityTrack = new NobilityTrackDTO();
//
//		fixture.setNobilityTrack(nobilityTrack);
//
//	}
//
//	@Test
//	public void testSetPawn_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		PawnDTO[] pawn = new PawnDTO[] {};
//
//		fixture.setPawn(pawn);
//
//	}
//
//	@Test
//	public void testSetPermitsDeck_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		PermitsDeckDTO[] permitsDeck = new PermitsDeckDTO[] {};
//
//		fixture.setPermitsDeck(permitsDeck);
//
//	}
//
//	@Test
//	public void testSetPlayers_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		PlayerDTO[] players = new PlayerDTO[] {};
//
//		fixture.setPlayers(players);
//
//	}
//
//	@Test
//	public void testSetPoliticsDeck_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		PoliticsDeckDTO politicsDeck = new PoliticsDeckDTO();
//
//		fixture.setPoliticsDeck(politicsDeck);
//
//	}
//
//	@Test
//	public void testSetRegions_1()
//		throws Exception {
//		MapDTO fixture = new MapDTO();
//		fixture.setPawn(new PawnDTO[] {});
//		fixture.setNobilityTrack(new NobilityTrackDTO());
//		fixture.setKingBonus(new ArrayList());
//		fixture.setKing(new KingDTO());
//		fixture.setPlayers(new PlayerDTO[] {});
//		fixture.setCouncilors(new ArrayList());
//		fixture.setRegions(new RegionDTO[] {});
//		fixture.setEmporiums(new EmporiumDTO[] {});
//		fixture.setCity(new CityDTO[] {});
//		fixture.setPoliticsDeck(new PoliticsDeckDTO());
//		fixture.setPermitsDeck(new PermitsDeckDTO[] {});
//		fixture.setBalcony(new BalconyDTO[] {});
//		fixture.setColorGroups(new ColorGroupDTO[] {});
//		fixture.setAssistants(new ArrayList());
//		RegionDTO[] regions = new RegionDTO[] {};
//
//		fixture.setRegions(regions);
//
//	}


	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MapDTOTest.class);
	}
}