package com.client.controller;

import java.util.ArrayList;

import com.client.ClientObserver;
import com.client.view.ClientCLI;
import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;

public class ClientController implements ClientObserver{
	
	private ClientCLI cli;
	private GameDTO game;
	private SocketConnection connection;

	public ClientController(ClientCLI cli, GameDTO game){
		this.cli = cli;
		this.game = game;
		cli.attachObserver(this);
		connection = new SocketConnection();
		connection.run();
		connection.attachObserver(this);
//		game.attachObserver(this); //Infattibie col DTO, serve un'altro modo.
	}

	@Override
	public void update() {
		
	}

	@Override
	public <C> void update(C change) {
		CommunicationObject in = (CommunicationObject) change;
		String cmd = in.getMsg();
		Object obj = in.getObj();
		switch(cmd){
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
			ArrayList<PermitsCardDTO> cards = new ArrayList<PermitsCardDTO>();
			cards.add(game.getMap().getPermitsDeck(regIndex).getSlot(0));
			cards.add(game.getMap().getPermitsDeck(regIndex).getSlot(1));
			int pcIndex = cli.getPermitIndex(cards);
			pcDTO = cards.get(pcIndex);
			connection.sendToServer("FreePermitsCard",pcDTO);
			break;
		case "OwnedPermitsCard":
			PermitsCardDTO pcOwnedDTO=null;
			//get ret: una carta permesso usata dal giocatore
			connection.sendToServer("OwnedPermitsCard",pcOwnedDTO);
			break;
		case "ItemToSell":
			ItemOnSale its = null;
			//get its: l'oggetto da vendere al mercato
			connection.sendToServer("ItemToSell",its);
			break;
		case "ItemToBuy":
			OnSaleDTO onsaleDTO = null;
			//get itemtobuy. obj = MarketDTO market
			connection.sendToServer("ItemToBuy",onsaleDTO);
			break;
		}
	}
	
}
