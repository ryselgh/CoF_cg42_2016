package com.client.controller;

import java.util.ArrayList;

import com.client.view.ClientCLI;
import com.client.view.InterfaceMiddleware;
import com.communication.RMIClientControllerRemote;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;


/**
 * The Class ToBuyState.
 */
public class ToBuyState implements Runnable{
	
	/** The game. */
	private GameDTO game;
	
	/** The middleware. */
	private InterfaceMiddleware view;
	
	/** The connection. */
	private SocketConnection connection;
	
	/** The player ID. */
	private int playerID;//non serve l'abort flag perchè c'è un solo input
	
	/**
	 * Instantiates a new to buy state.
	 *
	 * @param game the game
	 * @param cli the cli
	 * @param connection the connection
	 */
	public ToBuyState(GameDTO game, InterfaceMiddleware view, SocketConnection connection){
		this.game = game;
		this.view = view;
		this.connection = connection;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * prende l'input e lo manda
	 */
	@Override
	public void run() {
		String onSaleUID;
		ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
		onSaleUID = view.toBuy(game);
		connection.sendToServer("INPUT_TOBUY", onSaleUID);
	}

}
