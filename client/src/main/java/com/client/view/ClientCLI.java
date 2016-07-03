package com.client.view;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

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
import com.communication.market.AssistantOnSaleDTO;
import com.communication.market.OnSaleDTO;
import com.communication.market.PermitOnSaleDTO;
import com.communication.market.PoliticsOnSaleDTO;
import com.communication.values.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientCLI.
 */
public class ClientCLI {

	/** The game(DTO). */
	private GameDTO game;
	
	/** The out. */
	private PrintStream out;
	
	/** The map. */
	private String[][] map = new String[15][5];
	
	/** The cli queue. */
	private ArrayBlockingQueue<String> cliQueue;
	
	/** The abort flag. */
	private boolean abortFlag = false;

	/**
	 * constructor of the class.
	 *
	 * @param clientController the client controller
	 * @param q the q
	 */
	
	public ClientCLI(ClientController clientController, ArrayBlockingQueue<String> q){
		this.cliQueue = q;
		this.out = System.out;
	}

	/**
	 * Sets the game(DTO) and build map.
	 *
	 * @param game the new game(DTO) 
	 */
	public void setGameAndBuildMap(GameDTO game){
		this.game = game;
		constructMap();
		printGameStatus();
	}
	
	/**
	 * Construct map.
	 */
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
	 * print the map.
	 */
	public void printMap(){
		for(int i=0;i<5;i++){
			for(int j=0;j<15;j++){
				out.print(map[j][i]);
			}
			out.print("\n");
		}
	}

	/**
	 * print the city bonus, for each  city .
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
	
	/**
	 * Prints the councilor pool.
	 */
	public void printCouncilorPool(){
		out.print("\nCouncilors pool: [");
		for(CouncilorDTO c: game.getMap().getCouncilors())
			out.print(c.getColor().toString() + ",");
		out.print("]\n");
	}
	
	/**
	 * Prints the assistant pool.
	 */
	public void printAssistantPool(){
		int assistQty = game.getMap().getAssistants().size();
		out.println("\nAssistants pool: " + Integer.toString(assistQty));
	}
	
	/**
	 * Prints the king location.
	 */
	public void printKingLocation(){
		out.println("\n\nKing's location: " + game.getMap().getKing().getLocation().getName());
	}
	
	/**
	 * Prints the permits decks.
	 */
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
	
	/**
	 * Prints the councils.
	 */
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
	
	/**
	 * Prints the nobility track.
	 */
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
	
	/**
	 * Prints the region bonuses.
	 */
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
	
	/**
	 * Prints the color bonuses.
	 */
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
	
	/**
	 * Prints the king bonuses.
	 */
	public void printKingBonuses(){
		out.print("\nKing Bonus:");
		int i = 0;
		for(BonusDTO b: game.getMap().getKingBonus()){
			out.print("\n  "+Integer.toString(i+1)+"°: " + b.getType().toString() + "x" + Integer.toString(b.getQnt()));
			i++;
		}
	}
	
	/**
	 * Prints the placed emporiums.
	 */
	public void printPlacedEmporiums(){
		out.print("\nPlaced emporiums:\n");
		for(int i=0;i<game.getMap().getCity().length;i++){
			out.print(game.getMap().getCity()[i].getName()+": {");
			for(int j=0;j<game.getMap().getCity()[i].getSlot().length;j++){
				if(game.getMap().getCity()[i].getSlot()[j] != null){
					if(game.getMap().getCity()[i].getSlot()[j].getPlayer()!=null)
						out.print("["+game.getMap().getCity()[i].getSlot()[j].getPlayer().getPlayerID()+"]");
					else
						out.print("[StarterEmporium]");
						
				}
			}
			out.print("}\n");
		}
	}
	
	/**
	 * Prints the players status.
	 */
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
			tb.addRow(p.getPlayerID(), Integer.toString(p.getAvailableEmporiums().size()),
						Integer.toString(p.getAvailableAssistants().size()), Integer.toString(p.getCoins()),
						Integer.toString(p.getScore()), Integer.toString(p.getHand().size()), activePermits.toString(), Integer.toString(usedPermits), p.getBonusCards().toString(), String.valueOf(nobilityPos));
		}
		out.print("\n\nPlayers status:\n"+tb.toString()+"\n\n");
	}
	
	/**
	 * Prints the player hand.
	 *
	 * @param player the player
	 */
	public void printPlayerHand(PlayerDTO player){
		out.print("Your politics cards: ");
		for(PoliticsCardDTO pc: player.getHand())
			out.print("["+pc.getColor().toString()+"]");
		out.print("\n\n");
	}
	
	/**
	 * Prints the msg.
	 *
	 * @param msg the msg
	 */
	public void printMsg(String msg){
		out.println(msg);
	}
	
	/**
	 * Prints the bonus collection msg.
	 *
	 * @param type the type
	 * @param amm the amm
	 */
	public void printBonusCollectionMsg(String type, int amm)
	{
		out.print("Collecting bonus: type= " + type.toString() + ", amm= "+ amm + "\n");
	}

	/**
	 * print all the gamestatus.
	 */

	public void printGameStatus(){
		out.print("\n\n\n\n\n");  
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
	
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMsg(){
		/*String inStr = null;
		try {
			inStr = in.nextLine(5000);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inStr;*/
		String inStr = null;
		this.setAbortFlag(false);
		this.cliQueue.clear();
		do{
			inStr = this.cliQueue.poll();
			if(inStr != null && inStr.equals("ABORT")){
				this.setAbortFlag(true);
				return null;
			}
		}while(inStr==null);
		return inStr;
	}

	/**
	 * Gets the action.
	 *
	 * @param available the available action
	 * @return the action
	 */
	public int getAction(boolean[] available){
		out.println("Choose an action:");
		if(available[0] || available[1] || available[2] || available[3]){ // MAIN
			out.println("MAIN ACTION:");
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
						case 3:
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
			if(this.isAbortFlag())
				return -1;
			if(!available[choice])
				out.println("Selected action is disabled, you must chose from the list.");
		}while(!available[choice]);
		
		return choice;
	}
	
	/**
	 * Gets the item to sell.
	 *
	 * @return the item to sell
	 */
	public Object getItemToSell(){
		ArrayList<AssistantDTO> assistants = new ArrayList<AssistantDTO>(game.getActualPlayer().getAvailableAssistants());
		ArrayList<PermitsCardDTO> permits = new ArrayList<PermitsCardDTO>(game.getActualPlayer().getPermits());
		ArrayList<PoliticsCardDTO> cards = new ArrayList<PoliticsCardDTO>(game.getActualPlayer().getHand());
		switch(getSellType()){
			case 1:
				if(assistants.isEmpty()){
					out.println("No available assistants to sell, select something else.");
					return getItemToSell();
				}else
					return assistants.get(0);
			case 2:
				if(permits.isEmpty()){
					out.println("No available permits to sell, select something else.");
					return getItemToSell();
				}else
					return permits.get(getPermitsCardIndex(permits.size()));
			case 3:
				if(cards.isEmpty()){
					out.println("No available politics cards to sell, select something else.");
					return getItemToSell();
				}else
					return cards.get(getPoliticsCardIndex(cards.size()));
			case 4:
				return "Pass";
			default:
				return null;
		}
	}
	
	/**
	 * Gets the the permit to build.
	 *
	 * @return the builds the permit
	 */
	public int getBuildPermit(){
		int i = 1;
		for(PermitsCardDTO perm: game.getActualPlayer().getPermits()){
			if(!perm.isFaceDown()){
				out.print(Integer.toString(i)+"-[");
				for(String c: perm.getCityLetter())
					out.print(c+",");
				out.print("]\n");
				i++;
			}
		}
		return waitCorrectIntInput("Select the permit you want to use to build.",1,i) -1;
	}
	
	/**
	 * Gets the sell type.
	 *
	 * @return the sell type
	 */
	public int getSellType(){
		return waitCorrectIntInput("Insert the index of the category of the item you want to sell: \n1-Assistants\n2-PermitCard\n3-PoliticCard\n4-Pass\n",1,4);
	}
	
	/**
	 * Gets the sell price.
	 *
	 * @return the sell price
	 */
	public int getSellPrice(){
		return waitCorrectIntInput("Insert the price of the item you are selling\n",1,100);
	}
	
	/**
	 * Gets the politics card index.
	 *
	 * @param size the size
	 * @return the politics card index
	 */
	public int getPoliticsCardIndex(int size){
		return waitCorrectIntInput("\nHi " + game.getActualPlayer().getPlayerID() + ", insert the index of the PoliticCard you want to sell. Insert 0 to go back.\n",0,size) - 1;
	}
	
	/**
	 * Gets the permits card index.
	 *
	 * @param size the size
	 * @return the permits card index
	 */
	public int getPermitsCardIndex(int size){
		return waitCorrectIntInput("\nHi " + game.getActualPlayer().getPlayerID() + ", insert the index of the PermitsCard you want to sell. Insert 0 to go back.\n",0,size) - 1;
	}
	
	/**
	 * Gets the object to buy uid.
	 *
	 * @param size the size
	 * @param availableOnSale the available on sale
	 * @return the object to buy uid
	 */
	public String getObjectToBuyUID(int size, ArrayList<OnSaleDTO> availableOnSale){
		String UIDs[] = new String[availableOnSale.size()];
		int count =0;
		String whatIs = "";
		for(OnSaleDTO osDTO : availableOnSale){
			if(osDTO instanceof AssistantOnSaleDTO){
				whatIs = "Assistant";
				UIDs[count]= ((AssistantOnSaleDTO) osDTO).getUID();
				count++;
			}
			else if(osDTO instanceof PermitOnSaleDTO){
				PermitOnSaleDTO posDTO = (PermitOnSaleDTO) osDTO;
				PermitsCardDTO pcDTO = posDTO.getPermit();
				String pcBonus = "";
				String pcLetters = "";
				for(BonusDTO bDTO : pcDTO.getBonuses())
					pcBonus += bDTO.getType().toString() + "x" + bDTO.getQnt() + ", ";
				for(int i=0;i<pcDTO.getCityLetter().length; i++){
					pcLetters += pcDTO.getCityLetter()[i];
					if(i!=pcDTO.getCityLetter().length-1)
						pcLetters += ", ";
				}
				whatIs = "PermitCard, Bonus: " + pcBonus + " Letters: " + pcLetters;
				UIDs[count]= ((PermitOnSaleDTO) osDTO).getUID();
				count++;
			}
			else if(osDTO instanceof PoliticsOnSaleDTO){
				PoliticsOnSaleDTO posDTO = (PoliticsOnSaleDTO) osDTO;
				PoliticsCardDTO pcDTO = posDTO.getPoliticsCard();
				whatIs = "PoliticCard, Color: " + pcDTO.getColor().toString();
				UIDs[count]= ((PoliticsOnSaleDTO) osDTO).getUID();
				count++;
			}
			else return "";
			out.print("Seller: Player" + osDTO.getSeller().getPlayerID() + ", item: " + whatIs + ", price: " + osDTO.getPrice() + "\n");
		}
		int input = waitCorrectIntInput("\nHi " + game.getMarketCurrentPlayer() + ", insert the index of the item you want to buy on the market. Insert 0 to pass.\n",0,size);
		if(input==0)
			return "";
		return UIDs[input - 1];
	}
	
	
	/**
	 * Gets the permit index.
	 *
	 * @param cards the cards
	 * @return the permit index
	 */
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
	
	/**
	 * Gets the input city.
	 *
	 * @param cities the cities
	 * @return the input city
	 */
	public int getInputCity(CityDTO[] cities){
		out.print("\nInsert the index of the city:\n");
		for(int i=0; i< cities.length;i++)
			out.print(i+1 + "-" + cities[i].getName() + "\n");
		return waitCorrectIntInput("",1,cities.length) - 1;
	}
	
	
	/**
	 * Gets the target region.
	 *
	 * @param msg the msg
	 * @return the target region
	 */
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
	
	/**
	 * Gets the target balcony.
	 *
	 * @return the target balcony
	 */
	public int getTargetBalcony(){
		return waitCorrectIntInput("Select the balcony:\n"
				+ "1-Sea\n"
				+ "2-Hill\n"
				+ "3-Mountain\n"
				+ "4-King\n",1,4)-1;
	}
		
	/**
	 * Unavailable options.
	 */
	public void unavailableOptions(){
		out.print("\nUnavailable option. Chose another action.\n");
	}
	
	/**
	 * Gets the token bonus.
	 *
	 * @param bts the bts
	 * @param amm the amm
	 * @return the token bonus
	 */
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
					String resp = this.getMsg();
					if(this.isAbortFlag())
						return null;
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
	
	/**
	 * Gets the color index.
	 *
	 * @param availableCouncColor the available counc color
	 * @return the color index
	 */
	public int getColorIndex(ArrayList<CouncilorColor> availableCouncColor){
		for(int i=0;i<availableCouncColor.size();i++){
			out.print(i+1 + "-" + availableCouncColor.get(i).toString() + "\n");
		}
		out.print("Insert the index of the color:\n");
		return waitCorrectIntInput("",1,availableCouncColor.size()) -1;
	}
	
	/**
	 * Wait correct int input.
	 *
	 * @param msg the msg
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public int waitCorrectIntInput(String msg, int min, int max){
		int respInt = -1;
		do {
			String resp = getInput(msg);
			if(this.isAbortFlag())
				return 0;
			respInt = parseNum(resp, min,max);
		} while (respInt == -1);
		return respInt;
	}

	
	
	/**
	 * Check input cards.
	 *
	 * @param choice the choice
	 * @param hand the hand
	 * @return true, if successful
	 */
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
	
	/**
	 * Wait input cards.
	 *
	 * @param hand the hand
	 * @return the array list
	 */
	public ArrayList<PoliticsCardDTO> waitInputCards(ArrayList<PoliticsCardDTO> hand) {
		out.print("Select the cards you want to use to satisfy the council (Enter '*' for instructions)\n");
		String resp = "";
		resp = this.getMsg();
		if(this.isAbortFlag())
			return null;
		if (resp.equals("*")) { // Instructions
			out.print("Count the cards in your hand from left to right and select the numbers you want to use.\n\n"
					+ "Some examples:\n1,4,7\n1-4-7\n1, 4 and 7\nI'd like to use the 1st, 4th and the 7th card\n\n"
					+ "The output will always be [1,4,7]\n\n");
			out.print("Select the cards you want to use to satisfy the council\n");
			resp = this.getMsg();
			if(this.isAbortFlag())
				return null;
		}

		if (resp.equals(""))
			return null;

		resp = resp.replaceAll("[^0-9]+", " ");
		ArrayList<String> choice = new ArrayList<String>(Arrays.asList(resp.trim().split("[^0-9]+")));
		if (checkInputCards(choice, hand)) {
			ArrayList<PoliticsCardDTO> chosenCards = new ArrayList<PoliticsCardDTO>(choice.size());
			for (String c : choice) {
				chosenCards.add(hand.get(Integer.parseInt(c) - 1));
			}
			hand.removeAll(chosenCards);
			return chosenCards;
		} else {
			out.print("Wrong input or some of the selected cards are not in your hand. Try again\n");
			return waitInputCards(hand);
		}
	}

	/**
	 * Gets the input.
	 *
	 * @param message the message
	 * @return the input
	 */
	private String getInput(String message){
		out.print(message);
		return this.getMsg();
	}

	/**
	 * Parses the num.
	 *
	 * @param message the message
	 * @param min the min number of players
	 * @param max the max number of players
	 * @return the int
	 */
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
	
	

	/**
	 * Checks if is abort flag.
	 *
	 * @return true, if is abort flag
	 */
	public boolean isAbortFlag() {
		return abortFlag;
	}

	/**
	 * Sets the abort flag.
	 *
	 * @param abortFlag the new abort flag
	 */
	public void setAbortFlag(boolean abortFlag) {
		this.abortFlag = abortFlag;
	}



}
