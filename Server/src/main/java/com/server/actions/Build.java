package com.server.actions;

import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.Game;

public class Build extends Action{

	private City city;
	private PermitsCard permit;
	private Game game;
	
	public Build(City city, PermitsCard permit){
		this.city = city;
		this.permit = permit;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public boolean isValid(){
		return hasPermit() && !hasEmporium() && canBuild();
	}
	private boolean hasPermit(){
		for(PermitsCard p : game.getActualPlayer().getPermits())
			if(p.equals(permit)){
				permit = p;
				return true;
			}
		return false;
	}
	
	private boolean hasEmporium(){
		for(City c : game.getMap().getCity())
			if(c.getName().equals(city.getName())){ //trovo l'oggetto città sul server corrispondente a quello mandato da client
				this.city = c;//aggiorno l'istanza per l'execute
				for(Emporium e : c.getEmporium())
					if(e.getPlayer().equals(game.getActualPlayer()))//controllo che non ci sia un emporio di questo giocatore
						return true;
			}
		return false;
	}
	
	private boolean canBuild(){
		for(String l: permit.getCityLetter())
			if(!l.equals(Character.toString(city.getName().toLowerCase().charAt(0))))
				return false;//errore lettera
			if(game.getActualPlayer().getAvailableAssistants().size()<city.getEmporium().length){
				//errore no abb aiutanti
				return false;
			}
		return true;
	}
	
	/**
	 * Build an emporium in the specified city
	 * @param city the city where you want to build
	 */
	
	public void execute() {
		for(Emporium e: city.getEmporium())
			game.getActualPlayer().getAvailableAssistants().remove(0);
		city.setEmporium(game.getActualPlayer().getAvailableEmporiums().get(0));
		game.getActualPlayer().getAvailableEmporiums().remove(0);
	}
}
