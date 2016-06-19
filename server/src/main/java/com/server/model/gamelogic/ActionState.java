package com.server.model.gamelogic;
import java.rmi.RemoteException;
//gamehandler.waitfortwotokens(this (ActionState))
//da spostare la sendtoclient sul gamehandler
import java.util.ArrayList;
import java.util.Arrays;

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
import com.server.controller.RMISubscribed;
import com.server.model.board.Assistant;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.board.Emporium;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;

public class ActionState implements State {
	private Game game;
	private int mainCounter = 1;
	private int speedCounter = 1;
	private ClientHandler clienthandler;
	private GameHandler gamehandler;
	private Context context;
	private boolean RMI;
	private RMIClientControllerRemote remoteController;
	
	public ActionState(){
	}
	
	public String getStateID(){
		return "ActionState";
	}
	
	public void execute(ActionDTO actionDTO) throws RemoteException{
		boolean pass = false;
		Action action = null;
		if(RMI){
			actionDTO = remoteController.RMIgetAction(getAvailableActions());
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
		if(RMI)
			remoteController.RMIprintMsg("ActionAccepted");
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
		if(pass || !(mainCounter > 0 || speedCounter > 0))
			gamehandler.changeState(context);
		else{
			gamehandler.updateClientGame();
			this.execute(null);
		}
		
	}
	
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
				e.printStackTrace();
			}
		else
			clienthandler.sendToClient("StartTurn", draw.toDTO());
		try {
			gamehandler.updateClientGame();
			this.execute(null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	
	public void restoreState(){//riverginizza lo stato per essere usato da un altro giocatore senza creare una nuova istanza
		this.mainCounter = 1;
		this.speedCounter = 1;
		//il resto sta in context che viene cambiato dal gamehandler
	}
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
	private void decreaseCounter(Action action){
		if((action instanceof Build) || (action instanceof ObtainPermit) || (action instanceof SatisfyKing) || (action instanceof ShiftCouncilMain))
			mainCounter--;
		else if((action instanceof BuyAssistant) || (action instanceof BuyMainAction) || (action instanceof ChangeCards) || (action instanceof ShiftCouncilSpeed))
			speedCounter--;
			
	}
	
	private void collectBonus(Bonus b)// NOTA: AGISCE SUL GIOCATORE CHE STA
	// GIOCANDO IL TURNO
	{
		BonusToken[] btTmp;
		try {
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
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void collectONETOKEN(BonusTokenDTO[] chosen) throws RemoteException{
		BonusTokenDTO[] btTmp = getAvailableTokens();
		BonusTokenDTO chos = null;
		if (btTmp.length == 0)
			clienthandler.sendToClient("You have no available city tokens. Bonus discarded", null);//non è nack perchè non ho chiesto input al client
		else {
			if(RMI){
				chos = this.remoteController.RMIOneToken(btTmp);
			}
			else{
				if(chosen==null){
					clienthandler.sendToClient("ONETOKEN", btTmp);
					gamehandler.waitForInput("ONETOKEN", this);
					return;
				}
				chos = chosen[0];
			}
			BonusToken conv = new BonusToken(null);
			conv.setterFromDTO(chos);
			for (Bonus bo : conv.getBonus())
				collectBonus(bo);
			clienthandler.sendToClient("ONETOKENACK", null);
		}
	}
	
	public void collectTWOTOKENS(BonusTokenDTO[] chosen) throws RemoteException{
		BonusTokenDTO[] btTmp = getAvailableTokens();
		BonusToken[] converted;
		if (btTmp.length == 0)
			clienthandler.sendToClient("You have no available city tokens. Bonus discarded", null);
		else {
			if(RMI){
				chosen = this.remoteController.RMITwoTokens(btTmp);
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
	
	public void collectFREECARD(PermitsCardDTO chosen) throws RemoteException{
		boolean found = false;
		if(RMI)
			chosen = this.remoteController.RMIFreeCard();
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
					this.remoteController.RMIprintMsg("Invalid input permit. Try again");
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
	
	
	public void collectBONUSCARD(PermitsCardDTO chosen) throws RemoteException{
		ArrayList<PermitsCard> pcOwned = game.getActualPlayer().getPermits();
		if(RMI)
			chosen = this.remoteController.RMIBonusCard();
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
				this.remoteController.RMIprintMsg("Invalid input. Try again");
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
	
	private void playerDrawsPoliticsCard() {
		this.game.getActualPlayer().addPolitics(this.game.getMap().getPoliticsDeck().draw());
	}
	
	private BonusTokenDTO[] getAvailableTokens() {
		ArrayList<BonusTokenDTO> bts = new ArrayList<BonusTokenDTO>();
		for (City c : game.getMap().getCity())
			if (c.hasEmporium(game.getActualPlayer()) && c.getBonusToken() != null
			&& !Bonus.hasNobilityBonus(c.getBonusToken().getBonus()))
				bts.add(c.getBonusToken().toDTO());
		return bts.toArray(new BonusTokenDTO[0]);
	}
}
