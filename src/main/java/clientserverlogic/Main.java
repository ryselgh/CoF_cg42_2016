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
		Controller controller = new Controller();
		//ebbasta di istanziare roba, si occupa di tutto il controller
	}
}

