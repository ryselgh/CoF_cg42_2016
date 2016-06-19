package com.server.controller;

import java.rmi.RemoteException;
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
	private ArrayList<ClientHandler> players;
	private Game game;
	private String rawMap;
	private Context context;
	private Object toResume;
	private String toResumeStr;
	private boolean waitingInput = false;
	private Logger logger;
	private Lobby lobby;
	private Room room;
	private ClientHandler skippedTurn;
	private int progressiveCounter=0;//permette al timer di identificare il turno (per non skippare lo stesso giocatore al turno successivo)
	private boolean RMI;
	private ArrayList<RMISubscribed> remoteControllers;
	
	
	public GameHandler(ArrayList<ClientHandler> pl, boolean defaultMap, String map, Lobby lobby, Room room, boolean RMI, ArrayList<RMISubscribed> remoteControllers){
		this.remoteControllers = remoteControllers;
		this.RMI = RMI;
		this.players = pl;
		this.lobby = lobby;
		this.room = room;
		this.game = new Game(players.size(),defaultMap,map, clientNames(players));
		GameDTO gameDTO = this.game.toDto();
		if(RMI)
			for(RMISubscribed RMIs : this.remoteControllers )
				try {
					RMIs.getRemContr().RMIupdateGame(gameDTO);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		else
			for(ClientHandler ch : pl){
				ch.addObserver(this);
				ch.sendToClient("STARTGAME", gameDTO);
			}
	}

	@Override
	public void run() {
		if(!RMI)
			for(ClientHandler ch : players)
				this.addObserver(ch);
		this.context = new Context();
		context.setClienthandler(players.get(0));
		context.setGamehandler(this);
		context.setRMI(this.RMI);
		context.setRemoteController(RMISubscribed.getRemoteController(remoteControllers, players.get(0)));
		ActionState actState = new ActionState();
		actState.doAction(context);//avvio il primo stato di azione per il primo giocatore
	}
	
	private String[] clientNames(ArrayList<ClientHandler> players){
		String[] ret = new String[players.size()];
		for(int i=0;i<players.size();i++)
			ret[i]=players.get(i).getUserName();
		return ret;
	}
	
	public void endGame(ClientHandler winner){
		lobby.endGame(room, this, winner);
	}
	
	public void changeState(Context context){
		try {
			updateClientGame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		progressiveCounter++;
		ClientHandler client = context.getClienthandler();
		if(!client.isEquals(players.get(players.size()-1))){//se non è l'ultimo del giro
			context.getState().restoreState();//refresho lo stato
			ClientHandler nextPl = nextPlayer(client);
			context.setClienthandler(nextPl);//aggiorno il riferimento al client
			context.setRemoteController(RMISubscribed.getRemoteController(remoteControllers, nextPl));
			game.setActualPlayer(game.getActualPlayerIndex() +1);//aggiorno il giocatore in game
			if(!nextPl.isActive()){//se il giocatore è disconnesso skippa il turno
				changeState(context);
				return;
			}
			context.getState().doAction(context);//avvio lo stato
		}
		else{//è l'ultimo del giro
			State newState = nextState(context.getState());//ricavo lo stato successivo
			if(newState.getStateID().equals("BuyItemState") && this.game.getMarket().getObjectsOnSale().size()==0)
				newState = nextState(newState);//se nessuno ha venduto niente si skippa il giro di buy
			context.setClienthandler(players.get(0));//setto il turno al primo giocatore
			context.setRemoteController(RMISubscribed.getRemoteController(remoteControllers, players.get(0)));
			game.setActualPlayer(0);//aggiorno il giocatore in game
			if(!players.get(0).isActive()){
				changeState(context);
				return;
			}
			newState.doAction(context);//avvio lo stato
		}
	}
	
	public void updateClientGame() throws RemoteException{
		GameDTO gameDTO = this.game.toDto();
		for(ClientHandler ch : this.players){
			if(RMI)
				RMISubscribed.getRemoteController(this.remoteControllers, ch).RMIupdateGame(gameDTO);
			else
				ch.sendToClient("GAMEDTO", (Object) gameDTO);
		}
	}
	
	public void broadcastAnnounce(String msg, String obj){
		for(ClientHandler ch : this.players){
			if(RMI)
				try {
					RMISubscribed.getRemoteController(this.remoteControllers, ch).RMIprintMsg(obj);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				ch.sendToClient(msg, obj);
		}
	}
	public ClientHandler nextPlayer(ClientHandler pl){
		for(int i=0;i<players.size();i++)
			if(players.get(i).isEquals(pl))
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
	public synchronized void update(Observable o, Object arg) {//synchronyzed perchè il thread di InputTimer e ClientHandler si potrebbero pestare i piedi
		if(o instanceof InputTimer){
			TimeoutInfo infos = (TimeoutInfo) arg;
			String skipName = infos.getClientName();
			int skipCount = infos.getProgressiveCounter();
			if(this.context.getClienthandler().getUserName().equals(skipName) && this.progressiveCounter==skipCount){
				skippedTurn = this.context.getClienthandler();
				this.broadcastAnnounce("TIMEOUT", this.context.getClienthandler().getUserName());
				this.changeState(this.context);
			}
			return;			
		}
		else{ 
			if(skippedTurn != null && skippedTurn.isEquals(((ClientHandler) o )))
				return;//mossa del giocatore che ha saltato il turno
			else
				skippedTurn = null;
		}
		String[] msg = ((CommunicationObject) arg).getMsg().split("_");
		Object obj = ((CommunicationObject) arg).getObj();
		if(msg[0].equals("INPUT")){
			if(waitingInput && msg[1].equals(toResumeStr)){
				this.waitingInput = false;
				try {
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
			 catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
				}
				}
			else
				logger.log(Level.SEVERE,"Input not expected");
		}
		else if(msg[0].equals("SETTINGS_")){
			//impostazioni o altro
		}
		}
		
	
	
	
	public void setPlayers(ArrayList<ClientHandler> players) {
		this.players = players;
	}

	public ArrayList<ClientHandler> getPlayers() {
		return players;
	}

	public void waitForInput(String ID, Object action){//da spostare qui le richieste al client dal clienthandler che riceverà soltante
		this.waitingInput = true;
		this.toResume = action;
		this.toResumeStr = ID;
		InputTimer timer = new InputTimer(this.context.getClienthandler().getUserName(), progressiveCounter);
		timer.addObserver(this);
		Thread timerThread = new Thread(timer);
		timerThread.start();
	}
}
