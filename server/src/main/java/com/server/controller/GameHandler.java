package com.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
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

// TODO: Auto-generated Javadoc
/**
 * The Class GameHandler.
 */
public class GameHandler extends Observable implements Runnable, Observer{
	
	/** The players. */
	private ArrayList<ClientHandler> players;
	
	/** The game. */
	private Game game;
	
	/** The raw map. */
	private String rawMap;
	
	/** The context. */
	private Context context;
	
	/** The to resume. */
	private Object toResume;
	
	/** The to resume str. */
	private String toResumeStr;
	
	/** The waiting input. */
	private boolean waitingInput = false;
	
	/** The logger. */
	private Logger logger;
	
	/** The lobby. */
	private Lobby lobby;
	
	/** The room. */
	private Room room;
	
	/** The skipped turn. */
	private ClientHandler skippedTurn;
	
	/** The progressive counter. */
	private int progressiveCounter=0;//permette al timer di identificare il turno (per non skippare lo stesso giocatore al turno successivo)
	
	/** The rmi. */
	private boolean RMI;
	
	/** The remote controllers. */
	private ArrayList<RMISubscribed> remoteControllers;
	
	/** used to take trace of the index of the list containing the shuffled order of turns for the market buy cycle*/
	private int orderCounter=0;
	
	private ArrayList<Integer> order;
	
	/** millisecond before the turn skip 0=disabled*/
	private int timerDelay;
	
	
	
	/**
	 * Instantiates a new game handler.
	 *
	 * @param pl the players of the game
	 * @param defaultMap the default map
	 * @param map the map xml
	 * @param lobby the lobby instance
	 * @param room the room of the game
	 * @param RMI the bool rmi
	 * @param remoteControllers the remote controllers
	 */
	public GameHandler(ArrayList<ClientHandler> pl, String mapName, String map, Lobby lobby, Room room, boolean RMI, ArrayList<RMISubscribed> remoteControllers, int timerDelay){
		this.timerDelay = timerDelay;
		this.remoteControllers = remoteControllers;
		this.RMI = RMI;
		this.players = pl;
		this.lobby = lobby;
		this.room = room;
		this.game = new Game(players.size(),mapName,map, clientNames(players));
		this.order = new ArrayList<Integer>();
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

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * sets up the context and starts the first state
	 */
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
	
	/**
	 * Client names.
	 *
	 * @param players the players
	 * @return the names of the players
	 */
	private String[] clientNames(ArrayList<ClientHandler> players){
		String[] ret = new String[players.size()];
		for(int i=0;i<players.size();i++)
			ret[i]=players.get(i).getUserName();
		return ret;
	}
	
	/**
	 * End game.
	 *
	 * @param winner the winner of the game
	 */
	public void endGame(ClientHandler winner){
		lobby.endGame(room, this, winner);
	}
	
	/**
	 * Changes the state or the current player.
	 *
	 * @param context the context
	 */
	public void changeState(Context context){
		try {
			updateClientGame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		progressiveCounter++;
		ClientHandler client = context.getClienthandler();
		
		boolean lastPl = client.isEquals(players.get(players.size()-1));
		boolean buyState = context.getState().getStateID().equals("BuyItemState");
		boolean lastBuyState = this.orderCounter == this.players.size();
		
		if((lastPl&&!buyState) || (lastBuyState&&buyState)){ //se è l'ultimo del giro
			State newState = nextState(context.getState());//ricavo lo stato successivo
			int nextIndex=0;//nella parte qua sotto potrebbe essere modificato
			if(newState.getStateID().equals("BuyItemState")){
				if(this.game.getMarket().getObjectsOnSale().size()==0)
					newState = nextState(newState);//se nessuno ha venduto niente si skippa il giro di buy
				else{
					order = new ArrayList<Integer>();//pulisco
					for(int i=0;i<this.players.size();i++)
						order.add(i);
						Collections.shuffle(order);
					this.orderCounter=0;
					nextIndex = order.get(orderCounter);
					game.setMarketCurrentPlayer(this.players.get(nextIndex).getUserName());
					try {
						updateClientGame();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					orderCounter++;
				}
			}
				
			context.setClienthandler(players.get(nextIndex));//setto il turno al primo giocatore
			if(RMI)
				context.setRemoteController(RMISubscribed.getRemoteController(remoteControllers, players.get(0)));
			game.setActualPlayer(nextIndex);//aggiorno il giocatore in game
			if(!players.get(nextIndex).isActive()){
				changeState(context);
				return;
			}
			newState.doAction(context);//avvio lo stato
		}
		else{//non è l'ultimo del giro
			context.getState().restoreState();//refresho lo stato
			ClientHandler nextPl;
			int nextIndex ;
			if(context.getState().getStateID().equals("BuyItemState")){
				nextIndex = this.order.get(this.orderCounter);
				nextPl = players.get(nextIndex);
				game.setMarketCurrentPlayer(nextPl.getUserName());
				try {
					updateClientGame();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				orderCounter++;
			}
			else{
				nextPl = nextPlayer(client);
				nextIndex = game.getActualPlayerIndex() +1;
			}
			context.setClienthandler(nextPl);//aggiorno il riferimento al client
			if(RMI)
				context.setRemoteController(RMISubscribed.getRemoteController(remoteControllers, nextPl));
			game.setActualPlayer(nextIndex);//aggiorno il giocatore in game
			if(!nextPl.isActive()){//se il giocatore è disconnesso skippa il turno
				changeState(context);
				return;
			}
			context.getState().doAction(context);//avvio lo stato
		}
	}
	
	/**
	 * Update client game.
	 *
	 * @throws RemoteException the remote exception
	 */
	public void updateClientGame() throws RemoteException{
		GameDTO gameDTO = this.game.toDto();
		for(ClientHandler ch : this.players){
			if(RMI)
				RMISubscribed.getRemoteController(this.remoteControllers, ch).RMIupdateGame(gameDTO);
			else
				ch.sendToClient("GAMEDTO", (Object) gameDTO);
		}
	}
	
	/**
	 * Broadcast announce.
	 *
	 * @param msg the msg of the announce
	 * @param obj the obj of the announce
	 */
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
	
	/**
	 * Next player. It doesn't handle the 'last player event' because its handled in void changeState
	 *
	 * @param pl the current player
	 * @return the next player
	 */
	public ClientHandler nextPlayer(ClientHandler pl){
		for(int i=0;i<players.size();i++)
			if(players.get(i).isEquals(pl))
				return players.get(i+1);//viene chiamata dopo il check su pl che non deve essere l'ultimo, quindi questa funz non dovrebbe mai ritornare null
		return null;
	}
	
	/**
	 * Next state.
	 *
	 * @param state the current state
	 * @return the next state
	 */
	public State nextState(State state)
	{
		if(state instanceof ActionState)
			return new SellItemState();
		else if (state instanceof SellItemState)
			return new BuyItemState();
		else
			return new ActionState();
	}
	
	/**
	 * Draw card.
	 *
	 * @return the politics card just drawn
	 */
	public PoliticsCard drawCard(){
		return game.getMap().getPoliticsDeck().draw();
	}
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame(){
		return this.game;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
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
					((ActionState) toResume).collectONETOKEN((BonusTokenDTO) obj);
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
		
	
	
	
	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(ArrayList<ClientHandler> players) {
		this.players = players;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<ClientHandler> getPlayers() {
		return players;
	}

	/**
	 * Wait for input.
	 *
	 * @param ID
	 *            the id of the state to resume
	 * @param action
	 *            the action to resume
	 */
	public void waitForInput(String ID, Object action) {// da spostare qui le
														// richieste al client
														// dal clienthandler che
														// riceverà soltante
		this.waitingInput = true;
		this.toResume = action;
		this.toResumeStr = ID;
		if (this.timerDelay > 0) {
			InputTimer timer = new InputTimer(this.context.getClienthandler().getUserName(), progressiveCounter,timerDelay);
			timer.addObserver(this);
			Thread timerThread = new Thread(timer);
			timerThread.start();
		}
	}
}
