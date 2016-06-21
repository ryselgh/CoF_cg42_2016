package com.server.model.gamelogic;

import java.util.ArrayList;

import com.communication.RMIClientControllerRemote;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.controller.RMISubscribed;


/**
 * The Class Context.
 */
public class Context {
	
	   /** The state. */
   	private State state;
	   
   	/** The clienthandler. */
   	private ClientHandler clienthandler;
	   
   	/** The gamehandler. */
   	private GameHandler gamehandler;
	   
   	/** The remote controller. */
   	private RMIClientControllerRemote remoteController;
	   
   	/** The boolean rmi. */
   	private boolean RMI;
	   
	   /**
   	 * Instantiates a new context.
   	 */
   	public Context(){
	      state = null;
	   }

	   /**
   	 * Sets the state.
   	 *
   	 * @param state the new state
   	 */
   	public void setState(State state){
	      this.state = state;		
	   }

	   /**
   	 * Gets the state.
   	 *
   	 * @return the state
   	 */
   	public State getState(){
	      return state;
	   }

	/**
	 * Gets the clienthandler.
	 *
	 * @return the clienthandler
	 */
	public ClientHandler getClienthandler() {
		return clienthandler;
	}

	/**
	 * Sets the clienthandler.
	 *
	 * @param clienthandler the new clienthandler
	 */
	public void setClienthandler(ClientHandler clienthandler) {
		this.clienthandler = clienthandler;
	}

	/**
	 * Gets the gamehandler.
	 *
	 * @return the gamehandler
	 */
	public GameHandler getGamehandler() {
		return gamehandler;
	}

	/**
	 * Sets the gamehandler.
	 *
	 * @param gamehandler the new gamehandler
	 */
	public void setGamehandler(GameHandler gamehandler) {
		this.gamehandler = gamehandler;
	}

	/**
	 * Gets the remote controller.
	 *
	 * @return the remote controller
	 */
	public RMIClientControllerRemote getRemoteController() {
		return remoteController;
	}

	/**
	 * Sets the remote controller.
	 *
	 * @param remoteController the new remote controller
	 */
	public void setRemoteController(RMIClientControllerRemote remoteController) {
		this.remoteController = remoteController;
	}

	/**
	 * Checks if is rmi.
	 *
	 * @return true, if is rmi
	 */
	public boolean isRMI() {
		return RMI;
	}

	/**
	 * Sets the rmi.
	 *
	 * @param rMI the new rmi
	 */
	public void setRMI(boolean rMI) {
		RMI = rMI;
	}

	   
	   
}
