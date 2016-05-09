package clientserverlogic;
import java.io.Console;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import board.Balcony;
import board.Bonus;
import board.City;
import board.Councilor;
import board.Region;
import decks.PoliticsCard;
import gamelogic.Game;
import model.CouncilorColor;
import model.RegionName;

public class CLI {
	private static Scanner in;
	private static PrintStream out;

	public CLI(){
		in = new Scanner(System.in);
		out = System.out;
	}
	
	/*------------------------------------------OUTPUTS---------------------------------------------*/
	
	public int getAction(int plIndex){
		return waitCorrectIntInput("Hi there player" + plIndex + ", insert 1 for Main Action, insert 2 for Speed Action.\n",1,2);
	}
	
	public void mainActionMsg(int selection){
	
		switch(selection){
			case 0: 
				out.print("\nInsert the number related to your action:\n"
						+ "1-Satisfy a council. Earn: permit* Needed: min 1 politics card\n"
						+ "2-Satisfy the king's council. Build instantly. Needed: min 1 politics card*\n"
						+ "3-Shift Council. Earn: 4 coins\n"
						+ "4-Build an emporium in a city. Needed: permit\n\n"
						+ "5-Go back\n");
				break;
			case 1:
				out.print("\nSelect a council:\n1-Sea\n2-Hill\n3-Mountain");
				break;
			case 2:
				out.print("\nSelect the cards you want to use (insert '*' for instructions)");
				break;
			case 3:
				out.print("\nInsert the index of the region containing the councilors you want to shift:\n"
					+ "1-Sea\n"
					+ "2-Hill\n"
					+ "3-Mountain\n");
				break;
			case 4:
				out.print("\nInsert the index of the color:\n");
				break;
			default:
				out.print("\nsomething went wrong\n");
				break;
		}
	}
	
	public void speedActionMsg(int selection){
		
		switch(selection){
			case 0: 
				out.print("\nInsert the number related to your action:\n"
						+ "1-Buy an assistant. Pay: 3 coins\n"
						+ "2-Change permits card on the ground in a region. Pay: 1 assistant\n"
						+ "3-Shift a councilor. Pay: 1 assistant\n"
						+ "4-Buy a main action. Pay: 3 assistants\n\n"
						+ "5-Go back\n");
				break;
			case 2:
				out.print("\nSelect the cards you want to use (insert '*' for instructions)");
				break;
			case 3:
				out.print("\nInsert the index of the region containing the councilors you want to shift:\n"
					+ "1-Sea\n"
					+ "2-Hill\n"
					+ "3-Mountain\n");
				break;
			case 4:
				out.print("\nInsert the index of the color:\n");
				break;
			default:
				out.print("\nsomething went wrong\n");
				break;
		}
	}
	
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
		out.println("\nKing's location: " + game.getMap().getKing().getLocation().getName());
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
		//game.getMap().getNobilityTrack().getBonusVector();
		//TODO: RISOLVERE GLI ATTRIBUTI NULL IN MAP!
	}
	
	public void printRegionBonuses(Game game){
		//game.getMap().getNobilityTrack().getBonusVector();
		//TODO: RISOLVERE GLI ATTRIBUTI NULL IN MAP!
	}
	
	public void printColorBonuses(Game game){
		//game.getMap().getNobilityTrack().getBonusVector();
		//TODO: RISOLVERE GLI ATTRIBUTI NULL IN MAP!
	}
	
	public void printKingBonuses(Game game){
		//game.getMap().getNobilityTrack().getBonusVector();
		//TODO: RISOLVERE GLI ATTRIBUTI NULL IN MAP!
	}
	
	public void printGameStatus(Game game){
		this.printMap();
		this.printCouncils(game);
		this.printKingLocation(game);
		this.printCouncilorPool(game);
		this.printAssistantPool(game);
		this.printPermitsDecks(game);
	}
		

//	public void mainAction(ArrayList<CouncilorColor> availables){
//		int regIndex=0;
//		PoliticsCard[] chosenCards;
//		switch(selection){
//
//		case 0: return "Insert the number related to your action:\n"
//				+ "1-Satisfy a council. Earn: permit* Needed: min 1 politics card\n"
//				+ "2-Satisfy the king's council. Build instantly. Needed: min 1 politics card*\n"
//				+ "3-Shift Council. Earn: 4 coins\n"
//				+ "4-Build an emporium in a city. Needed: permit\n\n"
//				+ "5-Go back\n";
//		case 1:
//			regIndex = waitCorrectIntInput("Select a council:\n1-Sea\n2-Hill\n3-Mountain",1,3);
//			chosenCards = waitInputCards();	
//			break;
//		case 2:
//			regIndex = waitCorrectIntInput("Select the cards you want to use",1,9999);
//			break;
//		case 3://PURPLE, WHITE, BLACK, ORANGE, BLUESKY, PINK, JOLLY;
//			regIndex = waitCorrectIntInput("Insert the index of the region containing the councilors you want to shift:\n"
//					+ "1-Sea\n"
//					+ "2-Hill\n"
//					+ "3-Mountain\n",1,3);
//			for(int i=0;i<availables.size();i++){
//				cnsl.printf(i + "-" + availables.get(i).toString() + "\n");
//			}
//			cnsl.printf("Insert the index of the color:\n");
//			int colIndex = waitCorrectIntInput("",1,availables.size());
//			break;
//		case 4:
//			//notificare controller
//			break;
//		}
//
//	}

//	public void speedAction(ArrayList<CouncilorColor> availables){
//		int selection = waitCorrectIntInput("Insert the number related to your action:\n"
//				+ "1-Buy an assistant. Pay: 3 coins\n"
//				+ "2-Change permits card on the ground in a region. Pay: 1 assistant\n"
//				+ "3-Shift a council. Pay: 1 assistant\n"
//				+ "4-Buy a main action. Pay: 3 assistants\n\n"
//				+ "5-Go back\n",1,5);
//		int regIndex=0;
//
//		switch(selection){
//		case 1:
//			//int assAmm = waitCorrectInput("Insert the ammount of assistants you want to buy:\n",1,99); <-- non serve, ne può acquistare solo uno
//			//notificare controller
//			break;
//		case 2:
//			regIndex = waitCorrectIntInput("Insert the index of the region containing the cards you want to change:\n1-Sea\n2-Hill\n3-Mountain\n",1,3);
//			break;
//		case 3://PURPLE, WHITE, BLACK, ORANGE, BLUESKY, PINK, JOLLY;
//			regIndex = waitCorrectIntInput("Insert the index of the region containing the councilors you want to shift:\n"
//					+ "1-Sea\n"
//					+ "2-Hill\n"
//					+ "3-Mountain\n",1,3);
//			for(int i=0;i<availables.size();i++){
//				out.print(i + "-" + availables.get(i).toString() + "\n");
//			}
//			out.print("Insert the index of the color:\n");
//			int colIndex = waitCorrectIntInput("",1,availables.size());
//			break;
//		case 4:
//			//notificare controller
//			break;
//		}
//	}
	
	/*--------------------------------------END OF OUTPUTS------------------------------------------*/
	
	/*-----------------------------------------INPUTS-----------------------------------------------*/

	private int waitCorrectIntInput(String msg, int min, int max){
		int respInt = -1;
		do {
			String resp = getInput(msg);
			respInt = parseNum(resp, min,max);
		} while (respInt != -1);
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
		}while(respString != -1);
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
	
	public ArrayList<PoliticsCard> waitInputCards(String msg, ArrayList<PoliticsCard> hand){
		out.print(msg);
		String resp = in.nextLine();
		if(resp.equals("*")){ //Instructions
			out.print("Count the cards in your hand from left to right and select the numbers you want to use.\n\n"
						+ "Some examples:\n1,4,7\n1-4-7\n1, 4 and 7\nI'd like to use the 1st, 4th and the 7th card\n\n"
						+ "The output will always be [1,4,7]\n\n");
			out.print(msg);
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
			return waitInputCards("Wrong input or some of the selected cards are not in your hand. Try again\n", hand);
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
