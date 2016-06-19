package com.communication;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMILobbyRemote extends Remote{

	public int RMIlogIn(String userName) throws RemoteException;
	public void RMIsubscribe(String userName, int port) throws AccessException, RemoteException, NotBoundException;
	public String RMIlobbyCommand(String userName, String command, String map) throws RemoteException;
}
