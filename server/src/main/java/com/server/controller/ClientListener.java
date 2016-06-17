package com.server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.communication.CommunicationObject;

public class ClientListener extends Observable implements Runnable{

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
				if(in == null)
					throw new NullPointerException("Something went wrong with the CommunicationObject");//da cambiare
				else{
					setChanged();
				    notifyObservers(in);
				}
			} 
			catch(SocketException e){
				setChanged();
			    notifyObservers(new CommunicationObject("DisconnectedFromLobby",null));
			    return;
			}
			catch (ClassNotFoundException | IOException e) {
				logger.log(Level.SEVERE,"Failed to read the CommunicationObject\n" + e.toString(),e);
			}
		}
	}

	@Override
	public void run() {
		startListen();
		
	}
	
}
