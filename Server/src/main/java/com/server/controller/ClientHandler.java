package com.server.controller;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ClientHandler extends Observable implements Observer{
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
	
	private void sendToCLient(String msg){
		socketOut.println(msg);
		socketOut.flush();
	}

	@Override
	public void update(Observable o, Object arg) {//arg è nella forma TargetUserName_messaggio
		if(o instanceof Lobby){
			String[] splitted = ((String)arg).split("_");
			if(splitted[0].equals(this.getUserName()))
				this.sendToCLient(splitted[1]);}
			
		
		
	}
}
