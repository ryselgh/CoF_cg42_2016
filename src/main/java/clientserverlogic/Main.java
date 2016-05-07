package clientserverlogic ;

import board.Map;
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
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Main() {
		super();
		// TODO construct me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void main(String[] args) {
		Player pl1 = new Player();
		Player pl2 = new Player();
		Player[] pp = {pl1,pl2};
		Map mmm = new Map(pp, true,"");
	}
	
}

