package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.communication.actions.BuildDTO;
import com.server.model.board.Bonus;
import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class Build.
 */
public class Build extends Action{

	/** The city. */
	private City city;
	
	/** The permit. */
	private PermitsCard permit;
	
	/** The game. */
	private Game game;
	
	/** The errors. */
	private ArrayList<String> errors;
	
	/** The disable. */
	private boolean disable = false;//anche se questa funzione non da luogo ad errori che la disabilitano
	
	/**
	 * Instantiates a new builds the.
	 *
	 * @param city the city where you want to build
	 * @param permit the permit you want to use
	 */
	public Build(City city, PermitsCard permit){
		this.city = city;
		this.permit = permit;
		errors = new ArrayList<String>();
	}
	
	/* (non-Javadoc)
	 * @see com.server.actions.Action#setGame(com.server.model.gamelogic.Game)
	 */
	public void setGame(Game game){
		this.game = game;
	}
	
	/* 
	 * check if the action is valid, return true if it's valid
	 */
	public boolean isValid(){
		hasPermit();
		hasEmporium();
		canBuild();
		if(errors.size()>0)
			return false;
		return true;
	}
	
	/**
	 * Checks for permit. it adds an error if the permit is illegal
	 */
	private void hasPermit(){
		boolean found = false;
		for(PermitsCard p : game.getActualPlayer().getPermits())
			if(p.equals(permit)){
				permit = p;
				found= true;
			}
		if(!found)
			errors.add("Invalid permit card");//da levare se tutto funzionerà bene (è un errore di collaborazione client/server non di gioco)
	}
	
	/**
	 * Checks for emporium. if the player already has an emporium in the city it adds an error
	 */
	private void hasEmporium(){
		for(City c : game.getMap().getCity())
			if(c.getName().equals(city.getName())){ //trovo l'oggetto città sul server corrispondente a quello mandato da client
				this.city = c;//aggiorno l'istanza per l'execute
				for(Emporium e : c.getEmporium())
					if(e != null)
						if(e.getPlayer().equals(game.getActualPlayer()))//controllo che non ci sia un emporio di questo giocatore
							errors.add("You already have an emporium in the selected city");
			}
	}
	
	/**
	 * Can build. it checks if one of the letter on the permit matches the city and 
	 * if the player has enough assistants to pay the emporium fee 
	 */
	private void canBuild(){
		int count = 0;
		
		for(String l: permit.getCityLetter())
			if(!l.equals(Character.toString(city.getName().toLowerCase().charAt(0))))
				count++;
		
		if(count==permit.getCityLetter().length)
			errors.add("This permit can't build on the selected city");
		
		count = 0;
		
		for(Emporium e: city.getEmporium())
			if(e!=null)
				count++;
		
		if(game.getActualPlayer().getAvailableAssistants().size()<count){
			errors.add("You have not enought assistants");
		}
	}
	
	/**
	 * Gets the cities bonus. 
	 * When you build in a city you get all the other bonus in the cities linked with the new one. 
	 * You must have an emporium in each city if you want the bonus.
	 *
	 * @param startcity the startcity
	 * @return the cities bonus
	 */
	private Bonus[] getCitiesBonus(City startcity){
		ArrayList<City> checked = new ArrayList<City>();
		ArrayList<City> tocheck = new ArrayList<City>();
		ArrayList<Bonus> found = new ArrayList<Bonus>();
		found.addAll(new ArrayList<Bonus>(Arrays.asList(startcity.getBonusToken().getBonus())));//aggiungo i bonus della città di partenza
		checked.add(startcity);
		for (String c : startcity.getCloseCity())
			tocheck.add(game.getCityFromName(c));//aggiungo le città vicine alla lista da controllare
		while (true) {
			if (tocheck.size() == 0)//se la lista è vuota break
				break;
			for (int i = 0; i < tocheck.size(); i++) {
				if (tocheck.get(i).hasEmporium(game.getActualPlayer())) {//se la città da controllare ha un emporio
					found.addAll(new ArrayList<Bonus>(Arrays.asList(tocheck.get(i).getBonusToken().getBonus())));//aggiungo i bonus del token di quella città
					checked.add(tocheck.get(i));//la aggiungo alla lista delle città controllate
					for (String c : tocheck.get(i).getCloseCity())//aggiungo i suoi vicini alle città da controllare SE NON LE HO GIà CONTROLLATE
						if (!checked.contains(game.getCityFromName(c)))
							tocheck.add(game.getCityFromName(c));
					tocheck.remove(i);//rimuovo la città appena controllata
				}else{
					checked.add(tocheck.get(i));
					tocheck.remove(i);
				}
			}
		}
		Bonus toRemove = null;
		for(Bonus b: found)
			if(b==null)
				toRemove = b;
		found.remove(toRemove); //Avoid ConcurrentModificationException
		Bonus[] stockArr = new Bonus[found.size()];
		stockArr = found.toArray(stockArr);
		return stockArr;
	}
	
	/**
	 * If all it's ok you Build an emporium in the specified city and get the bonus
	 *  if there is an error gives a string error
	 *
	 * @return the action return
	 */
	
	public ActionReturn execute() {
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
			
		for(Emporium e: city.getEmporium())
			if(e!=null)
				game.getActualPlayer().getAvailableAssistants().remove(0);
		city.setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
		game.getActualPlayer().getAvailableEmporiums().remove(0);
		Bonus[] bonusToCollect = getCitiesBonus(city);
		permit.setFaceDown(true);
		return new ActionReturn(true,"",bonusToCollect);
	}
	
	/**
	 * Setter from dto.
	 *
	 * @param buildDTO the build dto
	 * @param player the player
	 * @param game the game
	 */
	public void setterFromDTO(BuildDTO buildDTO, Player player, Game game){
		this.permit = PermitsCard.fromDTO(buildDTO.getPermit(), player);
		this.game = game;
		this.city = game.getCityFromName(buildDTO.getCity().getName());
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	
	
}