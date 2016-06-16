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


public class Player {

	private ArrayList<Emporium> availableEmporiums;
	private ArrayList<Assistant> availableAssistants;
	private ArrayList<PermitsCard> permits;
	private ArrayList<PoliticsCard> hand;
	private ArrayList<BonusCard> bonusCards;
	private Pawn pawn;
	private int coins;
	private int score;
	private String playerID;

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

	public Player(String ID) {
		availableEmporiums = new ArrayList<Emporium>(10);
		availableAssistants = new ArrayList<Assistant>();
		bonusCards = new ArrayList<BonusCard>();
		permits = new ArrayList<PermitsCard>();
		hand = new ArrayList<PoliticsCard>();
		playerID = ID;
	}

	/**
	 * Gives a specific amount of assistants to this player
	 * 
	 * @param qty
	 *            The quantity of assistants you want to add to this player
	 */

	public void addAssistant(ArrayList<Assistant> assistants) {
			availableAssistants.addAll(assistants);
	}
	
	public void addAssistant(Assistant ass) {
		availableAssistants.add(ass);
	}
	
	public void removeAssistant(Assistant ass){
		for(int i=0;i<availableAssistants.size();i++)
			if(availableAssistants.get(i).equals(ass))
				availableAssistants.remove(i);
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
	 * @return the availableAssistants
	 */
	public ArrayList<Assistant> getAvailableAssistants() {
		return availableAssistants;
	}

	/**
	 * @param availableAssistants the availableAssistants to set
	 */
	public void setAvailableAssistants(ArrayList<Assistant> availableAssistants) {
		this.availableAssistants = availableAssistants;
	}

	/**
	 * @return player's color
	 */

	public String getColor() {
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
	 * @param coins the coins to set
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	/**
	 * @param coins the coins to add
	 */
	public void addCoins(int coins) {
		this.coins += coins;
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
	 * @return the playerID
	 */
	public String getID() {
		return playerID;
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
	
	public void removePolitics(PoliticsCard pc) {
		for(int i=0;i<hand.size();i++)
			if(hand.get(i)!= null && hand.get(i).equals(pc))
				hand.remove(i);
	}
	
	public void removePermit(PermitsCard pc)
	{
		for(PermitsCard p : permits)
			if(p.equals(pc))
				permits.remove(p);
	}

	public boolean hasUncoveredPermits()
	{
		for(PermitsCard pc : this.permits)
			if(!pc.isFaceDown())
				return true;
		return false;
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
	
	public ArrayList<PermitsCard> getPermits() {
		return permits;
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

	/**
	 * @return the bonusCards
	 */
	public ArrayList<BonusCard> getBonusCards() {
		return bonusCards;
	}

	/**
	 * @param bonusCards the bonusCards to add
	 */
	public void addBonusCards(ArrayList<BonusCard> bonusCards) {
		this.bonusCards.addAll(bonusCards);
	}

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
	
	public PlayerDTO compareToDTOs(ArrayList<PlayerDTO> plsDTO){
		for(PlayerDTO pDTO: plsDTO)
			if(pDTO.getPlayerID().equals(this.getID()))
				return pDTO;
		return null;
	}
	// <--------- MARKET END ---------->
	
}
