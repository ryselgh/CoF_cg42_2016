package com.server.model.board;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.server.model.decks.PermitsCard;
import com.server.model.decks.PermitsDeck;
import com.server.model.gamelogic.Player;
import com.communication.values.BonusType;
import com.communication.values.CityColor;

public class Importer {
	String location;
	Document doc;
	private Player[] players;
	private Pawn[] pawn;
	private NobilityTrack nobilityTrack;
	private Bonus[][] nobilityTrackBonus;
	private ArrayList<BonusToken> tokenPool;
	private PermitsCard[][] permitsCardPool;
	private Bonus[] regionBonus;
	private Bonus[] colorBonus;
	private String[] pawnColors;
	private City[] city;
	private ArrayList<Bonus> kingBonus;
	private PermitsDeck permitsDeck;
	private Map mapInst;
	private King k;
	
	/**
	 * 
	 * @param loc
	 * @param def
	 * @param m
	 * @param p
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public Importer(String rawMap, boolean def, Map m, Player[] p) throws ParserConfigurationException, SAXException, IOException{
		this.players = p;
		this.mapInst = m;
		if (def) {//importo da file
			this.location = "Default map.xml";
			File inputFile = new File(location);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			doc = (Document) dBuilder.parse(inputFile);
		}
		else{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();//importo da stringa
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(rawMap));
			doc = db.parse(is);
		}
		
		doc.getDocumentElement().normalize();
	}
	
	/**
	 * 
	 * @return
	 */

	public int startImport() {
		/// importo bonus regione
		regionBonus = new Bonus[3];// [0]=mare [1]=colline [2]=montagne
		String[] nomiRegioni = { "SEA", "HILL", "MOUNTAIN" };
		NodeList tmp = doc.getElementsByTagName("REGION_BONUS");
		Node regionNode = (doc).getElementsByTagName("REGION_BONUS").item(0);
		for (int i = 0; i < nomiRegioni.length; i++) {
			tmp = ((Element) regionNode).getElementsByTagName(nomiRegioni[i]);
			Element rElem = (Element) ((Element) regionNode).getElementsByTagName(nomiRegioni[i]).item(0);
			Element rBonusElem = (Element) rElem.getElementsByTagName("BONUS").item(0);
			String rBonusTypeStr = rBonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
			String rBonusAmmStr = rBonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			regionBonus[i] = new Bonus(parseBonus(rBonusTypeStr), Integer.parseInt(rBonusAmmStr));
		}

		/// importo bonus colore
		colorBonus = new Bonus[5];// ordine come nomiColori qua sotto
		String[] nomiColori = { "BLUE", "RED", "GREY", "YELLOW", "PURPLE" };
		Node colorNode = (doc).getElementsByTagName("COLOR_BONUS").item(0);
		for (int i = 0; i < nomiColori.length; i++) {
			Element cElem = (Element) ((Element) colorNode).getElementsByTagName(nomiColori[i]).item(0);
			Element cBonusElem = (Element) cElem.getElementsByTagName("BONUS").item(0);
			if(cBonusElem == null)
			{
				colorBonus[i] = null;
				break;
			}
			else
			{
				String cBonusTypeStr = cBonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
				String cBonusAmmStr = cBonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				colorBonus[i] = new Bonus(parseBonus(cBonusTypeStr), Integer.parseInt(cBonusAmmStr));
			}
		}

		// importo bonus token -> usati in importa città
		Node poolNode = (doc).getElementsByTagName("TOKEN_POOL").item(0);
		NodeList tokensList = ((Element) poolNode).getElementsByTagName("TOKEN");
		tokenPool = new ArrayList<BonusToken>(tokensList.getLength());
		for (int i = 0; i < tokensList.getLength(); i++) {
			Node tokenNode = tokensList.item(i);
			Element tokenElem = (Element) tokenNode;
			NodeList bonusList = tokenElem.getElementsByTagName("BONUS");
			Bonus[] tokenBonus = new Bonus[bonusList.getLength()];
			for (int j = 0; j < bonusList.getLength(); j++) {
				Node bonusNode = bonusList.item(j);
				Element bonusElem = (Element) bonusNode;
				String elem_type_str = bonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
				BonusType elem_type = parseBonus(elem_type_str);
				String elem_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				int elem_amm = Integer.parseInt(elem_amm_str);
				tokenBonus[j] = new Bonus(elem_type, elem_amm);

			}
			tokenPool.add(new BonusToken(tokenBonus));
		}
		//importo king
		Element kingElem = (Element) (doc).getElementsByTagName("KING").item(0);
		String kingLocationStr = kingElem.getElementsByTagName("LOCATION").item(0).getTextContent().toLowerCase();
		
		//importa città
		NodeList nList = (doc).getElementsByTagName("CITY");
		city = new City[nList.getLength()];
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element elem = (Element) nNode;
			String elem_name = elem.getElementsByTagName("NAME").item(0).getTextContent();
			String elem_color = elem.getElementsByTagName("COLOR").item(0).getTextContent();
			String elem_region = elem.getElementsByTagName("REGION").item(0).getTextContent();
			NodeList closeList = elem.getElementsByTagName("CLOSE");
			Node closeNode = closeList.item(0);
			Element closeElem = (Element) closeNode;
			NodeList closeNameList = closeElem.getElementsByTagName("NAME");
			String[] closes = new String[closeNameList.getLength()];
			for (int j = 0; j < closeNameList.getLength(); j++) {
				closes[j] = closeNameList.item(j).getTextContent();
			}
			BonusToken bt;
			if(!elem_name.toLowerCase().equals(kingLocationStr))
			{
				int ran = randomNum(0, tokenPool.size() - 1);
				bt = tokenPool.remove(ran);
			}
			else
				bt = new BonusToken(new Bonus[1]);
			city[i] = new City(elem_name, parseColor(elem_color), closes, players.length, bt);
			mapInst.insertCity(city[i], elem_region, elem_color);
		}
		if (!validateCities(city))
			return -1;// lancia errore

		// importo bonus del re
		
		City temp = city[0];
		for(City c: city)
			if(c.getName().toLowerCase().equals(kingLocationStr))
				k = new King(c);
		if(k==null) return -2;
		
		NodeList bonList = kingElem.getElementsByTagName("BONUS");
		kingBonus = new ArrayList<Bonus>(bonList.getLength());
		for (int i = 0; i < bonList.getLength(); i++) {
			Node bonusNode = bonList.item(i);
			Element bonusElem = (Element) bonusNode;
			String elem_type_str = bonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
			BonusType elem_type = parseBonus(elem_type_str);
			String elem_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			int elem_amm = Integer.parseInt(elem_amm_str);
			kingBonus.add(new Bonus(elem_type, elem_amm));
		}

		
		// importo colori pedine e creo pedine
				Element pedElem = (Element) (doc).getElementsByTagName("COLORI_PEDINE").item(0);
				NodeList colorList = pedElem.getElementsByTagName("COLORE");
				if (colorList.getLength() < 8)
					return -3;// lancia errore
				pawnColors = new String[colorList.getLength()];
				for (int i = 0; i < colorList.getLength(); i++) {
					Element colorElem = (Element) colorList.item(i);
					pawnColors[i] = colorElem.getTextContent();
				}
				pawn = new Pawn[players.length];
				int randomNo = 0;
				for (int i = 0; i < players.length; i++) {
					do {
						randomNo = randomNum(0, colorList.getLength() - 1);
					} while (pawnColors[randomNo] == "");
					pawn[i] = new Pawn(players[i], pawnColors[randomNo]);
					pawnColors[randomNo] = "";
				}
				
				
		// importo nobility track
		this.nobilityTrackBonus = new Bonus[21][3];// max 3 bonus per casella;
		Node nobilityNode = (doc).getElementsByTagName("NOBILITY_BONUS").item(0);
		NodeList posList = ((Element) nobilityNode).getElementsByTagName("POSIZIONE");
		int index = 0;
		for (int i = 0; i < posList.getLength(); i++) {
			Element posElem = (Element) posList.item(i);
			index = Integer.parseInt(posElem.getAttribute("index"));
			NodeList bonusList = posElem.getElementsByTagName("BONUS");
			for (int j = 0; j < bonusList.getLength(); j++) {
				Element bonusElem = (Element) bonusList.item(j);
				String bonus_type_str = bonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
				BonusType bonus_type = parseBonus(bonus_type_str);
				String bonus_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				int bonus_amm = Integer.parseInt(bonus_amm_str);
				nobilityTrackBonus[index][j] = new Bonus(bonus_type, bonus_amm);
			}
		}
		this.nobilityTrack = new NobilityTrack(pawn, nobilityTrackBonus);

		
		
		
		//importo carte permesso
		Element permElem = (Element) (doc).getElementsByTagName("POOL_CARTE_PERMESSO").item(0);
		NodeList cardList = permElem.getElementsByTagName("CARTA");
		permitsCardPool = new PermitsCard[3][cardList.getLength()/3];
		Bonus[] bonusBuff;
		String[] letterBuff;
		for (int i = 0; i < cardList.getLength(); i++) {
			Element cardElem = (Element) cardList.item(i);
			NodeList bonusList = cardElem.getElementsByTagName("BONUS");
			bonusBuff = new Bonus[bonusList.getLength()];
			for (int j = 0; j < bonusList.getLength(); j++) {
				Element bonusElem = (Element) bonusList.item(j);
				String bonus_type_str = bonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
				BonusType bonus_type = parseBonus(bonus_type_str);
				String bonus_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				int bonus_amm = Integer.parseInt(bonus_amm_str);
				bonusBuff[j] = new Bonus(bonus_type, bonus_amm);
			}
			NodeList letterList = cardElem.getElementsByTagName("LETTER");
			letterBuff = new String[letterList.getLength()];
			int regionCode= -1;
			for (int j = 0; j < letterList.getLength(); j++) {
				letterBuff[j] = letterList.item(j).getTextContent();
				int regionCodeTmp = letterToRegion(letterBuff[j]);
				if(regionCode != -1 && regionCodeTmp!=regionCode)
					return -3; //errore: lettere che si riferiscono a regioni diverse
				else //non necessario se resta il return sopra
					regionCode = regionCodeTmp;
			}
			permitsCardPool[regionCode][i%15] = new PermitsCard(bonusBuff,letterBuff);
		}
		return 1;
	}
	
	/**
	 * 
	 * @param Min
	 * @param Max
	 * @return a random number between min max
	 */


	private static int randomNum(int Min, int Max) {
		return Min + (int) (Math.random() * ((Max - Min) + 1));
	}

	/**
	 * convert a string to a member of the enum
	 * @param color
	 * 
	 */
	private CityColor parseColor(String color) {
		CityColor[] colors = CityColor.values();
		for (CityColor c : colors) {
			if (color.toLowerCase().equals(c.name().toLowerCase()))
				return c;
		}
		return null;
	}
	/**
	 * convert a stirng to a member of the enum
	 * @param b
	 * 
	 */
	private BonusType parseBonus(String b) {
		BonusType[] types = BonusType.values();
		for (BonusType t : types) {
			if (b.toLowerCase().equals(t.name().toLowerCase()))
				return t;
		}
		return null;
	}
	
/**
 * check if the city has been created right
 * @param c
 * @return
 */
	boolean validateCities(City[] c) {
		if (c.length != 15)
			return false; // controllo numero
		boolean[] iniziali = new boolean[c.length];// controllo iniziali diverse
		for (City cc : c) {
			char in = cc.getName().toLowerCase().charAt(0);
			int ascii = (int) in;
			int a_ascii = (int) 'a';
			if (iniziali[ascii - a_ascii] == true)
				return false;
			else
				iniziali[ascii - a_ascii] = true;
		}
		// controllo iniziali
		ArrayList<Character> inits = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'));// controllo iniziali
		for (int i = 0; i < c.length; i++) {
			Character cha = new Character(c[i].getName().toLowerCase().charAt(0));
			if (!inits.contains(cha))
				return false;
			inits.remove(cha);
		}
		if (inits.size() > 0)
			return false;
		return true;
	}
	
	/**
	 * associate the letter at the region
	 * @param letter is the letter in input
	 * @return the number of the city based on the letter
	 */

	int letterToRegion(String letter)
	{
		String[][] in = {{"a","b","c","d","e"},{"f","g","h","i","j"},{"k","l","m","n","o"}}; 
		for(int i=0;i<3;i++)
			for(String l : in[i])
			if(l.equals(letter.toLowerCase()))
				return i;
		return -1;
	}
	
	/**
	 * 
	 * @return the pawn array
	 */
	public Pawn[] getPawn() {
		return pawn;
	}
	/**
	 * 
	 * @return the region and the deck
	 */

	public PermitsCard[][] getPermitsCardPool() {
		return permitsCardPool;
	}
	/**
	 * 
	 * @return the nobility track
	 */

	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}
	/**
	 * 
	 * @return the city array
	 */

	public City[] getCity() {
		return city;
	}
	/**
	 * 
	 * @return  the bonus array
	 */
	
	public Bonus[] getColorBonus() {
		return colorBonus;
	}
	/**
	 * 
	 * @return the arraylist of king bonus cards
	 */

	public ArrayList<Bonus> getKingBonus() {
		return kingBonus;
	}
	/**
	 * 
	 * @return the king bonus deck
	 */
	
	public ArrayList<Bonus> getKingBonusDeck() {
		return kingBonus;
	}
	/**
	 * 
	 * @return the permits deck
	 */


	public PermitsDeck getPermitsDeck() {
		return permitsDeck;
	}
	/**
	 * 
	 * @return the regionbonus
	 */
	
	public Bonus[] getRegionBonus() {
		return regionBonus;
	}
	/**
	 * 
	 * @return the king
	 */
	
	public King getKing() {
		return k;
	}
}
