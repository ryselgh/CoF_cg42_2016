package com.server.model.gamelogic;

import com.communication.ItemOnSale;
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
	public SellItemState(){}
	
	@Override
	public void doAction(Context context) {
		this.context = context;
		context.setState(this);
		clienthandler = context.getClienthandler();
		gamehandler = context.getGamehandler();
		this.game = gamehandler.getGame();
		
		execute(null,false);
	}
	
	public void execute(ItemOnSale toSell, boolean passed){//uso passed perchè l'oggetto può essere null anche se ricevuto correttamente
		if(!passed){
			clienthandler.sendToClient("TOSELL", null);
			gamehandler.waitForInput("TOSELL", this);
			return;
		}
		if(toSell.getObj() == null)
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
		gamehandler.updateClientGame();
		gamehandler.changeState(context);
	}
	
	private OnSale DTOtoObj(ItemOnSale itemOnSale){
		Object DTO = itemOnSale.getObj();
		int price = itemOnSale.getPrice();
		if (DTO instanceof AssistantOnSaleDTO)
			if(game.getActualPlayer().getAvailableAssistants().size()>0)
				return new AssistantOnSale(game.getActualPlayer(),game.getActualPlayer().getAvailableAssistants().get(0),price);
			else
				return null;
		else if (DTO instanceof PermitOnSaleDTO){
			PermitsCard perm = PermitsCard.fromDTO(((PermitOnSaleDTO) DTO).getPermit(), game.getActualPlayer());
			return new PermitOnSale(game.getActualPlayer(),perm, price);
		}
		else if (DTO instanceof PoliticsOnSale){
			PoliticsCard politic = PoliticsCard.fromDTO(((PoliticsOnSaleDTO) DTO).getPoliticsCard(),game.getActualPlayer());
			return new PoliticsOnSale(game.getActualPlayer(),politic, price);
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
