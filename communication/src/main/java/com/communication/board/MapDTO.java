package com.communication.board ;

import java.io.Serializable;
import java.util.ArrayList;

import com.communication.decks.PermitsDeckDTO;
import com.communication.decks.PoliticsDeckDTO;


/**
 * The Class MapDTO.
 */
public class MapDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4519455876400010117L;
	
	/** The regions(DTO). */
	private RegionDTO[] regions;
	
	/** The color groups(DTO). */
	private ColorGroupDTO[] colorGroups;
	
	/** The list of councilors(DTO). */
	private ArrayList <CouncilorDTO> councilors;
	
	/** The array of balcony(DTO). */
	private BalconyDTO[] balcony;
	
	/** The list of assistants(DTO). */
	private ArrayList<AssistantDTO> assistants;
	
	/** The array of pawn(DTO). */
	private PawnDTO[] pawn;
	
	/** The nobility track(DTO). */
	private NobilityTrackDTO nobilityTrack;
	
	/** The array of city(DTO). */
	private CityDTO[] city;
	
	/** The politics deck(DTO). */
	private PoliticsDeckDTO politicsDeck;
	
	/** The list of king bonus(DTO). */
	private ArrayList<BonusDTO> kingBonus;
	
	/** The array of permits deck(DTO). */
	private PermitsDeckDTO[] permitsDeck;
	
	/** The king(DTO). */
	private KingDTO king;
	
	
	/**
	 * Gets the region(DTO).
	 *
	 * @param i the number of the region
	 * @return the region
	 */
	public RegionDTO getRegion(int i) {
		return regions[i];
	}
	
	/**
	 * Sets the regions(DTO).
	 *
	 * @param regions the regions to set(DTO)
	 */
	public void setRegions(RegionDTO[] regions) {
		this.regions = regions;
	}
	
	/**
	 * Gets the color group(DTO).
	 *
	 * @param i the number of colorgroup
	 * @return the colorGroups(DTO)
	 */
	public ColorGroupDTO getColorGroup(int i) {
		return colorGroups[i];
	}
	
	/**
	 * Sets the color groups(DTO).
	 *
	 * @param colorGroups the colorGroups(DTO) to set
	 */
	public void setColorGroups(ColorGroupDTO[] colorGroups) {
		this.colorGroups = colorGroups;
	}
	
	/**
	 * Gets the list of councilors(DTO).
	 *
	 * @return the councilors(DTO)
	 */
	public ArrayList<CouncilorDTO> getCouncilors() {
		return councilors;
	}
	
	/**
	 * Sets the list of councilors(DTO).
	 *
	 * @param councilors the list of councilors(DTO) to set
	 */
	public void setCouncilors(ArrayList<CouncilorDTO> councilors) {
		this.councilors = councilors;
	}
	
	/**
	 * Gets the balcony(DTO).
	 *
	 * @param i the the number of the balcony
	 * @return the balcony(DTO)
	 */
	public BalconyDTO getBalcony(int i) {
		return balcony[i];
	}
	
	/**
	 * Sets the array of balconies(DTO).
	 *
	 * @param balcony the array of balconies(DTO) to set
	 */
	public void setBalcony(BalconyDTO[] balcony) {
		this.balcony = balcony;
	}
	
	/**
	 * Gets the list of assistants(DTO).
	 *
	 * @return the list of assistants(DTO)
	 */
	public ArrayList<AssistantDTO> getAssistants() {
		return assistants;
	}
	
	/**
	 * Sets the list of assistants(DTO).
	 *
	 * @param assistants the list of assistants(DTO) to set
	 */
	public void setAssistants(ArrayList<AssistantDTO> assistants) {
		this.assistants = assistants;
	}
	
	/**
	 * Gets the array of pawns (DTO).
	 *
	 * @return the array of pawn(DTO)
	 */
	public PawnDTO[] getPawn() {
		return pawn;
	}
	
	/**
	 * Sets the array of pawn(DTO).
	 *
	 * @param pawn the array of pawns(DTO) to set
	 */
	public void setPawn(PawnDTO[] pawn) {
		this.pawn = pawn;
	}
	
	/**
	 * Gets the nobility track(DTO).
	 *
	 * @return the nobilityTrack(DTO)
	 */
	public NobilityTrackDTO getNobilityTrack() {
		return nobilityTrack;
	}
	
	/**
	 * Sets the nobility track.(DTO)
	 *
	 * @param nobilityTrack the nobilityTrack(DTO) to set
	 */
	public void setNobilityTrack(NobilityTrackDTO nobilityTrack) {
		this.nobilityTrack = nobilityTrack;
	}
	
	/**
	 * Gets the array of city(DTO).
	 *
	 * @return the array of city (DTO)
	 */
	public CityDTO[] getCity() {
		return city;
	}
	
	/**
	 * Sets the array of cities(DTO).
	 *
	 * @param city the array of cities (DTO)to set
	 */
	public void setCity(CityDTO[] city) {
		this.city = city;
	}
	
	/**
	 * Gets the politics deck(DTO).
	 *
	 * @return the politicsDeck(DTO)
	 */
	public PoliticsDeckDTO getPoliticsDeck() {
		return politicsDeck;
	}
	
	/**
	 * Sets the politics deck(DTO).
	 *
	 * @param politicsDeck the politicsDeck(DTO) to set
	 */
	public void setPoliticsDeck(PoliticsDeckDTO politicsDeck) {
		this.politicsDeck = politicsDeck;
	}
	
	/**
	 * Gets the list of king bonuses(DTO).
	 *
	 * @return the list of kingBonuses(DTO)
	 */
	public ArrayList<BonusDTO> getKingBonus() {
		return kingBonus;
	}
	
	/**
	 * Sets the list of king bonuses(DTO).
	 *
	 * @param kingBonus the list of kingBonuses(DTO) to set
	 */
	public void setKingBonus(ArrayList<BonusDTO> kingBonus) {
		this.kingBonus = kingBonus;
	}
	
	/**
	 * Gets the permits deck(DTO).
	 *
	 * @param i the the number of the permitsdeck(DTO)
	 * @return the permitsDeck(DTO)
	 */
	public PermitsDeckDTO getPermitsDeck(int i) {
		return permitsDeck[i];
	}
	
	/**
	 * Sets the array ofpermits decks(DTO).
	 *
	 * @param permitsDeck the array of permitsDecks(DTO) to set
	 */
	public void setPermitsDeck(PermitsDeckDTO[] permitsDeck) {
		this.permitsDeck = permitsDeck;
	}
	
	/**
	 * Gets the king(DTO).
	 *
	 * @return the king(DTO)
	 */
	public KingDTO getKing() {
		return king;
	}
	
	/**
	 * Sets the king(DTO).
	 *
	 * @param king the king(DTO) to set
	 */
	public void setKing(KingDTO king) {
		this.king = king;
	}
	
	
}