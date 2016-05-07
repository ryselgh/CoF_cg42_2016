import java.io.Console;
import java.util.ArrayList;

import board.Balcony;
import board.Councilor;
import decks.PoliticsCard;
import model.CouncilorColor;

public class CLI {
	Console cnsl;

	public CLI()
	{
		cnsl = System.console();
	}
	
	public void speedAction(ArrayList<CouncilorColor> availables){
		int selection = waitCorrectInput("Insert the number related to your action:\n"
				+ "1-Buy Assistants\n"
				+ "2-Change Permits Card\n"
				+ "3-Shift Council\n"
				+ "4-Buy Main Action\n",1,4);
		int regIndex=0;
		switch(selection){
			case 1:
				int assAmm = waitCorrectInput("Insert the ammount of assistants you want to buy:\n",1,99);
				break;
			case 2:
				regIndex = waitCorrectInput("Insert the index of the region containing the cards you want to change:\n1-Sea\n2-Hill\n3-Mountain\n",1,3);
				break;
			case 3://PURPLE, WHITE, BLACK, ORANGE, BLUESKY, PINK, JOLLY;
				regIndex = waitCorrectInput("Insert the index of the region containing the councilors you want to shift:\n"
						+ "1-Sea\n"
						+ "2-Hill\n"
						+ "3-Mountain\n",1,3);
				/*ArrayList<CouncilorColor> availables = new ArrayList<CouncilorColor>();
				for(Councilor c: game.getMap().getCouncilorsPool())
					if(!availables.contains(c.getCouncilorColor()))
						availables.add(c.getCouncilorColor());*/ //DA AGGIUNGERE A CONTROLLER CHE LO PASSA QUI COME ARGOMENTO
				for(int i=0;i<availables.size();i++)
				{
					cnsl.printf(i + "-" + availables.get(i).toString() + "\n");
				}
				cnsl.printf("Insert the index of the color:\n");
				int colIndex = waitCorrectInput("",1,availables.size());
				break;
			case 4:
				
				break;
				}
		}
		
		public void mainAction(){
		
		PoliticsCard[] chosenPolitics = null; //temporary
		Balcony chosenBalcony = null; //temporary
		/*switch(selection){
			case 1:
				//this.game.getMainAction().canObtainPermit(chosenPolitics, chosenBalcony);
				break;
			case 2:
				//this.game.getMainAction().canSatisfyKing(chosenPolitics);
				break;
			case 3:
				//this.game.getMainAction().shiftCouncil();
				break;
			case 4:
				//this.game.getMainAction().build();
				break;
		}*/
				
		}

		private int waitCorrectInput(String msg, int min, int max)
		{
			int respInt = -1;
			do {
				String resp = getInput(msg);
				respInt = parseNum(resp, min,max);
			} while (respInt != -1);
			return respInt;
		}
		
		private String getInput(String message)
		{
			cnsl.printf(message);
			return cnsl.readLine();
			
		}
		
		private int parseNum(String msg, int min, int max) {
			int read=0;
			try
			{ read = Integer.parseInt(msg);
			}
			catch (NumberFormatException e) 
			{
				cnsl.printf("Wrong input format. Try again\n");
				return -1;
			}
			if (read <= max && read >= min)
				return read;
			else {
				cnsl.printf("Input out of bounds. Try again\n");
				return -1;}
		}
		
		public int getAction(int plIndex)
		{
			return waitCorrectInput("Hi there player" + plIndex + ", insert 1 for Main Action, insert 2 for Speed Action.\n",1,2);
		}
}
