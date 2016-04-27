package board ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.Array;

import gamelogic.Player;
import model.CouncilorColor;
import model.CityColor;
import model.BonusType;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Map
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Player[] players;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Region[] regions;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList <Councilor> councilors;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Balcony[] balcony;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Assistant[] assistants;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Emporium[] emporiums;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Pawn[] pawn;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private NobilityTrack nobilityTrack;
	private Bonus[][] nobilityTrackBonus;
	private BonusToken[] token_pool;
	private Bonus[] pool_carte_permesso;
	private String[] colori_pedine;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private City[] city;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @generated
	 * @ordered
	 */
	
	public Map(Player[] p, boolean _default) {//da implementare default
		this.players = p;
		initializeMapObjects();
		try {
			importMap("",_default);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @generated
	 * @ordered
	 */
	
	public void importMap(String file, boolean _default) throws Exception  { //inizializza: città(+bonus token), regioni(+bonus card), color_group(+bonus card), king, nobility_track
		
		
		
		
		File inputFile = new File(file);
        DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc;
		dBuilder = dbFactory.newDocumentBuilder();
		doc = (Document) dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
        
      
		///importo bonus regione
		Bonus[] bonusRegioni = new Bonus[3];//[0]=mare     [1]=colline     [2]=montagne
		String[] nomiRegioni = {"MARE","COLLINE","MONTAGNE"};
		Node regionNode = (doc).getElementsByTagName("REGION_BONUS").item(0);
		for (int i = 0; i < 3; i++) {
			Element rElem = (Element) ((Element) regionNode).getElementsByTagName(nomiRegioni[i]).item(0);
			Element rBonusElem = (Element) rElem.getElementsByTagName("BONUS").item(0);
			String rBonusTypeStr = rBonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
			String rBonusAmmStr = rBonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			bonusRegioni[i] = new Bonus(parseBonus(rBonusTypeStr), Integer.parseInt(rBonusAmmStr));
		}
		
		
		/// importo bonus colore
		Bonus[] bonusColori = new Bonus[5];// ordine come nomiColori qua sotto
		String[] nomiColori = { "BLUE", "GREY", "YELLOW", "RED", "PURPLE" };
		Node colorNode = (doc).getElementsByTagName("REGION_BONUS").item(0);
		for (int i = 0; i < 5; i++) {
			Element cElem = (Element) ((Element) colorNode).getElementsByTagName(nomiColori[i]).item(0);
			Element cBonusElem = (Element) cElem.getElementsByTagName("BONUS").item(0);
			String cBonusTypeStr = cBonusElem.getElementsByTagName("TYPE").item(0).getTextContent();
			String cBonusAmmStr = cBonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			bonusColori[i] = new Bonus(parseBonus(cBonusTypeStr), Integer.parseInt(cBonusAmmStr));
		}
		
		
      //importo bonus token
	    Node poolNode = (doc).getElementsByTagName("TOKEN_POOL").item(0);
	    NodeList tokensList = ((Element) poolNode).getElementsByTagName("TOKEN");
	    token_pool = new BonusToken[tokensList.getLength()];
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
            	tokenBonus[j]= new Bonus(elem_type, elem_amm);
            	
        	}
        	token_pool[i] = new BonusToken(tokenBonus);
        }	
        
        
        
        //importo città TODO: DA INSERIRE NELLE REGIONI, ATTRIBUTO REGION NON ANCORA UTILIZZATO 
        //TODO2: inserire il token nella città una volta importati gli oggetti, ho messo la funzione setToken(t)
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
        	city[i] = new City(elem_name,parseColor(elem_color),closes,players.length);//da assegnare il token
        	if (validateCities(city)==false)
        		;//lancia errore
        }	
        	

		// importo king
		Element kingElem = (Element) (doc).getElementsByTagName("KING").item(0);
		String kingLocationStr = kingElem.getElementsByTagName("LOCATION").item(0).getTextContent();
		boolean presente = false;
		for(City c: city)
			if(kingLocationStr.equals(c.getName()))
				presente = true;
		if(!presente) ;//lancia errore
		
		
		//importo nobility track
		this.nobilityTrackBonus = new Bonus[20][3];//max 3 bonus per casella;
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
            	nobilityTrackBonus[index][j]= new Bonus(bonus_type, bonus_amm);
        	}
        }	
	    nobilityTrack = new NobilityTrack(pawn,nobilityTrackBonus);
	    
		// importo pool carte permesso
		Element permElem =(Element) (doc).getElementsByTagName("POOL_CARTE_PERMESSO").item(0);
		NodeList bonusList = permElem.getElementsByTagName("BONUS");
	    pool_carte_permesso = new Bonus[bonusList.getLength()];
		for (int i = 0; i < posList.getLength(); i++) {
			Element bonusElem = (Element) bonusList.item(i);
			String bonus_type_str = bonusElem.getElementsByTagName("NAME").item(0).getTextContent();
			BonusType bonus_type = parseBonus(bonus_type_str);
			String bonus_amm_str = bonusElem.getElementsByTagName("AMM").item(0).getTextContent();
			int bonus_amm = Integer.parseInt(bonus_amm_str);
			pool_carte_permesso[i] = new Bonus(bonus_type, bonus_amm);
		}
	

		// importo colori pedine
		Element pedElem =(Element) (doc).getElementsByTagName("COLORI_PEDINE").item(0);
		NodeList colorList = permElem.getElementsByTagName("COLORE");
		if(colorList.getLength()<8);//lancia errore
	    colori_pedine = new String[colorList.getLength()];
		for (int i = 0; i < colorList.getLength(); i++) {
			Element colorElem = (Element) colorList.item(i);
			colori_pedine[i] = colorElem.getTextContent();
		}
		pawn = new Pawn[players.length];
		int randomNo=0;
		for(int i=0;i<players.length;i++)
		{		
			do{
				randomNo = (int)(Math.random() * ((colorList.getLength() - 1) + 1));}
			while (colori_pedine[randomNo]!="");
			pawn[i]=new Pawn(players[i], colori_pedine[randomNo]);
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void initializeMapObjects() {
		int i,k;
		
		//councilors
		ArrayList <Councilor> councilors = new ArrayList <Councilor>();
		for (CouncilorColor c : CouncilorColor.values()) {
		    for(i=0;i<4;i++)
		    	councilors.add(new Councilor(c));
		}
		Collections.shuffle(councilors);    	
		
		//balconi
		balcony = new Balcony[3];
		int count =0;
		for (Balcony b : balcony) {
			ArrayList <Councilor> toRet = new ArrayList <Councilor>();
			Councilor[] retArr = new Councilor[4];
			for(i=0;i<4;i++)
			{
				toRet.add(councilors.get(0));
				councilors.remove(0);
			}
			retArr = councilors.toArray(retArr);
			b=new Balcony(retArr);
		}
		
		//empori
		emporiums = new Emporium[players.length * 10];
		for (i=0;i<players.length;i++) {
			for(k=0;k<10;k++)
				emporiums[i*10 + k] = new Emporium(players[i]);
		}
		//BISOGNA PASSARLI A CIASCUN GIOCATORE
		
		//assistenti
		int assistNo=50;
		assistants = new Assistant[assistNo];
		for(i=0;i<assistNo;i++)
		{
			assistants[i]=new Assistant();
		}
	}
	
	
	
	
	CityColor parseColor(String color)
	{
		CityColor[] colors = CityColor.values();
		for(CityColor c: colors)
		{
			if(color.toLowerCase().equals(c.name().toLowerCase()))
				return c;
		}
		return null;
	}
	
	BonusType parseBonus(String b)
	{
		BonusType[] types = BonusType.values();
		for(BonusType t: types)
		{
			if(b.toLowerCase().equals(t.name().toLowerCase()))
				return t;
		}
		return null;
	}
	boolean validateCities(City[] c)
	{
		if(c.length!=15) return false; //controllo numero
		boolean[] iniziali = new boolean[13];//controllo iniziali diverse
		for(City cc : c)
		{
			char in = cc.getName().toLowerCase().charAt(0);
			int ascii = (int) in;
			int a_ascii = (int) 'a';
			if (iniziali[ascii - a_ascii]==true)
				return false;
			else
				iniziali[ascii - a_ascii]=true;
		}
		//controllo iniziali
		ArrayList <Character> inits = (ArrayList<Character>) Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o');//controllo iniziali
		for(int i=0;i<c.length;i++)
		{
			Character cha = new Character(c[i].getName().charAt(0));
			if (!inits.contains(cha)) return false;
			inits.remove(cha);
		}
		if (inits.size()>0) return false;
		return true;
	}
	
}

