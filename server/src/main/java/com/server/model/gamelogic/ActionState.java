package com.server.model.gamelogic;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//gamehandler.waitfortwotokens(this (ActionState))
//da spostare la sendtoclient sul gamehandler
import java.util.ArrayList;

import java.util.Date;

import com.communication.RMIClientControllerRemote;
import com.communication.actions.ActionDTO;
import com.communication.actions.BuildDTO;
import com.communication.actions.BuyAssistantDTO;
import com.communication.actions.BuyMainActionDTO;
import com.communication.actions.ChangeCardsDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.PassDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;
import com.server.actions.Action;
import com.server.actions.ActionReturn;
import com.server.actions.Build;
import com.server.actions.BuyAssistant;
import com.server.actions.BuyMainAction;
import com.server.actions.ChangeCards;
import com.server.actions.ObtainPermit;
import com.server.actions.Pass;
import com.server.actions.SatisfyKing;
import com.server.actions.ShiftCouncilMain;
import com.server.actions.ShiftCouncilSpeed;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;

import com.server.model.board.Assistant;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;


/**
 * The Class ActionState.
 */
public class ActionState implements State {
	
	/** The game. */
	private Game game;
	
	/** The main counter. */
	private int mainCounter = 1;
	
	/** The speed counter. */
	private int speedCounter = 1;
	
	/** The clienthandler. */
	private ClientHandler clienthandler;
	
	/** The gamehandler. */
	private GameHandler gamehandler;
	
	/** The context. */
	private Context context;
	
	/** The rmi. */
	private boolean RMI;
	
	/** The remote controller. */
	private RMIClientControllerRemote remoteController;
	
	String startDate = "";
	
	/**
	 * Instantiates a new action state.
	 */
	public ActionState(){
		
	}
	
	private String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private boolean timeoutReached(String start, String current){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime1= LocalDateTime.parse(start, formatter);
		LocalDateTime dateTime2= LocalDateTime.parse(current, formatter);
		long diffInSeconds = java.time.Duration.between(dateTime1, dateTime2).toMillis();
		if(gamehandler.getTimerDelay()>0 && diffInSeconds>gamehandler.getTimerDelay()){
			gamehandler.RMIAbortTurn();
			return true;
		}
		return false;
		
	}
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#getStateID()
	 */
	public String getStateID(){
		return "ActionState";
	}
	
	/**
	 * Execute.
	 *
	 * @param actionDTO the action DTO
	 * @throws RemoteException the remote exception
	 */
	public void execute(ActionDTO actionDTO) throws RemoteException, SocketException{
		boolean pass = false;
		Action action = null;
		if(RMI){
			if(startDate.equals(""))
				startDate = getTime();
			actionDTO = remoteController.RMIgetAction(getAvailableActions());
			if(timeoutReached(startDate, getTime())){
				endTurn(true);
				return;
				}
		}
		else{
		if(actionDTO==null){
			clienthandler.sendToClient("AvailableActions", getAvailableActions());
			gamehandler.waitForInput("ACTION", this);
			return;
		}
		}
		action = DTOtoObj(actionDTO);
		action.setGame(game);
		if (!action.isValid()) {
			ActionReturn ret = action.execute();// gli errori li grabbo dall'actionreturn ritornato dall'execute
			if(RMI){
				remoteController.RMIprintMsg(ret.getError());
				this.execute(null);
				return;
			}
			else{
				clienthandler.sendToClient("ActionNotValid", ret.getError());
				gamehandler.waitForInput("ACTION", this);//non reinvio le azioni disponibili perchè non sono cambiate
				return;
			}
		}
		if(RMI){
			remoteController.RMIprintMsg("ActionAccepted");
			if(timeoutReached(startDate, getTime())){
				endTurn(true);
				return;
				}
		}
		else
			clienthandler.sendToClient("ActionAccepted", null);
		if(action instanceof Pass)//niente check perchè è abilitata solo quando mainaction già fatta
			pass=true;
		ActionReturn ret = action.execute();
		if (ret.getBonus() != null)
			for (Bonus b : ret.getBonus())
				collectBonus(b);
		decreaseCounter(action);
		if(action instanceof Build)
			if(checkWin())
				return;
		endTurn(pass);
		
	}
	
	private void endTurn(boolean pass){
		if(pass || !(mainCounter > 0 || speedCounter > 0))
			gamehandler.changeState(context);
		else{
			try {
				gamehandler.updateClientGame();
				this.execute(null);
			} catch (RemoteException | SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Check win.
	 *
	 * @return true, if successful
	 */
	private boolean checkWin() {
		int count = 0;
		for (City city : gamehandler.getGame().getMap().getCity())
			for (Emporium e : city.getEmporium())
				if (e != null) 
					if(e.getPlayer().getID().equals(clienthandler.getUserName()))
					count++;
		if (count >= 10) {
			this.gamehandler.endGame(clienthandler);
			return true;
		}
		return false;

	}
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#doAction(com.server.model.gamelogic.Context)
	 */
	public void doAction(Context context) {
		this.context = context;
		this.context.setState(this);
		this.clienthandler = context.getClienthandler();
		this.gamehandler = context.getGamehandler();
		this.remoteController = context.getRemoteController();
		this.game = gamehandler.getGame();
		this.RMI = context.isRMI();
		PoliticsCard draw = game.getMap().getPoliticsDeck().draw();//da aggiungere alla mano del giocatore
		this.game.getActualPlayer().addPolitics(draw);
		if(RMI)
			try {
				remoteController.RMIStartTurn(draw.toDTO());
			} catch (RemoteException e) {
				this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
				this.gamehandler.changeState(this.context);
			}
		else
			clienthandler.sendToClient("StartTurn", draw.toDTO());
		try {
			gamehandler.updateClientGame();
			this.execute(null);
		} catch (RemoteException | SocketException e) {
			this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
			this.gamehandler.changeState(this.context);
		}
	}
	
	/**
	 * DT oto obj.
	 *
	 * @param actDTO the act DTO
	 * @return the action
	 */
	private Action DTOtoObj(ActionDTO actDTO){
		Action act;
		if(actDTO instanceof BuildDTO){
			act = new Build(null,null);
			((Build)act).setterFromDTO((BuildDTO) actDTO, game.getActualPlayer(), game);
			return act;}
		else if(actDTO instanceof BuyAssistantDTO){
			act = new BuyAssistant();
			return act;}
		else if(actDTO instanceof BuyMainActionDTO){
			act = new BuyMainAction();
			return act;
		}
		else if(actDTO instanceof ChangeCardsDTO){
			act = new ChangeCards(((ChangeCardsDTO)actDTO).getBalconyIndex());
			return act;
		}
		else if(actDTO instanceof ObtainPermitDTO){
			act = new ObtainPermit(null,0,0);
			((ObtainPermit)act).setterFromDTO((ObtainPermitDTO) actDTO, game.getActualPlayer(), game);
			return act;}
		else if(actDTO instanceof PassDTO){
			act = new Pass();
			return act;
		}
		else if(actDTO instanceof SatisfyKingDTO){
			act = new SatisfyKing(null,null);
			((SatisfyKing)act).setterFromDTO((SatisfyKingDTO) actDTO, game.getActualPlayer(), game);
			return act;}
		else if(actDTO instanceof ShiftCouncilMainDTO){
			act = new ShiftCouncilMain(0,null);
			((ShiftCouncilMain)act).setterFromDTO((ShiftCouncilMainDTO) actDTO, game.getActualPlayer(), game);
			return act;}
		else if(actDTO instanceof ShiftCouncilSpeedDTO){
			act = new ShiftCouncilSpeed(0,null);
			((ShiftCouncilSpeed)act).setterFromDTO((ShiftCouncilSpeedDTO) actDTO, game.getActualPlayer(), game);
			return act;}
		else
			return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#restoreState()
	 */
	public void restoreState(){//riverginizza lo stato per essere usato da un altro giocatore senza creare una nuova istanza
		this.mainCounter = 1;
		this.speedCounter = 1;
		//il resto sta in context che viene cambiato dal gamehandler
	}
	
	/**
	 * Gets the available actions.
	 *
	 * @return the available actions
	 */
	private boolean[] getAvailableActions(){
		//order
		//main: obtainpermit[0], satisfyking[1], shiftcouncilmain[2], build[3]
		//speed: buyassistant[4], changecards[5], shiftcouncilspeed[6], buymainaction[7]
		//pass[8]
		//------GLOBAL CHECK------
		boolean[] toRet = new boolean[9];//4main + 4speed + pass
		if(mainCounter>0){
			for(int i=0;i<4;i++)
				toRet[i]=true;
			toRet[8]=false;//non puoi passare senza aver fatto la main
		}
		else
			toRet[8]=true;
		if(speedCounter>0)
			for(int i=4;i<8;i++)
				toRet[i]=true;
		//-------SINGLE CHECK----- //i check potrebbero essere più accurati, ma sarebbe una ripetizione del vero check che fa il server all'esecuzione dell'azione, questa è una prima scrematura
		//BUILD
		boolean availablePermit = false;
		for(PermitsCard pc : game.getActualPlayer().getPermits())
			if(!pc.isFaceDown())
				availablePermit = true;
		if(!availablePermit)
			toRet[3]=false;
		//BUY ASSISTANT
		if(game.getActualPlayer().getCoins()<3)
			toRet[4]=false;
		//BUY MAIN ACTION
		if(game.getActualPlayer().getAvailableAssistants().size()<3)
			toRet[7]=false;
		//CHANGE CARDS
		if(game.getActualPlayer().getAvailableAssistants().size()==0)
			toRet[5]=false;
		return toRet;
	}
	
	/**
	 * Decrease counter.
	 *
	 * @param action the action
	 */
	private void decreaseCounter(Action action){
		if((action instanceof Build) || (action instanceof ObtainPermit) || (action instanceof SatisfyKing) || (action instanceof ShiftCouncilMain))
			mainCounter--;
		else if((action instanceof BuyAssistant) || (action instanceof BuyMainAction) || (action instanceof ChangeCards) || (action instanceof ShiftCouncilSpeed))
			speedCounter--;
			
	}
	
	/**
	 * Collect bonus.
	 *
	 * @param b the b
	 */
	private void collectBonus(Bonus b)// NOTA: AGISCE SUL GIOCATORE CHE STA
	// GIOCANDO IL TURNO
	{
		switch (b.getType()) {
		case CARD:
			for (int i = 0; i < b.getQnt(); i++)
				playerDrawsPoliticsCard();
			break;
		case POINT:
			int oldScore = game.getActualPlayer().getScore();
			game.getActualPlayer().setScore(oldScore + b.getQnt());
			break;
		case COIN:
			int oldCoin = game.getActualPlayer().getCoins();
			game.getActualPlayer().setCoins(oldCoin + b.getQnt());
			break;
		case ASSISTANT:
			ArrayList<Assistant> ass = game.getMap().getAssistant(b.getQnt());
			game.getActualPlayer().addAssistant(ass);
			break;
		case NOBILITY:
			Bonus[] bGained = game.getMap().getNobilityTrack().advance(game.getActualPlayerIndex(), b.getQnt());
			if (bGained != null)
				for (Bonus bo : bGained)
					collectBonus(bo);
			break;
		case MAINACTION:
			mainCounter++;
			break;
		case TOKEN:
			collectONETOKEN(null);
			break;
		case TWOTOKENS:
			collectTWOTOKENS(null);
			break;
		case FREECARD: 
			collectFREECARD(null);
			break;
		case BONUSCARD:// input da convertire da DTO a oggetti
			collectBONUSCARD(null);
			break;

		}
	}
	
	/**
	 * Collect ONETOKEN.
	 *
	 * @param chosen the chosen
	 * @throws RemoteException the remote exception
	 */
	public void collectONETOKEN(BonusTokenDTO chosen){
		BonusTokenDTO[] btTmp = getAvailableTokens();
		if (btTmp.length == 0)
			clienthandler.sendToClient("You have no available city tokens. Bonus discarded", null);//non è nack perchè non ho chiesto input al client
		else {
			if(RMI){
				try {
					chosen = this.remoteController.RMIOneToken(btTmp);
				} catch (RemoteException e) {
					this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
					this.gamehandler.changeState(this.context);
				}
			}
			else{
				if(chosen==null){
					clienthandler.sendToClient("ONETOKEN", btTmp);
					gamehandler.waitForInput("ONETOKEN", this);
					return;
				}
			}
			BonusToken conv = new BonusToken(null);
			conv.setterFromDTO(chosen);
			for (Bonus bo : conv.getBonus())
				collectBonus(bo);
			clienthandler.sendToClient("ONETOKENACK", null);
		}
	}
	
	/**
	 * Collect TWOTOKENS.
	 *
	 * @param chosen the chosen
	 * @throws RemoteException the remote exception
	 */
	public void collectTWOTOKENS(BonusTokenDTO[] chosen){
		BonusTokenDTO[] btTmp = getAvailableTokens();
		BonusToken[] converted;
		if (btTmp.length == 0)
			clienthandler.sendToClient("You have no available city tokens. Bonus discarded", null);
		else {
			if(RMI){
				try {
					chosen = this.remoteController.RMITwoTokens(btTmp);
				} catch (RemoteException e) {
					this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
					this.gamehandler.changeState(this.context);
				}
			}
			else
				if(chosen==null){
					clienthandler.sendToClient("TWOTOKENS", btTmp);
					gamehandler.waitForInput("TWOTOKENS", this);
					return;
				}
			
			converted = new BonusToken[chosen.length];
			for(int i=0;i<chosen.length;i++)
				converted[i].setterFromDTO(chosen[i]);
			for(BonusToken bt : converted)
				for (Bonus bo : bt.getBonus())
				collectBonus(bo);
			clienthandler.sendToClient("TWOTOKENSACK", null);
		}
	}
	
	/**
	 * Collect FREECARD.
	 *
	 * @param chosen the chosen
	 * @throws RemoteException the remote exception
	 */
	public void collectFREECARD(PermitsCardDTO chosen) {
		boolean found = false;
		if(RMI){
			try {
				chosen = this.remoteController.RMIFreeCard();
			} catch (RemoteException e) {
				this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
				this.gamehandler.changeState(this.context);
			}
			if(timeoutReached(startDate, getTime()))
				return;
		}
		else if(chosen==null){
				clienthandler.sendToClient("FREECARD", null);
				gamehandler.waitForInput("FREECARD", this);
				return;
			}
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++) {
					PermitsCard temp = game.getMap().getPermitsDeck(i).getSlot(j, false);
					if (temp.equalsDTO(chosen)) {
						found = true;
						game.getActualPlayer().addPermits(game.getMap().getPermitsDeck(i).getSlot(j, true));
					}
				}
			if(!found){
				if(RMI){
					try {
						this.remoteController.RMIprintMsg("Invalid input permit. Try again");
					} catch (RemoteException e) {
						this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
						this.gamehandler.changeState(this.context);
					}
					this.collectFREECARD(null);
					return;
				}
				else{
					clienthandler.sendToClient("FREECARDNACK", "Invalid input permit. Try again");
					clienthandler.sendToClient("FREECARD", null);
					gamehandler.waitForInput("FREECARD", this);
				}
			}
			else
				if(!RMI)//non (ancora?) implementato per rmi perchè credo sia inutile
					clienthandler.sendToClient("FREECARDACK", null);
		}
	
	
	/**
	 * Collect BONUSCARD.
	 *
	 * @param chosen the chosen
	 * @throws RemoteException the remote exception
	 */
	public void collectBONUSCARD(PermitsCardDTO chosen){
		ArrayList<PermitsCard> pcOwned = game.getActualPlayer().getPermits();
		if(RMI){
			try {
				chosen = this.remoteController.RMIBonusCard();
			} catch (RemoteException e) {
				this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
				this.gamehandler.changeState(this.context);
			}
			if(timeoutReached(startDate, getTime()))
				return;
		}
		else if(chosen==null){
			clienthandler.sendToClient("BONUSCARD", null);
			gamehandler.waitForInput("BONUSCARD",this);
			return;
		}
		PermitsCard temp=null;
		for(PermitsCard pc : pcOwned)
			if(pc.equalsDTO(chosen))
				temp = pc;
		if(temp==null){
			if(RMI){
				try {
					this.remoteController.RMIprintMsg("Invalid input. Try again");
				} catch (RemoteException e) {
					this.gamehandler.getLobby().disconnectFromGame(this.context.getClienthandler(), this.gamehandler);
					this.gamehandler.changeState(this.context);
				}
				this.collectBONUSCARD(null);
			}
			else{
				clienthandler.sendToClient("BONUSCARDNACK", "Invalid input. Try again");
				clienthandler.sendToClient("BONUSCARD", null);
				gamehandler.waitForInput("BONUSCARD", this);
			}
			return;
		}
		else{
		for (Bonus bo : temp.getBonus())
			collectBonus(bo);
		if(!RMI)//anche questo non implementato per rmi perchè di dubbia utilità
			clienthandler.sendToClient("BONUSCARDACK", null);
		}
	}
	
	/**
	 * Player draws politics card.
	 */
	private void playerDrawsPoliticsCard() {
		this.game.getActualPlayer().addPolitics(this.game.getMap().getPoliticsDeck().draw());
	}
	
	/**
	 * Gets the available tokens.
	 *
	 * @return the available tokens
	 */
	private BonusTokenDTO[] getAvailableTokens() {
		ArrayList<BonusTokenDTO> bts = new ArrayList<BonusTokenDTO>();
		for (City c : game.getMap().getCity())
			if (c.hasEmporium(game.getActualPlayer()) && c.getBonusToken() != null
			&& !Bonus.hasNobilityBonus(c.getBonusToken().getBonus()))
				bts.add(c.getBonusToken().toDTO());
		return bts.toArray(new BonusTokenDTO[0]);
	}
}
