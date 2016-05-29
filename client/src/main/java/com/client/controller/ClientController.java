package com.client.controller;

import com.client.ClientObserver;
import com.client.view.ClientCLI;
import com.communication.gamelogic.GameDTO;

public class ClientController implements ClientObserver{
	
	private ClientCLI cli;
	private GameDTO game;
	private SocketConnection connection;

	public ClientController(ClientCLI cli, GameDTO game){
		this.cli = cli;
		this.game = game;
		connection = new SocketConnection();
		connection.run();
		cli.attachObserver(this);
//		game.attachObserver(this); //Infattibie col DTO, serve un'altro modo.
	}

	@Override
	public void update() {
		
	}

	@Override
	public <C> void update(C change) {
		
	}
	
}
