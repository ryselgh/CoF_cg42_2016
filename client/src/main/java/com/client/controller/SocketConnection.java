package com.client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.client.ClientObservable;
import com.communication.CommunicationObject;


/**
 * The Class SocketConnection.
 */
public class SocketConnection extends Observable{

	/** The socket. */
	private Socket socket = null;
	
	/** The output stream. */
	private ObjectOutputStream outputStream = null;
	
	/** The input stream. */
	private ObjectInputStream inputStream = null;
	
	/** The logger. */
	private Logger logger;
	
	/** The Constant PORT. */
	private static final int PORT = 29999;
	
	/** The Constant IP_ADDRESS. */
	private static final String IP_ADDRESS = System.getProperty("server.ip");
	
	/** The Constant NICKNAME_MAX_LENGHT. */
	private static final int NICKNAME_MAX_LENGHT = 5;

	/**
	 * Instantiates a new socket connection.
	 */
	public SocketConnection(){
		
	}

	/**
	 * Run. sets up socket and streams
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void run() throws IOException{
			socket = new Socket(IP_ADDRESS, PORT);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Start listen. listening loop
	 */
	public void startListen(){
		while(true){//la comunicazione mvc avviene nella lobby. in game invece è ad invocazione diretta e questo loop bloccherebbe il thread
			CommunicationObject in = null;
			try {
				in = (CommunicationObject) inputStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				logger.log(Level.SEVERE,"Failed to read the CommunicationObject",e);
			}
			if(in == null)
				throw new NullPointerException("Something went wrong with the CommunicationObject");
			else{
				this.setChanged();
				this.notifyObservers(in);
			}
		}
	}

	/**
	 * Send to server.
	 *
	 * @param s the message
	 * @param o the object
	 */
	public synchronized void sendToServer(String s, Object o){
		CommunicationObject toSend = new CommunicationObject(s,(Object) ((Object)o));
		try {
			outputStream.reset();
			outputStream.writeObject(toSend);
			outputStream.flush();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to send", e);
		}
	}
}
