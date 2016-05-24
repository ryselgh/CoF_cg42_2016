package com.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketAcceptor implements Runnable {
	private final static int PORT = 29999;
	private Lobby lobby;
	private Logger logger;
	
	
	public SocketAcceptor(Lobby l){
		this.lobby = l;
	}
	
	private void acceptLoop() throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		while(true){
			Socket socket = serverSocket.accept();
			IdentifyPlayer identifier = new IdentifyPlayer(socket, lobby);
			Thread thread = new Thread(identifier);
			thread.start();
		}
	}

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
