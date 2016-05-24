package com.server.actions;

import java.util.ArrayList;

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
	
	/**
	 * Build an emporium in the specified city
	 * @param city the city where you want to build
	 */
	//ActionReturn(boolean success, String error, boolean disable, boolean addMainBonus)
	public ActionReturn execute() {
		for(Emporium e: city.getEmporium())
			game.getActualPlayer().getAvailableAssistants().remove(0);
		city.setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
		game.getActualPlayer().getAvailableEmporiums().remove(0);
		return new ActionReturn(true,"",false,false);
	}
}
