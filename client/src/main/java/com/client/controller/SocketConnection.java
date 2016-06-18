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

public class SocketConnection extends Observable{

	private Socket socket = null;
	private ObjectOutputStream outputStream = null;
	private ObjectInputStream inputStream = null;
	private Logger logger;
	private static final int PORT = 29999;
	private static final String IP_ADDRESS = System.getProperty("server.ip");
	private static final int NICKNAME_MAX_LENGHT = 5;

	public SocketConnection(){
		
	}

	public void run() throws IOException{
		try {
			socket = new Socket(IP_ADDRESS, PORT);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to connect to "+IP_ADDRESS+":"+Integer.toString(PORT)+".",e);
		} 
	}

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
