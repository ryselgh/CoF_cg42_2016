package com.server.model.gamelogic;

import java.rmi.RemoteException;
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

// TODO: Auto-generated Javadoc
/**
 * The Class SellItemState.
 */
public class SellItemState implements State{
	
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
	 * Instantiates a new sell item state.
	 */
	public SellItemState(){}
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#getStateID()
	 */
	public String getStateID(){
		return "SellItemState";
	}
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#doAction(com.server.model.gamelogic.Context)
	 * set's up local variables and executes the action
	 */
	@Override
	public void doAction(Context context) {
		this.context = context;
		context.setState(this);
		this.clienthandler = context.getClienthandler();
		this.gamehandler = context.getGamehandler();
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
	 * @param toSell the item to sell
	 * @param passed true if protocol used is socket and the client input has already been received
	 * @throws RemoteException the remote exception
	 */
	public void execute(ItemOnSale toSell, boolean passed) throws RemoteException{//uso passed perchè l'oggetto può essere null anche se ricevuto correttamente
		if(RMI){
			toSell = this.remoteController.RMIToSell();
		}
		else if(!passed){
			clienthandler.sendToClient("TOSELL", null);
			gamehandler.waitForInput("TOSELL", this);
			return;
		}
		if(toSell == null){
			if(RMI)
				this.remoteController.RMIprintMsg("NullSellReceived");
			else
				clienthandler.sendToClient("TOSELLACK","NullSellReceived");
		}
		else {
			OnSale soldObj = DTOtoObj(toSell);
			if(soldObj==null)
				if(RMI)
					this.remoteController.RMIprintMsg("ObjectNotRecognized");
				else
					clienthandler.sendToClient("TOSELLNACK","ObjectNotRecognized");
			else{
				game.getMarket().addObj(soldObj);
				if(RMI)
					this.remoteController.RMIprintMsg("ValidSellReceived");
				else
					clienthandler.sendToClient("TOSELLACK","ValidSellReceived");
			}
		}
		gamehandler.changeState(context);
	}
	
	/**
	 * convert an ItemOnSale(DTO) to OnSale
	 *
	 * @param itemOnSale the item on sale
	 * @return the on sale
	 */
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
	
	
	
	/**
	 * converts a PoliticsOnSaleDTO to a PoliticsCard.
	 *
	 * @param pDTO the dto
	 * @return the politics card
	 */
	private PoliticsCard DTOtoPolitic(PoliticsOnSaleDTO pDTO){
		for(PoliticsCard pc : game.getActualPlayer().getHand())
			if(pc.equalsDTO(pDTO.getPoliticsCard()))
				return pc;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.server.model.gamelogic.State#restoreState()
	 */
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
