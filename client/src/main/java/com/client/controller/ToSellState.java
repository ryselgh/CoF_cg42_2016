package com.client.controller;


import com.client.view.InterfaceMiddleware;
import com.communication.ItemOnSale;



/**
 * The Class ToSellState.
 */
public class ToSellState implements Runnable{
	
	/** The cli. */
	private InterfaceMiddleware view;
	
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
	public ToSellState(InterfaceMiddleware view, SocketConnection connection){
		this.view = view;
		this.connection = connection;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * gets the input from the user (whether an item or 'pass') and sends it to the server
	 */
	@Override
	public void run() {
		ItemOnSale its = view.toSell();;
		connection.sendToServer("INPUT_TOSELL",its);
	}

}
