package com.client.controller;

import com.client.view.ClientCLI;
import com.communication.ItemOnSale;
import com.communication.gamelogic.GameDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ToSellState.
 */
public class ToSellState implements Runnable{
	
	/** The cli. */
	private ClientCLI cli;
	
	/** The connection. */
	private SocketConnection connection;
	
	/** The player ID. */
	private int playerID;//non serve l'abort flag perchè c'è un solo input
	
	/**
	 * Instantiates a new to sell state.
	 *
	 * @param cli the cli
	 * @param connection the SocketConnection
	 */
	public ToSellState(ClientCLI cli, SocketConnection connection){
		this.cli = cli;
		this.connection = connection;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * gets the input from the user (whether an item or 'pass') and sends it to the server
	 */
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
