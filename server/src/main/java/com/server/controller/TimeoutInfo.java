package com.server.controller;


/**
 * The Class TimeoutInfo.
 */
public class TimeoutInfo {
	
	/** The client name. */
	private String clientName;
	
	/** The progressive counter. */
	private int progressiveCounter;
	
	
	/**
	 * Instantiates a new timeout info.
	 *
	 * @param c the client name
	 * @param progressiveCounter the progressive counter
	 */
	public TimeoutInfo(String c, int progressiveCounter){
		this.clientName = c;
		this.progressiveCounter = progressiveCounter;
	}


	/**
	 * Gets the client name.
	 *
	 * @return the client name
	 */
	public String getClientName() {
		return clientName;
	}


	/**
	 * Gets the progressive counter.
	 *
	 * @return the progressive counter
	 */
	public int getProgressiveCounter() {
		return progressiveCounter;
	}
	
	
}
