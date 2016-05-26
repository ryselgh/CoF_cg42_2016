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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String command = in.getMsg();
			setChanged();
		    notifyObservers(command);//check di validità sul comando. sarà lo stesso che attua il client prima dell'invio, ma ripetuto qui sul server per controllare che non ci siano stati errori di trasmissione
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private CommunicationObject getClientInput(){
		CommunicationObject in = null;
		try {
			in = (CommunicationObject) inputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	public Action getAction(){//da specificare le disponibili
		sendToClient("GetAction",null);//il client alla ricezione di questo msg deve disabilitare qualsiasi invio eccetto le azioni
		CommunicationObject received = getClientInput();
		Action action = (Action) received.getObj();
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
	
	public OnSaleInterface getItemToBuy(ArrayList<OnSaleInterface> availableItems){
		sendToClient("ItemToBuy", availableItems);
		OnSaleInterface ret = (OnSaleInterface) getClientInput().getObj();
		return ret;
	}
}
