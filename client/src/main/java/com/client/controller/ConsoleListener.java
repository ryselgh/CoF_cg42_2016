package com.client.controller;

import java.util.Observable;
import java.util.Scanner;

import com.client.ClientObservable;

public class ConsoleListener extends Observable implements Runnable{
	private Scanner in;
	private ClientController clientController;
	private boolean active = true;
	
	public ConsoleListener(ClientController c){
		this.clientController = c;
		this.in = new Scanner(System.in);
	}

	@Override
	public void run() {
		String cmd = "";
		do {
			if (in.hasNextLine()) {
				cmd = in.nextLine();
				this.setChanged();
				this.notifyObservers(cmd);
			} else
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} while (!clientController.isInGame());
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
