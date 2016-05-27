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

import com.communication.actions.ActionDTO;
import com.communication.market.MarketDTO;
import com.communication.market.OnSaleDTO;
import com.server.actions.Action;
import com.server.model.board.BonusToken;
import com.server.model.decks.PermitsCard;
import com.server.model.gamelogic.ItemOnSale;
import com.server.model.market.OnSaleInterface;

public class ClientHandler extends Observable implements Observer, Runnable{
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;	
	private String userName;
	private Logger logger;
	
	public ClientHandler(Socket s, ObjectInputStream si, ObjectOutputStream so, String un){
		this.socket = s;
		this.inputStream = si;
		this.outputStream = so;
		this.userName = un;
	}
	
	private void startListen(){
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
				String command = in.getMsg();
				setChanged();
			    notifyObservers(command);//check di validità sul comando. sarà lo stesso che attua il client prima dell'invio, ma ripetuto qui sul server per controllare che non ci siano stati errori di trasmissione
			}
		}
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void sendToClient(String msg, Object o){
		CommunicationObject toSend = new CommunicationObject(msg,o);
		try {
			outputStream.writeObject(toSend);
			outputStream.flush();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to send", e);
		}
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
	public ActionDTO getAction(){//da specificare le disponibili | Prima era un public ActionDTO che ritornava Action, decidi quale dei due
		sendToClient("GetAction",null);//il client alla ricezione di questo msg deve disabilitare qualsiasi invio eccetto le azioni
		CommunicationObject received = getClientInput();
		ActionDTO action = (ActionDTO) received.getObj(); //<-------------- ho cambiato questo
		return action;
	}

	@Override
	public void update(Observable o, Object arg) {//arg è nella forma TargetUserName_messaggio
		if(o instanceof Lobby){
			String[] splitted = ((String)arg).split("_");
			if(splitted[0].equals(this.getUserName()))
				this.sendToClient(splitted[1],null);}
	}

	@Override
	public void run() {
		startListen();
		
	}

	public BonusToken[] getBonusToken(BonusToken[] tokenPool){//IN TUTTI QUESTI GETTER C'è DA IMPLEMENTARE IL CHECK SUL PRIMO PARAMETRO (STRINGA) CHE DEVE IDENTIFICARE L'INPUT CORRETTAMENTE
		sendToClient("OneBonusToken", tokenPool);
		BonusToken[] ret = (BonusToken[]) getClientInput().getObj();
		return ret;
	}
	
	public PermitsCard getFreePermitsCard(){
		sendToClient("FreePermitsCard", null);
		PermitsCard ret = (PermitsCard) getClientInput().getObj();
		return ret;
	}
	
	public PermitsCard getOwnedPermitsCard(){
		sendToClient("OwnedPermitsCard", null);
		PermitsCard ret = (PermitsCard) getClientInput().getObj();
		return ret;
	}
	
	public ItemOnSale getItemToSell(){
		sendToClient("ItemToSell", null);
		ItemOnSale ret = (ItemOnSale) getClientInput().getObj();
		return ret;
	}
	
	public OnSaleDTO getItemToBuy(MarketDTO market){
		sendToClient("ItemToBuy", market);
		OnSaleDTO ret = (OnSaleDTO) getClientInput().getObj();
		return ret;
	}
}
