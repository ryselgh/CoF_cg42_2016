package com.server.controller;

import java.util.ArrayList;
import java.util.Observable;

import org.w3c.dom.Document;

import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;

public class GameHandler extends Observable implements Runnable{
	ArrayList<ClientHandler> players;
	private Game game;
	private Document rawMap;
	private Context context;
	
	public GameHandler(ArrayList<ClientHandler> pl, boolean defaultMap, Document map){
		players = pl;
		this.game = new Game(players.size(),defaultMap,map);
	}

	@Override
	public void run() {
		for(ClientHandler ch : players)
			this.addObserver(ch);
		this.context = new Context();
	}
	
	public PoliticsCard drawCard(){
		return game.getMap().getPoliticsDeck().draw();
	}
	
	public Game getGame(){
		return this.game;
	}
}
