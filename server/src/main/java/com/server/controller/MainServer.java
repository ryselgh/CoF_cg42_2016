package com.server.controller;



/**
 * The Class MainServer.
 */
public class MainServer {

	/**
	 * The main method.
	 */
	public static void main(String[] args) {
		Lobby lobby = new Lobby(true);
		Thread thread = new Thread(lobby);
		thread.start();
		
	}

}
