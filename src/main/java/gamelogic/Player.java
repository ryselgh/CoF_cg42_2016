package gamelogic;

import java.util.ArrayList;
import board.Assistant;
import board.Emporium;
import board.Map;
import board.Pawn;
import decks.PermitsCard;
import decks.PoliticsCard;
import model.PawnColor;
import model.Track;


public class Player {

	private ArrayList<Emporium> availableEmporiums;
	private ArrayList<Assistant> availableAssistants;
	private ArrayList<PermitsCard> permits;
	private ArrayList<PoliticsCard> hand;
	private Pawn pawn;
	private MainAction mainAction;
	private SpeedAction speedAction;
	private int coins;
	private int score;

	/**
	 * Constructs an object of type Player
	 * 
	 * @param emporiums
	 *            Quantity of emporiums to give to the player
	 * @param assistants
	 *            Quantity of assistants to give to the player
	 * @param color
	 *            The chosen color of the player
	 */

	public Player() {
		mainAction = new MainAction();
		speedAction = new SpeedAction();
	}

	/**
	 * Gives a specific amount of assistants to this player
	 * 
	 * @param qty
	 *            The quantity of assistants you want to add to this player
	 */

	public void addAssistant(int qty) {
		for (int i = 0; i < qty; i++) {
			availableAssistants.add(Map.getAssistant());
		}
	}

	/**
	 * @return the available emporiums
	 */
	public ArrayList<Emporium> getAvailableEmporiums() {
		return availableEmporiums;
	}

	/**
	 * @param availableEmporiums
	 *            the available emporiums to set
	 */
	public void setAvailableEmporiums(ArrayList<Emporium> availableEmporiums) {
		this.availableEmporiums = availableEmporiums;
	}

	/**
	 * @return player's color
	 */

	public PawnColor getColor() {
		return this.getPawn().getColor();
	}

	/**
	 * @return player's pawn
	 */

	public Pawn getPawn() {
		return pawn;
	}

	/**
	 * Shows cards in the player's hand
	 */

	public ArrayList<PoliticsCard> getHand() {
		return hand;
	}

	/**
	 * @return the coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * @param coins
	 *            the coins to set
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Adds a politics card to the hand
	 * 
	 * @param poc
	 *            a politics card, typically obtained by draw or buy actions
	 */

	public void addPolitics(PoliticsCard poc) {
		hand.add(poc);
	}

	/**
	 * Removes a politics card from the hand
	 * 
	 * @param index
	 *            the index of the card in the ArrayList
	 */

	public void removePolitics(int index) {
		hand.remove(index);
	}

	/**
	 * Assigns a permit card to the player
	 * 
	 * @param pec
	 *            a permit, typically obtained by satisfying a council or buy
	 *            action
	 */

	public void addPermits(PermitsCard pec) {
		permits.add(pec);
	}

	/**
	 * @return the mainAction
	 */
	public MainAction getMainAction() {
		return mainAction;
	}

	/**
	 * @return the speedAction
	 */
	public SpeedAction getSpeedAction() {
		return speedAction;
	}

	
	// <--------- MARKET BEGIN ---------->
	
	/**
	 * Sell an assistant
	 * 
	 * @param a
	 *            the assistant that is being sold
	 */

	public void sellAssistant(Assistant a) {
		// TODO implement me
	}

	/**
	 * Sell a politics card
	 * 
	 * @param polc
	 *            the politics card that is being sold
	 */

	public void sellPoliticsCard(PoliticsCard polc) {
		// TODO implement me
	}

	/**
	 * Sell a permit
	 * 
	 * @param perc
	 *            the permit that is being sold
	 */

	public void sellPermitsCard(PermitsCard perc) {
		// TODO implement me
	}
	
	// <--------- MARKET END ---------->

}
