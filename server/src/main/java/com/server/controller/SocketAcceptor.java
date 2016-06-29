package com.server.controller;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Class SocketAcceptor.
 */
public class SocketAcceptor implements Runnable {
	
	/** The socket PORT. */
	private final static int PORT = 29999;
	
	/** The lobby. */
	private Lobby lobby;
	
	/** The logger. */
	private Logger logger;
	
	
	/**
	 * Instantiates a new socket acceptor.
	 *
	 * @param l the lobby
	 */
	public SocketAcceptor(Lobby l){
		this.lobby = l;
		logger = Logger.getLogger("myLogger");
	}
	
	/**
	 * Accept loop.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void acceptLoop() throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		while(true){
			Socket socket = serverSocket.accept();
			IdentifyPlayer identifier = new IdentifyPlayer(socket, lobby);
			identifier.addObserver(lobby);
			Thread thread = new Thread(identifier);
			thread.start();
		}
	}

	/* 
	 * run method
	 */
	@Override
	public void run() {
		try {
			acceptLoop();
		} catch (IOException e) {
			// MSG: ERROR DURING THE HANDLE OF SOCKET
			logger.log(Level.SEVERE, "I/O Error in socket accepting connection: acceptLoop()",e);
		}
	}
}
