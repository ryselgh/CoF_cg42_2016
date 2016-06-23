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

/**
 * The Class IdentifyPlayer.
 */
public class IdentifyPlayer extends Observable implements Runnable  {
	
	/** The socket. */
	private Socket socket;
	
	/** The user name. */
	private String userName;
	
	/** The lobby. */
	private Lobby lobby;
	
	/** The logger. */
	private Logger logger;
	
	/**
	 * Instantiates a new identify player.
	 *
	 * @param s the s
	 * @param l the l
	 */
	public IdentifyPlayer(Socket s, Lobby l){
		this.socket = s;
		this.lobby = l;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 * @throws IOException Asks the player for a nickname until it's ok, then creates the ClientHandler and gives it's istance to the lobby
	 */
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
		ClientHandler client = new ClientHandler(socket,inputStream,outputStream,userName,lobby);
		Thread thread = new Thread(client);
		thread.start();
		lobby.addObserver(client);
		client.addObserver(lobby);
		setChanged();
		notifyObservers(client);
	}
	
	/**
	 * Checks if is correct.
	 *
	 * @param name the name
	 * @return whats wrong in the chosen nickname. "" if it's ok
	 */
	private String isCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX012345679]"))//regex equivalente a tutti i caratteri a parte le lettere
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
