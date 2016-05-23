package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SocketAcceptor {
	private final static int PORT = 29999;
	private BlockingQueue clientQueue;
	
	
	public SocketAcceptor(BlockingQueue q){
		this.clientQueue = q;
	}
	
	private void acceptLoop() throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		while(true){
			Socket socket = serverSocket.accept();
			Scanner socketIn = new Scanner(socket.getInputStream());
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
			IdentifyPlayer identifier = new IdentifyPlayer(socket, socketIn,socketOut,clientQueue);
			//identifier.getname
		}
	}
}
