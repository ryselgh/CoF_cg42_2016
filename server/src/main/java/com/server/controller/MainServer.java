package com.server.controller;


public class MainServer {

	public static void main(String[] args) {
		Lobby lobby = new Lobby(true);
		Thread thread = new Thread(lobby);
		thread.start();
		while(true)//per l'RMI, altrimenti si chiude dato che non ci sono loop di ascolto
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
