package com.server.controller;

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class InputTimer.
 */
public class InputTimer extends Observable implements Runnable{
	
	/** The timeout delay. */
	private static int timeoutDelay = 100000;
	
	/** The client name. */
	private String clientName;
	
	/** The progressive counter. The client name isn't enought because it doen't avoid the -wrong- skip of the next turn of the same player */
	private int progressiveCounter;
	
	/**
	 * Instantiates a new input timer.
	 *
	 * @param c the clientName
	 * @param pC the progressive counter
	 */
	public InputTimer(String c, int pC){
		this.clientName = c;
		this.progressiveCounter = pC;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * just sleep then notify
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(timeoutDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers(new TimeoutInfo(this.clientName, this.progressiveCounter));
	}
	
	
}
