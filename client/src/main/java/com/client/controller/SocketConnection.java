package com.client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.client.ClientObservable;
import com.communication.CommunicationObject;
import com.communication.ItemOnSale;
import com.communication.SerObject;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.market.OnSaleDTO;

public class SocketConnection extends ClientObservable{

	private Socket socket = null;
	private ObjectOutputStream outputStream = null;
	private ObjectInputStream inputStream = null;
	private PrintStream out;
	private Logger logger;
	private static final int PORT = 29999;
	private static final String IP_ADDRESS = "127.0.0.1";
	private static final int NICKNAME_MAX_LENGHT = 5;

	public SocketConnection(){
		
	}

	public void run(){
		try {
			socket = new Socket(IP_ADDRESS, PORT);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
			startListen();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to connect to "+IP_ADDRESS+":"+Integer.toString(PORT)+".",e);
		}
	}

	public void startListen(){
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
				this.notifyObservers(in);
			}
		}
	}

	public boolean isNicknameCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX0123456789]")|| name.length()<NICKNAME_MAX_LENGHT)//regex equivalente a tutti i caratteri a parte le lettere
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
