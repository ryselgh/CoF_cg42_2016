package board ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import decks.PermitsDeck;
import decks.PermitsCard;
import decks.PoliticsDeck;

import java.io.IOException;

import gamelogic.Player;
import model.CouncilorColor;
import model.CityColor;
import model.RegionName;

public class Map {

	private static final int EMPORIUMQTY = 10;
	private static final int ASSISTQTY = 50;
	private Player[] players;
	private Region[] regions;//0 sea    1 hill     2 mountain
	private ColorGroup[] colorGroups;
	private ArrayList <Councilor> councilors;
	private Balcony[] balcony;
	private static ArrayList<Assistant> assistants;
	private Emporium[] emporiums;
	private Pawn[] pawn;
	private NobilityTrack nobilityTrack;
	private City[] city;
	private PoliticsDeck politicsDeck;
	private ArrayList<Bonus> kingBonus;
	private PermitsDeck[] permitsDeck;
	private King king;

	/**
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws ParserConfigurationException
	 */
	
	
	public Map(Player[] p, boolean _default, String file) {//default: to be implemented
		this.players = p;
		initializeMapObjects();
		try {
			importMap(file,_default);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int importMap(String file, boolean _default) throws Exception  {
		Importer reader = new Importer(file,_default, this, players);
		reader.startImport();
		
		Bonus[] bonusRegioni = reader.getRegionBonus();
		for(int i=0;i<3;i++)
			regions[i].setBonus(new BonusCard(bonusRegioni[i]));//-----------------------------
		Bonus[] bonusColori = reader.getColorBonus();
		for(int i=0;i<colorGroups.length;i++)
			if(bonusColori[i]!=null)
				colorGroups[i].setBonus(new BonusCard(bonusColori[i]));	
		setCity(reader.getCity()); //in questo metodo Importer accede al metodo Map.inserisciCittà per inserire le città (alla creazione) nelle regioni e nei cologroup, le cui istanze sono qui su Map perchè create non in questo metodo
		setKing(reader.getKing());
		nobilityTrack = reader.getNobilityTrack();
		kingBonus = reader.getKingBonus();
		
		PermitsCard[][] pool = reader.getPermitsCardPool();
		permitsDeck = new PermitsDeck[3];
		for(int i=0;i<3;i++)
			permitsDeck[i] = new PermitsDeck(pool[i],i);
		
		
		return 1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public void initializeMapObjects() {
		//councilors
		int councNum = CouncilorColor.values().length*4;
		councilors = new ArrayList <Councilor>(councNum);
		for (CouncilorColor c : CouncilorColor.values()) {
			if(c != CouncilorColor.JOLLY)
				for(int i=0;i<4;i++)
					councilors.add(new Councilor(c));
		}
		Collections.shuffle(councilors);    	

		//balconi
		balcony = new Balcony[4];
		for (int i=0;i< balcony.length;i++) {
			ArrayList <Councilor> toRet = new ArrayList <Councilor>();
			Councilor[] retArr = new Councilor[4];
			for(int j=0;j<4;j++)
			{
				toRet.add(councilors.get(0));
				councilors.remove(0);
			}
			retArr = councilors.toArray(retArr);
			balcony[i]=new Balcony(retArr);
		}

		//empori
		emporiums = new Emporium[players.length * EMPORIUMQTY];
		for (int i=0;i<players.length;i++) {
			for(int k=0;k<10;k++)
				emporiums[i*10 + k] = new Emporium(players[i]);
		}
		//BISOGNA PASSARLI A CIASCUN GIOCATORE -> Ci pensa Game.java usando il metodo getPlayerEmporiums

		//assistenti
		int assistNo=ASSISTQTY;
		assistants = new ArrayList<Assistant>(assistNo);
		for(int i=0;i<assistNo;i++)
			assistants.add(new Assistant());

		//regioni
		regions = new Region[3];
		regions[0]= new Region(RegionName.SEA);
		regions[1]= new Region(RegionName.HILL);
		regions[2]= new Region(RegionName.MOUNTAIN);

		//color group
		colorGroups = new ColorGroup[5];
		colorGroups[0]= new ColorGroup(CityColor.BLUE);
		colorGroups[1]= new ColorGroup(CityColor.RED);
		colorGroups[2]= new ColorGroup(CityColor.GREY);
		colorGroups[3]= new ColorGroup(CityColor.YELLOW);
		colorGroups[4]= new ColorGroup(CityColor.PURPLE);
		
		//deck carte politica
		politicsDeck = new PoliticsDeck();

	}

	public int insertCity(City c, String regione, String color)
	{
		if (regione.toLowerCase().equals("sea"))
			regions[0].addCity(c);
		else if (regione.toLowerCase().equals("hill"))
			regions[1].addCity(c);
		else if (regione.toLowerCase().equals("mountain"))
			regions[2].addCity(c);
		else
			return -1;//lancia errore

		switch (color.toLowerCase()) {
		case "blue":  colorGroups[0].addCity(c);
		break;
		case "grey":  colorGroups[1].addCity(c);
		break;
		case "purple":  colorGroups[2].addCity(c);
		break;
		case "red":  colorGroups[3].addCity(c);
		break;
		case "yellow":  colorGroups[4].addCity(c);
		break;
		default: return -2;//lancia errore
		}
		return 1;
	}
	
	public Region getRegion(int index)
	{
		return regions[index];
	}
	
	public ColorGroup getColorGroup(int index)
	{
		return colorGroups[index];
	}

	/**
	 * 
	 * @param index the index that indicates which player is asking for emporiums
	 * @return an ArrayList of 10 emporiums
	 */

	public ArrayList<Emporium> getPlayerEmporiums(int index) {
		ArrayList<Emporium> emporiums = new ArrayList<Emporium>(EMPORIUMQTY);

		for(int i=index*EMPORIUMQTY; i<EMPORIUMQTY*(index+1);i++)
			emporiums.add(this.emporiums[i]);

		return emporiums;
	}

	public PoliticsDeck getPoliticsDeck() {
		return politicsDeck;
	}

	/**
	 * @return an ArrayList of assistants
	 */
	public ArrayList<Assistant> getAssistantsPool() {
		return assistants;
	}

	public ArrayList<Assistant> getAssistant(int qty) {
		ArrayList<Assistant> assistantsToGive = new ArrayList<Assistant>(qty);
		for(int i=0;i<qty;i++){
			assistantsToGive.add(getAssistantsPool().get(0));
			getAssistantsPool().remove(0);
		}
		return assistantsToGive;
	}
	
	public PermitsDeck getPermitsDeck(int index)
	{
		return permitsDeck[index];
	}
	
	/*private boolean isColorAvailable(CouncilorColor c)
	{
		for(Councilor cc: councilors)
			if(cc.getCouncilorColor().equals(c))
				return true;
		return false;
	}*/
	
	public ArrayList<CouncilorColor> getAvailableColors()
	{
		ArrayList<CouncilorColor> availables = new ArrayList<CouncilorColor>();
		for(Councilor c: councilors)
			if(!availables.contains(c.getCouncilorColor()))
				availables.add(c.getCouncilorColor());
		return availables;
	}
	/**
	 * Get a balcony from map
	 * @param selection 0: sea balcony, 1: hill balcony, 2: mountain balcony, 3: king balcony
	 * @return the selected balcony
	 */
	public Balcony getBalcony(int selection) {
		return this.balcony[selection];
	}

	/**
	 * @return the king
	 */
	public King getKing() {
		return king;
	}

	/**
	 * @param king the king to set
	 */
	private void setKing(King king) {
		this.king = king;
	}

	/**
	 * @return the pawn
	 */

	/**
	 * @param pawn the pawn to set
	 */
	/**
	 * @return the nobilityTrack
	 */
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	/**
	 * @param nobilityTrack the nobilityTrack to set
	 */
	public void setNobilityTrack(NobilityTrack nobilityTrack) {
		this.nobilityTrack = nobilityTrack;
	}

	/**
	 * @return the kingBonus
	 */
	public ArrayList<Bonus> getKingBonus() {
		return kingBonus;
	}

	/**
	 * @param kingBonus the kingBonus to set
	 */
	public void setKingBonus(ArrayList<Bonus> kingBonus) {
		this.kingBonus = kingBonus;
	}

	/**
	 * @return the city
	 */
	public City[] getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City[] city) {
		this.city = city;
	}

	/**
	 * @return the councilors
	 */
	public ArrayList<Councilor> getCouncilorsPool() {
		return councilors;
	}
	
	public Councilor getCouncilor(CouncilorColor col)
	{
		for(Councilor c : councilors)
			if(c.getCouncilorColor().equals(col))
				{
				councilors.remove(c);
				return c;
				}
		return null;
	}
}

