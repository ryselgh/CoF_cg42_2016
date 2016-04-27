package board ;

import java.util.ArrayList;
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
	 * @generated
	 * @ordered
	 */
	
	public Map(Player[] p) {//qui come parametro deve avere quasi tutto
		this.players = p;	
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
	
	public void importMap(String file) throws ParserConfigurationException, IOException, SAXException { //inizializza: città(+bonus token), regioni(+bonus card), color_group(+bonus card), king, nobility_track
		//nobility track bonus
		this.nobilityTrackBonus = new Bonus[20][2]; //DA IMPORTARE O CREARE RANDOM
		//nobility track
		this.nobilityTrack = new NobilityTrack(pawn,nobilityTrackBonus);
		
		
		
		File inputFile = new File(file);
        DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = (Document) dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        
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
        	city[i] = new City(elem_name,parseColor(elem_color),closes,players.length, null);//da assegnare il token
        	if (validateCities(city)==false)
        		break;
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
		//pedine
		pawn = new Pawn[players.length];
		for(i=0;i<players.length;i++)
		{
			pawn[i]=new Pawn(players[i]);
		}
		
		
		
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
		boolean[] iniziali = new boolean[13];
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
		return true;
	}
	
}

