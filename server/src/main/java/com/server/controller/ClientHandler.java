package com.server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.LobbyStatus;
import com.communication.actions.ActionDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.market.MarketDTO;
import com.communication.market.OnSaleDTO;
import com.server.actions.Action;
import com.server.model.board.BonusToken;
import com.server.model.decks.PermitsCard;
import com.server.model.market.OnSaleInterface;

public class ClientHandler extends Observable implements Observer, Runnable{
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;	
	private String userName;
	private Logger logger;
	public boolean inGame = false;
	private boolean active = true;
	private ClientListener listener;
	private Lobby lobby;
	
	public ClientHandler(Socket s, ObjectInputStream si, ObjectOutputStream so, String un, Lobby l){
		this.socket = s;
		this.inputStream = si;
		this.outputStream = so;
		this.userName = un;
		this.lobby=l;
	}

	@Override
	public void run() {
		startListen();
		
	}
	
	public void startListen(){
		listener = new ClientListener(inputStream);
		Thread clListThread = new Thread(listener);
		listener.addObserver(this);
		clListThread.run();
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void sendToClient(String msg, Object o){
		if(this.active){
		CommunicationObject toSend = new CommunicationObject(msg,(Object) o);
		try {
			outputStream.reset();
			outputStream.writeObject(toSend);
			outputStream.flush();
		} catch (Exception e) {
			lobby.disconnectFromGame(this);
		    return;
		}}
		else{String br = "breakpointami e correggi";}
	}
	
	private CommunicationObject getClientInput(){
		CommunicationObject in = null;
		try {
			in = (CommunicationObject) inputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			logger.log(Level.SEVERE, "Failed to receive", e);
		}
		return in;
	}
	public ActionDTO getAction(){//le azioni disponibili sono già state comunicate al client nella comunicazione precedente
		sendToClient("GetAction",null);//il client alla ricezione di questo msg deve disabilitare qualsiasi invio eccetto le azioni
		CommunicationObject received = getClientInput();
		ActionDTO action = (ActionDTO) received.getObj();
		return action;
	}

	@Override
	public void update(Observable o, Object arg) {//arg è nella forma TargetUserName_messaggio
		if(o instanceof Lobby){
			if(arg instanceof String){
			String[] splitted = ((String)arg).split("_",2);//splitta na volta sola
			if(splitted[0].equals(this.getUserName()))//se è un messaggio per questo client
				this.sendToClient(splitted[1],null);
			}
			else if(arg instanceof LobbyStatus){//se è un update di lobbystatus (quindi in broadcast)
				this.sendToClient("LOBBYSTATUS",arg);
			}
		}
		else if(o instanceof ClientListener){
			setChanged();
		    notifyObservers(arg);//passa alla lobby
		}
	}
	
	public void closeSocket(){
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public boolean isEquals(ClientHandler c){
		if(c==null)
			return false;
		if(c.getUserName().equals(this.getUserName()))
			return true;
		return false;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
