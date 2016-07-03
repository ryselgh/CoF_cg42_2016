package com.server.model.gamelogic ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.communication.gamelogic.GameDTO;
import com.communication.gamelogic.PlayerDTO;
import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.board.Map;
import com.server.model.board.Pawn;
import com.server.model.decks.PermitsCard;
import com.server.model.market.Market;

// TODO: Auto-generated Javadoc
/**
 * This is the main controller of the game.
 */

public class Game {
	
	
	/** The Constant COINOFFSET. */
	private static final int COINOFFSET = 10;
	
	/** The Constant ASSISTOFFSET. */
	private static final int ASSISTOFFSET = 1;
	
	/** The Constant INITIALCARDS. */
	private static final int INITIALCARDS = 6;
	
	/** The players. */
	private ArrayList<Player> players;
	
	/** The players qty. */
	private int playersQty;
	
	/** The map. */
	private Map map;
	
	/** The market. */
	private Market market;
	
	/** The actual player. */
	private Player actualPlayer;
	
	/** The final turn. */
	private boolean finalTurn;
	
	/** The map name. */
	private String mapName;
	
	/** The raw map. */
	private String rawMap;
	
	/** The graph map. */
	private GraphMap graphMap;
	
	/** The client names. */
	private String[] clientNames;
	
	/**The name of the player currently buying at the market. We need this because of the shuffled order*/
	private String marketCurrentPlayer="";
	/**
	 * Constructs a new object of type Game.
	 *
	 * @param playersQty how many players are playing
	 * @param defaultMap set to true if you want to load the default map
	 * @param rawMap the raw map
	 * @param clientNames the client names
	 */
	
	public Game(int playersQty, String mapName, String rawMap, String[] clientNames) {
		this.clientNames = clientNames;
		this.playersQty = playersQty;
		this.mapName = mapName;
		this.rawMap = rawMap;
		this.initializeObjects();
		
	}
	
	/**
	 * Initializes all the objects needed for the game.
	 */
	
	private void initializeObjects() {
		market = new Market (); 
		players = new ArrayList<Player>(playersQty);
				
		//Players initialize
		for(int i=0; i<playersQty; i++){
			players.add(new Player(clientNames[i]));
		}
		actualPlayer = players.get(0);
		
		//Map
		map = new Map(players.toArray(new Player[players.size()]),mapName,rawMap);
		graphMap = new GraphMap(map);
		PermitsCard cardSea = this.getMap().getPermitsDeck(0).getSlot(0,true);
		PermitsCard cardHill = this.getMap().getPermitsDeck(1).getSlot(0,true);
		PermitsCard cardMount = this.getMap().getPermitsDeck(2).getSlot(0,true);
		ArrayList<String> covered = new ArrayList<String>();
		covered.addAll(Arrays.asList(cardSea.getCityLetter()));
		covered.addAll(Arrays.asList(cardHill.getCityLetter()));
		covered.addAll(Arrays.asList(cardMount.getCityLetter()));
		Player fakePlayer = new Player("_FakePlayer_");
		Pawn fakePawn = new Pawn(fakePlayer,map.getEmpColor());
		fakePlayer.setPawn(fakePawn);
		for(int i=0;i<covered.size();i++){
			City city = this.getCityFromName(covered.get(i));
			Emporium emp = new Emporium(fakePlayer);
			city.setEmporium(emp);
					
		}
		
		//Player construction
		for(int i=0; i<playersQty; i++){
			if(playersQty>4)
				players.get(i).setCoins(i+(COINOFFSET-(playersQty-4)/2));
			else
				players.get(i).setCoins(i+COINOFFSET);
			players.get(i).setAvailableEmporiums(map.getPlayerEmporiums(i));
			players.get(i).setScore(0);
			players.get(i).addAssistant(this.getMap().getAssistant(i+ASSISTOFFSET));
			for(int j=0;j<INITIALCARDS;j++)
				players.get(i).addPolitics(map.getPoliticsDeck().draw());
		}
		
	
		
	}
	
	
	/**
	 * Gets the players.
	 *
	 * @return an array of Player(s)
	 */
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Select a specific player by its number.
	 *
	 * @param id the integer that identifies the wanted player
	 * @return a specific Player
	 */
	
	public Player getThatPlayer(int id){
		return this.players.get(id);
	}
	
	/**
	 * The market.
	 *
	 * @return the market
	 */
	
	public Market getMarket() {
		return this.market;	
	}
	
	/**
	 * Gets the actual player.
	 *
	 * @return the player that is playing right now
	 */
	
	public Player getActualPlayer() {
		return actualPlayer;	
	}
	
	/**
	 * Sets the final turn.
	 *
	 * @param finalTurn set to true when a player places its last emporium
	 */
	
	public void setFinalTurn(boolean finalTurn) {
		this.finalTurn = finalTurn;
	}
	
	/**
	 * Checks if is final turn.
	 *
	 * @return true, if is final turn
	 */
	
	public boolean isFinalTurn() {
		return this.finalTurn;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Sets the actual player.
	 *
	 * @param index the new actual player
	 */
	public void setActualPlayer(int index)
	{
		this.actualPlayer = this.players.get(index);
	}
	
	/**
	 * Gets the actual player index.
	 *
	 * @return the actual player index
	 */
	public int getActualPlayerIndex(){
		for(int i=0;i<players.size();i++)
			if(players.get(i).equals(actualPlayer))
				return i;
		return -1;
				
	}
	
	/**
	 * Gets the graph map.
	 *
	 * @return the graphMap
	 */
	public GraphMap getGraphMap() {
		return graphMap;
	}
	
	/**
	 * Gets the city from name.
	 *
	 * @param name the name
	 * @return the city from name
	 */
	public City getCityFromName(String name){
		if(name.length()==1){
			for(City c : this.getMap().getCity())
				if(c.getName().substring(0, 1).toLowerCase().equals(name.toLowerCase()))
					return c;
		}
		else{
		for(City c : this.getMap().getCity())
			if(c.getName().toLowerCase().equals(name.toLowerCase()))
				return c;
		}
		return null;
	}
	
	/**
	 * To dto.
	 *
	 * @return the game DTO
	 */
	public GameDTO toDto(){
		GameDTO gameDTO = new GameDTO();
		ArrayList<PlayerDTO> plsDTO = new ArrayList<PlayerDTO>();
		for(Player p: players)
			plsDTO.add(p.toDTO());
		gameDTO.setPlayers(plsDTO);
		gameDTO.setActualPlayer(actualPlayer.toDTO());
		gameDTO.setMapName(mapName);
		gameDTO.setFinalTurn(finalTurn);
		gameDTO.setMap(map.toDTO(plsDTO));
		gameDTO.setMarket(market.toDTO());
		gameDTO.setPlayersQty(playersQty);
		gameDTO.setMarketCurrentPlayer(marketCurrentPlayer);
		return gameDTO;
	}

	public String getMarketCurrentPlayer() {
		return marketCurrentPlayer;
	}

	public void setMarketCurrentPlayer(String marketCurrentPlayer) {
		this.marketCurrentPlayer = marketCurrentPlayer;
	}
	
	
	
}