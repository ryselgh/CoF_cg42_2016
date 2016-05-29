package com.communication.board ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.decks.PermitsDeckDTO;
import com.communication.decks.PoliticsDeckDTO;
import com.communication.gamelogic.PlayerDTO;

public class MapDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4519455876400010117L;
	private PlayerDTO[] players;
	private RegionDTO[] regions;
	private ColorGroupDTO[] colorGroups;
	private ArrayList <CouncilorDTO> councilors;
	private BalconyDTO[] balcony;
	private ArrayList<AssistantDTO> assistants;
	private EmporiumDTO[] emporiums;
	private PawnDTO[] pawn;
	private NobilityTrackDTO nobilityTrack;
	private CityDTO[] city;
	private PoliticsDeckDTO politicsDeck;
	private ArrayList<BonusDTO> kingBonus;
	private PermitsDeckDTO[] permitsDeck;
	private KingDTO king;
	
	
	/**
	 * @return the players
	 */
	public PlayerDTO[] getPlayers() {
		return players;
	}
	/**
	 * @param players the players to set
	 */
	public void setPlayers(PlayerDTO[] players) {
		this.players = players;
	}
	/**
	 * @return the regions
	 */
	public RegionDTO getRegion(int i) {
		return regions[i];
	}
	/**
	 * @param regions the regions to set
	 */
	public void setRegions(RegionDTO[] regions) {
		this.regions = regions;
	}
	/**
	 * @return the colorGroups
	 */
	public ColorGroupDTO getColorGroup(int i) {
		return colorGroups[i];
	}
	/**
	 * @param colorGroups the colorGroups to set
	 */
	public void setColorGroups(ColorGroupDTO[] colorGroups) {
		this.colorGroups = colorGroups;
	}
	/**
	 * @return the councilors
	 */
	public ArrayList<CouncilorDTO> getCouncilors() {
		return councilors;
	}
	/**
	 * @param councilors the councilors to set
	 */
	public void setCouncilors(ArrayList<CouncilorDTO> councilors) {
		this.councilors = councilors;
	}
	/**
	 * @return the balcony
	 */
	public BalconyDTO getBalcony(int i) {
		return balcony[i];
	}
	/**
	 * @param balcony the balcony to set
	 */
	public void setBalcony(BalconyDTO[] balcony) {
		this.balcony = balcony;
	}
	/**
	 * @return the assistants
	 */
	public ArrayList<AssistantDTO> getAssistants() {
		return assistants;
	}
	/**
	 * @param assistants the assistants to set
	 */
	public void setAssistants(ArrayList<AssistantDTO> assistants) {
		this.assistants = assistants;
	}
	/**
	 * @return the emporiums
	 */
	public EmporiumDTO[] getEmporiums() {
		return emporiums;
	}
	/**
	 * @param emporiums the emporiums to set
	 */
	public void setEmporiums(EmporiumDTO[] emporiums) {
		this.emporiums = emporiums;
	}
	/**
	 * @return the pawn
	 */
	public PawnDTO[] getPawn() {
		return pawn;
	}
	/**
	 * @param pawn the pawn to set
	 */
	public void setPawn(PawnDTO[] pawn) {
		this.pawn = pawn;
	}
	/**
	 * @return the nobilityTrack
	 */
	public NobilityTrackDTO getNobilityTrack() {
		return nobilityTrack;
	}
	/**
	 * @param nobilityTrack the nobilityTrack to set
	 */
	public void setNobilityTrack(NobilityTrackDTO nobilityTrack) {
		this.nobilityTrack = nobilityTrack;
	}
	/**
	 * @return the city
	 */
	public CityDTO[] getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(CityDTO[] city) {
		this.city = city;
	}
	/**
	 * @return the politicsDeck
	 */
	public PoliticsDeckDTO getPoliticsDeck() {
		return politicsDeck;
	}
	/**
	 * @param politicsDeck the politicsDeck to set
	 */
	public void setPoliticsDeck(PoliticsDeckDTO politicsDeck) {
		this.politicsDeck = politicsDeck;
	}
	/**
	 * @return the kingBonus
	 */
	public ArrayList<BonusDTO> getKingBonus() {
		return kingBonus;
	}
	/**
	 * @param kingBonus the kingBonus to set
	 */
	public void setKingBonus(ArrayList<BonusDTO> kingBonus) {
		this.kingBonus = kingBonus;
	}
	/**
	 * @param i 
	 * @return the permitsDeck
	 */
	public PermitsDeckDTO getPermitsDeck(int i) {
		return permitsDeck[i];
	}
	/**
	 * @param permitsDeck the permitsDeck to set
	 */
	public void setPermitsDeck(PermitsDeckDTO[] permitsDeck) {
		this.permitsDeck = permitsDeck;
	}
	/**
	 * @return the king
	 */
	public KingDTO getKing() {
		return king;
	}
	/**
	 * @param king the king to set
	 */
	public void setKing(KingDTO king) {
		this.king = king;
	}
	
	
}