package com.server.controller;

public class TimeoutInfo {
	private String clientName;
	private int progressiveCounter;
	
	
	public TimeoutInfo(String c, int progressiveCounter){
		this.clientName = c;
		this.progressiveCounter = progressiveCounter;
	}


	public String getClientName() {
		return clientName;
	}


	public int getProgressiveCounter() {
		return progressiveCounter;
	}
	
	
}
