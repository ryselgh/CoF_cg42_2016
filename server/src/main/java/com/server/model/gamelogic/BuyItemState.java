package com.server.model.gamelogic;

import java.rmi.RemoteException;

import com.communication.RMIClientControllerRemote;
import com.communication.market.OnSaleDTO;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.model.market.AssistantOnSale;
import com.server.model.market.OnSale;
import com.server.model.market.OnSaleInterface;
import com.server.model.market.PermitOnSale;
import com.server.model.market.PoliticsOnSale;

// TODO: Auto-generated Javadoc
/**
 * The Class BuyItemState.
 */
public class BuyItemState implements State{
	
	/** The game. */
	private Game game;
	
	/** The clienthandler. */
	private ClientHandler clienthandler;
	
	/** The gamehandler. */
	private GameHandler gamehandler;
	
	/** The context. */
	private Context context;
	
	/** The remote controller. */
	private RMIClientControllerRemote remoteController;
	
	/** The rmi. */
	private boolean RMI;
	
	
	/**
	 * Instantiates a new buy item state.
	 */
	public BuyItemState(){}
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#getStateID()
	 */
	public String getStateID(){
		return "BuyItemState";
	}
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#doAction(com.server.model.gamelogic.Context)
	 * set's up local variables and executes the action
	 */
	@Override
	public void doAction(Context context) {
		this.context = context;
		context.setState(this);
		clienthandler = context.getClienthandler();
		gamehandler = context.getGamehandler();
		this.RMI = context.isRMI();
		this.remoteController = context.getRemoteController();
		this.game = gamehandler.getGame();
		
		try {
			execute(null,false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Execute.
	 *
	 * @param toBuyUID the item to buy UID
	 * @param passed true if protocol used is socket and the client input has already been received
	 * @throws RemoteException the remote exception
	 */
	public void execute(String toBuyUID, boolean passed) throws RemoteException {// uso passed perchè l'oggetto può essere null anche se ricevuto correttamente
		if (!passed || RMI) {
			if(game.getMarket().getObjectsOnSale().isEmpty()){
				if(RMI)
					this.remoteController.RMIprintMsg("Every item has been bought. Turn skipped");
				else
					clienthandler.sendToClient("TOBUYEMPTY", null);
				gamehandler.changeState(context);
				return;
			}
			if(RMI)
				toBuyUID = this.remoteController.RMIToBuy();
			else{
				clienthandler.sendToClient("TOBUY", game.getMarket().toDTO());
				gamehandler.waitForInput("TOBUY", this);
				return;
			}
		}
		if (toBuyUID.equals("")) {
			if(RMI)
				this.remoteController.RMIprintMsg("NullBuyReceived");
			else
				clienthandler.sendToClient("TOBUYACK", "NullBuyReceived");
		} 
		else {
			OnSale toBuy = DTOtoObj(toBuyUID);
			if (toBuy == null) {
				if(RMI)
					this.execute(null, false);
				else{
					clienthandler.sendToClient("TOBUYNACK", "InvalidObjectReceived");
					clienthandler.sendToClient("TOBUY", game.getMarket().toDTO());
					gamehandler.waitForInput("TOBUY", this);
				}
				return;
			} 
			else {
				toBuy.obtain(game.getActualPlayer());
				game.getMarket().removeObj(toBuy.getUID());
				if(RMI)
					this.remoteController.RMIprintMsg("BuyObjectReceived");
				else
					clienthandler.sendToClient("TOBUYACK", "BuyObjectReceived");
			}
		}
		gamehandler.changeState(context);
	}
	
	/**
	 * Converts the UID back into OnSale object.
	 *
	 * @param UID the uid
	 * @return the on sale
	 */
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
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#restoreState()
	 */
	@Override
	public void restoreState() {
		// TODO Auto-generated method stub
		
	}

}
