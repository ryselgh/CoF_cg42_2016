package com.server.model.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;

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
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;

public class ActionState implements State {
	private Game game;
	private int mainCounter = 1;
	private int speedCounter = 1;
	private ClientHandler clienthandler;
	private GameHandler gamehandler;

	public ActionState(){}
	
	public void doAction(Context context) {
		context.setState(this);
		clienthandler = context.getClienthandler();
		gamehandler = context.getGamehandler();
		this.game = gamehandler.getGame();
		PoliticsCard draw = game.getMap().getPoliticsDeck().draw();
		clienthandler.sendToClient("StartTurn", draw);
		while (mainCounter > 0 || speedCounter > 0) {
			clienthandler.sendToClient("AvailableActions", getAvailableActions());
			boolean valid = false;
			ActionDTO actionDTO = null;
			Action action = null;
			while (!valid) {
				actionDTO = clienthandler.getAction();
				action = DTOtoObj(actionDTO);
				action.setGame(game);
				if (action.isValid()) {
					valid = true;
					clienthandler.sendToClient("ActionAccepted", null);
				} else {
					ActionReturn ret = action.execute();// gli errori li grabbo dall'actionreturn ritornato dall'execute
					clienthandler.sendToClient("ActionNotValid", ret.getError());
				}
			}
			if(action instanceof Pass)//niente check perchè è abilitata solo quando mainaction già fatta
				break;
			ActionReturn ret = action.execute();
			if (ret.getBonus() != null)
				for (Bonus b : ret.getBonus())
					collectBonus(b);
			decreaseCounter(action);
		}
		//QUI DOVREBBE ESSERE FINITO IL TURNO DI UN GIOCATORE
		gamehandler.changeState(context);
	}
	
	private Action DTOtoObj(ActionDTO actDTO){
		Action act;
		if(actDTO instanceof BuildDTO){
			act = new Build(null,null);
			act.setterFromDTO((BuildDTO) actDTO, game.getActualPlayer(), game);
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
			act.setterFromDTO((ObtainPermitDTO) actDTO, game.getActualPlayer(), game);
			return act;}
		else if(actDTO instanceof PassDTO){
			act = new Pass();
			return act;
		}
		else if(actDTO instanceof SatisfyKingDTO){
			act = new SatisfyKing(null,null);
			act.setterFromDTO((SatisfyKingDTO) actDTO, game.getActualPlayer(), game);
			return null;}
		else if(actDTO instanceof ShiftCouncilMainDTO){
			act = new ShiftCouncilMain(0,null);
			act.setterFromDTO((ShiftCouncilMainDTO) actDTO, game.getActualPlayer(), game);
			return null;}
		else if(actDTO instanceof ShiftCouncilSpeedDTO){
			act = new ShiftCouncilSpeed(0,null);
			act.setterFromDTO((ShiftCouncilSpeedDTO) actDTO, game.getActualPlayer(), game);
			return null;}
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
		//main: build[0], obtainpermit[1], satisfyking[2], shiftcouncilmain[3]
		//speed: buyassistant[4], buymainaction[5], changecards[6], shiftcouncilspeed[7]
		//pass[8]
		//------GLOBAL CHECK------
		boolean[] toRet = new boolean[9];//4main + 4speed + pass
		if(mainCounter>0){
			for(int i=0;i<4;i++)
				toRet[i]=true;
			toRet[8]=false;//non puoi passare senza aver fatto la main
		}
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
			toRet[0]=false;
		//BUY ASSISTANT
		if(game.getActualPlayer().getCoins()<3)
			toRet[4]=false;
		//BUY MAIN ACTION
		if(game.getActualPlayer().getAvailableAssistants().size()<3)
			toRet[5]=false;
		//CHANGE CARDS
		if(game.getActualPlayer().getAvailableAssistants().size()==0)
			toRet[6]=false;
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
			btTmp = getAvailableTokens();
			if (btTmp.length == 0)
				clienthandler.sendToClient("You have no available city tokens. Bonus discarded", null);
			else {
				BonusToken[] chosen = clienthandler.getBonusToken(btTmp);
				for (Bonus bo : chosen[0].getBonus())
					collectBonus(bo);
			}
			break;
		case TWOTOKENS:
			btTmp = getAvailableTokens();
			if (btTmp.length == 0)
				clienthandler.sendToClient("You have no available city tokens. Bonus discarded", null);
			else {
				BonusToken[] chosen = clienthandler.getBonusToken(btTmp);
				for(BonusToken bt : chosen)
					for (Bonus bo : bt.getBonus())
					collectBonus(bo);
			}
			break;
		case FREECARD: 
			boolean found = false;
			while (!found) {
				PermitsCardDTO chosen = clienthandler.getFreePermitsCard();
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 2; j++) {
						PermitsCard temp = game.getMap().getPermitsDeck(i).getSlot(j, false);
						if (temp.equalsDTO(chosen)) {
							found = true;
							game.getActualPlayer().addPermits(game.getMap().getPermitsDeck(i).getSlot(j, true));
						}
					}
				if(!found)
					clienthandler.sendToClient("Invalid input permit. Try again", null);
			}
			break;
		case BONUSCARD:// input da convertire da DTO a oggetti
			ArrayList<PermitsCard> pcOwned = game.getActualPlayer().getPermits();
			PermitsCardDTO chosen = clienthandler.getOwnedPermitsCard();
			PermitsCard temp=null;
			for(PermitsCard pc : pcOwned)
				if(pc.equalsDTO(chosen))
					temp = pc;
			if(temp==null)
				;//errore di conversione dto->ogg
			else{
			for (Bonus bo : temp.getBonus())
				collectBonus(bo);
			}
			break;

		}
	}
	
	private void playerDrawsPoliticsCard() {
		this.game.getActualPlayer().addPolitics(this.game.getMap().getPoliticsDeck().draw());
	}
	
	private BonusToken[] getAvailableTokens() {
		ArrayList<BonusToken> bts = new ArrayList<BonusToken>();
		for (City c : game.getMap().getCity())
			if (c.hasEmporium(game.getActualPlayer()) && c.getBonusToken() != null
			&& !Bonus.hasNobilityBonus(c.getBonusToken().getBonus()))
				bts.add(c.getBonusToken());
		return bts.toArray(new BonusToken[0]);
	}
}
