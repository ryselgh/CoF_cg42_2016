package com.client.controller;

import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

import com.client.ClientObservable;
import com.client.view.ClientCLI;

public class ConsoleListener extends Observable implements Runnable{
	private ClientController clientController;
	private boolean active = true;
	private Scanner in;
	private ClientCLI cli;
	private ArrayBlockingQueue<String> cliQueue;
	
	public ConsoleListener(ClientController c, ClientCLI cli, ArrayBlockingQueue<String> q){
		this.clientController = c;
		this.cli = cli;
		in = new Scanner(System.in);
		this.cliQueue = q;
	}

	@Override
	public void run() {
		/*String cmd = "";
		do {
			cmd = cli.getMsg();
			if(cmd!=null){
				this.setChanged();
				this.notifyObservers(cmd);
			}
			System.out.print("-");
		} while (!clientController.isInGame());*/
		while(true){
			cliQueue.add(in.nextLine());
			this.setChanged();
			this.notifyObservers();
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
