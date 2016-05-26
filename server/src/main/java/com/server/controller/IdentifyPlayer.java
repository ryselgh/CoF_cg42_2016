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

public class IdentifyPlayer extends Observable implements Runnable  {
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
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
		
		outputStream.writeObject(new CommunicationObject("Please tell us your nickname, only letters allowed",null));
		outputStream.flush();
		boolean correct=false;
		String inputName = "";
		while (!correct) {
			CommunicationObject in = null;
			try {
				in = (CommunicationObject) inputStream.readObject();
			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE, "Failed o read object", e);
			}
			if(in == null)
				throw new NullPointerException("Something went wrong with the CommunicationObject");
			else{
				inputName = in.getMsg();
				correct = isCorrect(inputName);
				if (!correct) {
					outputStream.writeObject(new CommunicationObject("Invalid nickname. Insert another one", null));
					outputStream.flush();
				}
			}
		}
		this.userName = inputName;
		ClientHandler client = new ClientHandler(socket,inputStream,outputStream,userName);
		Thread thread = new Thread(client);
		thread.start();
		lobby.addObserver(client);
		this.addObserver(lobby);
		client.addObserver(lobby);
		setChanged();
		notifyObservers(client);
	}
	
	private boolean isCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX]"))//regex equivalente a tutti i caratteri a parte le lettere
			return false;
		return true;
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
