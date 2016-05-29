package com.client.view;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.client.ClientObservable;
import com.client.ClientObserver;
import com.communication.gamelogic.GameDTO;

public class ClientCLI extends ClientObservable implements ClientObserver{

	private GameDTO game;
	private PrintStream out;
	private String[][] map = new String[15][5];

	/**
	 * constructor of the class
	 * @param game is the GameStatus
	 */

	public ClientCLI(GameDTO game){
//		game.attachObserver(this);
		this.game = game;
		this.out = System.out;
		constructMap();
	}

	private void constructMap() {

//		//City + Region separators
//		String[] initialsRowOne = {"A","C","|","F","I","|","K","N"};
//		String[] initialsRowTwo = {"B","D","|","G","J","|","L","O"};
//		String[] initialsRowThree = {"E"," ","|","H"," ","|","M"," "};
//
//		for(int i=0;i<15;i++){
//			for(int j=0;j<5;j++){
//				switch(j){
//				case 0:
//					if(i%2==0)
//						map[i][j]=initialsRowOne[i/2];
//					break;
//				case 2:
//					if(i%2==0)
//						map[i][j]=initialsRowTwo[i/2];
//					break;
//				case 4:
//					if(i%2==0)
//						map[i][j]=initialsRowThree[i/2];
//					break;
//				default:
//					if(i==4 || i==10)
//						map[i][j]="|";
//					break;
//				}
//			}
//		}
//
//		//Fill empty spaces
//		for(int i=0;i<15;i++){
//			for(int j=0;j<5;j++){
//				if(map[i][j]==null)
//					map[i][j] = " ";
//			}
//		}
//
//		//Connections
//		for(int i=0;i<15;i++){
//			for(int j=0;j<5;j++){
//				for(int cityID=0; cityID < game.getCityNames().size(); cityID++){
//					String c = game.getCityNames().get(cityID);
//					for(String cc: game.getCloseCities().get(cityID)){
//						String letter1 = String.valueOf(c.charAt(0)).toUpperCase();
//						String letter2 = String.valueOf(cc.charAt(0)).toUpperCase();
//						//Rows
//						if(i%2!=0 && (j==0 || j==2) && i-1>=0 && i+1<15){ //First and Third row of emptyes
//							if(map[i+1][j]!="|" && map[i-1][j]!="|") //if NOT a separator
//								if(map[i-1][j].equals(letter1) &&
//										map[i+1][j].equals(letter2)) //And there is a connection
//									map[i][j]="–"; //Simple connection
//						}
//						if((j==0||j==2)&&map[i][j]=="|") //if a separator
//							if(map[i-2][j].equals(letter1) &&
//									map[i+2][j].equals(letter2)){
//								map[i-1][j]="–";
//								map[i+1][j]="–";
//							}
//						if(j==4 && i>=4 && i<=12 && map[i][j]=="|")
//							if(map[i-4][j].equals(letter1) &&
//									map[i+2][j].equals(letter2)){
//								map[i-3][j] = "–";
//								map[i-2][j] = "–";
//								map[i-1][j] = "–";
//								map[i+1][j] = "–";
//							}
//						//Columns
//						if(j%2!=0)
//							if(map[i][j-1].equals(letter1)&&map[i][j+1].equals(letter2))
//								map[i][j]="|";
//						//Diagonal "\"
//						if(j%2!=0 && i>0)
//							if(map[i-1][j-1].equals(letter1) && map[i+1][j+1].equals(letter2))
//								map[i][j]="\\";
//						//Diagonal "/"
//						if(j%2!=0 && i<14)
//							if(map[i+1][j-1].equals(letter1) && map[i-1][j+1].equals(letter2))
//								map[i][j]="/";
//					}
//				}
//			}
//		}
//
//	}
//
//	/**
//	 * print the map
//	 */
//	public void printMap(){
//		for(int i=0;i<5;i++){
//			for(int j=0;j<15;j++){
//				System.out.print(map[j][i]);
//			}
//			System.out.print("\n");
//		}
	}

//	/**
//	 *print the city bonus, for each  city 
//	 * @param game is the game status
//	 */
//	public void printCityBonus(GameDTO game){
//		ArrayList<String> cities = game.getCityNames();
//		out.println("City bonus tokens:");
//		for(int cityID=0; cityID < game.getCityNames().size(); cityID++){
//			String c = game.getCityNames().get(cityID);
//			ArrayList<String> bounsTypes = new ArrayList<String>();
//				for(BonusInfo b: game.getCityTokens().get(cityID))
//					if(b != null)
//						bounsTypes.add(b.getType()+"("+Integer.toString(b.getQnt())+")");
//			out.println(c+ ": " + bounsTypes);
//		}
//		out.println("\n");
//	}

	/**
	 * print the councilor pool
	 * @param game is the game status
	 */

	public void printCouncilorPool(GameDTO game){

	}

	/**
	 * print the assistant pool
	 * @param game is the game status
	 */

	public void printAssistantPool(GameDTO game){

	}

	/**
	 * print the location of the king
	 * @param game is the game status
	 */

	public void printKingLocation(GameDTO game){

	}

	/**
	 * print the permitsdeck
	 * @param game is the game status
	 */

	public void printPermitsDecks(GameDTO game){

	}

	/**
	 * print the balcony
	 * @param game is the game status
	 */

	public void printCouncils(GameDTO game){

	}

	/**
	 * print the nobility track
	 * @param game is the game status
	 */

	public void printNobilityTrack(GameDTO game){

	}

	/**
	 * print the region bonuses
	 * @param game is the game status
	 */

	public void printRegionBonuses(GameDTO game){

	}

	/**
	 * print the color bonuses
	 * @param game is the game status
	 */

	public void printColorBonuses(GameDTO game){

	}

	/**
	 * print the king bonuses
	 * @param game is the game status
	 */

	public void printKingBonuses(GameDTO game){

	}

	/**
	 * print the placed emporiums for each player
	 * @param game is the game status
	 */

	public void printPlacedEmporiums(GameDTO game){

	}

	/**
	 * print the player status
	 * @param game is the game status
	 */

	public void printPlayersStatus(GameDTO game){

	}

	/**
	 *print the player hand
	 * @param player is the game status
	 */

	public void printPlayerHand(GameDTO player){

	}

	/**
	 * print a message
	 * @param msg is a Stirng 
	 */

	public void printMsg(String msg){

	}

	/**
	 * print the bonus you're receiving
	 * @param type the type of the bonus
	 * @param amm the ammount of the bonus
	 */

	public void printBonusCollectionMsg(String type, int amm){

	}

	/**
	 * print all the gamestatus
	 * @param game is the game status
	 */

	public void printGameStatus(GameDTO game){
//		this.printMap();
		this.printCouncils(game);
		this.printPlayerHand(game);
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

	/*Observer*/

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * print the new GameDTO after an update
	 */
	@Override
	public <C> void update(C change) {
		this.printGameStatus((GameDTO) change);

	}

}
