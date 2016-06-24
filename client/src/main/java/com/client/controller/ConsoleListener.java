package com.client.controller;

import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

import com.client.view.ClientCLI;


/**
 * The listener interface for receiving console events.
 * The class that is interested in processing a console
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addConsoleListener<code> method. When
 * the console event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ConsoleEvent
 */
public class ConsoleListener extends Observable implements Runnable{
	
	/** The client controller. */
	private ClientController clientController;
	
	/** The active. */
	private boolean active = true;
	
	/** The in. */
	private Scanner in;
	
	/** The cli. */
	private ClientCLI cli;
	
	/** The cli queue. */
	private ArrayBlockingQueue<String> cliQueue;
	
	/**
	 * Instantiates a new console listener.
	 *
	 * @param c the client controller
	 * @param cli the cli
	 * @param q the blockingqueue
	 */
	public ConsoleListener(ClientController c, ClientCLI cli, ArrayBlockingQueue<String> q){
		this.clientController = c;
		this.cli = cli;
		in = new Scanner(System.in);
		this.cliQueue = q;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * input getter loop
	 */
	@Override
	public void run() {
		while(true){
			cliQueue.add(in.nextLine());
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
