package com.server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.communication.CommunicationObject;


/**
 * The listener interface for receiving client events.
 * The class that is interested in processing a client
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addClientListener<code> method. When
 * the client event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ClientEvent
 */
public class ClientListener extends Observable implements Runnable{

	/** The input stream. */
	private ObjectInputStream inputStream;
	
	/** The logger. */
	private Logger logger;
	
	private ClientHandler clientController;
	/**
	 * Instantiates a new client listener.
	 *
	 * @param is the is
	 */
	public ClientListener(ObjectInputStream is, ClientHandler clientController){
		this.inputStream = is;
		this.clientController = clientController;
	}
	
	/**
	 * Starts listening for client's messages (objects actually)
	 */
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
			    notifyObservers(new CommunicationObject("DisconnectedFromLobby",this.clientController));
			    return;
			}
			catch (ClassNotFoundException | IOException e) {
				logger.log(Level.SEVERE,"Failed to read the CommunicationObject\n" + e.toString(),e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		startListen();
		
	}
	
}
