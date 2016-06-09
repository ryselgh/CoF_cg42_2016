package com.client.view;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import com.client.ClientObservable;
import com.client.ClientObserver;
import com.client.controller.ClientController;
import com.client.utils.TableBuilder;
import com.communication.board.AssistantDTO;
import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.CouncilorDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.*;

public class ClientCLI extends Observable implements Observer, Runnable{

	private GameDTO game;
	private PrintStream out;
	private Scanner in;
	private String[][] map = new String[15][5];

	/**
	 * constructor of the class
	 * @param controller is the client controller
	 */

	public ClientCLI(ClientController clientController){
		clientController.addObserver(this);
		this.out = System.out;
		this.in = new Scanner(System.in);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void setGameAndBuildMap(GameDTO game){
		this.game = game;
		constructMap();
	}
	private void constructMap() {

		//City + Region separators
		String[] initialsRowOne = {"A","C","|","F","I","|","K","N"};
		String[] initialsRowTwo = {"B","D","|","G","J","|","L","O"};
		String[] initialsRowThree = {"E"," ","|","H"," ","|","M"," "};

		for(int i=0;i<15;i++){
			for(int j=0;j<5;j++){
				switch(j){
				case 0:
					if(i%2==0)
						map[i][j]=initialsRowOne[i/2];
					break;
				case 2:
					if(i%2==0)
						map[i][j]=initialsRowTwo[i/2];
					break;
				case 4:
					if(i%2==0)
						map[i][j]=initialsRowThree[i/2];
					break;
				default:
					if(i==4 || i==10)
						map[i][j]="|";
					break;
				}
			}
		}

		//Fill empty spaces
		for(int i=0;i<15;i++){
			for(int j=0;j<5;j++){
				if(map[i][j]==null)
					map[i][j] = " ";
			}
		}

		//Connections
		for(int i=0;i<15;i++){
			for(int j=0;j<5;j++){
				for(int cityID=0; cityID < game.getMap().getCity().length; cityID++){
					String c = game.getMap().getCity()[cityID].getName();
					for(String cc: game.getMap().getCity()[cityID].getCloseCities()){
						String letter1 = String.valueOf(c.charAt(0)).toUpperCase();
						String letter2 = String.valueOf(cc.charAt(0)).toUpperCase();
						//Rows
						if(i%2!=0 && (j==0 || j==2) && i-1>=0 && i+1<15){ //First and Third row of emptyes
							if(map[i+1][j]!="|" && map[i-1][j]!="|") //if NOT a separator
								if(map[i-1][j].equals(letter1) &&
										map[i+1][j].equals(letter2)) //And there is a connection
									map[i][j]="–"; //Simple connection
						}
						if((j==0||j==2)&&map[i][j]=="|") //if a separator
							if(map[i-2][j].equals(letter1) &&
									map[i+2][j].equals(letter2)){
								map[i-1][j]="–";
								map[i+1][j]="–";
							}
						if(j==4 && i>=4 && i<=12 && map[i][j]=="|")
							if(map[i-4][j].equals(letter1) &&
									map[i+2][j].equals(letter2)){
								map[i-3][j] = "–";
								map[i-2][j] = "–";
								map[i-1][j] = "–";
								map[i+1][j] = "–";
							}
						//Columns
						if(j%2!=0)
							if(map[i][j-1].equals(letter1)&&map[i][j+1].equals(letter2))
								map[i][j]="|";
						//Diagonal "\"
						if(j%2!=0 && i>0)
							if(map[i-1][j-1].equals(letter1) && map[i+1][j+1].equals(letter2))
								map[i][j]="\\";
						//Diagonal "/"
						if(j%2!=0 && i<14)
							if(map[i+1][j-1].equals(letter1) && map[i-1][j+1].equals(letter2))
								map[i][j]="/";
					}
				}
			}
		}

	}
	
	/*------------------------------------------OUTPUTS---------------------------------------------*/

	/**
	 * print the map
	 */
	public void printMap(){
		for(int i=0;i<5;i++){
			for(int j=0;j<15;j++){
				System.out.print(map[j][i]);
			}
			System.out.print("\n");
		}
	}

	/**
	 *print the city bonus, for each  city 
	 * @param game is the game status
	 */

	public void printCityBonus(){
		ArrayList<CityDTO> cities = new ArrayList<CityDTO>(Arrays.asList(game.getMap().getCity()));
		for(CityDTO c: cities){
			ArrayList<String> bounsTypes = new ArrayList<String>();
				for(BonusDTO b: c.getToken().getBonus())
					if(b != null)
						bounsTypes.add(b.getType().toString()+"("+Integer.toString(b.getQnt())+")");
			out.println(c.getName()+ ": " + bounsTypes);
		}
	}
	
	public void printCouncilorPool(){
		out.print("\nCouncilors pool: [");
		for(CouncilorDTO c: game.getMap().getCouncilors())
			out.print(c.getColor().toString() + ",");
		out.print("]\n");
	}
	
	public void printAssistantPool(){
		int assistQty = game.getMap().getAssistants().size();
		out.println("\nAssistants pool: " + Integer.toString(assistQty));
	}
	
	public void printKingLocation(){
		out.println("\n\nKing's location: " + game.getMap().getKing().getLocation().getName());
	}
	
	public void printPermitsDecks(){
		out.print("\nPermits:\n  ");
		int i;
		for(i=0;i<3;i++){
			out.print(RegionName.values()[i].toString() + ":\n    Slot 1 {" + Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(0).getCityLetter()) + ",[");
		
			for(BonusDTO b: Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(0).getBonuses()))
				out.print(b.getType().toString()+"x"+Integer.toString(b.getQnt())+"|");
			
			out.print("}\n    Slot 2 {(" + Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(1).getCityLetter()) + ",[");
			
			for(BonusDTO b: Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(1).getBonuses()))
				out.print("|"+b.getType().toString()+"x"+Integer.toString(b.getQnt())+"|");
			
			out.print("]}\n  ");
		}
	}
	
	public void printCouncils(){
		out.print("Balconies:\n");
		for(int i=0; i<3; i++){
			out.print(RegionName.values()[i].toString() +":    	[");
			for(int j=0; j<4; j++){
				out.print(game.getMap().getBalcony(i).getCouncilor()[j].getColor().toString());
				if(j<3) out.print(",");
			}
			out.print("]<-\n");
		}
		out.print("KING:		[");
		for(int k=0; k<4; k++){
			out.print(game.getMap().getBalcony(3).getCouncilor()[k].getColor().toString());
			if(k<3) out.print(",");
		}
		out.print("]<-\n\n");
	}
	
	public void printNobilityTrack(){
		//Provisoional solution
		out.println("\nNobility Track:\n 1	2	    3		4	5	6	 7	    8		9	    10	     11	       12		13	    14		15	16	 17	   18	   19	   	20	  21");
		for(int i=0;i<21;i++){
			boolean hasBonus = false;
			out.print("[");
			for(int j=0;j<game.getMap().getNobilityTrack().getBonusVector()[i].length;j++){
				if(game.getMap().getNobilityTrack().getBonusVector()[i][j] != null){
					hasBonus = true;
					out.print("("+game.getMap().getNobilityTrack().getBonusVector()[i][j].getType()+"x");
					out.print(game.getMap().getNobilityTrack().getBonusVector()[i][j].getQnt()+")");	
				}
				else if(!hasBonus)
					out.print(" ");
			}
			out.print("]");
		}
	}
	
	public void printRegionBonuses(){
		out.print("\nRegion Bonus:\n    ");
		for(int i=0;i<3;i++){
			if(game.getMap().getRegion(i).getBonusCard().getBonus() != null)
				out.print(RegionName.values()[i].toString() + ": " 
				+game.getMap().getRegion(i).getBonusCard().getBonus().getType().toString()
				+"x"+ Integer.toString(game.getMap().getRegion(i).getBonusCard().getBonus().getQnt()));
			out.print("\n    ");
		}
	}
	
	public void printColorBonuses(){
		out.print("\nColor Bonus:\n    ");
		for(int i=0;i<CityColor.values().length-1;i++){
			out.print(CityColor.values()[i].toString() + " cities: " );
			if(game.getMap().getColorGroup(i).getBonusCard() != null)
				out.print(game.getMap().getColorGroup(i).getBonusCard().getBonus().getType().toString()
				+"x"+ Integer.toString(game.getMap().getColorGroup(i).getBonusCard().getBonus().getQnt()));
			out.print("\n    ");
		}
	}
	
	public void printKingBonuses(){
		out.print("\nKing Bonus:");
		int i = 0;
		for(BonusDTO b: game.getMap().getKingBonus()){
			out.print("\n  "+Integer.toString(i+1)+"°: " + b.getType().toString() + "x" + Integer.toString(b.getQnt()));
			i++;
		}
	}
	
	public void printPlacedEmporiums(){
		out.print("\nPlaced emporiums:\n");
		for(int i=0;i<game.getMap().getCity().length;i++){
			out.print(game.getMap().getCity()[i].getName()+": {");
			for(int j=0;j<game.getMap().getCity()[i].getSlot().length;j++){
				if(game.getMap().getCity()[i].getSlot()[j] != null){
					out.print("["+Integer.toString(game.getMap().getCity()[i].getSlot()[j].getPlayer().getPlayerID())+"]");
				}
			}
			out.print("}\n");
		}
	}
	
	public void printPlayersStatus(){
		TableBuilder tb = new TableBuilder();
		tb.addRow("Player", "Emporiums", "Assistants", "Coins", "Points", "Cards", "Active permits", "Used permits", "King Bonus", "Nobility");
		tb.addRow("--------------","--------------","--------------", "--------------", "--------------", "--------------", "--------------", "--------------", "--------------", "--------------");
		for(int i=0; i<game.getPlayers().size();i++){
			PlayerDTO p= game.getPlayers().get(i);
			int usedPermits = 0;
			ArrayList<String> activePermits = new ArrayList<String>();
			ArrayList<String> singlePermit = new ArrayList<String>();
			for(PermitsCardDTO pc: p.getPermits())
				if(pc.isFaceDown())
					usedPermits++;
				else{
					singlePermit.addAll(Arrays.asList(pc.getCityLetter()));
					activePermits.add(singlePermit.toString());
//					singlePermit.removeAll(singlePermit);
				}
			int nobilityPos = game.getMap().getNobilityTrack().getPawns()[i].getPos();
			tb.addRow(Integer.toString(p.getPlayerID()), Integer.toString(p.getAvailableEmporiums().size()),
						Integer.toString(p.getAvailableAssistants().size()), Integer.toString(p.getCoins()),
						Integer.toString(p.getScore()), Integer.toString(p.getHand().size()), activePermits.toString(), Integer.toString(usedPermits), p.getBonusCards().toString(), String.valueOf(nobilityPos));
		}
		out.print("\n\nPlayers status:\n"+tb.toString()+"\n\n");
	}
	
	public void printPlayerHand(PlayerDTO player){
		out.print("Your politics cards: ");
		for(PoliticsCardDTO pc: player.getHand())
			out.print("["+pc.getColor().toString()+"]");
		out.print("\n\n");
	}
	
	public void printMsg(String msg){
		out.println(msg);
	}
	
	public void printBonusCollectionMsg(String type, int amm)
	{
		out.print("Collecting bonus: type= " + type.toString() + ", amm= "+ amm + "\n");
	}

	/**
	 * print all the gamestatus
	 * @param game is the game status
	 */

	public void printGameStatus(){
		this.printMap();
		this.printCouncils();
		this.printPlayerHand(game.getActualPlayer());
		this.printPlacedEmporiums();
		this.printNobilityTrack();
		this.printKingLocation();
		this.printCouncilorPool();
		this.printAssistantPool();
		this.printPermitsDecks();
		this.printRegionBonuses();
		this.printColorBonuses();
		this.printKingBonuses();
		this.printPlayersStatus();
	}
	
	/*--------------------------------------END OF OUTPUTS------------------------------------------*/	
	
	/*-----------------------------------------INPUTS-----------------------------------------------*/
	
	
	public String getMsg(){
		return in.nextLine();
	}

	public int getAction(boolean[] available){
		out.println("Choose an action:");
		if(available[0] || available[1] || available[2] || available[3]){ // MAIN
			out.println("MAIN ACTION:");
			int mainCount = 0;
			for(int i=0;i<4;i++)
				if(available[i]){
					out.print("\n" + Integer.toString(i) + "-");
					switch(i){
						case 0:
							out.print("Satisfy a council. Earn: permit; Needed: min 1 politics card\n");
							break;
						case 1:
							out.print("Satisfy the king's council. Build instantly. Needed: min 1 politics card\n");
							break;
						case 2:
							out.print("Shift Council. Earn: 4 coins\n");
							break;
						case 4:
							out.print("Build an emporium in a city. Needed: permit\n");
							break;
					}
				}
			out.print("\n");
		}
		if(available[4] || available[5] || available[6] || available[7]){ // SPEED
			out.println("SPEED ACTION:");
			for(int i=4;i<8;i++)
				if(available[i]){
					out.print("\n" + Integer.toString(i) + "-");
					switch(i){
						case 4:
							out.print("Buy an assistant. Pay: 3 coins\n");
							break;
						case 5:
							out.print("Change permits card on the ground in a region. Pay: 1 assistant\n");
							break;
						case 6:
							out.print("Shift a councilor. Pay: 1 assistant\n");
							break;
						case 7:
							out.print("Buy a main action. Pay: 3 assistants\n");
							break;
					}
				}
			out.print("\n");
		}
		if(available[8])   // PASS
			out.println("8-Pass turn");
		
		int choice = 0;
		do{
			choice = waitCorrectIntInput("",0,8);
			if(!available[choice])
				out.println("Selected action is disabled, you must chose from the list.");
		}while(available[choice]);
		
		return choice;
	}
	
	public Object getItemToSell(int playerID){
		ArrayList<AssistantDTO> assistants = new ArrayList<AssistantDTO>(game.getPlayers().get(playerID).getAvailableAssistants());
		ArrayList<PermitsCardDTO> permits = new ArrayList<PermitsCardDTO>(game.getPlayers().get(playerID).getPermits());
		ArrayList<PoliticsCardDTO> cards = new ArrayList<PoliticsCardDTO>(game.getPlayers().get(playerID).getHand());
		switch(getSellType()){
			case 1:
				if(assistants.isEmpty()){
					out.println("No available assistants to sell, select something else.");
					return getItemToSell(playerID);
				}else
					return assistants.get(0);
			case 2:
				if(permits.isEmpty()){
					out.println("No available permits to sell, select something else.");
					return getItemToSell(playerID);
				}else
					return permits.get(getPermitsCardIndex(permits.size(),playerID));
			case 3:
				if(cards.isEmpty()){
					out.println("No available politics cards to sell, select something else.");
					return getItemToSell(playerID);
				}else
					return cards.get(getPoliticsCardIndex(cards.size(),playerID));
			case 4:
				return "Pass";
			default:
				return null;
		}
	}
	
	public int getBuildPermit(int playerID){
		int i = 1;
		for(PermitsCardDTO perm: game.getPlayers().get(playerID).getPermits()){
			if(!perm.isFaceDown()){
				out.println(Integer.toString(i)+"-[");
				for(String c: perm.getCityLetter())
					out.print(c+",");
				out.print("]\n");
			}
		}
		return waitCorrectIntInput("Select the permit you want to use to build.",1,i) -1;
	}
	
	public int getSellType(){
		return waitCorrectIntInput("Insert the index of the category of the item you want to sell: \n1-Assistants\n2-PermitCard\n3-PoliticCard\n4-Pass\n",1,4);
	}
	
	public int getSellPrice(){
		return waitCorrectIntInput("Insert the price of the item you are selling\n",1,100);
	}
	
	public int getPoliticsCardIndex(int size, int playerIndex){
		return waitCorrectIntInput("\nHi Player" + playerIndex + ", insert the index of the PoliticCard you want to sell. Insert 0 to go back.\n",0,size) - 1;
	}
	
	public int getPermitsCardIndex(int size, int playerIndex){
		return waitCorrectIntInput("\nHi Player" + playerIndex + ", insert the index of the PermitsCard you want to sell. Insert 0 to go back.\n",0,size) - 1;
	}
	
	public int getObjectToBuyIndex(int size, int playerIndex){
		return waitCorrectIntInput("\nHi Player" + playerIndex + ", insert the index of the item you want to buy on the market. Insert 0 to pass.\n",0,size) - 1;
	}
	
	
	public int getPermitIndex(ArrayList<PermitsCardDTO> cards){
		for(int i=0; i<cards.size();i++)
		{
			out.print((i+1) + "° CARD:\nBonus: ");
			for(BonusDTO b: cards.get(i).getBonuses())
			{
				out.print("["+ b.getType().toString() + "x"+ b.getQnt()+ "]");
			}
			out.print("\n");
		}
		return waitCorrectIntInput("\nInsert the index of the card you want to choose.\n",1,cards.size()) - 1;
	}
	
	public int getInputCities(CityDTO[] cities){
		out.print("\nInsert the indexes of the cities:\n");
		for(int i=0; i< cities.length;i++)
			out.print(i+1 + "-" + cities[i].getName() + "\n");
		return waitCorrectIntInput("",1,cities.length) - 1;
	}
	
	
	public int getTargetRegion(int msg){
		String messages[] = {"\nInsert the index of the region related to the permits card you want to change:\n"
				+ "1-Sea\n"
				+ "2-Hill\n"
				+ "3-Mountain\n",
				"\nInsert the index of the region containing the councilors you want to shift:\n"
						+ "1-Sea\n"
						+ "2-Hill\n"
						+ "3-Mountain\n",
						"\nInsert the index of the region containing the permit card you want to pick:\n"
								+ "1-Sea\n"
								+ "2-Hill\n"
								+ "3-Mountain\n",
								"\nInsert the index of the region containing the council you want to satisfy:\n"
										+ "1-Sea\n"
										+ "2-Hill\n"
										+ "3-Mountain\n"};
		return waitCorrectIntInput(messages[msg],1,3) - 1;
	}
	
	public int getTargetBalcony(){
		return waitCorrectIntInput("Select the balcony:\n"
				+ "1-Sea\n"
				+ "2-Hill\n"
				+ "3-Mountain\n"
				+ "4-King\n",1,4)-1;
	}
		
	public void unavailableOptions(){
		out.print("\nUnavailable option. Chose another action.\n");
	}
	public BonusTokenDTO[] getTokenBonus(BonusTokenDTO[] bts, int amm){
		if(amm==1)
			out.print("\nInsert the index of the BonusToken you want:\n");
		else
			out.print("\nInsert the indexes of the BonusTokens you want, separated each other by a comma:\n");
		for(int i=0;i<bts.length;i++){
			out.print(i+"° BonusToken:\n");
			for(BonusDTO b : bts[i].getBonus())
				out.print("Bonus: [Type=" + b.getType().toString() + ", Amm= " + b.getQnt() + "\n");
			out.print("\n");
		}
		if(amm==1)
			return new BonusTokenDTO[] {bts[waitCorrectIntInput("",1,bts.length) - 1]};
		else {
			boolean convError = false;
			do {
				try {
					convError = false;
					ArrayList<BonusTokenDTO> toRet = new ArrayList<BonusTokenDTO>();
					String resp = in.nextLine();
					String[] tks = resp.split(",");
					for (String tk : tks)
						{
							int parsed = Integer.parseInt(tk);
							if(parsed>0 && parsed <= bts.length)
								toRet.add(bts[parsed]);
							else
								convError = true;
						}
					if(!convError)
						return Arrays.copyOf(toRet.toArray(new BonusTokenDTO[0]),amm);
				} catch (NumberFormatException e) {
					convError = true;
					out.print("\nWrong input format, try again\n");
				}
			} while (convError == true);
		}
		return null;//non dovrebbe mai essere eseguito
	}
	
	public int getColorIndex(ArrayList<CouncilorColor> availableCouncColor){
		for(int i=0;i<availableCouncColor.size();i++){
			out.print(i+1 + "-" + availableCouncColor.get(i).toString() + "\n");
		}
		out.print("Insert the index of the color:\n");
		return waitCorrectIntInput("",1,availableCouncColor.size()) -1;
	}
	
	public int waitCorrectIntInput(String msg, int min, int max){
		int respInt = -1;
		do {
			String resp = getInput(msg);
			respInt = parseNum(resp, min,max);
		} while (respInt == -1);
		return respInt;
	}

	private String waitCorrectStringInput(String msg, String[] possibilities){
		int respString = -1;
		out.print(msg);
		do{
			String resp = in.nextLine();
			for(String p: possibilities)
				if(p.toLowerCase().equals(resp.toLowerCase())){
					return resp.toLowerCase();
				}
			out.print("Wrong input. Try Again.\n");
		}while(respString != -1);//while(1) va bene, tanto esci col return
		return "-1";
	}
	
	public boolean checkInputCards(ArrayList<String> choice, ArrayList<PoliticsCardDTO> hand){
		int compare = 0;
		for(String c: choice){
			if(Integer.parseInt(c) == 0)
				return false;
			if(Integer.parseInt(c)-1>compare)
				compare = Integer.parseInt(c)-1;
		}
		if(compare>=hand.size())
			return false;
		return true;
	}
	
	public ArrayList<PoliticsCardDTO> waitInputCards(ArrayList<PoliticsCardDTO> hand){
		out.print("Select the cards you want to use to satisfy the council (Enter '*' for instructions)\n");
		String resp = in.nextLine();
		if(resp.equals("*")){ //Instructions
			out.print("Count the cards in your hand from left to right and select the numbers you want to use.\n\n"
						+ "Some examples:\n1,4,7\n1-4-7\n1, 4 and 7\nI'd like to use the 1st, 4th and the 7th card\n\n"
						+ "The output will always be [1,4,7]\n\n");
			out.print("Select the cards you want to use to satisfy the council\n");
			resp = in.nextLine();
		}
		resp = resp.replaceAll("[^0-9]+", " "); 
		ArrayList<String> choice = new ArrayList<String>(Arrays.asList(resp.trim().split("[^0-9]+")));
		if(checkInputCards(choice, hand)){
			ArrayList<PoliticsCardDTO> chosenCards = new ArrayList<PoliticsCardDTO>(choice.size());
			for(String c: choice){
				chosenCards.add(hand.get(Integer.parseInt(c)-1));
			}
			hand.removeAll(chosenCards);
			return chosenCards;
		}else{
			out.print("Wrong input or some of the selected cards are not in your hand. Try again\n");
			return waitInputCards(hand);
		}
	}

	private String getInput(String message){
		out.print(message);
		return in.nextLine();

	}

	private int parseNum(String msg, int min, int max){
		int read=0;
		try{
			read = Integer.parseInt(msg);
		}
		catch (NumberFormatException e) {
			out.print("Wrong input format. Try again\n");
			return -1;
		}
		if (read <= max && read >= min)
			return read;
		else {
			out.print("Input out of bounds. Try again\n");
			return -1;}
	}

	/*--------------------------------------END OF INPUTS--------------------------------------------*/
	
	

	/*Observer*/

	/**
	 * print the new GameDTO after an update
	 */
	@Override
	public void update(Observable o, Object change) {
		if(change instanceof GameDTO){
			this.game = (GameDTO) change;
			this.printGameStatus();
		}else
			;//throw new IllegalArgumentException("Wrong instance. Failed to update the game.");
	}



}
