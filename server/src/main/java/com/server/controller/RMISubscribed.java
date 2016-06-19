package com.server.controller;

import com.communication.RMIClientControllerRemote;

public class RMISubscribed {

	private ClientHandler ch;
	private RMIClientControllerRemote remContr;
	
	public RMISubscribed(ClientHandler ch, RMIClientControllerRemote remContr){
		this.ch = ch;
		this.remContr = remContr;
	}

	public ClientHandler getCh() {
		return ch;
	}

	public RMIClientControllerRemote getRemContr() {
		return remContr;
	}
	
	
}
