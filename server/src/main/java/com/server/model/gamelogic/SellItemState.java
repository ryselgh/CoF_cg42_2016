package com.server.model.gamelogic;

import java.util.ArrayList;
import java.util.UUID;
import com.communication.ItemOnSale;
import com.communication.RMIClientControllerRemote;
import com.communication.board.AssistantDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.market.AssistantOnSaleDTO;
import com.communication.market.OnSaleDTO;
import com.communication.market.PermitOnSaleDTO;
import com.communication.market.PoliticsOnSaleDTO;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.model.board.Assistant;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.model.market.AssistantOnSale;
import com.server.model.market.OnSale;
import com.server.model.market.OnSaleInterface;
import com.server.model.market.PermitOnSale;
import com.server.model.market.PoliticsOnSale;

public class SellItemState implements State{
	private Game game;
	private ClientHandler clienthandler;
	private GameHandler gamehandler;
	private Context context;
	private RMIClientControllerRemote remoteController;
	private boolean RMI;
	
	public SellItemState(){}
	
	public String getStateID(){
		return "SellItemState";
	}
	
	@Override
	public void doAction(Context context) {
		this.context = context;
		context.setState(this);
		this.clienthandler = context.getClienthandler();
		this.gamehandler = context.getGamehandler();
		this.RMI = context.isRMI();
		this.remoteController = context.getRemoteController();
		this.game = gamehandler.getGame();
		
		execute(null,false);
	}
	
	public void execute(ItemOnSale toSell, boolean passed){//uso passed perchè l'oggetto può essere null anche se ricevuto correttamente
		
		if(!passed){
			clienthandler.sendToClient("TOSELL", null);
			gamehandler.waitForInput("TOSELL", this);
			return;
		}
		if(toSell == null)
			clienthandler.sendToClient("TOSELLACK","NullSellReceived");
		else {
			OnSale soldObj = DTOtoObj(toSell);
			if(soldObj==null)
				clienthandler.sendToClient("TOSELLNACK","ObjectNotRecognized");
			else{
				game.getMarket().addObj(soldObj);
				clienthandler.sendToClient("TOSELLACK","ValidSellReceived");
			}
		}
		gamehandler.changeState(context);
	}
	
	private OnSale DTOtoObj(ItemOnSale itemOnSale){
		Object DTO = itemOnSale.getObj();
		int price = itemOnSale.getPrice();
		String UID = UUID.randomUUID().toString().substring(0, 15);
		if (DTO instanceof AssistantDTO){
			ArrayList<Assistant> assList = game.getActualPlayer().getAvailableAssistants();
			if(assList.size()>0){
				AssistantOnSale assOS = new AssistantOnSale(game.getActualPlayer(),game.getActualPlayer().getAvailableAssistants().get(0),price, UID);
				return assOS;
			}
			else
				return null;
		}
		else if (DTO instanceof PermitsCardDTO){
			PermitsCard perm = PermitsCard.fromDTO((PermitsCardDTO) DTO, game.getActualPlayer());
			PermitOnSale permOS = new PermitOnSale(game.getActualPlayer(),perm, price, UID);
			return permOS;
		}
		else if (DTO instanceof PoliticsCardDTO){
			PoliticsCard politic = PoliticsCard.fromDTO((PoliticsCardDTO) DTO,game.getActualPlayer());
			PoliticsOnSale polOS = new PoliticsOnSale(game.getActualPlayer(),politic, price, UID);
			return polOS;
		}
		
		return null;
	}
	
	
	
	private PoliticsCard DTOtoPolitic(PoliticsOnSaleDTO pDTO){
		for(PoliticsCard pc : game.getActualPlayer().getHand())
			if(pc.equalsDTO(pDTO.getPoliticsCard()))
				return pc;
		return null;
	}
	/*
	private void addToMarket(ItemOnSale item){
		Object item_obj = item.getObj();
		int item_price = item.getPrice();
		boolean found = false;
		if(item_obj instanceof PoliticsCard){
			OnSale polSale = new PoliticsOnSale(game.getActualPlayer(), 
					(PoliticsCard) item_obj, item_price);
			game.getMarket().addObj(polSale);
		}
		else if(item_obj instanceof PermitsCard){
			OnSale permSale = new PermitOnSale(game.getActualPlayer(),
					(PermitsCard) item_obj, item_price);
			game.getMarket().addObj(permSale);
		}
		else if(item_obj instanceof Assistant){
			OnSale assSale = new AssistantOnSale(game.getActualPlayer(),
					(Assistant) item_obj, item_price);
			game.getMarket().addObj(assSale);
		}
	}
	
	
	private boolean isValid(ItemOnSale item){//da convertire dto->obj
		Object item_obj = item.getObj();
		int item_price = item.getPrice();
		boolean found = false;
		if(item_obj instanceof PoliticsCard){
			PoliticsCard card = (PoliticsCard) item_obj;
			for(PoliticsCard pc : game.getActualPlayer().getHand())
				if(pc.equals(card))
					found=true;
			if(found==false){
				clienthandler.sendToClient("Invalid PoliticCard input", null);
				return false;
			}
		}
		else if(item_obj instanceof PermitsCard){
			PermitsCard card = (PermitsCard) item_obj;
			for(PermitsCard pc : game.getActualPlayer().getPermits())
				if(pc.equals(card))
					found=true;
			if(found==false){
				clienthandler.sendToClient("Invalid PermitsCard input", null);
				return false;
			}
		}
		else if(item_obj instanceof Assistant){
			if(game.getActualPlayer().getAvailableAssistants().size()==0){
				clienthandler.sendToClient("You have not enought assistants", null);
				return false;
			}
		}
		else{
			clienthandler.sendToClient("Object not recognized", null);
			return false;
		}
			
		return true;
	}
	
	*/
	@Override
	public void restoreState() {
		// nothing to be restored
		
	}

}
