package com.client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.SerObject;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.market.OnSaleDTO;

public class SocketConnection {

	private Socket socket = null;
	private ObjectOutputStream outputStream = null;
	private ObjectInputStream inputStream = null;
	private Logger logger;
	private static final int PORT = 29999;
	private static final String IP_ADDRESS = "127.0.0.1";
	
	public SocketConnection(){
		//Singleton?
	}

	public void run(){

		try {
			socket = new Socket(IP_ADDRESS, PORT);

			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to connect to "+IP_ADDRESS+":"+Integer.toString(PORT)+".",e);
		}
	}
	
	public void startListen()
	{
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
				String command = in.getMsg();
				parseCommand(command, in.getObj());
				}
		}
	}
	
	private void parseCommand(String cmd, Object obj){
		switch(cmd){
		case "InsertNickname":
			//stampa: inserisci nickname + istruzioni
			String nick = "";
			while(!isNicknameCorrect(nick)){
			//get nickname
			}
			sendToServer("InsertNickname",nick);
			break;
		case "OneBonusToken":
			BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
			//get bonus token. obj = BonusToken[] tokenPool
			sendToServer("OneBonusToken",btDTO);
			break;
		case "FreePermitsCard":
			PermitsCardDTO pcDTO=null;
			//get ret: una carta permesso di quelle a faccia in su nelle regioni
			sendToServer("FreePermitsCard",pcDTO);
			break;
		case "OwnedPermitsCard":
			PermitsCardDTO pcOwnedDTO=null;
			//get ret: una carta permesso usata dal giocatore
			sendToServer("OwnedPermitsCard",pcOwnedDTO);
			break;
		case "ItemToSell":
			ItemOnSale its = null;
			//get its: l'oggetto da vendere al mercato
			sendToServer("ItemToSell",its);
			break;
		case "ItemToBuy":
			OnSaleDTO onsaleDTO = null;
			//get itemtobuy. obj = MarketDTO market
			sendToServer("ItemToBuy",onsaleDTO);
			break;
		}
	}
	
	private boolean isNicknameCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX]")|| name.length()<5)//regex equivalente a tutti i caratteri a parte le lettere
			return false;
		return true;
	}
	
	public void sendToServer(String s, Object o){
		CommunicationObject toSend = new CommunicationObject(s,(SerObject) o);
		try {
			outputStream.writeObject(toSend);
			outputStream.flush();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to send", e);
		}
	}
}
