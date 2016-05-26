package com.server.controller;

public class Main {

	public static void main(String[] args) {
		Lobby lobby = new Lobby();
		Thread thread = new Thread(lobby);
		thread.start();

	}

}
