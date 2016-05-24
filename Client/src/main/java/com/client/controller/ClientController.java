package com.client.controller;

import com.client.ClientObserver;
import com.client.model.GameStatus;
import com.client.view.ClientCLI;

public class ClientController implements ClientObserver{
	
	private ClientCLI cli;
	private GameStatus game;

	public ClientController(ClientCLI cli, GameStatus game){
		this.cli = cli;
		this.game = game;
		cli.attachObserver(this);
	}

	@Override
	public void update() {
		
	}

	@Override
	public <C> void update(C change) {
		
	}
	
}
