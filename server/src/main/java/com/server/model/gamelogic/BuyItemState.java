package com.server.model.gamelogic;

import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.model.market.OnSaleInterface;

public class BuyItemState implements State{
	private Game game;
	private ClientHandler clienthandler;
	private GameHandler gamehandler;
	
	public BuyItemState(){}
	
	@Override
	public void doAction(Context context) {
		context.setState(this);
		clienthandler = context.getClienthandler();
		gamehandler = context.getGamehandler();
		this.game = gamehandler.getGame();
		
		OnSaleInterface toBuy = clienthandler.getItemToBuy(game.getMarket().getObjectsOnSale());
		if(toBuy == null)
			clienthandler.sendToClient("NullBuyReceived", null);
		else if(isValid(toBuy)){
				clienthandler.sendToClient("ValidBuyReceived", null);
			}
		gamehandler.changeState(context);
		
	}

	private boolean isValid(OnSaleInterface item){
		
		return true;
	}
	@Override
	public void restoreState() {
		// TODO Auto-generated method stub
		
	}

}
