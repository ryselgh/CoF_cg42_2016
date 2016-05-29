package com.client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketConnection {

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Logger logger;
	private static final int PORT = 29999;
	private static final String IP_ADDRESS = "127.0.0.1";
	
	public SocketConnection(){
		//Singleton?
	}

	public void run(){

		try {
			socket = new Socket(IP_ADDRESS, PORT);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to connect to "+IP_ADDRESS+":"+Integer.toString(PORT)+".",e);
		}
	}
}
