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
	private Context context;
	public BuyItemState(){}
	
	@Override
	public void doAction(Context context) {
		this.context = context;
		context.setState(this);
		clienthandler = context.getClienthandler();
		gamehandler = context.getGamehandler();
		this.game = gamehandler.getGame();
		
		execute(null,false);
	}
	
	public void execute(OnSaleDTO toBuyDTO, boolean passed){//uso passed perchè l'oggetto può essere null anche se ricevuto correttamente
		if(!passed){
			clienthandler.sendToClient("TOBUY", game.getMarket().toDTO());
			gamehandler.waitForInput("TOBUY", this);
			return;
		}
		if(toBuyDTO == null)
			clienthandler.sendToClient("TOBUYACK","NullBuyReceived");
		OnSale toBuy = DTOtoObj(toBuyDTO);
		if(toBuy==null){
			clienthandler.sendToClient("TOBUYNACK","InvalidObjectReceived");
			clienthandler.sendToClient("TOBUY", game.getMarket().toDTO());
			gamehandler.waitForInput("TOBUY", this);
			return;
		}
		else{
			toBuy.obtain(game.getActualPlayer());
			clienthandler.sendToClient("TOBUYACK","BuyObjectReceived");
		}
		gamehandler.updateClientGame();
		gamehandler.changeState(context);
	}
	
	private OnSale DTOtoObj(OnSaleDTO item){
		for(OnSale os : game.getMarket().getObjectsOnSale())
			if(os.equalsDTO(item))
				return os;
		return null;
	}
	
	@Override
	public void restoreState() {
		// TODO Auto-generated method stub
		
	}

}
