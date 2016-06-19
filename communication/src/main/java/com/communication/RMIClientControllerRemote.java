package com.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.communication.actions.ActionDTO;
import com.communication.gamelogic.GameDTO;

public interface RMIClientControllerRemote extends Remote{

	public void RMIupdateLobby(LobbyStatus status)throws RemoteException;
	public void RMIupdateGame(GameDTO game)throws RemoteException;
	public void RMIprintMsg(String msg)throws RemoteException;
	public ActionDTO getAction(boolean[] availableActions)throws RemoteException;
}
