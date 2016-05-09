package clientserverlogic;
import java.io.Console;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import board.Balcony;
import board.Bonus;
import board.BonusToken;
import board.City;
import board.Councilor;
import board.Region;
import decks.PermitsCard;
import decks.PoliticsCard;
import gamelogic.Game;
import gamelogic.Player;
import model.CityColor;
import model.CouncilorColor;
import model.RegionName;
import utils.TableBuilder;

public class CLI {
	private static Scanner in;
	private static PrintStream out;

	public CLI(){
		in = new Scanner(System.in);
		out = System.out;
	}
	
	/*------------------------------------------OUTPUTS---------------------------------------------*/
	
	
	public void printMap(){
		out.print("\n.......................................................................\n" +
					"|       ~ SEA ~        |       ᴖ HILL ᴖ        |     ^ MOUNTAIN ^     |\n" +
					"|                      |                       |                      |\n" +
					"|                      |        _.-------[I]-. |                      |\n" +
					"|    [A]--.           ,+----[F]'          |   '+----[K]----.          |\n" +
					"|          `--.      / |                 .'    |            `--.      |\n" +
					"|              `[C]-'  |                 |     |                [N]   |\n" +
					"|                |     |                 |     |                 |    |\n" +
					"|                |     |                 |     |                 |    |\n" +
					"|                |     |                 |     |                 |    |\n" +
					"|                |     |                 |     |                 |    |\n" +
					"|   [B]          |     |    [G]----.     |     |,-[L]-----.      |    |\n" +
					"|  .'            |  ,--+---'        `.   |    ,+           |     |    |\n" +
					"|  |            [D]'   |              |  |  ,' |            `.   |    |\n" +
					"|  |                   |               `[J]'   |              |  |    |\n" +
					"|  |                   |                /      |               `[O]   |\n" +
					"|  |                   |               |       |                 |    |\n" +
					"|  |                   |      ,--------'       |                 |    |\n" +
					"|  |                   |     /                 |                 |    |\n" +
					"|  '.                  | .--[H]----.           |                 ;    |\n" +
					"|   [E]----------------+'           `----------+---[M]----------'     |\n" +
					"|                      |                       |                      |\n" +
					"|                      |                       |                      |\n" +
					".......................................................................\n\n");
	}
	
	public void printCityBonus(Game game){
		ArrayList<City> cities = new ArrayList<City>(Arrays.asList(game.getMap().getCity()));
		for(City c: cities){
			ArrayList<String> bounsTypes = new ArrayList<String>();
				for(Bonus b: c.getBonusToken().getBonus())
					if(b != null)
						bounsTypes.add(b.getType().toString()+"("+Integer.toString(b.getQnt())+")");
			out.println(c.getName()+ ": " + bounsTypes);
		}
	}
	
	public void printCouncilorPool(Game game){
		out.print("\nCouncilors pool: [");
		for(Councilor c: game.getMap().getCouncilorsPool())
			out.print(c.getCouncilorColor().toString() + ",");
		out.print("]\n");
	}
	
	public void printAssistantPool(Game game){
		int assistQty = game.getMap().getAssistantsPool().size();
		out.println("\nAssistants pool: " + Integer.toString(assistQty));
	}
	
	public void printKingLocation(Game game){
		out.println("\n\nKing's location: " + game.getMap().getKing().getLocation().getName());
	}
	
	public void printPermitsDecks(Game game){
		out.print("\nPermits:\n  ");
		int i;
		for(i=0;i<3;i++){
			out.print(RegionName.values()[i].toString() + ":\n    Slot 1 {" + Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(0, false).getCityLetter()) + ",[");
		
			for(Bonus b: Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(0, false).getBonus()))
				out.print(b.getType().toString()+"x"+Integer.toString(b.getQnt())+"|");
			
			out.print("}\n    Slot 2 {(" + Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(1, false).getCityLetter()) + ",[");
			
			for(Bonus b: Arrays.asList(game.getMap().getPermitsDeck(i).getSlot(1, false).getBonus()))
				out.print("|"+b.getType().toString()+"x"+Integer.toString(b.getQnt())+"|");
			
			out.print("]}\n  ");
		}
	}
	
	public void printCouncils(Game game){
		out.print("Balconies:\n");
		for(int i=0; i<3; i++){
			out.print(RegionName.values()[i].toString() +":    	[");
			for(int j=0; j<4; j++){
				out.print(game.getMap().getBalcony(i).getCouncilors()[j].getCouncilorColor().toString());
				if(j<3) out.print(",");
			}
			out.print("]<-\n");
		}
		out.print("KING:		[");
		for(int k=0; k<4; k++){
			out.print(game.getMap().getBalcony(3).getCouncilors()[k].getCouncilorColor().toString());
			if(k<3) out.print(",");
		}
		out.print("]<-\n");
	}
	
	public void printNobilityTrack(Game game){
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
	
	public void printRegionBonuses(Game game){
		out.print("\nRegion Bonus:\n    ");
		for(int i=0;i<3;i++){
			if(game.getMap().getRegion(i).getBonus().getBonus() != null)
				out.print(RegionName.values()[i].toString() + ": " 
				+game.getMap().getRegion(i).getBonus().getBonus().getType().toString()
				+"x"+ Integer.toString(game.getMap().getRegion(i).getBonus().getBonus().getQnt()));
			out.print("\n    ");
		}
	}
	
	public void printColorBonuses(Game game){
		out.print("\nColor Bonus:\n    ");
		for(int i=0;i<CityColor.values().length-1;i++){
			out.print(CityColor.values()[i].toString() + " cities: " );
			if(game.getMap().getColorGroup(i).getBonus() != null)
				out.print(game.getMap().getColorGroup(i).getBonus().getBonus().getType().toString()
				+"x"+ Integer.toString(game.getMap().getColorGroup(i).getBonus().getBonus().getQnt()));
			out.print("\n    ");
		}
	}
	
	public void printKingBonuses(Game game){
		out.print("\nKing Bonus:");
		int i = 0;
		for(Bonus b: game.getMap().getKingBonus()){
			out.print("\n  "+Integer.toString(i+1)+"°: " + b.getType().toString() + "x" + Integer.toString(b.getQnt()));
			i++;
		}
	}
	
	public void printPlacedEmporiums(Game game){
		out.print("\nPlaced emporiums:\n");
		for(int i=0;i<game.getMap().getCity().length;i++){
			out.print(game.getMap().getCity()[i].getName()+": {");
			for(int j=0;j<game.getMap().getCity()[i].getEmporium().length;j++){
				if(game.getMap().getCity()[i].getEmporium()[j] != null){
					out.print("["+Integer.toString(game.getMap().getCity()[i].getEmporium()[j].getPlayer().getID())+"]");
				}
			}
			out.print("}\n");
		}
	}
	
	public void printGameStatus(Game game){
		this.printMap();
		this.printCouncils(game);
		this.printPlacedEmporiums(game);
		this.printNobilityTrack(game);
		this.printKingLocation(game);
		this.printCouncilorPool(game);
		this.printAssistantPool(game);
		this.printPermitsDecks(game);
		this.printRegionBonuses(game);
		this.printColorBonuses(game);
		this.printKingBonuses(game);
		this.printPlayersStatus(game);
	}
	
	public void printPlayersStatus(Game game){
		TableBuilder tb = new TableBuilder();
		tb.addRow("Player", "Emporiums", "Assistants", "Coins", "Points", "Cards", "Active permits", "Used permits", "King Bonus");
		tb.addRow("--------------","--------------", "--------------", "--------------", "--------------", "--------------", "--------------", "--------------", "--------------");
		for(Player p: game.getPlayers()){
			int usedPermits = 0;
			ArrayList<PermitsCard> activePermits = new ArrayList<PermitsCard>();
			for(PermitsCard pc: p.getPermits())
				if(pc.isFaceDown())
					usedPermits++;
				else
					activePermits.add(pc);
			tb.addRow(Integer.toString(p.getID()), Integer.toString(p.getAvailableEmporiums().size()),
						Integer.toString(p.getAvailableAssistants().size()), Integer.toString(p.getCoins()),
						Integer.toString(p.getScore()), Integer.toString(p.getHand().size()), activePermits.toString(), Integer.toString(usedPermits), p.getBonusCards().toString());
		}
		out.print("\n\nPlayers status:\n"+tb.toString()+"\n\n");
	}
	
	/*--------------------------------------END OF OUTPUTS------------------------------------------*/
	
	
	
	/*-----------------------------------------INPUTS-----------------------------------------------*/

	public int getAction(int plIndex, boolean main, boolean speed){
		out.print("Hi there player" + plIndex + ", ");
		if(main)
			out.print("insert 1 for Main Action, ");
		if(speed)
			out.print("insert 2 for Speed Action, ");
		int resp = waitCorrectIntInput("insert 3 to pass\n",1,3);
		if((resp==1 && !main) || (resp==2 && !speed))
		{
			out.print("Selection inavailable. Try again\n");
			return getAction(plIndex, main, speed);
		}
		return resp;
	}
	
	public int mainActionChoice(){
		return waitCorrectIntInput("\nMAIN ACTION\nInsert the number related to your action:\n"
				+ "1-Satisfy a council. Earn: permit* Needed: min 1 politics card\n"
				+ "2-Satisfy the king's council. Build instantly. Needed: min 1 politics card*\n"
				+ "3-Shift Council. Earn: 4 coins\n"
				+ "4-Build an emporium in a city. Needed: permit\n\n"
				+ "5-Go back\n",1,5);
	}
	
	
	public int getPermitIndex(ArrayList<PermitsCard> cards)
	{
		for(int i=0; i<cards.size();i++)
		{
			out.print((i+1) + "° CARD:\nBonus:\n");
			for(Bonus b: cards.get(i).getBonus())
			{
				out.print("Type: "+ b.getType().toString() + "\nAmmount: "+ b.getQnt()+ "\n");
			}
		}
		return waitCorrectIntInput("\nInsert the index of the card you want to use.\n",1,cards.size()) - 1;
	}
	
	public int getInputCities(City[] cities)
	{
		out.print("\nInsert the indexes of the cities:\n");
		for(int i=0; i< cities.length;i++)
			out.print(i+1 + "-" + cities[i].getName() + "\n");
		return waitCorrectIntInput("",1,cities.length) - 1;
	}
	
	
	public int speedActionChoice(){
		return waitCorrectIntInput("\nSPEED ACTION \nInsert the number related to your action:\n"
						+ "1-Buy an assistant. Pay: 3 coins\n"
						+ "2-Change permits card on the ground in a region. Pay: 1 assistant\n"
						+ "3-Shift a councilor. Pay: 1 assistant\n"
						+ "4-Buy a main action. Pay: 3 assistants\n\n"
						+ "5-Go back\n",1,5);
	}
	
	
	public int getTargetRegion(int msg)
	{
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
								+ "3-Mountain\n"};
		return waitCorrectIntInput(messages[msg],1,3) - 1;
	}
	
	
		
	
	public BonusToken[] getTokenBonus(BonusToken[] bts, int amm)
	{
		if(amm==1)
			out.print("\nInsert the index of the BonusToken you want:\n");
		else
			out.print("\nInsert the indexes of the BonusTokens you want, separated each other by a comma:\n");
		for(int i=0;i<bts.length;i++)
		{
			out.print(i+"° BonusToken:\n");
			for(Bonus b : bts[i].getBonus())
				out.print("Bonus: [Type=" + b.getType().toString() + ", Amm= " + b.getQnt() + "\n");
			out.print("\n");
		}
		if(amm==1)
			return new BonusToken[] {bts[waitCorrectIntInput("",1,bts.length) - 1]};
		else {
			boolean convError = false;
			do {
				try {
					convError = false;
					ArrayList<BonusToken> toRet = new ArrayList<BonusToken>();
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
						return Arrays.copyOf(toRet.toArray(new BonusToken[0]),amm);
				} catch (NumberFormatException e) {
					convError = true;
					out.print("\nWrong input format, try again\n");
				}
			} while (convError == true);
		}
		return null;//non dovrebbe mai essere eseguito
	}
	public int getColorIndex(ArrayList<CouncilorColor> availableCouncColor)
	{
		for(int i=0;i<availableCouncColor.size();i++){
			out.print(i+1 + "-" + availableCouncColor.get(i).toString() + "\n");
		}
		out.print("Insert the index of the color:\n");
		return waitCorrectIntInput("",1,availableCouncColor.size()) -1;
	}
	
	private int waitCorrectIntInput(String msg, int min, int max){
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
	
	public boolean checkInputCards(ArrayList<String> choice, ArrayList<PoliticsCard> hand){
		int compare = 0;
		for(String c: choice){
			if(Integer.parseInt(c) == 0)
				return false;
			if(Integer.parseInt(c)>compare)
				compare = Integer.parseInt(c);
		}
		if(compare>=hand.size())
			return false;
		return true;
	}
	
	public ArrayList<PoliticsCard> waitInputCards(ArrayList<PoliticsCard> hand){
		out.print("Select the cards you want to use to satisfy the council\n");
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
			ArrayList<PoliticsCard> chosenCards = new ArrayList<PoliticsCard>(choice.size());
			for(String c: choice){
				chosenCards.add(hand.get(Integer.parseInt(c)));
				hand.remove(Integer.parseInt(c));
			}
			return chosenCards;
		}else
		{
			out.print("Wrong input or some of the selected cards are not in your hand. Try again\n");
			return waitInputCards(hand);
		}
	}

	private String getInput(String message)
	{
		out.print(message);
		return in.nextLine();

	}

	private int parseNum(String msg, int min, int max){
		int read=0;
		try
		{
			read = Integer.parseInt(msg);
		}
		catch (NumberFormatException e) 
		{
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
}
