package com.server.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.actions.ActionDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;
import com.server.actions.Action;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.ActionState;
import com.server.model.gamelogic.BuyItemState;
import com.server.model.gamelogic.Context;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.SellItemState;
import com.server.model.gamelogic.State;

public class GameHandler extends Observable implements Runnable, Observer{
	ArrayList<ClientHandler> players;
	private Game game;
	private String rawMap;
	private Context context;
	private Object toResume;
	private String toResumeStr;
	private boolean waitingInput = false;
	private Logger logger;
	
	public GameHandler(ArrayList<ClientHandler> pl, boolean defaultMap, String map){
		players = pl;
		this.game = new Game(players.size(),defaultMap,map, clientNames(players));
		GameDTO gameDTO = this.game.toDto();
		for(ClientHandler ch : pl){
			ch.addObserver(this);
			ch.sendToClient("STARTGAME", gameDTO);
		}
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
	
	private String[] clientNames(ArrayList<ClientHandler> players){
		String[] ret = new String[players.size()];
		for(int i=0;i<players.size();i++)
			ret[i]=players.get(i).getUserName();
		return ret;
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
			if(newState.getStateID().equals("BuyItemState") && this.game.getMarket().getObjectsOnSale().size()==0)
				newState = nextState(newState);//se nessuno ha venduto niente si skippa il giro di buy
			context.setClienthandler(players.get(0));//setto il turno al primo giocatore
			game.setActualPlayer(0);//aggiorno il giocatore in game
			newState.doAction(context);//avvio lo stato
		}
		updateClientGame();
	}
	
	public void updateClientGame(){
		GameDTO gameDTO = this.game.toDto();
		for(ClientHandler ch : this.players){
			ch.sendToClient("GAMEDTO", (Object) gameDTO);
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

	@Override
	public void update(Observable o, Object arg) {//intestazioni: SETTINGS_ , INPUT_
		String[] msg = ((CommunicationObject) arg).getMsg().split("_");
		Object obj = ((CommunicationObject) arg).getObj();
		if(msg[0].equals("INPUT")){
			if(waitingInput && msg[1].equals(toResumeStr)){
				this.waitingInput = false;
				switch(toResumeStr){
				case "BONUSCARD":
					((ActionState) toResume).collectBONUSCARD((PermitsCardDTO) obj);
					break;
				case "FREECARD":
					((ActionState) toResume).collectFREECARD((PermitsCardDTO) obj);
					break;
				case "TWOTOKENS":
					((ActionState) toResume).collectTWOTOKENS((BonusTokenDTO[]) obj);
					break;
				case "ONETOKEN":
					((ActionState) toResume).collectONETOKEN((BonusTokenDTO[]) obj);
					break;
				case "TOBUY":
					((BuyItemState) toResume).execute((String) obj, true);
					break;
				case "TOSELL":
					((SellItemState) toResume).execute((ItemOnSale) obj, true);
					break;
				case "ACTION":
					((ActionState) toResume).execute((ActionDTO) obj);
					break;
				
				
				}
			}
			else
				logger.log(Level.SEVERE,"Input not expected");
		}
		else if(msg[0].equals("SETTINGS_")){
			//impostazioni o altro
		}
		}
		
	
	
	
	public void waitForInput(String ID, Object action){//da spostare qui le richieste al client dal clienthandler che riceverà soltante
		this.waitingInput = true;
		this.toResume = action;
		this.toResumeStr = ID;
	}
}
