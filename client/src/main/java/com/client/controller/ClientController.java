package com.client.controller;

import java.util.ArrayList;

import com.client.ClientObservable;
import com.client.ClientObserver;
import com.client.view.ClientCLI;
import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;

public class ClientController extends ClientObservable implements ClientObserver{

	private ClientCLI cli;
	private GameDTO game;
	private SocketConnection connection;
	
	private int playerID; // <------------------------------------ PROVVISORIO, serve comunque un identificativo

	public ClientController(){
		cli = new ClientCLI(this);
		cli.attachObserver(this);
	}
	
	public void run(){
		connection = new SocketConnection();
		connection.run();
		connection.attachObserver(this);
	}

	@Override
	public <C> void update(C change) {
		
		CommunicationObject in = (CommunicationObject) change;
		String cmd = in.getMsg();
		Object obj = in.getObj();
		
		if (cmd.contains("lobby_msg-")) {// messaggio della lobby
						cmd = cmd.substring("lobby_msg-".length());
						// printa cmd che è un messaggio
					} else {// messaggio di gioco
						switch (cmd) {
						
						case "InsertNickname":
							cli.printMsg("Insert your nickname:");
							String nick = cli.getMsg();
							while(!connection.isNicknameCorrect(nick)){
								cli.printMsg("Only characters and numbers are allowed, try again:");
								nick = cli.getMsg();
							}
							connection.sendToServer("InsertNickname",nick);
							break;
							
						case "OneBonusToken":
							BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
							//get bonus token. obj = BonusToken[] tokenPool
							ArrayList<BonusTokenDTO> bt = new ArrayList<BonusTokenDTO>();
							for(CityDTO city: game.getMap().getCity())
								bt.add(city.getToken());
							BonusTokenDTO[] btArray = new BonusTokenDTO[bt.size()];
							bt.toArray(btArray);
							btDTO = cli.getTokenBonus(btArray, 1);
							connection.sendToServer("OneBonusToken",btDTO);
							break;
							
						case "OneBonusTokenAck":
							break;
							
						case "OneBonusTokenNack":
							// obj->(string) error
							break;
							
						case "FreePermitsCard":
							PermitsCardDTO pcDTO=null;
							//get ret: una carta permesso di quelle a faccia in su nelle regioni
							int regIndex = cli.getTargetRegion(2);
							ArrayList<PermitsCardDTO> availablePermits = new ArrayList<PermitsCardDTO>();
							availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(0));
							availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(1));
							int pcIndex = cli.getPermitIndex(availablePermits);
							pcDTO = availablePermits.get(pcIndex);
							connection.sendToServer("FreePermitsCard",pcDTO);
							break;
							
						case "FreePermitsCardAck":
							break;
							
						case "FreePermitsCardNack":
							// obj->(string) error
							break;
							
						case "OwnedPermitsCard":
							PermitsCardDTO pcOwnedDTO=null;
							//get ret: una carta permesso usata dal giocatore
							connection.sendToServer("OwnedPermitsCard",pcOwnedDTO);
							ArrayList<PermitsCardDTO> ownedPermits = new ArrayList<PermitsCardDTO>();
							ownedPermits.addAll(game.getPlayers().get(playerID).getPermits());
							for(PermitsCardDTO pc: ownedPermits)
								if(!pc.isFaceDown())
									ownedPermits.remove(pc);
							int pcOwnedIndex = cli.getPermitIndex(ownedPermits);
							pcOwnedDTO = ownedPermits.get(pcOwnedIndex);
							connection.sendToServer("OwnedPermitsCard",pcOwnedIndex);
							break;
							
						case "OwnedPermitsCardAck":
							break;
							
						case "OwnedPermitsCardNack":
							// obj->(string) error
							break;
							
						case "ItemToSell":						//Da aggiustare      aggiustala così: se item è null equivale a pass
							ItemOnSale its = null;
							//get its: l'oggetto da vendere al mercato
							Object item = cli.getItemToSell(playerID);
							if(item instanceof String){
								String pass = (String) item;
								connection.sendToServer("ItemToSell", pass);
							}else{
								int price = cli.getSellPrice();
								its = new ItemOnSale(price, item);
								connection.sendToServer("ItemToSell",its);
							}
							break;
							
						case "ItemToSellAck":
							break;
							
						case "ItemToSellNack":
							// obj->(string) error
							break;
							
						case "ItemToBuy":					//Anche questa
							OnSaleDTO onSaleDTO = null;
							ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
							//get itemtobuy. obj = MarketDTO market
							connection.sendToServer("ItemToBuy",onSaleDTO);
							onSaleDTO = availableOnSale.get(cli.getObjectToBuyIndex(availableOnSale.size(), playerID));
							connection.sendToServer("ItemToBuy", onSaleDTO);
							break;
							
						case "ItemToBuyAck":
							break;
							
						case "ItemToBuyNack":
							// obj->(string) error
							break;
							
						case "StartTurn":
							// aggiorna interfaccia con la carta pescata(obj =
							// PoliticsCardDTO)
							break;
							
						case "AvailableActions":
							// aggiorna il vettore di azioni disponibili (obj = boolean[]).
							// la descrizione del vettore la trovi in
							// ActionState.getAvailableActions()
							break;
							
						case "ActionAccepted":
							// ack dell'invio azione.
							break;
							
						case "ActionNotValid":
							// ack negativo dell'invio azione. il server ne aspetta un'altra
							break;
						default:
							throw new IllegalArgumentException("Command not recognized.");
						}
					}
		
		
		/*CommunicationObject in = (CommunicationObject) change;
		String cmd = in.getMsg();
		Object obj = in.getObj();

		switch(cmd){
		
			case "UpdateGameStatus":
				this.game = (GameDTO) obj;
				this.notifyObservers(game);
				break;
				
			case "InsertNickname":
				cli.printMsg("Insert your nickname:");
				String nick = cli.getMsg();
				while(!connection.isNicknameCorrect(nick)){
					cli.printMsg("Only characters and numbers are allowed, try again:");
					nick = cli.getMsg();
				}
				connection.sendToServer("InsertNickname",nick);
				break;
				
			case "OneBonusToken":
				BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
				//get bonus token. obj = BonusToken[] tokenPool
				ArrayList<BonusTokenDTO> bt = new ArrayList<BonusTokenDTO>();
				for(CityDTO city: game.getMap().getCity())
					bt.add(city.getToken());
				BonusTokenDTO[] btArray = new BonusTokenDTO[bt.size()];
				bt.toArray(btArray);
				btDTO = cli.getTokenBonus(btArray, 1);
				connection.sendToServer("OneBonusToken",btDTO);
				break;
				
			case "FreePermitsCard":
				PermitsCardDTO pcDTO=null;
				//get ret: una carta permesso di quelle a faccia in su nelle regioni
				int regIndex = cli.getTargetRegion(2);
				ArrayList<PermitsCardDTO> availablePermits = new ArrayList<PermitsCardDTO>();
				availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(0));
				availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(1));
				int pcIndex = cli.getPermitIndex(availablePermits);
				pcDTO = availablePermits.get(pcIndex);
				connection.sendToServer("FreePermitsCard",pcDTO);
				break;
				
			case "OwnedPermitsCard":
				PermitsCardDTO pcOwnedDTO=null;
				//get ret: una carta permesso usata dal giocatore
				connection.sendToServer("OwnedPermitsCard",pcOwnedDTO);
				ArrayList<PermitsCardDTO> ownedPermits = new ArrayList<PermitsCardDTO>();
				ownedPermits.addAll(game.getPlayers().get(playerID).getPermits());
				for(PermitsCardDTO pc: ownedPermits)
					if(!pc.isFaceDown())
						ownedPermits.remove(pc);
				int pcOwnedIndex = cli.getPermitIndex(ownedPermits);
				pcOwnedDTO = ownedPermits.get(pcOwnedIndex);
				connection.sendToServer("OwnedPermitsCard",pcOwnedIndex);
				break;
				
			case "ItemToSell":						//Da aggiustare      aggiustala così: se item è null equivale a pass
				ItemOnSale its = null;
				//get its: l'oggetto da vendere al mercato
				Object item = cli.getItemToSell(playerID);
				if(item instanceof String){
					String pass = (String) item;
					connection.sendToServer("ItemToSell", pass);
				}else{
					int price = cli.getSellPrice();
					its = new ItemOnSale(price, item);
					connection.sendToServer("ItemToSell",its);
				}
				break;
				
			case "ItemToBuy":					//Anche questa
				OnSaleDTO onSaleDTO = null;
				ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
				//get itemtobuy. obj = MarketDTO market
				connection.sendToServer("ItemToBuy",onSaleDTO);
				onSaleDTO = availableOnSale.get(cli.getObjectToBuyIndex(availableOnSale.size(), playerID));
				connection.sendToServer("ItemToBuy", onSaleDTO);
				break;
			default:
				throw new IllegalArgumentException("Command not recognized.");
		}*/
	}

}
