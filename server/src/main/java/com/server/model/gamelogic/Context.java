package com.server.model.gamelogic;

import java.util.ArrayList;

import com.communication.RMIClientControllerRemote;
import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;
import com.server.controller.RMISubscribed;

public class Context {
	
	   private State state;
	   private ClientHandler clienthandler;
	   private GameHandler gamehandler;
	   private RMIClientControllerRemote remoteController;
	   private boolean RMI;
	   
	   public Context(){
	      state = null;
	   }

	   public void setState(State state){
	      this.state = state;		
	   }

	   public State getState(){
	      return state;
	   }

	public ClientHandler getClienthandler() {
		return clienthandler;
	}

	public void setClienthandler(ClientHandler clienthandler) {
		this.clienthandler = clienthandler;
	}

	public GameHandler getGamehandler() {
		return gamehandler;
	}

	public void setGamehandler(GameHandler gamehandler) {
		this.gamehandler = gamehandler;
	}

	public RMIClientControllerRemote getRemoteController() {
		return remoteController;
	}

	public void setRemoteController(RMIClientControllerRemote remoteController) {
		this.remoteController = remoteController;
	}

	public boolean isRMI() {
		return RMI;
	}

	public void setRMI(boolean rMI) {
		RMI = rMI;
	}

	   
	   
}
