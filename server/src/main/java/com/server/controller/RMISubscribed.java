package com.server.controller;

import java.util.ArrayList;

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
	
	public static RMIClientControllerRemote getRemoteController(ArrayList<RMISubscribed> subs, ClientHandler ch){
		for(RMISubscribed s: subs)
			if(s.getCh().isEquals(ch))
				return s.getRemContr();
		return null;
	}
	
}
