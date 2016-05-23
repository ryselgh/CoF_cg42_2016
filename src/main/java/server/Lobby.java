package server;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Lobby {
	private final BlockingQueue clientQueue;
	
	public Lobby(){
		clientQueue = new ArrayBlockingQueue(50,true);
		
	}
}
