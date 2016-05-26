package com.server.controller;

import java.util.ArrayList;
import java.util.Observable;

import org.w3c.dom.Document;

import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.ActionState;
import com.server.model.gamelogic.BuyItemState;
import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.SellItemState;
import com.server.model.gamelogic.State;

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
		context.setClienthandler(players.get(0));
		context.setGamehandler(this);
		ActionState actState = new ActionState();
		actState.doAction(context);//avvio il primo stato di azione per il primo giocatore
	}
	
	public void changeState(Context context){
		ClientHandler client = context.getClienthandler();
		if(!client.equals(players.get(players.size()-1))){//se non è l'ultimo del giro
			context.getState().restoreState();//refresho lo stato
			context.setClienthandler(nextPlayer(client));//aggiorno il riferimento al client
			game.setActualPlayer(game.getActualPlayerIndex() +1);//aggiorno il giocatore in game
			context.getState().doAction(context);//avvio lo stato
		}
		else{//è l'ultimo del giro
			State newState = nextState(context.getState());//ricavo lo stato successivo
			context.setClienthandler(players.get(0));//setto il turno al primo giocatore
			game.setActualPlayer(0);//aggiorno il giocatore in game
			newState.doAction(context);//avvio lo stato
		}
	}
	
	public ClientHandler nextPlayer(ClientHandler pl){
		for(int i=0;i<players.size();i++)
			if(players.get(i).equals(pl))
				return players.get(i+1);//viene chiamata dopo il check su pl che non deve essere l'ultimo, quindi questa funz non dovrebbe mai ritornare null
		return null;
	}
	public State nextState(State state)
	{
		if(state instanceof ActionState)
			return new SellItemState();
		else if (state instanceof SellItemState)
			return new BuyItemState();
		else
			return new ActionState();
	}
	public PoliticsCard drawCard(){
		return game.getMap().getPoliticsDeck().draw();
	}
	
	public Game getGame(){
		return this.game;
	}
}
