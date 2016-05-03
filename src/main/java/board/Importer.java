package board;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import decks.PermitsCard;
import decks.PermitsDeck;
import gamelogic.Player;
import model.BonusType;
import model.CityColor;

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
	private ArrayList<Bonus> kingBonus;// to initialize
	private PermitsDeck permitsDeck;// to initialize
	private Map mapInst;
	private King k;
	
	
	public Importer(String loc, boolean def, Map m, Player[] p) throws Exception {
		this.players = p;
		this.mapInst = m;
		if (def)
			this.location = "default location";
		else
			this.location = loc;

		File inputFile = new File(location);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dBuilder = dbFactory.newDocumentBuilder();
		doc = (Document) dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
	}

	public int startImport() {
		/// importo bonus regione
		Bonus[] bonusRegioni = new Bonus[3];// [0]=mare [1]=colline [2]=montagne
		String[] nomiRegioni = { "MARE", "COLLINE", "MONTAGNE" };
		Node regionNode = (doc).getElementsByTagName("REGION_BONUS").item(0);
		for (int i = 0; i < nomiRegioni.length; i++) {
			Element rElem = (Element) ((Element) regionNode).getElementsByTagName(nomiRegioni[i]).item(0);
			Element rBonusElem = (Element) rElem.getElementsByTagName("BONUS").item(0);
			String rBonusTypeStr = rBonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
			String rBonusAmmStr = rBonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			bonusRegioni[i] = new Bonus(parseBonus(rBonusTypeStr), Integer.parseInt(rBonusAmmStr));
		}

		/// importo bonus colore
		Bonus[] colorBonus = new Bonus[5];// ordine come nomiColori qua sotto
		String[] nomiColori = { "BLUE", "GREY", "YELLOW", "RED", "PURPLE" };
		Node colorNode = (doc).getElementsByTagName("REGION_BONUS").item(0);
		for (int i = 0; i < nomiColori.length; i++) {
			Element cElem = (Element) ((Element) colorNode).getElementsByTagName(nomiColori[i]).item(0);
			Element cBonusElem = (Element) cElem.getElementsByTagName("BONUS").item(0);
			String cBonusTypeStr = cBonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
			String cBonusAmmStr = cBonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			colorBonus[i] = new Bonus(parseBonus(cBonusTypeStr), Integer.parseInt(cBonusAmmStr));
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
				String elem_type_str = bonusElem.getElementsByTagName("NAME").item(0).getTextContent();
				BonusType elem_type = parseBonus(elem_type_str);
				String elem_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				int elem_amm = Integer.parseInt(elem_amm_str);
				tokenBonus[j] = new Bonus(elem_type, elem_amm);

			}
			tokenPool.add(new BonusToken(tokenBonus));
		}

		//importa città
		NodeList nList = (doc).getElementsByTagName("city");
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
			BonusToken bt = tokenPool.remove(randomNum(0, tokenPool.size() - 1));
			city[i] = new City(elem_name, parseColor(elem_color), closes, players.length, bt);
			mapInst.insertCity(city[i], elem_region, elem_color);
			if (!validateCities(city))
				return -1;// lancia errore
		}

		// importo king e bonus del re
		Element kingElem = (Element) (doc).getElementsByTagName("KING").item(0);
		String kingLocationStr = kingElem.getElementsByTagName("LOCATION").item(0).getTextContent().toLowerCase();
		for(City c: city)
			if(c.getName().toLowerCase()==kingLocationStr)
				k = new King(c);
		if(k==null) return -2;
		
		NodeList bonList = kingElem.getElementsByTagName("BONUS");
		for (int i = 0; i < bonList.getLength(); i++) {
			Node bonusNode = bonList.item(i);
			Element bonusElem = (Element) bonusNode;
			String elem_type_str = bonusElem.getElementsByTagName("NAME").item(0).getTextContent();
			BonusType elem_type = parseBonus(elem_type_str);
			String elem_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			int elem_amm = Integer.parseInt(elem_amm_str);
			kingBonus.add(new Bonus(elem_type, elem_amm));
		}

		// importo nobility track
		this.nobilityTrackBonus = new Bonus[20][3];// max 3 bonus per casella;
		Node nobilityNode = (doc).getElementsByTagName("NOBILITY_BONUS").item(0);
		NodeList posList = ((Element) nobilityNode).getElementsByTagName("POSIZIONE");
		int index = 0;
		for (int i = 0; i < posList.getLength(); i++) {
			Element posElem = (Element) posList.item(i);
			index = Integer.parseInt(posElem.getAttribute("index"));
			NodeList bonusList = posElem.getElementsByTagName("BONUS");
			for (int j = 0; j < posList.getLength(); j++) {
				Element bonusElem = (Element) bonusList.item(j);
				String bonus_type_str = bonusElem.getElementsByTagName("NAME").item(0).getTextContent();
				BonusType bonus_type = parseBonus(bonus_type_str);
				String bonus_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				int bonus_amm = Integer.parseInt(bonus_amm_str);
				nobilityTrackBonus[index][j] = new Bonus(bonus_type, bonus_amm);
			}
		}
		this.nobilityTrack = new NobilityTrack(pawn, nobilityTrackBonus);

		
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
			pawnColors[randomNo] = "";
			pawn[i] = new Pawn(players[i], pawnColors[randomNo]);
		}
		
		//importo carte permesso
		Element permElem = (Element) (doc).getElementsByTagName("POOL_CARTE_PERMESSO").item(0);
		NodeList cardList = permElem.getElementsByTagName("CARTA");
		permitsCardPool = new PermitsCard[3][cardList.getLength()];
		Bonus[] bonusBuff;
		String[] letterBuff;
		for (int i = 0; i < cardList.getLength(); i++) {
			Element cardElem = (Element) cardList.item(i);
			NodeList bonusList = cardElem.getElementsByTagName("BONUS");
			bonusBuff = new Bonus[bonusList.getLength()];
			for (int j = 0; j < cardList.getLength(); j++) {
				Element bonusElem = (Element) bonusList.item(j);
				String bonus_type_str = bonusElem.getElementsByTagName("NAME").item(0).getTextContent();
				BonusType bonus_type = parseBonus(bonus_type_str);
				String bonus_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
				int bonus_amm = Integer.parseInt(bonus_amm_str);
				bonusBuff[i] = new Bonus(bonus_type, bonus_amm);
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
			permitsCardPool[regionCode][i] = new PermitsCard(bonusBuff,letterBuff);
		}
		return 1;
	}


	private static int randomNum(int Min, int Max) {
		return Min + (int) (Math.random() * ((Max - Min) + 1));
	}

	private CityColor parseColor(String color) {
		CityColor[] colors = CityColor.values();
		for (CityColor c : colors) {
			if (color.toLowerCase().equals(c.name().toLowerCase()))
				return c;
		}
		return null;
	}

	private BonusType parseBonus(String b) {
		BonusType[] types = BonusType.values();
		for (BonusType t : types) {
			if (b.toLowerCase().equals(t.name().toLowerCase()))
				return t;
		}
		return null;
	}
	

	boolean validateCities(City[] c) {
		if (c.length != 15)
			return false; // controllo numero
		boolean[] iniziali = new boolean[13];// controllo iniziali diverse
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
		ArrayList<Character> inits = (ArrayList<Character>) Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o');// controllo iniziali
		for (int i = 0; i < c.length; i++) {
			Character cha = new Character(c[i].getName().charAt(0));
			if (!inits.contains(cha))
				return false;
			inits.remove(cha);
		}
		if (inits.size() > 0)
			return false;
		return true;
	}

	int letterToRegion(String letter)
	{
		String[][] in = {{"a","b","c","d","e"},{"f","g","h","i","j"},{"k","l","m","n","o"}}; 
		for(int i=0;i<3;i++)
			for(String l : in[i])
			if(l.equals(letter.toLowerCase()))
				return i;
		return -1;
	}
	public Pawn[] getPawn() {
		return pawn;
	}

	public PermitsCard[][] getPermitsCardPool() {
		return permitsCardPool;
	}

	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	public City[] getCity() {
		return city;
	}
	
	public Bonus[] getColorBonus() {
		return colorBonus;
	}

	public ArrayList<Bonus> getKingBonus() {
		return kingBonus;
	}
	
	public ArrayList<Bonus> getKingBonusDeck() {
		return kingBonus;
	}


	public PermitsDeck getPermitsDeck() {
		return permitsDeck;
	}
	
	public Bonus[] getRegionBonus() {
		return regionBonus;
	}
	
	public King getKing() {
		return k;
	}
}