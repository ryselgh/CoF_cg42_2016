package com.server.model.gamelogic;

import com.communication.market.OnSaleDTO;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.model.market.AssistantOnSale;
import com.server.model.market.OnSale;
import com.server.model.market.OnSaleInterface;
import com.server.model.market.PermitOnSale;
import com.server.model.market.PoliticsOnSale;

public class BuyItemState implements State{
	private Game game;
	private ClientHandler clienthandler;
	private GameHandler gamehandler;
	private Context context;
	public BuyItemState(){}
	
	public String getStateID(){
		return "BuyItemState";
	}
	
	@Override
	public void doAction(Context context) {
		this.context = context;
		context.setState(this);
		clienthandler = context.getClienthandler();
		gamehandler = context.getGamehandler();
		this.game = gamehandler.getGame();
		
		execute(null,false);
	}
	
	public void execute(String toBuyUID, boolean passed) {// uso passed perchè
															// l'oggetto può
															// essere null anche
															// se ricevuto
															// correttamente
		if (!passed) {
			if(game.getMarket().getObjectsOnSale().isEmpty()){
				clienthandler.sendToClient("TOBUYEMPTY", null);
				gamehandler.changeState(context);
				return;
			}
			clienthandler.sendToClient("TOBUY", game.getMarket().toDTO());
			gamehandler.waitForInput("TOBUY", this);
			return;
		}
		if (toBuyUID.equals("")) {
			clienthandler.sendToClient("TOBUYACK", "NullBuyReceived");
		} else {
			OnSale toBuy = DTOtoObj(toBuyUID);
			if (toBuy == null) {
				clienthandler.sendToClient("TOBUYNACK", "InvalidObjectReceived");
				clienthandler.sendToClient("TOBUY", game.getMarket().toDTO());
				gamehandler.waitForInput("TOBUY", this);
				return;
			} else {
				toBuy.obtain(game.getActualPlayer());
				game.getMarket().removeObj(toBuy.getUID());
				clienthandler.sendToClient("TOBUYACK", "BuyObjectReceived");
			}
		}
		gamehandler.changeState(context);
	}
	
	private OnSale DTOtoObj(String UID){
		for(OnSale os : game.getMarket().getObjectsOnSale()){
			String tmpUID = os.getUID();//porcoddio va bene questa, poi pulisco
			if(os instanceof AssistantOnSale){
				AssistantOnSale assOS = (AssistantOnSale) os;
				String assUID = assOS.getUID();
				if(assUID.equals(UID))
					return os;
			}
			else if(os instanceof PermitOnSale){
					if(((PermitOnSale)os).getUID().equals(UID))
						return os;
			}
			else if(os instanceof PoliticsOnSale){
				if(((PoliticsOnSale)os).getUID().equals(UID))
					return os;
			}
			
		}
		return null;
	}
	
	@Override
	public void restoreState() {
		// TODO Auto-generated method stub
		
	}

}
