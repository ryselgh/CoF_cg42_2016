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
	ArrayList<AssistantDTO> availableAssistants = new ArrayList<AssistantDTO>();
	ArrayList<EmporiumDTO> availableEmporiums = new ArrayList<EmporiumDTO>();
	ArrayList<BonusCardDTO>bonusCards= new ArrayList<BonusCardDTO>();
	ArrayList<PoliticsCardDTO> hand = new ArrayList<PoliticsCardDTO>();
	ArrayList<PermitsCardDTO> permits = new ArrayList<PermitsCardDTO>();
	CouncilorDTO[] councilor;
	ArrayList<CouncilorDTO> councilors;
	AssistantDTO[] assistant;
	ArrayList<AssistantDTO> assistants = new ArrayList<AssistantDTO>();
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
		
		//all you need to set on a map
		//3 pawns
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
		
		king = new KingDTO();
		king.setLocation(fixture1);
		//3 players
		players= new PlayerDTO[3];
		players[0]=new PlayerDTO();
		players[0].setAvailableAssistants(availableAssistants);
		players[0].setAvailableEmporiums(availableEmporiums);
		players[0].setBonusCards(bonusCards);
		players[0].setCoins(2);
		players[0].setHand(hand);
		players[0].setPawn(pawns[0]);
		players[0].setPermits(permits);
		players[0].setPlayerID("5");
		players[0].setScore(20);
		players[1]=new PlayerDTO();
		players[1].setAvailableAssistants(availableAssistants);
		players[1].setAvailableEmporiums(availableEmporiums);
		players[1].setBonusCards(bonusCards);
		players[1].setCoins(2);
		players[1].setHand(hand);
		players[1].setPawn(pawns[1]);
		players[1].setPermits(permits);
		players[1].setPlayerID("5");
		players[1].setScore(20);
		players[2]=new PlayerDTO();
		players[2].setAvailableAssistants(availableAssistants);
		players[2].setAvailableEmporiums(availableEmporiums);
		players[2].setBonusCards(bonusCards);
		players[2].setCoins(2);
		players[2].setHand(hand);
		players[2].setPawn(pawns[2]);
		players[2].setPermits(permits);
		players[2].setPlayerID("5");
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
		
		//4 balconies
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
		
		//3 permitsDeck
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
		
		
		//politicsdeck
		
		politicsDeckDTO= new  PoliticsCardDTO[12];
		for (PoliticsCardDTO pc: politicsDeckDTO){
			pc= new PoliticsCardDTO();
			pc.setColor(CouncilorColor.ORANGE);
		}
		
		politicsDeck = new ArrayList<PoliticsCardDTO>(Arrays.asList(politicsDeckDTO));
		
		politicDeck = new PoliticsDeckDTO();
		politicDeck.setGarbage(new ArrayList<PoliticsCardDTO>());
		politicDeck.setPoliticsDeck(politicsDeck);
		
		//3regions
		region=  new RegionDTO[3];
		region[0]= new RegionDTO();
		region[1]= new RegionDTO();
		region[2]= new RegionDTO();
	
		
	}
	
	
	@Test
	public void testMapDTO()
		throws Exception {
		MapDTO result = new MapDTO();
		assertNotNull(result);
	}
	
	// getters and setters are tested together


	@Test
	public void testGetAssistants()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetBalcony()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region );
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		BalconyDTO result = fixture.getBalcony(0);

		assertNotNull(result);
		assertEquals(result.getCouncilor().length,4);
	}

	@Test
	public void testGetCity()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region );
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
	public void testGetColorGroups()
		throws Exception {
		MapDTO fixture = new MapDTO();
		ColorGroupDTO[] cgsDTO = new ColorGroupDTO[5];
		cgsDTO[0] = new ColorGroupDTO();
		
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(cgsDTO );
		fixture.setAssistants(assistants);

		ColorGroupDTO result = fixture.getColorGroup(0);

		assertNotNull(result);
		
	}

	@Test
	public void testGetCouncilors()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetKing()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetKingBonus()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetNobilityTrack()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);
		
		NobilityTrackDTO result = fixture.getNobilityTrack();

		assertNotNull(result);
		
	}

	@Test
	public void testGetPawn()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetPermitsDeck()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetPoliticsDeck()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
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
	public void testGetRegions()
		throws Exception {
		MapDTO fixture = new MapDTO();
		fixture.setPawn(pawns);
		fixture.setNobilityTrack(new NobilityTrackDTO());
		fixture.setKingBonus(new ArrayList<BonusDTO>());
		fixture.setKing(king);
		fixture.setCouncilors(councilors);
		fixture.setRegions(region);
		fixture.setCity(new CityDTO[15] );
		fixture.setPoliticsDeck(politicDeck);
		fixture.setPermitsDeck(permitsDeck);
		fixture.setBalcony(balcony);
		fixture.setColorGroups(new ColorGroupDTO[5] );
		fixture.setAssistants(assistants);

		RegionDTO result = fixture.getRegion(0);

		assertNotNull(result);
		
	}



	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MapDTOTest.class);
	}
}