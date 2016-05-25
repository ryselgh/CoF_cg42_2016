package com.server.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.server.model.board.Bonus;
import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Game;

public class Build extends Action{

	private City city;
	private PermitsCard permit;
	private Game game;
	private ArrayList<String> errors;
	private boolean disable = false;//anche se questa funzione non da luogo ad errori che la disabilitano
	
	public Build(City city, PermitsCard permit){
		this.city = city;
		this.permit = permit;
		errors = new ArrayList<String>();
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public boolean isValid(){
		hasPermit();
		hasEmporium();
		canBuild();
		if(errors.size()>0)
			return false;
		return true;
	}
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
	
	private void hasEmporium(){
		for(City c : game.getMap().getCity())
			if(c.getName().equals(city.getName())){ //trovo l'oggetto città sul server corrispondente a quello mandato da client
				this.city = c;//aggiorno l'istanza per l'execute
				for(Emporium e : c.getEmporium())
					if(e.getPlayer().equals(game.getActualPlayer()))//controllo che non ci sia un emporio di questo giocatore
						errors.add("You already have an emporium in the selected city");
			}
	}
	
	private void canBuild(){
		for(String l: permit.getCityLetter())
			if(!l.equals(Character.toString(city.getName().toLowerCase().charAt(0)))){
				errors.add("This permit can't build on the selected city");
			}
			if(game.getActualPlayer().getAvailableAssistants().size()<city.getEmporium().length){
				errors.add("You have not enought assistants");
			}
	}
	
	private Bonus[] getCitiesBonus(City startcity){
		ArrayList<City> checked = new ArrayList<City>();
		ArrayList<City> tocheck = new ArrayList<City>();
		ArrayList<Bonus> found = new ArrayList<Bonus>();
		found.addAll(new ArrayList<Bonus>(Arrays.asList(startcity.getBonusToken().getBonus())));//aggiungo i bonus della città di partenza
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
				}
			}
		}
		Bonus[] stockArr = new Bonus[found.size()];
		stockArr = found.toArray(stockArr);
		return stockArr;
	}
	
	/**
	 * Build an emporium in the specified city
	 * @param city the city where you want to build
	 */
	//ActionReturn(boolean success, String error, boolean disable, boolean addMainBonus)
	public ActionReturn execute() {
		if(errors.size()>0){
			String errorsStr = "";
			for(String e : errors)
				errorsStr += "\n" + e;
			return new ActionReturn(false,errorsStr,null);
		}
			
		for(Emporium e: city.getEmporium())
			game.getActualPlayer().getAvailableAssistants().remove(0);
		city.setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
		game.getActualPlayer().getAvailableEmporiums().remove(0);
		Bonus[] bonusToCollect = getCitiesBonus(city);
		return new ActionReturn(true,"",bonusToCollect);
	}
}
