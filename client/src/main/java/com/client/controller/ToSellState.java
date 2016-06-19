package com.client.controller;

import com.client.view.ClientCLI;
import com.communication.ItemOnSale;
import com.communication.gamelogic.GameDTO;

public class ToSellState implements Runnable{
	private ClientCLI cli;
	private SocketConnection connection;
	private int playerID;//non serve l'abort flag perchè c'è un solo input
	
	public ToSellState(ClientCLI cli, SocketConnection connection){
		this.cli = cli;
		this.connection = connection;
	}
	
	@Override
	public void run() {
		ItemOnSale its = null;
		Object item = cli.getItemToSell();
		if(item instanceof String){
			connection.sendToServer("INPUT_TOSELL", null);
		}else{
			int price = cli.getSellPrice();
			its = new ItemOnSale(price, (Object) item);
			connection.sendToServer("INPUT_TOSELL",its);
		}
	}

}
