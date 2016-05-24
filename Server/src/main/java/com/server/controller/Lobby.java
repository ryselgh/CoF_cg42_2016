package com.server.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
//comandi: newRoom(roomName,maxplayers, minplayers),   joinRoom(roomName)   startgame(roomName)--->requires admin
//         getRoomList()--->obj ad hoc

public class Lobby implements Runnable, Observer  {
	private ArrayList<ClientHandler> clients;
	
	public Lobby(){ }

	@Override
	public void run() { 
		clients = new ArrayList<ClientHandler>();
		SocketAcceptor acceptor = new SocketAcceptor(this);
		Thread thread = new Thread(acceptor);
		thread.start();
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof IdentifyPlayer)//ricevo un nuovo client
			clients.add((ClientHandler) arg1);
		else if(arg0 instanceof ClientHandler){//ricevo un comando da un client
			String command = (String) arg1;
			ClientHandler sender = (ClientHandler) arg0;
		}
	}
	
	
}
