package com.server.model.gamelogic;

import com.server.controller.ClientHandler;
import com.server.controller.GameHandler;

public class Context {
	
	   private State state;
	   private ClientHandler clienthandler;
	   private GameHandler gamehandler;
	   private int playerIndex;
	   
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

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	   
	   
}
