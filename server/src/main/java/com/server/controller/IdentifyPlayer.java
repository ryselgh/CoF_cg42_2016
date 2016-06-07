package com.server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.communication.CommunicationObject;

public class IdentifyPlayer extends Observable implements Runnable  {
	private Socket socket;
	private String userName;
	private Lobby lobby;
	private Logger logger;
	
	public IdentifyPlayer(Socket s, Lobby l){
		this.socket = s;
		this.lobby = l;
	}
	
	private void getName() throws IOException{
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		
		String correct="nope";
		String inputName = "";
		while (!(correct=="")) {
			outputStream.writeObject(new CommunicationObject("INSERTNICKNAME",null));
			outputStream.flush();
			CommunicationObject in = null;
			try {
				in = (CommunicationObject) inputStream.readObject();
			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE, "Failed o read object", e);
			}
			if(in == null)
				throw new NullPointerException("Something went wrong with the CommunicationObject");
			else{//da aggiungere check su in.getMsg()=="InsertNickname"
				inputName = (String) in.getObj();
				correct = isCorrect(inputName);
				if (!correct.equals("")) {
					outputStream.writeObject(new CommunicationObject("INSERTNICKNAMENACK",(Object) correct));
					outputStream.flush();
				}
			}
		}
		outputStream.writeObject(new CommunicationObject("INSERTNICKNAMEACK",null));
		outputStream.flush();
		this.userName = inputName;
		ClientHandler client = new ClientHandler(socket,inputStream,outputStream,userName);
		Thread thread = new Thread(client);
		thread.start();
		lobby.addObserver(client);
		client.addObserver(lobby);
		setChanged();
		notifyObservers(client);
	}
	
	private String isCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX]"))//regex equivalente a tutti i caratteri a parte le lettere
			return "Illegal characters";
		if(name.length()<5 || name.length()>13)
			return "Nickname size must be >5 and <13";
		if(lobby.isNicknameUsed(name))
			return "Nickname already used";
		return "";
	}
	@Override
	public void run() {
		try {
			getName();
		} catch (IOException e) {
			// MSG: ERROR HANDLING SOCKET I/O
			logger.log(Level.SEVERE, "I/O Error in socket connection: getName()",e);
		}
	}
}
