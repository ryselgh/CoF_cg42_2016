package com.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdentifyPlayer extends Observable implements Runnable  {
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	private String userName;
	private Lobby lobby;
	private Logger logger;
	
	public IdentifyPlayer(Socket s, Lobby l){
		this.socket = s;
		this.lobby = l;
	}
	
	private void getName() throws IOException{
		socketIn = new Scanner(socket.getInputStream());
		socketOut = new PrintWriter(socket.getOutputStream());
		//get tramite socket
		ClientHandler client = new ClientHandler(socket,socketIn,socketOut,userName);
		lobby.addObserver(client);
		this.addObserver(lobby);
		client.addObserver(lobby);
		setChanged();
		notifyObservers(client);
	}

	@Override
	public void run() {
		try {
			getName();
		} catch (IOException e) {
			// MSG: ERROR HANDLING SOCKET I/O
			logger.log(Level.SEVERE, "I/O Error in socket connection: getName()",e);
		}
	}
}
