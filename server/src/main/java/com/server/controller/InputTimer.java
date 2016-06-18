package com.server.controller;

import java.util.Observable;

public class InputTimer extends Observable implements Runnable{
	private static int timeoutDelay = 40000;
	private String clientName;
	private int progressiveCounter;
	
	public InputTimer(String c, int pC){
		this.clientName = c;
		this.progressiveCounter = pC;
	}

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
