package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PlayerConnection {
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	private String userName;
	
	public PlayerConnection(Socket s, Scanner si, PrintWriter so, String un){
		this.socket = s;
		this.socketIn = si;
		this.socketOut = so;
		this.userName = un;
	}
}

