package clientserverlogic ;

import java.io.Console;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import board.Bonus;
import board.City;
import board.Councilor;
import board.Map;
import decks.PoliticsCard;
import gamelogic.Game;
import gamelogic.GraphMap;
import gamelogic.Player;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Main
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Lobby lobby;
	private static Scanner in = new Scanner(System.in);
	private static PrintStream out = System.out;
	private static Game game;
	private static CLI cli;
	private static GraphMap graph;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Main() {	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void main(String[] args) {
		game = new Game(2, true, "");
		//Controller controller = new Controller();
		cli = new CLI();
		graph = new GraphMap(game.getMap());
		int cost;
		cost = graph.shortestPathCost(game.getMap().getCity()[9]);
		System.out.println("From Juvelar to "+ game.getMap().getCity()[9].getName() + " you have to pay: ");
		System.out.print(Integer.toString(cost));
		/*Player pl1 = new Player();
		Player pl2 = new Player();
		Player[] pp = {pl1,pl2};
		Map mmm = new Map(pp,true,"");*/
	}
}

