package com.server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.communication.CommunicationObject;

public class ClientListener extends Observable{

	private ObjectInputStream inputStream;
	private Logger logger;
	
	public ClientListener(ObjectInputStream is){
		this.inputStream = is;
	}
	
	public void startListen(){
		while(true){
			CommunicationObject in = null;
			try {
				in = (CommunicationObject) inputStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				logger.log(Level.SEVERE,"Failed to read the CommunicationObject",e);
			}
			if(in == null)
				throw new NullPointerException("Something went wrong with the CommunicationObject");
			else{
				setChanged();
			    notifyObservers(in);
			}
		}
	}
	
}
