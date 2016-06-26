package com.server.model.gamelogic;

import java.util.ArrayList;

import com.communication.board.AssistantDTO;
import com.communication.board.BonusCardDTO;
import com.communication.board.EmporiumDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.PawnColor;
import com.communication.values.Track;
import com.server.model.board.Assistant;
import com.server.model.board.BonusCard;
import com.server.model.board.Emporium;
import com.server.model.board.Map;
import com.server.model.board.Pawn;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;



/**
 * The Class Player.
 */
public class Player {

	/** The available emporiums. */
	private ArrayList<Emporium> availableEmporiums;
	
	/** The available assistants. */
	private ArrayList<Assistant> availableAssistants;
	
	/** The permits. */
	private ArrayList<PermitsCard> permits;
	
	/** The hand. */
	private ArrayList<PoliticsCard> hand;
	
	/** The bonus cards. */
	private ArrayList<BonusCard> bonusCards;
	
	/** The pawn. */
	private Pawn pawn;
	
	/** The coins. */
	private int coins;
	
	/** The score. */
	private int score;
	
	/** The player ID. */
	private String playerID;

	/**
	 * Constructs an object of type Player.
	 *
	 * @param ID the id
	 */

	public Player(String ID) {
		availableEmporiums = new ArrayList<Emporium>(10);
		availableAssistants = new ArrayList<Assistant>();
		bonusCards = new ArrayList<BonusCard>();
		permits = new ArrayList<PermitsCard>();
		hand = new ArrayList<PoliticsCard>();
		playerID = ID;
	}

	/**
	 * Gives a specific amount of assistants to this player.
	 *
	 * @param assistants the assistants
	 */

	public void addAssistant(ArrayList<Assistant> assistants) {
			availableAssistants.addAll(assistants);
	}
	
	/**
	 * Adds the assistant.
	 *
	 * @param ass the ass
	 */
	public void addAssistant(Assistant ass) {
		availableAssistants.add(ass);
	}
	
	/**
	 * Removes the assistant.
	 *
	 * @param ass the ass
	 */
	public void removeAssistant(Assistant ass){
		for(int i=0;i<availableAssistants.size();i++)
			if(availableAssistants.get(i).equals(ass))
				availableAssistants.remove(i);
	}

	/**
	 * Gets the available emporiums.
	 *
	 * @return the available emporiums
	 */
	public ArrayList<Emporium> getAvailableEmporiums() {
		return availableEmporiums;
	}

	/**
	 * Sets the available emporiums.
	 *
	 * @param availableEmporiums            the available emporiums to set
	 */
	public void setAvailableEmporiums(ArrayList<Emporium> availableEmporiums) {
		this.availableEmporiums = availableEmporiums;
	}

	/**
	 * Gets the available assistants.
	 *
	 * @return the availableAssistants
	 */
	public ArrayList<Assistant> getAvailableAssistants() {
		return availableAssistants;
	}

	/**
	 * Sets the available assistants.
	 *
	 * @param availableAssistants the availableAssistants to set
	 */
	public void setAvailableAssistants(ArrayList<Assistant> availableAssistants) {
		this.availableAssistants = availableAssistants;
	}

	/**
	 * Gets the color.
	 *
	 * @return player's color
	 */

	public String getColor() {
		return this.getPawn().getColor();
	}

	/**
	 * Gets the pawn.
	 *
	 * @return player's pawn
	 */

	public Pawn getPawn() {
		return pawn;
	}

	/**
	 * Shows cards in the player's hand.
	 *
	 * @return the hand
	 */

	public ArrayList<PoliticsCard> getHand() {
		return hand;
	}

	/**
	 * Gets the coins.
	 *
	 * @return the coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Sets the coins.
	 *
	 * @param coins the coins to set
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	/**
	 * Adds the coins.
	 *
	 * @param coins the coins to add
	 */
	public void addCoins(int coins) {
		this.coins += coins;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the id.
	 *
	 * @return the playerID
	 */
	public String getID() {
		return playerID;
	}

	/**
	 * Adds a politics card to the hand.
	 *
	 * @param poc            a politics card, typically obtained by draw or buy actions
	 */

	public void addPolitics(PoliticsCard poc) {
		hand.add(poc);
	}

	/**
	 * Removes a politics card from the hand.
	 *
	 * @param index            the index of the card in the ArrayList
	 */

	public void removePolitics(int index) {
		hand.remove(index);
	}
	
	/**
	 * Removes the politics.
	 *
	 * @param pc the pc
	 */
	public void removePolitics(PoliticsCard pc) {
		for(int i=0;i<hand.size();i++)
			if(hand.get(i)!= null && hand.get(i).equals(pc))
				hand.remove(i);
	}
	
	/**
	 * Removes the permit.
	 *
	 * @param pc the pc
	 */
	public void removePermit(PermitsCard pc)
	{
		for(int i=0;i<permits.size();i++)
		      if(permits.get(i).equals(pc))
		        permits.remove(i);
	}

	/**
	 * Checks for uncovered permits.
	 *
	 * @return true, if successful
	 */
	public boolean hasUncoveredPermits()
	{
		for(PermitsCard pc : this.permits)
			if(!pc.isFaceDown())
				return true;
		return false;
	}
	
	/**
	 * Assigns a permit card to the player.
	 *
	 * @param pec            a permit, typically obtained by satisfying a council or buy
	 *            action
	 */

	public void addPermits(PermitsCard pec) {
		permits.add(pec);
	}
	
	/**
	 * Gets the permits.
	 *
	 * @return the permits
	 */
	public ArrayList<PermitsCard> getPermits() {
		return permits;
	}

	
	// <--------- MARKET BEGIN ---------->
	
	/**
	 * Sell an assistant.
	 *
	 * @param a            the assistant that is being sold
	 */

	public void sellAssistant(Assistant a) {
		// TODO implement me
	}

	/**
	 * Sell a politics card.
	 *
	 * @param polc            the politics card that is being sold
	 */

	public void sellPoliticsCard(PoliticsCard polc) {
		// TODO implement me
	}

	/**
	 * Sell a permit.
	 *
	 * @param perc            the permit that is being sold
	 */

	public void sellPermitsCard(PermitsCard perc) {
		// TODO implement me
	}

	/**
	 * Gets the bonus cards.
	 *
	 * @return the bonusCards
	 */
	public ArrayList<BonusCard> getBonusCards() {
		return bonusCards;
	}

	/**
	 * Adds the bonus cards.
	 *
	 * @param bonusCards the bonusCards to add
	 */
	public void addBonusCards(ArrayList<BonusCard> bonusCards) {
		this.bonusCards.addAll(bonusCards);
	}

	/**
	 * To DTO.
	 *
	 * @return the player DTO
	 */
	public PlayerDTO toDTO() {
		PlayerDTO playerDTO = new PlayerDTO();
		ArrayList<AssistantDTO> assDTO = new ArrayList<AssistantDTO>();
		ArrayList<EmporiumDTO> empDTO = new ArrayList<EmporiumDTO>();
		ArrayList<BonusCardDTO> bcDTO = new ArrayList<BonusCardDTO>();
		ArrayList<PoliticsCardDTO> handDTO = new ArrayList<PoliticsCardDTO>();
		ArrayList<PermitsCardDTO> permDTO = new ArrayList<PermitsCardDTO>();
		for(Assistant ass: availableAssistants)
			assDTO.add(ass.toDTO());
		playerDTO.setAvailableAssistants(assDTO);
		for(Emporium emp: availableEmporiums)
			empDTO.add(emp.toDTO(playerDTO));
		playerDTO.setAvailableEmporiums(empDTO);
		for(BonusCard bc: bonusCards)
			bcDTO.add(bc.toDTO());
		playerDTO.setBonusCards(bcDTO);
		playerDTO.setCoins(coins);
		for(PoliticsCard pc: hand)
			handDTO.add(pc.toDTO());
		playerDTO.setHand(handDTO);
	//	playerDTO.setPawn(pawn.toDTO());
		for(PermitsCard perm: permits)
			permDTO.add(perm.toDTO());
		playerDTO.setPermits(permDTO);
		playerDTO.setPlayerID(playerID);
		playerDTO.setScore(score);
		return playerDTO;
	}
	
	/**
	 * Compare to DT os.
	 *
	 * @param plsDTO the pls DTO
	 * @return the player DTO
	 */
	public PlayerDTO compareToDTOs(ArrayList<PlayerDTO> plsDTO){
		for(PlayerDTO pDTO: plsDTO)
			if(pDTO.getPlayerID().equals(this.getID()))
				return pDTO;
		return null;
	}
	// <--------- MARKET END ---------->
	
}
