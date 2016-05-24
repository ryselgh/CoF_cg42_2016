package com.server.controller;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class ClientHandler extends Observable{
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	private String userName;
	
	public ClientHandler(Socket s, Scanner si, PrintWriter so, String un){
		this.socket = s;
		this.socketIn = si;
		this.socketOut = so;
		this.userName = un;
	}
	
	private void startListen(){
		while(true){
			String command = socketIn.nextLine();
			setChanged();
		    notifyObservers(command);//check di validità sul comando. sarà lo stesso che attua il client prima dell'invio, ma ripetuto qui sul server per controllare che non ci siano stati errori di trasmissione
		}
	}
	
	public String getUserName() {
		return userName;
	}
}
