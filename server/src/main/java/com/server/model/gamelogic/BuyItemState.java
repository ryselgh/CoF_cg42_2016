package com.server.model.gamelogic;

import com.communication.market.OnSaleDTO;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.model.market.OnSale;
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
		
		OnSaleDTO toBuyDTO = clienthandler.getItemToBuy(game.getMarket().toDTO());
		if(toBuyDTO == null)
			clienthandler.sendToClient("ItemToBuyNack", "Null object received");
		OnSale toBuy = DTOtoObj(toBuyDTO);
		if(toBuy==null)
			clienthandler.sendToClient("ItemToBuyNack", "Invalid object received");
		else{
			toBuy.obtain(game.getActualPlayer());
			clienthandler.sendToClient("ItemToBuyAck", null);
		}
		gamehandler.changeState(context);
		
	}
	
	private OnSale DTOtoObj(OnSaleDTO item){
		for(OnSale os : game.getMarket().getObjectsOnSale())
			if(os.equals(item))
				return os;
		return null;
	}
	
	@Override
	public void restoreState() {
		// TODO Auto-generated method stub
		
	}

}
