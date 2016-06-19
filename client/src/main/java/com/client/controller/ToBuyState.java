package com.client.controller;

import java.util.ArrayList;

import com.client.view.ClientCLI;
import com.communication.RMIClientControllerRemote;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;

public class ToBuyState implements Runnable{
	private GameDTO game;
	private ClientCLI cli;
	private SocketConnection connection;
	private int playerID;//non serve l'abort flag perchè c'è un solo input
	
	public ToBuyState(GameDTO game, ClientCLI cli, SocketConnection connection){
		this.game = game;
		this.cli = cli;
		this.connection = connection;
	}
	
	@Override
	public void run() {
		String onSaleUID;
		ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
		onSaleUID = cli.getObjectToBuyUID(availableOnSale.size(), availableOnSale);
		connection.sendToServer("INPUT_TOBUY", onSaleUID);
	}

}
