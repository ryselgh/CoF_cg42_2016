package com.client.controller;

import com.client.view.gui.GUIController;

public class Launcher {
	private ClientController clientController;
	private GUIController guiController;
	
	public Launcher(ClientController c, GUIController g){
		this.clientController = c;
		this.guiController = g;
		
		clientController.addObserver(guiController);
		guiController.addObserver(clientController);
		clientController.setGuiController(guiController);
		
		Thread contrThread = new Thread(clientController);
		contrThread.start();
	}
}
