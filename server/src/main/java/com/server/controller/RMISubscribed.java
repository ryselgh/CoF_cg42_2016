package com.server.controller;

import java.util.ArrayList;

import com.communication.RMIClientControllerRemote;


/**
 * The Class RMISubscribed. this object is used in the RMI protocol to bind a client handler to its remote controller after the subscription.
 */
public class RMISubscribed {

	/** The client handler. */
	private ClientHandler ch;
	
	/** The remote controller for the client handler. */
	private RMIClientControllerRemote remContr;
	
	/**
	 * Instantiates a new RMI subscribed.
	 *
	 * @param ch the client handler
	 * @param remContr the remote controller
	 */
	public RMISubscribed(ClientHandler ch, RMIClientControllerRemote remContr){
		this.ch = ch;
		this.remContr = remContr;
	}

	/**
	 * Gets the clienthandler.
	 *
	 * @return the clienthandler
	 */
	public ClientHandler getCh() {
		return ch;
	}

	public void setRemContr(RMIClientControllerRemote remContr) {
		this.remContr = remContr;
	}

	/**
	 * Gets the rem contr.
	 *
	 * @return the rem contr
	 */
	public RMIClientControllerRemote getRemContr() {
		return remContr;
	}
	
	/**
	 * Gets the remote controller, related to a given clienthandler, included in a RMISubscribed arraylist.
	 *
	 * @param subs the RMISubscribed arraylist
	 * @param ch the target clienthandler
	 * @return the remote controller
	 */
	public static RMIClientControllerRemote getRemoteController(ArrayList<RMISubscribed> subs, ClientHandler ch){
		for(RMISubscribed s: subs)
			if(s.getCh().isEquals(ch))
				return s.getRemContr();
		return null;
	}
	
}
