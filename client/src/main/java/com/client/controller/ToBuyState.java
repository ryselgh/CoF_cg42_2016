package com.client.controller;

import java.util.ArrayList;

import com.client.view.ClientCLI;
import com.communication.RMIClientControllerRemote;
import com.communication.gamelogic.GameDTO;
import com.communication.market.OnSaleDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ToBuyState.
 */
public class ToBuyState implements Runnable{
	
	/** The game. */
	private GameDTO game;
	
	/** The cli. */
	private ClientCLI cli;
	
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
	public ToBuyState(GameDTO game, ClientCLI cli, SocketConnection connection){
		this.game = game;
		this.cli = cli;
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
		onSaleUID = cli.getObjectToBuyUID(availableOnSale.size(), availableOnSale);
		connection.sendToServer("INPUT_TOBUY", onSaleUID);
	}

}
