package com.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.client.ClientObservable;
import com.client.ClientObserver;
import com.client.view.ClientCLI;
import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.SerObject;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;

public class ClientController extends ClientObservable implements ClientObserver{

	private ClientCLI cli;
	private GameDTO game;
	private SocketConnection connection;
	private Logger logger;
	
	private int playerID; // <------------------------------------ PROVVISORIO, serve comunque un identificativo

	public ClientController(){
		cli = new ClientCLI(this);
		cli.attachObserver(this);
	}
	
	public void run() throws IOException{
		connection = new SocketConnection();
		try {
			connection.run();
			connection.attachObserver(this);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Connection lost.", e);
		}
	}

	@Override
	public <C> void update(C change) {
		
		CommunicationObject in = (CommunicationObject) change;
		String cmd = in.getMsg();
		Object obj = in.getObj();
		
		if (cmd.contains("lobby_msg-")) {// messaggio della lobby
						cmd = cmd.substring("lobby_msg-".length());
						cli.printMsg(cmd + "\n");
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
							
						case "ONETOKEN":
							BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
							//get bonus token. obj = BonusToken[] tokenPool
							ArrayList<BonusTokenDTO> bt = new ArrayList<BonusTokenDTO>();
							for(CityDTO city: game.getMap().getCity())
								bt.add(city.getToken());
							BonusTokenDTO[] btArray = new BonusTokenDTO[bt.size()];
							bt.toArray(btArray);
							btDTO = cli.getTokenBonus(btArray, 1);
							connection.sendToServer("ONETOKEN",btDTO);
							break;
							
						case "ONETOKEN_ACK":
							break;
							
						case "ONETOKEN_NACK":
							break;
						case "TWOTOKENS":
							BonusTokenDTO[] btDTO2 = new BonusTokenDTO[1];
							//get bonus token. obj = BonusToken[] tokenPool
							ArrayList<BonusTokenDTO> bt2 = new ArrayList<BonusTokenDTO>();
							for(CityDTO city: game.getMap().getCity())
								bt2.add(city.getToken());
							BonusTokenDTO[] btArray2 = new BonusTokenDTO[bt2.size()];
							bt2.toArray(btArray2);
							btDTO2 = cli.getTokenBonus(btArray2, 2);
							connection.sendToServer("TWOTOKENS",btDTO2);
							break;
							
						case "TWOTOKENS_ACK":
							break;
							
						case "TWOTOKENS_NACK":
							break;
						case "FREECARD":
							PermitsCardDTO pcDTO=null;
							//get ret: una carta permesso di quelle a faccia in su nelle regioni
							int regIndex = cli.getTargetRegion(2);
							ArrayList<PermitsCardDTO> availablePermits = new ArrayList<PermitsCardDTO>();
							availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(0));
							availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(1));
							int pcIndex = cli.getPermitIndex(availablePermits);
							pcDTO = availablePermits.get(pcIndex);
							connection.sendToServer("FREECARD",pcDTO);
							break;
							
						case "FREECARDACK":
							break;
							
						case "FREECARDNACK":
							cli.printMsg(((String) obj)+ "\n");
							break;
							
						case "BONUSCARD":
							PermitsCardDTO pcOwnedDTO=null;
							ArrayList<PermitsCardDTO> ownedPermits = new ArrayList<PermitsCardDTO>();
							ownedPermits.addAll(game.getPlayers().get(playerID).getPermits());
							for(PermitsCardDTO pc: ownedPermits)
								if(!pc.isFaceDown())
									ownedPermits.remove(pc);
							int pcOwnedIndex = cli.getPermitIndex(ownedPermits);
							pcOwnedDTO = ownedPermits.get(pcOwnedIndex);
							connection.sendToServer("BONUSCARD",pcOwnedDTO);
							break;
							
						case "BONUSCARDACK":
							break;
							
						case "BONUSCARDNACK":
							cli.printMsg(((String) obj)+ "\n");
							break;
							
						case "TOSELL":						//Da aggiustare      aggiustala così: se item è null equivale a pass
							ItemOnSale its = null;
							//get its: l'oggetto da vendere al mercato
							Object item = cli.getItemToSell(playerID);
							if(item instanceof String){
								String pass = (String) item;
								connection.sendToServer("TOSELL", pass);
							}else{
								int price = cli.getSellPrice();
								its = new ItemOnSale(price, (SerObject) item);
								connection.sendToServer("TOSELL",its);
							}
							break;
							
						case "TOSELLACK":
							cli.printMsg(((String) obj)+ "\n");
							break;
							
						case "TOSELLNACK":
							cli.printMsg(((String) obj)+ "\n");
							break;
							
						case "TOBUY":					//Anche questa
							OnSaleDTO onSaleDTO = null;
							ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
							//get itemtobuy. obj = MarketDTO market
							connection.sendToServer("TOBUY",onSaleDTO);
							onSaleDTO = availableOnSale.get(cli.getObjectToBuyIndex(availableOnSale.size(), playerID));
							connection.sendToServer("TOBUY", onSaleDTO);
							break;
							
						case "TOBUYACK":
							cli.printMsg(((String) obj)+ "\n");
							break;
							
						case "TOBUYNACK":
							cli.printMsg(((String) obj)+ "\n");
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
							throw new IllegalArgumentException("Command not recognized: " + cmd);
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
