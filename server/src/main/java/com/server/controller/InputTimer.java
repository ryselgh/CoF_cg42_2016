package com.server.controller;

import java.util.Observable;

public class InputTimer extends Observable implements Runnable{
	private static int timeoutDelay = 40000;
	private String clientName;
	public InputTimer(String c){
		this.clientName = c;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(timeoutDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers(this.clientName);
	}
	
	
}
