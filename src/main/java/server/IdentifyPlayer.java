package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class IdentifyPlayer {
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	private String userName;
	private final BlockingQueue clientQueue;
	
	public IdentifyPlayer(Socket s, Scanner si, PrintWriter so, BlockingQueue q){
		this.socket = s;
		this.socketIn = si;
		this.socketOut = so;
		this.clientQueue = q;
	}
	
	private void getName(){
		//get tramite socket
		PlayerConnection client = new PlayerConnection(socket,socketIn,socketOut,userName);
		//da passare alla lobby
	}
}
