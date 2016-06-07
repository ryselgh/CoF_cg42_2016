package com.communication.board;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PermitsDeckDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.decks.PoliticsDeckDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.BonusType;
import com.communication.values.CityColor;
import com.communication.values.CouncilorColor;

public class MapDTOTest {
	
	PlayerDTO player1;
	PlayerDTO player2;
	PlayerDTO player3;
	PawnDTO[] pawns;
	CityDTO fixture1;
	String[] closeCities;
	BonusDTO[] b;
	KingDTO king;
	PlayerDTO[] players;
	ArrayList<AssistantDTO> availableAssistants = new ArrayList();
	ArrayList<EmporiumDTO> availableEmporiums = new ArrayList();
	ArrayList<BonusCardDTO>bonusCards= new ArrayList();
	ArrayList<PoliticsCardDTO> hand = new ArrayList();
	ArrayList<PermitsCardDTO> permits = new ArrayList();
	CouncilorDTO[] councilor;
	ArrayList<CouncilorDTO> councilors;
	AssistantDTO[] assistant;
	ArrayList<AssistantDTO> assistants = new ArrayList();
	BalconyDTO[] balcony;
	
	CouncilorDTO[] counc;
	
	PermitsDeckDTO[] permitsDeck;
	
	BonusDTO[] bonuses;
	String[] cityLetters;
	PermitsCardDTO[] permitsDeckDTO;
	ArrayList<PermitsCardDTO> permitDeck; 
	
	PoliticsCardDTO[] politicsDeckDTO;
	ArrayList<PoliticsCardDTO> politicsDeck; 
	PoliticsDeckDTO politicDeck;
	
	RegionDTO[] region;
	
	
	

	@Before
	public void setUp()
		throws Exception {
		
		pawns=new PawnDTO[3];
		pawns[0]=new PawnDTO();
		pawns[0].setHexColor("green");
		pawns[0].setPos(7);
		pawns[0].setP(player1);
		
		pawns[1]=new PawnDTO();
		pawns[1].setHexColor("yellow");
		pawns[1].setPos(7);
		pawns[1].setP(player2);
		
		pawns[2]=new PawnDTO();
		pawns[2].setHexColor("red");
		pawns[2].setPos(7);
		pawns[2].setP(player3);
		
		b = new BonusDTO[2];
		b[0]=new BonusDTO();
		b[0].setQuantity(1);
		b[0].setType(BonusType.ASSISTANT);
		b[1]=new BonusDTO();
		b[1].setQuantity(2);
		b[1].setType(BonusType.CARD);
		BonusTokenDTO btDTO = new BonusTokenDTO();
		btDTO.setBonus(b);
		
		fixture1 = new CityDTO();
		fixture1.setName("Juvelar");
		fixture1.setColor(CityColor.BLUE);
		fixture1.setCloseCities(closeCities);
		
		fixture1.setSlot(new EmporiumDTO[10] );
		fixture1.setPlayerNum(1);
		fixture1.setToken(btDTO);
		king = new KingDTO();
		king.setLocation(fixture1);
		
		players= new PlayerDTO[3];
		players[0]=new PlayerDTO();
		players[0].setAvailableAssistants(availableAssistants);
		players[0].setAvailableEmporiums(availableEmporiums);
		players[0].setBonusCards(bonusCards);
		players[0].setCoins(2);
		players[0].setHand(hand);
		players[0].setPawn(pawns[0]);
		players[0].setPermits(permits);
		players[0].setPlayerID(5);
		players[0].setScore(20);
		
		players[1]=new PlayerDTO();
		players[1].setAvailableAssistants(availableAssistants);
		players[1].setAvailableEmporiums(availableEmporiums);
		players[1].setBonusCards(bonusCards);
		players[1].setCoins(2);
		players[1].setHand(hand);
		players[1].setPawn(pawns[1]);
		players[1].setPermits(permits);
		players[1].setPlayerID(5);
		players[1].setScore(20);
		
		players[2]=new PlayerDTO();
		players[2].setAvailableAssistants(availableAssistants);
		players[2].setAvailableEmporiums(availableEmporiums);
		players[2].setBonusCards(bonusCards);
		players[2].setCoins(2);
		players[2].setHand(hand);
		players[2].setPawn(pawns[2]);
		players[2].setPermits(permits);
		players[2].setPlayerID(5);
		players[2].setScore(20);
		
		councilor= new CouncilorDTO[3];
		councilor[0]= new CouncilorDTO();
		councilor[1]= new CouncilorDTO();
		councilor[2]= new CouncilorDTO();
		
		councilors=new ArrayList<CouncilorDTO>(Arrays.asList(councilor));
		for (CouncilorDTO c: councilors)
			c.setColor(CouncilorColor.BLACK);
		
		assistant= new AssistantDTO[3];
		assistant[0]= new AssistantDTO();
		assistant[1]= new AssistantDTO();
		assistant[2]= new AssistantDTO();
		assistants = new ArrayList<AssistantDTO>(Arrays.asList(assistant));
		
		counc=new CouncilorDTO[4];
		balcony= new BalconyDTO[3];
		balcony[0]= new BalconyDTO();
		balcony[0].setCouncilor(counc);
		balcony[1]= new BalconyDTO();
		balcony[1].setCouncilor(counc);
		balcony[2]= new BalconyDTO();
		balcony[2].setCouncilor(counc);
		

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
		
		permitDeck = new ArrayList<PermitsCardDTO>(Arrays.asList(permitsDeckDTO));
		
		permitsDeck= new PermitsDeckDTO[3];
		permitsDeck[0]= new PermitsDeckDTO();
		permitsDeck[0].setPermitsDeck(permitDeck);
		permitsDeck[0].setRegionCode(1);
		permitsDeck[0].setSlot(new PermitsCardDTO[2]);
		permitsDeck[1]= new PermitsDeckDTO();
		permitsDeck[1].setPermitsDeck(permitDeck);
		permitsDeck[1].setRegionCode(2);
		permitsDeck[1].setSlot(new PermitsCardDTO[2]);
		permitsDeck[2]= new PermitsDeckDTO();
		permitsDeck[2].setPermitsDeck(permitDeck);
		permitsDeck[2].setRegionCode(3);
		permitsDeck[2].setSlot(new PermitsCardDTO[2]);
		
		politicsDeckDTO= new  PoliticsCardDTO[12];
		for (PoliticsCardDTO pc: politicsDeckDTO){
			pc= new PoliticsCardDTO();
			pc.setColor(CouncilorColor.ORANGE);
		}
		
		politicsDeck = new ArrayList<PoliticsCardDTO>(Arrays.asList(politicsDeckDTO));
		
		politicDeck = new PoliticsDeckDTO();
		politicDeck.setGarbage(new ArrayList());
		politicDeck.setPoliticsDeck(politicsDeck);
		
		region=  new RegionDTO[3];
		region[0]= new RegionDTO();
		region[1]= new RegionDTO();
		region[2]= new RegionDTO();
	
		
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
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		ArrayList<AssistantDTO> result = fixture.getAssistants();

		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testGetBalcony_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region );
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		BalconyDTO result = fixture.getBalcony(0);

		assertNotNull(result);
	}

	@Test
	public void testGetCity_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region );
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		CityDTO[] result = fixture.getCity();

		assertNotNull(result);
		assertEquals(15, result.length);
	}

	@Test
	public void testGetColorGroups_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		ColorGroupDTO result = fixture.getColorGroup(0);

		assertNotNull(fixture);
	}

	@Test
	public void testGetCouncilors_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);
		
		ArrayList<CouncilorDTO> result = fixture.getCouncilors();

		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testGetEmporiums_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region );
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		EmporiumDTO[] result = fixture.getEmporiums();

		assertNotNull(result);
		assertEquals(10, result.length);
	}

	@Test
	public void testGetKing_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		KingDTO result = fixture.getKing();

		assertNotNull(result);
		assertEquals(fixture1, result.getLocation());
	}

	@Test
	public void testGetKingBonus_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		ArrayList<BonusDTO> result = fixture.getKingBonus();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetNobilityTrack_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);
		
		NobilityTrackDTO result = fixture.getNobilityTrack();

		assertNotNull(result);
		assertEquals(null, result.getBonusVector());
		assertEquals(null, result.getPawns());
	}

	@Test
	public void testGetPawn_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		PawnDTO[] result = fixture.getPawn();

		assertNotNull(result);
		assertEquals(3, result.length);
	}

	@Test
	public void testGetPermitsDeck_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		PermitsDeckDTO result = fixture.getPermitsDeck(0);

		assertNotNull(result);
	}

	@Test
	public void testGetPlayers_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		PlayerDTO[] result = fixture.getPlayers();

		assertNotNull(result);
		assertEquals(3, result.length);
	}

	@Test
	public void testGetPoliticsDeck_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		PoliticsDeckDTO result = fixture.getPoliticsDeck();

		assertNotNull(result);
		assertEquals(politicsDeck, result.getPoliticsDeck());
		assertNotEquals(null, result.getGarbage());
	}

	@Test
	public void testGetRegions_1()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList());
		fixture.setKing(king);
		fixture.setPlayers(players);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setEmporiums(new EmporiumDTO[10] );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		RegionDTO result = fixture.getRegion(0);

		assertNotNull(result);
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