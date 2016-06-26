package com.server.controller;


public class MainServer {

	public static void main(String[] args) {
		Lobby lobby = new Lobby(true);
		Thread thread = new Thread(lobby);
		thread.start();
		
	}

}
